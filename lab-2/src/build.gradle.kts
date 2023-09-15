plugins {
    id("java")
}

group = "ru.dvoretckii"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(project(mapOf("path" to ":lab-2:DAO")))
    testImplementation(project(mapOf("path" to ":lab-2:Service")))
}

tasks.test {
    useJUnitPlatform()
}