import org.springframework.boot.gradle.tasks.bundling.BootJar
import java.util.regex.Pattern

plugins {
    id("java")
}

group = "ru.dvoretckii"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mockito:mockito-inline:5.2.0")
    implementation("org.springframework.boot:spring-boot-starter-integration:3.0.4")
    annotationProcessor("org.springframework.boot:spring-boot-gradle-plugin:3.0.4")
    implementation("org.springframework.boot:org.springframework.boot.gradle.plugin:3.0.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("junit:junit:4.13.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    compileOnly ("javax.servlet:javax.servlet-api:4.0.1")
    implementation ("javax.servlet:jstl:1.2")
    implementation ("org.springframework:spring-webmvc:5.2.22.RELEASE")
    implementation ("org.webjars:bootstrap:5.2.0")
    testImplementation ("org.springframework:spring-test:5.2.22.RELEASE")
    testImplementation ("org.hamcrest:hamcrest-core:2.2")
    implementation("org.springframework.data:spring-data-jpa:3.0.4")


    implementation("com.borjaglez:springify-repository:0.3.3")
    implementation("org.springframework.boot:org.springframework.boot.gradle.plugin:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-integration:3.0.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    annotationProcessor("org.springframework.boot:org.springframework.boot.gradle.plugin:3.0.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation ("org.postgresql:postgresql:42.6.0")

    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    //developmentOnly ("org.springframework.boot:spring-boot-devtools")
    annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
    Pattern.compile("org.springframework.boot:spring-boot-starter-security")
    Pattern.compile("org.springframework.boot', name: 'spring-boot-starter-security")
    implementation ("org.springframework.boot:spring-boot-starter-security:3.0.4")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}