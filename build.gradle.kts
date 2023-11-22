plugins {
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(platform("io.ktor:ktor-bom:2.3.6"))
    implementation("io.ktor:ktor-client-core")
    implementation("io.ktor:ktor-client-cio")

    runtimeOnly("ch.qos.logback:logback-classic:1.2.12")
}