plugins {
  id("com.android.library")
  kotlin("android")
  id("com.vanniktech.maven.publish")
}

android {
  compileSdkVersion(30)

  defaultConfig {
    minSdkVersion(21)
    targetSdkVersion(30)
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

allprojects {
  plugins.withId("com.vanniktech.maven.publish") {
    mavenPublish {
      sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
    }
  }
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

  implementation("androidx.appcompat:appcompat:1.2.0")
  implementation("androidx.core:core-ktx:1.3.2")
  implementation("androidx.fragment:fragment-ktx:1.3.3")
}
