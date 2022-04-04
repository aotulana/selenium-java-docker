FROM selenium/standalone-chrome

USER root

RUN apt-get update \
    && apt-get upgrade -y

# Maven version
ARG MAVEN_VERSION=3.8.5

# Maven Installation
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && echo "Downlaoding maven" \
  && curl -fsSL -o /tmp/apache-maven.tar.gz https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "Unziping maven" \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && echo "Cleaning and setting links" \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "~/.m2"

WORKDIR /usr/app

USER 1200

ENV START_XVFB=true

COPY . .

RUN sudo chmod -R 777 . .

CMD Xvfb :99 -screen 0 1024x768x24 -nolisten tcp & mvn test
