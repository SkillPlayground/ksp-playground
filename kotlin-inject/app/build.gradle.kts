
plugins {
    id("com.google.devtools.ksp") version "1.5.20-1.0.0-beta04"
    `kotlin-jvm`
}

kotlin.sourceSets.all {
    kotlin.srcDirs("$buildDir/generated/ksp/main/kotlin")
}

dependencies {
    implementation(projects.kotlinInject.internal)
    implementation(projects.kotlinInject.open)
    implementation(projects.kotlinInject.presentation)

    implementation("me.tatarka.inject:kotlin-inject-runtime:0.3.5")
    ksp("me.tatarka.inject:kotlin-inject-compiler-ksp:0.3.5")
}
