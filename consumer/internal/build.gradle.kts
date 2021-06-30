
plugins {
    id("com.google.devtools.ksp") version "1.5.20-1.0.0-beta04"
    `kotlin-jvm`
}

kotlin.sourceSets.all {
    kotlin.srcDirs("$buildDir/generated/ksp/main/kotlin")
}

dependencies {
    implementation(projects.consumer.open)

    implementation(projects.processor.annotations)
    ksp(projects.processor.compiler)
}
