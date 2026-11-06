plugins {
    kotlin("jvm") version "2.4.10"
}

group = "com.derricknjeru"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

// Run any Kotlin `main`, e.g. ./gradlew -q runMain -PmainClass=intercom._1agentassignment.solution.SolutionKt
tasks.register<JavaExec>("runMain") {
    group = "application"
    description = "Runs the Kotlin main class given by -PmainClass=<fully.qualified.FileKt>"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set(providers.gradleProperty("mainClass"))
    standardInput = System.`in`
}
kotlin {
    jvmToolchain(17)
}