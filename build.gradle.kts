import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.10"
}

group = "me.ieva"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

javafx {
    modules = listOf("javafx.controls", "javafx.swing")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.9")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}