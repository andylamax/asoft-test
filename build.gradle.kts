plugins {
    kotlin("multiplatform") version ("1.3.70")
    id("com.android.library") version ("3.6.0")
    id("maven-publish")
}

object versions {
    val coroutines = "1.3.4"
    val selenium = "4.0.0-alpha-4"
    val espresso = "3.2.0"
    val test_runner = "1.2.0"
    val test_rules = "1.2.0"
}

group = "tz.co.asoft"
version = "4.2.1"

repositories {
    google()
    jcenter()
    maven(url = "https://jitpack.io")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(1)
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        val main by getting {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            resources.srcDirs("src/androidMain/resources")
        }
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    android {
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
        publishLibraryVariants("release")
    }

    jvm {
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
    }

    js {
        compilations.all {
            kotlinOptions {
                metaInfo = true
                sourceMap = true
                moduleKind = "commonjs"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${versions.coroutines}")
                api(kotlin("test-common"))
                api(kotlin("test-annotations-common"))
            }
        }

        val commonTest by getting {
            dependencies {

            }
        }

        val jvmCommonMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("stdlib"))
                api(kotlin("test"))
                api(kotlin("test-junit"))
            }
        }

        val androidMain by getting {
            dependsOn(jvmCommonMain)
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.coroutines}")
                api("androidx.test.espresso:espresso-core:${versions.espresso}")
                api("androidx.test:runner:${versions.test_runner}")
                api("androidx.test:rules:${versions.test_rules}")
            }
        }

        val androidTest by getting {
            dependencies {

            }
        }

        val jvmMain by getting {
            dependsOn(jvmCommonMain)
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.coroutines}")
                api("org.seleniumhq.selenium:selenium-java:${versions.selenium}")
            }
        }

        val jvmTest by getting {
            dependencies {

            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions.coroutines}")
                api(kotlin("test-js"))
            }
        }

        val jsTest by getting {
            dependencies {

            }
        }
    }
}