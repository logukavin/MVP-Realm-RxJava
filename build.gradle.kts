// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("material_Version", "1.11.0")
        set("constraintlayout_Version", "2.1.4")
        set("multidex_Version", "2.0.1")
        set("espresso_core_Version", "3.5.1")
        set("test_junit_Version", "1.1.5")
        set("junit_Version", "4.13.2")
        set("appcompat_Version", "1.6.1")
        set("core_ktx_Version", "1.12.0")
        set("recyclerview_Version", "1.3.2")

        //Retrofit
        set("retrofit_Version", "2.11.0")
        set("retrofit_converter_Version", "2.11.0")
        set("gson_Version", "2.11.0")
        set("logging_interceptor_Version", "4.12.0")

        //Rxjava
        set("rxjava_Version", "3.1.5")
        set("rxandroid_Version", "3.0.0")

        //Dagger2
        set("dagger_Version", "2.38.1")
        set("daggercompiler_Version", "2.38.1")

        //paging
        set("paging_Version", "2.1.2")

        //Glide
        set("glide_Version", "5.0.0-rc01")

        //Realm
        set("realm_Version", "10.11.1")

    }
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        //noinspection UseTomlInstead
        classpath("io.realm:realm-gradle-plugin:10.15.1")
    }

}

plugins {
    id("com.android.application") version "8.3.2" apply false }


