plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")  // Para o Kapt, que é usado pelo Hilt
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
    implementation(libs.compose.animation)  // Dependência adicionada
    implementation(libs.compose.runtime)    // Dependência adicionada

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

    // Testes
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
}

