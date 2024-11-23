// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("com.android.library") version "7.4.2" apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.google.services)
    }
}