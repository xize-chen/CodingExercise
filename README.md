# Coding Exercise 
The goal of the exercise is to create a very simple Spring Boot application for managing charge points used to charge electric vehicles. 
## Setup 
Use [Spring Initializr](https://start.spring.io/) to create a basic Spring Boot application with the following configuration/dependencies: 
- Java 
- Gradle 
- Spring Data JPA 
- H2 Database 
- Spring Web 
## Basic REST API 
The version information of the web application consists of (a) Version of the application and (b) Version of the database schema. 
1. Both application version and database schema version are specified as properties in `application.properties`. 
2. Create a Spring service allowing access to the version information from `application.properties`. 
3. Implement a Spring REST controller that returns the version information using the previously created service. 
## Enty Model 
Create an entity model satisfying the description below. We named key properties of the respective entity if they are not part of the description. 
- The system manages customers (name) and their charge points (name, unique serial number). Customers do not share charge points. 
- A charge point has one or more connectors (connector number). 
- RFID tags (name, number) are used to authenticate the access to charge points. 
    - RFID tags are owned by customers. Customers do not share RFID tags. 
    - The numbers of RFID tags are globally unique. 
    - Linked to at most one vehicle 
- After a vehicle (name, registration plate) is plugged into a connector, a charging session is started. The me and meter value are persisted in the database.
- When the charging session has ended, the end me and end meter value are persisted in the database. 
- The charge point transmits an error message if the charging session cannot be completed successfully. The error message is stored as part of the charging session. 

Make sensible assumptions about the data model and the properties of the enes to fill in gaps in the description. 
## Database Access 
Implement a utility class allowing you to convert a string value to a Java object representing a date with me. The converter shall support different patterns and automatically determine the pattern to be used for a string value. For example, it should be able to convert the dates “2020/03/01” and “2020-03-05 13:10”. 

Write unit tests for the utility class. 

Implement an endpoint in your REST API which will return the list of charging sessions stored in the database (pagination is not required). Use a Spring repository for retrieving the data from the database. The endpoint should allow you to specify a date range to filter the result. 

Describe in words what unit tests you would implement to test that the date range filter is working correctly. 
## Code Versioning 
Create a github repository and upload your web application.
