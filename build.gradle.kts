import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifactoryCredsPsw: String? = project.findProperty("artifactory.psw")?.toString() ?: System.getenv("ARTIFACTORY_CREDS_PSW")
val artifactoryCredsUser: String? = project.findProperty("artifactory.user")?.toString() ?: System.getenv("ARTIFACTORY_CREDS_USR")

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.upstart"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven {
        url = uri("https://upstart.jfrog.io/artifactory/upst-libs-release-local")
        credentials {
            username = artifactoryCredsUser
            password = artifactoryCredsPsw
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("ch.qos.logback:logback-core")
    implementation("ch.qos.logback:logback-classic")
    implementation("org.slf4j:slf4j-api")

    implementation("org.springdoc:springdoc-openapi-data-rest:1.6.11")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.11")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.11")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.11")

    testImplementation("io.mockk:mockk:1.12.7")
    testImplementation("com.ninja-squad:springmockk:3.1.1")

    testImplementation("org.skyscreamer:jsonassert:1.5.1")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    implementation("com.upstart.mortgage:mortgage-db-config:0.1.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
