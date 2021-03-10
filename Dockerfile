FROM adoptopenjdk/openjdk11:latest

MAINTAINER Lincoln Luiz <lincolnluiz.com@gmail.com>

ADD target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]