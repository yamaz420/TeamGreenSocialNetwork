# SSN - Spring Social Network

SSN is a Social Network Web Application based on Java Spring Boot created as a learning Project.

## Getting Started
The App was created according to the MVC (Model-View-Controller) paradigm. Basis of the View component was made using HTML/CSS/JS.

Deploy the App locally:

* Clone Repository to desired local destination directory
* Create a Database with MySQL (DATABASE, USERNAME and PASSWORD)
* In application.properties enter the DATABASE, USERNAME and PASSWORD:
```Java
spring.datasource.url=jdbc:mysql://localhost:3306/DATABASE?useSSL=false&serverTimezone=UTC
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
```
* Run the project using IntelliJ (IDE) and, in your browser, navigate to URL: localhost:8080

## Technology Stack
* MySQL - Database used

* Java 11 - Java/JDK version used

* Maven - Dependency Management

* Spring Boot - Back-End Framework used with the following dependencies:
  * Spring Boot Starter Data JPA
  * Spring Boot Starter Thymeleaf
  * Spring Boot Starter Web (uses Spring MVC)
  * Spring Boot DevTools
  * MySql Connector Java
  * Spring Boot Starter Test

jQuery - JavaScript Library

IntelliJ - IDE used

## Versioning
* Git for versioning
  * Git Bash - Git CLI
  * GitKraken - Git GUI

## Author
Marcus Mobark (dev0l) - [GitHub](https://www.github.com/dev0l) | [LinkedIn](https://se.linkedin.com/in/marcus-mobark-43358386)
