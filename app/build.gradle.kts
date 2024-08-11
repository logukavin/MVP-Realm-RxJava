
plugins {
    id("com.android.application")
    id("realm-android")
}



android {
    namespace = "com.example.sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "DOMAIN_URL", "\"https://reqres.in/\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
    realm {
        isSyncEnabled=true
    }


}


dependencies {

    //Android Libraries
    implementation("androidx.core:core-ktx:${rootProject.extra["core_ktx_Version"]}")
    implementation("androidx.appcompat:appcompat:${rootProject.extra["appcompat_Version"]}")
//    implementation(libs.transport.runtime)
    testImplementation("junit:junit:${rootProject.extra["junit_Version"]}")
    androidTestImplementation("androidx.test.ext:junit:${rootProject.extra["test_junit_Version"]}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.extra["espresso_core_Version"]}")
    implementation("androidx.multidex:multidex:${rootProject.extra["multidex_Version"]}")
    implementation("androidx.recyclerview:recyclerview:${rootProject.extra["recyclerview_Version"]}")

    // UI
    implementation("com.google.android.material:material:${rootProject.extra["material_Version"]}")
    implementation("androidx.constraintlayout:constraintlayout:${rootProject.extra["constraintlayout_Version"]}")

    //Network
    implementation("com.squareup.retrofit2:retrofit:${rootProject.extra["retrofit_Version"]}")
    implementation("com.squareup.retrofit2:converter-gson:${rootProject.extra["retrofit_converter_Version"]}")
    implementation("com.squareup.okhttp3:logging-interceptor:${rootProject.extra["logging_interceptor_Version"]}")
    implementation("com.squareup.retrofit2:adapter-rxjava3:${rootProject.extra["retrofit_converter_Version"]}")

    //Rxjava
    implementation("io.reactivex.rxjava3:rxjava:${rootProject.extra["rxjava_Version"]}")
    implementation("io.reactivex.rxjava3:rxandroid:${rootProject.extra["rxandroid_Version"]}")

    //Dagger2
    implementation("com.google.dagger:dagger:${rootProject.extra["dagger_Version"]}")
    annotationProcessor("com.google.dagger:dagger-compiler:${rootProject.extra["daggercompiler_Version"]}")

    //paging
    implementation("androidx.paging:paging-runtime-ktx:${rootProject.extra["paging_Version"]}")

    // Glide
    implementation("com.github.bumptech.glide:glide:${rootProject.extra["glide_Version"]}")
    annotationProcessor("com.github.bumptech.glide:compiler:${rootProject.extra["glide_Version"]}")

    // Gson
    implementation("com.google.code.gson:gson:${rootProject.extra["gson_Version"]}")

//    //Realm
//    implementation("io.realm:realm-android:${rootProject.extra["realm_Version"]}")

}

//android{
//    realm {
//        isSyncEnabled=true
//    }
//}