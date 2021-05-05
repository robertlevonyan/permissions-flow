plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  compileSdkVersion(30)

  defaultConfig {
    applicationId = "com.innfinity.permissionflow"
    minSdkVersion(21)
    targetSdkVersion(30)
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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

  implementation("androidx.appcompat:appcompat:1.2.0")
  implementation("androidx.core:core-ktx:1.3.2")
  implementation("androidx.fragment:fragment-ktx:1.3.3")

  implementation ("com.robertlevonyan.components:PermissionsFlow:1.2.0")
}
