buildscript {

    repositories {
        maven { url = uri("https://jitpack.io") }
        google()
        mavenCentral()
    }

    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
    }
}

repositories {
    maven { url = uri("https://jitpack.io") }
    google()
    mavenCentral()
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
