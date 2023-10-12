pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
//    versionCatalogs {
//        create("libs") {
//            from(files("/gradle/libs.versions.toml"))
//        }
//    }
}

rootProject.name = "ContentProviderApp"
include(":app")
include(":core:redux")
include(":feature:note:data")
include(":feature:note:domain")
include(":feature:note:utils")
include(":feature:note:presentation")
 