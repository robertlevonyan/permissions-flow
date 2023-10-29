plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  compileSdk = 34

  defaultConfig {
    applicationId = "com.innfinity.permissionflow"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  buildFeatures {
    viewBinding = true
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
  namespace = "com.innfinity.permissionflow"
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.fragment:fragment-ktx:1.6.1")

  implementation ("com.robertlevonyan.components:PermissionsFlow:1.2.5")
}
