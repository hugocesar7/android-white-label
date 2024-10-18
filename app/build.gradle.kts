plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.androidwhitelabel"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidwhitelabel"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // Configuração do Java e Kotlin para usar target 1.8
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    // Habilitando Jetpack Compose
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()  // Versão correta do Compose
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Jetpack Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.tooling)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.activity)
    implementation(libs.compose.material3)
    implementation(libs.compose.animation)
    implementation(libs.compose.runtime)

    // Lifecycle e ViewModel
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.compose)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Retrofit e Gson
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.runtime.livedata)
    kapt(libs.hilt.compiler)

    // Coil para carregamento de imagens
    implementation(libs.coil.compose)

    // Para evitar problemas de incompatibilidade com JavaPoet (geralmente associado ao Hilt)
    implementation("com.squareup:javapoet:1.13.0")

    // Testes Unitários
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.arch.core.testing)  // Para usar InstantTaskExecutorRule e testar LiveData
    testImplementation(libs.coroutines.test)    // Para testes com Coroutines
    testImplementation(libs.hilt.android.testing)  // Hilt para testes
    kaptTest(libs.hilt.compiler)
    testImplementation(libs.mockk)  // Alternativa ao Mockito

    // Testes instrumentados (Android Test)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
