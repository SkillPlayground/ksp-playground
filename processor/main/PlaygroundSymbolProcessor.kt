package com.javiersc.ksp.playground.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.validate
import java.io.OutputStream

class PlaygroundSymbolProcessor(
    val codeGenerator: CodeGenerator,
    val logger: KSPLogger,
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(Playground)
        logger.info(symbols.toString())
        val validatedSymbols = symbols.filter { !it.validate() }.toList()
        logger.info(validatedSymbols.toString())
        symbols.filter { it is KSClassDeclaration && it.validate() }.forEach {
            it.accept(PlaygroundVisitor(), Unit)
        }
        return validatedSymbols
    }

    inner class PlaygroundVisitor : KSVisitorVoid() {

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            classDeclaration.primaryConstructor!!.accept(this, data)
        }

        override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
            val parent = function.parentDeclaration as KSClassDeclaration
            val packageName = parent.containingFile!!.packageName.asString()
            val className = "${parent.simpleName.asString()}Playground"
            val file =
                codeGenerator.createNewFile(
                    Dependencies(true, function.containingFile!!),
                    packageName,
                    className,
                )
            file.appendText("package $packageName\n\n")
            file.appendText(
                """|class $className {
                   |
                   |    fun greetings() = println("Hello")
                   |}
                   |
                """.trimMargin(),
            )
            file.close()
        }
    }

    companion object {

        private const val Playground = "com.javiersc.ksp.playground.processor.Playground"
    }
}

class PlaygroundProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return PlaygroundSymbolProcessor(environment.codeGenerator, environment.logger)
    }
}

private fun OutputStream.appendText(str: String) {
    this.write(str.toByteArray())
}
