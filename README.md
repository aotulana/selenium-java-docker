# Automation Code Challenge

This project contains submission for both Web and API Automation Challenges. 
The Web Automation was implemented using Selenium v4 and RESTAssured for the API Automation.

## Setup

- JAVA 8 jdk must be [installed](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
- Maven must be [installed](https://maven.apache.org/install.html)
- Google Chrome web browser must be [installed](https://www.google.com/chrome/)
- Docker must be [installed](https://docs.docker.com/get-docker/) to run tests in docker container

## Running the Test Suite

From project root directory:

### Locally
- run `mvn test` to run both Web and API tests

### Docker
- run `docker build -t automation/code-challenge .` to build docker image
- run `docker run -v /dev/shm:/dev/shm -it automation/code-challenge` to run tests in docker container

## Viewing Test Report

1. Go to test report directory: `{project-root-directory}/test-output/`
2. Open `TestReport.html` in a web browser
