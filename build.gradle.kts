plugins {
	java
	id("org.springframework.boot") version "3.2.9"
	id("io.spring.dependency-management") version "1.1.6"
	jacoco
    kotlin("jvm")
	id("org.sonarqube") version "3.3"
}

group = "com.itau.banking"
version = "1.0.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}
dependencies {
	// Spring Boot
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Validation
	implementation("org.hibernate.validator:hibernate-validator:6.2.0.Final")
	implementation("javax.validation:validation-api:2.0.1.Final")

	// OpenFeign
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

	// Resilience
	implementation("io.github.resilience4j:resilience4j-spring-boot2:1.7.0")
	implementation("io.github.resilience4j:resilience4j-retry:1.7.1")
	implementation("io.github.resilience4j:resilience4j-circuitbreaker:1.7.0")

	// Testes
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter:5.9.3")

	// Logs
	implementation("org.slf4j:slf4j-api:2.0.7")
	implementation("ch.qos.logback:logback-classic:1.4.12")

	// Lombok
	compileOnly("org.projectlombok:lombok:1.18.28")
	annotationProcessor("org.projectlombok:lombok:1.18.28")

	// SpringDoc OpenAPI
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

	// Monitoring
	implementation("org.springframework.boot:spring-boot-starter-actuator")
  	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("io.github.resilience4j:resilience4j-spring-boot2")
	implementation("io.micrometer:micrometer-core")
	implementation("io.micrometer:micrometer-registry-prometheus")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jacoco {
	toolVersion = "0.8.8"
}


