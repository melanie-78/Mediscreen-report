# Mediscreen-report

## Context

Within a technical team of an international company that collaborates with health clinics and private practices to screen for disease risks.
The objective is to set up a product to help doctors identify the patients most at risk of developing diabetes.

The project therefore consists of creating four microservices.
- The microservice called mediscreen that will store patient demographics
- The microservice called mediscreen-notes which will manage the notes issued by doctors for patients
- The microservice called mediscreen-report which will generate a kind of report on the probability of developing diabetes by a patient
- The front microservice that will serve as the user interface called mediscreen-web

Using declarative web service feign to communicate with other microservices.

## Technical:

1. Framework: Spring Boot v2.7.6
2. Java 8
3. JUnit 5.8.2
4. Mockito 4.5.1
5. Javax Validation 2.0.1 final
6. springdoc-openapi-ui 1.6.8
7. spring cloud openfeign

## Setup with Intellij IDE

1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Add folders
    - Source root: src/main/java
    - View: src/main/resources

## Write Unit Test
1. Create unit test and place in package com.openclassrooms.mediscreen in folder test > java
2. Execute unit test by the command mvn test
