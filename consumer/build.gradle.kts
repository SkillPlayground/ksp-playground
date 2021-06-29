
plugins {
    id("com.google.devtools.ksp") version "1.5.20-1.0.0-beta04"
    `kotlin-jvm`
}

kotlin.sourceSets.all {
    kotlin.srcDirs("$buildDir/generated/ksp/main/kotlin")
}

dependencies {
    implementation(project(":processor"))
    ksp(project(":processor"))
}
