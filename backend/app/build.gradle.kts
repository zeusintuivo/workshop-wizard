/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.3/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("io.ktor.plugin") version "2.3.5"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"


    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-sessions-jvm:2.3.6")
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.6")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.20")
    val ktor_version = "2.3.6"
    val logback_version = "1.4.11"
    val slf4j_version = "2.0.9"
    val kotlin_version = "1.9.20"
    val logstash_version = "7.4"
    val flyway_version = "10.6.0"
    val hikari_version = "5.1.0"
    val postgres_version = "42.7.1"

    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0") // Use the latest version

    // Use the JUnit 5 integration.
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")

    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")

    runtimeOnly("ch.qos.logback:logback-classic:$logback_version")
    runtimeOnly("org.slf4j:slf4j-api:$slf4j_version")
    runtimeOnly("net.logstash.logback:logstash-logback-encoder:$logstash_version")

    // Auth
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")


    // Serialization
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    // Kotlin Query
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("com.github.seratch:kotliquery:1.9.0")


    // Database
    // https://mvnrepository.com/artifact/org.flywaydb/flyway-core - database migrations
    implementation("org.flywaydb:flyway-core:$flyway_version")
    implementation("org.flywaydb:flyway-database-postgresql:$flyway_version")

    // https://mvnrepository.com/artifact/com.zaxxer/HikariCP - connection pooling
    implementation("com.zaxxer:HikariCP:$hikari_version")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql - database driver
    implementation("org.postgresql:postgresql:$postgres_version")

}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    // Define the main class for the application.
    mainClass.set("backend.AppKt")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }
}
