plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "dev.xuanran.anycopy"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.xuanran.anycopy"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {
    compileOnly("de.robv.android.xposed:api:82")
}

tasks.register("installDebugTask") {
    doLast {
        println("Running gradlew installDebug...")
        exec {
            commandLine("gradlew", "installDebug")
        }
    }
}
