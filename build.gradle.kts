buildscript {
    repositories {
        google()
        maven {
            url =uri ("https://jitpack.io")
        }

    }

    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        //val nav_version = "2.7.5"
        val nav_version = "2.5.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}
plugins {
    id("com.android.application") version "8.2.0" apply false
    id ("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false

}