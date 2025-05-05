plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs")
    id ("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}
apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.capstone.zainflix"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.capstone.zainflix"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField ("String", "API_KEY", "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNGVmYmYwNmNkOGQ1N2E0NzhkOTEwMTE0MWE5YTFlOCIsIm5iZiI6MTY2NTQ3MDY1MS4zNDYsInN1YiI6IjYzNDUxMGJiOWM5N2JkMDA3YzIxZjY0NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.nPz9WHnIyubJUjEyRyhUjTox-zWf91dgQnXBXQsHQZ0\"")
        buildConfigField ("String", "TOKEN_KEY", "\"04efbf06cd8d57a478d9101141a9a1e8\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")

    //room
    ksp("androidx.room:room-compiler:2.6.1")
}