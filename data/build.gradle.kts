plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("androidx.room")
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"${property("base_url")}\"")
            buildConfigField("String", "API_KEY", "\"${property("api_key")}\"")
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation(project(":domain"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    //Retrofit and JSON
    api("com.google.code.gson:gson:2.10.1")
    api("com.squareup.retrofit2:retrofit:2.10.0")
    api("com.squareup.retrofit2:converter-gson:2.10.0")
    api("com.squareup.okhttp3:logging-interceptor:4.9.2")


    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    // for room
    implementation("android.arch.lifecycle:extensions:1.1.1")
    implementation ("android.arch.persistence.room:runtime:1.1.1")
    annotationProcessor( "android.arch.lifecycle:compiler:1.1.1")
    annotationProcessor ("android.arch.persistence.room:compiler:1.1.1")
}
kapt {
    correctErrorTypes = true
}