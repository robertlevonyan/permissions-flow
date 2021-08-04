plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  compileSdk = 31

  defaultConfig {
    applicationId = "com.innfinity.permissionflow"
    minSdk = 21
    targetSdk = 31
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  buildFeatures {
    viewBinding = true
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

  implementation("androidx.appcompat:appcompat:1.3.1")
  implementation("androidx.core:core-ktx:1.6.0")
  implementation("androidx.fragment:fragment-ktx:1.3.6")

  implementation ("com.robertlevonyan.components:PermissionsFlow:1.2.2")
}
