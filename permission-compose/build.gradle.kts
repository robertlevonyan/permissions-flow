plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "com.innfinity.compose.permissions"
    compileSdk = 37

    defaultConfig.minSdk = 27
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.material)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.activity.compose)
}