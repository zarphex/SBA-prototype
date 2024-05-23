plugins {
    id("java")
}

group = "com.zarphex"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.miglayout:miglayout-swing:11.2")
}

tasks.test {
    useJUnitPlatform()
}