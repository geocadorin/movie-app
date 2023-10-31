import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")

}
//Necessário apenas para configurar a apiKey do TMDB
val apiKeyPropertiesFile = rootProject.file("apiKey.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))

android {
    namespace = "com.example.movie"
    compileSdk = 34

    //Necessário apenas para configurar a apiKey do TMDB
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.movie"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //Necessário apenas para configurar a apiKey do TMDB
        val apiKey = apiKeyProperties["API_KEY"] as String
        val urlBase = apiKeyProperties["BASE_URL"] as String
        val baseImage = apiKeyProperties["BASE_URL_IMAGE"] as String
        buildConfigField("String", "API_KEY", apiKey)
        buildConfigField("String", "BASE_URL", urlBase)
        buildConfigField("String", "BASE_URL_IMAGE", baseImage)

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    // Coil
    implementation ("io.coil-kt:coil-compose:2.2.2")

    // Timber
    implementation ("com.jakewharton.timber:timber:5.0.1")

    // DataStore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    // Splashscreen
    implementation ("androidx.core:core-splashscreen:1.0.0")

    // Gson
    implementation ("com.google.code.gson:gson:2.10")

    // Others - Compose dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.compose.material:material-icons-extended:1.4.0")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.17.0")
    implementation ("androidx.navigation:navigation-compose:2.5.3")

    // Paging3
    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha18")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // DI - Hilt
    implementation ("com.google.dagger:hilt-android:2.45")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt ("com.google.dagger:hilt-compiler:2.45")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")

    // Room
    implementation ("androidx.room:room-ktx:2.4.2")
    implementation ("androidx.room:room-runtime:2.4.2")
    kapt ("androidx.room:room-compiler:2.4.2")
}