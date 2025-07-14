// GhostStories/domain/build.gradle.kts

plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(22)
}

dependencies {

    implementation(libs.kotlinx.coroutines.core)

    // For pure JVM unit tests
    testImplementation(libs.junit)

}