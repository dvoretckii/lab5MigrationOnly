import org.springframework.boot.gradle.tasks.bundling.BootJar

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
    implementation("org.springframework.security:spring-security-config:6.0.2")
    implementation("org.springframework.security:spring-security-web:6.0.2")
    implementation("org.apache.tomcat.embed:tomcat-embed-core:10.1.7")
    implementation("com.rabbitmq:amqp-client:5.13.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}