plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.codeborne:selenide:6.2.1")

    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testImplementation("io.qameta.allure:allure-junit5:2.17.2")

    testRuntimeOnly("org.slf4j:slf4j-simple:1.7.35")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    compileOnly ("org.projectlombok:lombok:1.18.22")
    annotationProcessor ("org.projectlombok:lombok:1.18.22")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}