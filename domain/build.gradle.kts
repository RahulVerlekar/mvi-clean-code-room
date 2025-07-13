// GhostStories/domain/build.gradle.kts

plugins {
    alias(libs.plugins.kotlin.jvm)
}


dependencies {

    implementation(libs.kotlinx.coroutines.core)

    // For pure JVM unit tests
    testImplementation(libs.junit)

}