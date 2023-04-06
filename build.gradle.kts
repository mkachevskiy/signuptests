plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.codeborne:selenide:6.12.3")
    implementation("commons-io:commons-io:2.11.0")

    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testImplementation("io.qameta.allure:allure-junit5:2.21.0")

    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.5")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    compileOnly ("org.projectlombok:lombok:1.18.24")
    annotationProcessor ("org.projectlombok:lombok:1.18.22")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}