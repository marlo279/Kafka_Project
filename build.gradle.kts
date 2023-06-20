plugins {
    id("java")
}

group = "com.marlo.demo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
    implementation("org.apache.kafka:kafka-clients:3.0.0");

    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:1.7.32");

    implementation("org.slf4j:slf4j-simple:1.7.32");

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.xellitix.commons:jackson-utils:0.2.0")
    implementation("com.konghq:unirest-objectmapper-jackson:4.0.0-RC7")
    implementation("com.xellitix.commons:jackson-utils:0.2.0")
    implementation("com.github.lbovolini:ObjectMapper:0.2.3")



}

tasks.test {
    useJUnitPlatform()
}