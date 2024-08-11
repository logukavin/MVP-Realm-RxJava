pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://realm.artifactoryonline.com/realm/realm-android")
        }
        maven {
            url = uri("https://jitpack.io")
        }
        maven {
            url = uri("https://maven.google.com")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }

    }
}

rootProject.name = "sample"
include(":app")
 