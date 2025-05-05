plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id ("androidx.navigation.safeargs")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}
apply(from = "../shared_dependencies.gradle")
android {
    namespace = "com.capstone.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField ("String", "API_KEY", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNGVmYmYwNmNkOGQ1N2E0NzhkOTEwMTE0MWE5YTFlOCIsIm5iZiI6MTY2NTQ3MDY1MS4zNDYsInN1YiI6IjYzNDUxMGJiOWM5N2JkMDA3YzIxZjY0NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.nPz9WHnIyubJUjEyRyhUjTox-zWf91dgQnXBXQsHQZ0\"")
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
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")

    //room
    ksp("androidx.room:room-compiler:2.6.1")
}