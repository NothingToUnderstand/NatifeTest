plugins {
    alias(libs.plugins.com.android.app)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.safe.args)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}


android {
    namespace = "com.example.natifetest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.natifetest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        base.archivesName.set("natife_test - $versionName")
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
        all {
            buildConfigField("String", "API_KEY", "\"Hq94HY8gP3jBq9aO7pamwIs2P2qp7Pms\"")
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
        kapt {
            correctErrorTypes = true
        }
    }

    dependencies {
        implementation(libs.giphy)
        implementation(libs.timber)
        implementation(libs.splash)

        implementation(libs.hilt.android)
        kapt(libs.hilt.kapt)

        implementation(libs.bundles.lifecycle)
        implementation(libs.bundles.navigation)
        implementation(libs.bundles.network)


        implementation(libs.core.ktx)
        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.test.ext.junit)
        androidTestImplementation(libs.espresso.core)
    }