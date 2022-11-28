import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.serialization") version "1.7.21"
    id("io.ktor.plugin") version "2.1.3"
}

group = "me.vldf"
version = "1.0-SNAPSHOT"

val ktorVersion = "2.1.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("org.slf4j:slf4j-api:2.0.4")
    implementation("org.slf4j:slf4j-simple:2.0.4")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")

    implementation("com.github.ajalt.colormath:colormath:3.2.1")
    implementation("com.github.haifengl:smile-core:2.6.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.8"
}

application {
    mainClass.set("me.vldf.colors.MainKt")
}

ktor {
    fatJar {
        archivesName.set("dominant-colors-extractor.jar")
    }
}

val distZip by tasks
distZip.enabled = false

val distTar by tasks
distTar.enabled = false

val shadowDistTar by tasks
shadowDistTar.enabled = false

val shadowDistZip by tasks
shadowDistZip.enabled = false
