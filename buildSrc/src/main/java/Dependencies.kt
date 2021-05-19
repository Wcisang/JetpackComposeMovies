object Version {
    const val coreKtx = "1.3.2"
    const val appCompat = "1.2.0"
    const val material = "1.3.0"
    const val compose = "1.0.0-beta06"
    const val lifecycleKtx = "2.3.1"
    const val composeActivity = "1.3.0-alpha07"
    const val composeNavigation = "1.0.0-alpha10"
    const val retrofit = "2.9.0"
    const val okHttp = "4.9.0"
    const val retrofitCoroutines = "0.9.2"
    const val coroutines = "1.4.3"
    const val composeCoil = "0.8.1"
    const val gson = "2.8.6"
    const val hilt = "2.35.1"
    const val hiltCompiler = "1.0.0"
    const val hiltNavigation = "1.0.0-alpha01"
}

object Libs {
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val composeUi = "androidx.compose.ui:ui:${Version.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Version.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleKtx}"
    const val composeActivity = "androidx.activity:activity-compose:${Version.composeActivity}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Version.composeNavigation}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
    const val jacksonConverter = "com.squareup.retrofit2:converter-jackson:${Version.retrofit}"
    const val retrofitCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.retrofitCoroutines}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    const val composeCoil = "com.google.accompanist:accompanist-coil:${Version.composeCoil}"
    const val gson = "com.google.code.gson:gson:${Version.gson}"
    const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Version.hiltNavigation}"
}

object Kapt {
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Version.hiltCompiler}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
}

object LibsTest {

}

object LibsAndroidTest {

}