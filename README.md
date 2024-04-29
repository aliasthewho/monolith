# My mono-repo

1. Project setup and configuration
2. Core framework setup
3. Database setup
4. Domain model and repository layer
5. Service layer
6. API layer (controllers)
7. Security configuration
8. Error handling
9. Data validation
10. Logging configuration
11. Monitoring and metrics
12. API documentation
13. Message Queue and event-driven architecture
14. Caching mechanism
15. Batch processing
16. Scheduled tasks
17. File handling and resource management
18. Performance optimization
19. Containerization and Local deployment
20. CI/CD integration

# Pre-requisites

1. Docker
2. Java 17
3. Gradlew 8.*
4. Jacoco
5. SonarQube 4.*

## 2. JACOCO

1. All the required configuration on the build.gradle file.


## 3. SONARQUBE

# Using Docker

1. Pull the image from Docker Hub

```bash
docker pull sonarqube
```

2. Run the image

```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube
```

3. Access the SonarQube dashboard

```bash
http://localhost:9000
```

4. Run scan

```bash 
./gradlew sonar
```

4. Stop the container

```bash
docker stop sonarqube
```
