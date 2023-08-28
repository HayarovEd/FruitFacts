// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {


        val hilt = "2.45"
        dependencies {
            classpath   ("com.google.dagger:hilt-android-gradle-plugin:$hilt")
        }

    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}