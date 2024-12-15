plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.avicenna.enterprise.solutions.news"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.avicenna.enterprise.solutions.news"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    // navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.andoridx.naviggation.ui)
    // live data
    implementation(libs.androidx.lifecycle.livedata)
    // viewmodel
    implementation(libs.androidx.lifecycle.viewmodel)
    // coroutines
    implementation(libs.kotlinx.coroutines.android)
    // coil
    implementation(libs.coil)
    // retrofit
    implementation(libs.square.retrofit)
    implementation(libs.retrofit.converter)
    // Room
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.coroutine)
    // ksp
    ksp(libs.androidx.room.compiler)
    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    // kapt
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}