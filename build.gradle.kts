import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral() // for transitive dependencies
    maven {
        url = uri("https://m2.dv8tion.net/releases")
    }
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.dv8tion:JDA:4.3.0_298") {
        exclude(module = "opus-java")
    }
    implementation ("com.jagrosh:jda-utilities-commons:3.0.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "15"
}
