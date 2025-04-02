plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("kotlin-parcelize")


    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.sweet"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sweet"
        minSdk = 28
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    // הפעלת View Binding
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.circleimageview)
    implementation (libs.material.vversion)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation (libs.material.v160)
    implementation (libs.material.vversion)

    //עבור העלאת תמונה לאפליקציה:
    implementation(libs.glide)
    annotationProcessor(libs.compiler)


    // תלויות Firebase ו-Firestore
    implementation(platform("com.google.firebase:firebase-bom:33.11.0"))
    implementation(libs.firebase.auth.ktx) // Firebase Authentication
    implementation(libs.firebase.firestore.ktx)// Firestore
    implementation(libs.firebase.common.ktx)// Firebase Common

    // פריווילגיות ל-firebase ן- firestore
    implementation(libs.firebase.auth)
    implementation(libs.firebase.analytics)

    //שירותי גוגל
    implementation(libs.play.services.auth)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.play.services.base)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
apply(plugin = "com.google.gms.google-services")
