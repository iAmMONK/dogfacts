const val kotlinVersion = "1.6.0"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "7.0.4"
        const val ktlintGradle = "10.2.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val ktlintGradlePlugin = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlintGradle}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "android"
    const val kapt = "kotlin-kapt"
    const val kotlinAndroidExtensions = "android.extensions"

}

object AndroidSdk {
    const val min = 21
    const val compile = 31
    const val target = compile
    const val buildTools = "30.0.2"
}

object Libraries {
    private object Versions {
        const val ads = "19.7.0"
        const val timber = "5.0.1"
        const val glide = "4.12.0"
        const val koin = "3.1.4"
        const val retrofit = "2.9.0"
        const val okHttp = "3.9.0"
        const val htmlSpanner = "0.4"
    }

    const val kotlinStdLib     = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val ads              = "com.google.android.gms:play-services-ads:${Versions.ads}"

    const val timber           = "com.jakewharton.timber:timber:${Versions.timber}"
    const val glide            = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideKtx         = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val koin             = "io.insert-koin:koin-android:${Versions.koin}"
    const val retrofit         = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson     = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitLogging  = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val htmlSpanner      = "com.github.NightWhistler:HtmlSpanner:${Versions.htmlSpanner}"
}

object AndroidLibraries {
    private object Versions {
        const val jetpack = "1.3.1"
        const val material = "1.4.0"
        const val core = "1.7.0"
        const val navigation = "2.3.2"
        const val constraintLayout = "2.0.4"
        const val fragment = "1.4.0"
        const val lifeCycle = "2.4.0"
        const val activityKtx = "1.4.0"
        const val work = "2.7.1"
        const val splash = "1.0.0-alpha02"
        const val paging = "3.1.0"
        const val room = "2.4.0-alpha03"
    }

    const val navigation            = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi          = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationModules     = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
    const val fragment              = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val activityKtx           = "androidx.activity:activity-ktx:${Versions.activityKtx}"

    const val appCompat             = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val core                  = "androidx.core:core-ktx:${Versions.core}"
    const val viewModel             = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val liveData              = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val liveDataRx            = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifeCycle}"
    const val liveDataKapt          = "androidx.lifecycle:lifecycle-compiler:${Versions.lifeCycle}"
    const val splash                = "androidx.core:core-splashscreen:${Versions.splash}"

    const val constraintLayout      = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material              = "com.google.android.material:material:${Versions.material}"
    const val work                  = "androidx.work:work-runtime-ktx:${Versions.work}"

    const val paging                = "androidx.paging:paging-runtime:${Versions.paging}"
    const val room                  = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx               = "androidx.room:room-ktx:${Versions.room}"
    const val roomKapt              = "androidx.room:room-compiler:${Versions.room}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val testRunner = "1.1.0-alpha4"
        const val espresso = "3.1.0-alpha4"
    }
    const val junit4     = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso   = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}