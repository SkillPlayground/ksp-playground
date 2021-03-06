
plugins {
    `kotlin-jvm`
}

dependencies {
    implementation(projects.processor.annotations)

    implementation("com.google.devtools.ksp:symbol-processing-api:1.5.20-1.0.0-beta04")
    implementation("com.squareup:kotlinpoet:1.9.0")
}
