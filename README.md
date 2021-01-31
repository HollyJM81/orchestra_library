# orchestra_library
Orchestral library database

## Tech Stack
Frontend: Typescript, framework TBD
Backend: Kotlin/Spring Boot
Database: Postgres
CI System: GitHub Actions

## Prerequisites (delete if empty)
- Java 11 or higher
- Kotlin 1.4 or higher
- Node 12 or higher

## Development Instructions
- install the pre-commit hook: `npm i`

### Backend
- Build the project: `cd backend && ./gradlew assemble` (initial run will be slow) OR use the Gradle window in IDEA
- Run the application: use the gradle bootRun task and visit https://localhost:8080/composers. 
The credentials are found in `/backend/application.properties`. 

## Resources
- [Getting Started with Spring Boot](https://spring.io/guides/gs/spring-boot/)

## Troubleshooting
- RestController can't be imported?
  - run gradle clean and reopen project

