plugins {
    id("java")
    id ("org.springframework.boot") version "3.0.5"
    id ("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "ru.dvoretckii"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.borjaglez:springify-repository:0.3.3")
    implementation("org.springframework.boot:org.springframework.boot.gradle.plugin:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-integration:3.0.4")
    implementation(project(mapOf("path" to ":lab-2:Service")))
    implementation(project(mapOf("path" to ":lab-2:DAO")))
    implementation(project(mapOf("path" to ":lab-2:DAO")))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    annotationProcessor("org.springframework.boot:org.springframework.boot.gradle.plugin:3.0.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation ("org.postgresql:postgresql:42.6.0")

    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
   // implementation ("org.springframework.boot:spring-boot-starter-integration")
    implementation ("org.springframework.boot:spring-boot-starter-web")
   // implementation ("org.springframework.integration:spring-integration-http")
    //implementation ("org.springframework.integration:spring-integration-jpa")
    developmentOnly ("org.springframework.boot:spring-boot-devtools")
    annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.springframework.integration:spring-integration-test")
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}