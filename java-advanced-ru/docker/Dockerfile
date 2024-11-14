FROM eclipse-temurin:20-jdk

ARG GRADLE_VERSION=8.2

RUN apt-get update && apt-get install -yq make unzip

RUN wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip \
    && rm gradle-${GRADLE_VERSION}-bin.zip

ENV GRADLE_HOME=/opt/gradle

RUN mv gradle-${GRADLE_VERSION} ${GRADLE_HOME}

ENV PATH=$PATH:$GRADLE_HOME/bin

COPY ./app .

RUN gradle installDist

CMD ./build/install/simple-application/bin/simple-application
# Сообщаем, что контейнер использует 3000 порт
EXPOSE 3000

#1 build: sudo docker build . -t simple-application
#3 all images: sudo docker images
#2 run: docker run -it -p 3000:8080 simple-application