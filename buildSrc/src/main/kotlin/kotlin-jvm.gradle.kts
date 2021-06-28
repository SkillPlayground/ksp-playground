
plugins {
    kotlin("jvm")
}

group = gradle.parent?.rootProject?.name ?: ""
version = "1.0.0"

kotlin.sourceSets.all {
    kotlin.srcDirs(name)
    resources.srcDirs(name)
}
