
plugins {
    id("com.google.devtools.ksp") version "1.5.20-1.0.0-beta04"
    `kotlin-jvm`
}

dependencies {
    implementation(project(":processor"))
    ksp(project(":processor"))
}
