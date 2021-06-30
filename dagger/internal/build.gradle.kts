
plugins {
    `kotlin-jvm`
    kotlin("kapt")
}

dependencies {
    implementation(projects.dagger.open)

    implementation("com.google.dagger:dagger:2.37")
    kapt("com.google.dagger:dagger-compiler:2.37")
}
