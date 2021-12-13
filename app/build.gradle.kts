plugins {
    id(BuildPlugins.androidApplication)
    kotlin(BuildPlugins.kotlinAndroid)
}

android {
    buildToolsVersion = AndroidSdk.buildTools
    compileSdk = AndroidSdk.compile
    defaultConfig {
        applicationId = "com.dev.monk.dogfacts"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 22
        versionName = "2.1.0"
        multiDexEnabled = true
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
//        forEach { build ->
//            build.resValue("string", "admob_app_id", project.property("AdMobAppId") as String)
//            build.resValue("string", "admob_ad_unit_id", project.property("AdMobAdUnitId") as String)
//        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.ads)
    implementation(AndroidLibraries.core)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.activityKtx)
    implementation(AndroidLibraries.material)
    implementation(AndroidLibraries.constraintLayout)
    implementation(AndroidLibraries.liveData)
    implementation(AndroidLibraries.viewModel)
    implementation(AndroidLibraries.work)
    implementation(AndroidLibraries.splash)
}

repositories {
    mavenCentral()
    google()
}
