plugins {
  alias(libs.plugins.android.application)
}

android {
  compileSdk = 37

  defaultConfig {
    applicationId = "com.innfinity.permissionflow"
    minSdk = 27
    targetSdk = 37
    versionCode = 1
    versionName = "1.0"
  }
  buildFeatures {
    viewBinding = true
  }
  namespace = "com.innfinity.permissionflow"
}

kotlin {
  jvmToolchain(17)
}

dependencies {
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.coroutines.android)

  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.fragment.ktx)

  implementation(libs.permission.flow)
}
