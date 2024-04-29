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
