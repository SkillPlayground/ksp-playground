enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

include(
    ":consumer:app",
    ":consumer:app-no-di",
    ":consumer:internal",
    ":consumer:open",
    ":consumer:presentation",
)

include(
    ":dagger:app",
    ":dagger:internal",
    ":dagger:open",
    ":dagger:presentation",
)

include(
    ":kotlin-inject:app",
    ":kotlin-inject:internal",
    ":kotlin-inject:open",
    ":kotlin-inject:presentation",
)

include(
    ":processor:annotations",
    ":processor:compiler",
)
