
plugins {
    `kotlin-jvm`
}

kotlin.sourceSets.all {
    kotlin.srcDirs("$buildDir/generated/ksp/main/kotlin")
}
