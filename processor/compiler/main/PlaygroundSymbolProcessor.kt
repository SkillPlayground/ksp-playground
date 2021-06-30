package com.javiersc.ksp.playground.processor.compiler

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
import com.javiersc.ksp.playground.processor.annotations.Injectable
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets.UTF_8

class PlaygroundSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(Injectable.name)
        val validatedSymbols = symbols.filter { !it.validate() }.toList()
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
            val declarations = parent.containingFile?.declarations?.map { it.simpleName.asString() }
            log(declarations?.joinToString("\n").toString())
            val className = "${parent.simpleName.asString()}Playground"
            val superTypes = parent.superTypes
            superTypes.forEach { log(it.resolve().declaration.packageName.asString()) }
            //            val file =
            //                codeGenerator.createNewFile(
            //                    Dependencies(true, function.containingFile!!),
            //                    packageName,
            //                    className,
            //                )
            //            file.appendText("package $packageName\n\n")
            //            file.appendText(
            //                """|class $className {
            //                   |
            //                   |    fun greetings() = println("Hello")
            //                   |}
            //                   |
            //                """.trimMargin(),
            //            )
            //            file.close()

            val file =
                codeGenerator.createNewFile(
                    Dependencies(true, function.containingFile!!),
                    packageName,
                    className,
                )
            val fileSpec =
                FileSpec.builder(packageName.replace("`", ""), className)
                    .indent("    ")
                    .addType(
                        TypeSpec.classBuilder(className)
                            .addFunction(
                                FunSpec.builder("greetings")
                                    .returns(ClassName("kotlin", "Unit"))
                                    .addStatement("println(%P)", "Hello")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            OutputStreamWriter(file, UTF_8).use(fileSpec::writeTo)
        }
    }

    private fun log(message: String) {
        logger.info("MESSAGE: $message")
    }
}

class PlaygroundProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return PlaygroundSymbolProcessor(environment.codeGenerator, environment.logger)
    }
}
