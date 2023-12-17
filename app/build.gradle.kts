
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.kaar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kaar"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
}

dependencies {

    //val navVersion = "2.7.5"
    val navVersion = "2.5.3"
    val lottieVersion = "3.4.0"
    val lifecycle_version = "2.6.2"

    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Firebase
    implementation("com.google.firebase:firebase-auth:22.3.0")
    // Kotlin-navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    //Coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    //Dagger hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //page Indicator
    implementation("com.tbuonomo:dotsindicator:5.0")
    //Lottie
    implementation ("com.airbnb.android:lottie:$lottieVersion")
    //security sharedPreferences
    implementation ("androidx.security:security-crypto:1.1.0-alpha06")
    //circularButton Github
    implementation("com.github.leandroborgesferreira:loading-button-android:2.3.0")
    //Retrofit
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //MoationToast
    //implementation ("com.github.Spikeysanju:MotionToast:1.4")
    //toasty
    //implementation ("com.github.shubhamvashisht:KToasty:1.0")
    //circular image
    implementation ("de.hdodenhof:circleimageview:3.1.0")





}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
