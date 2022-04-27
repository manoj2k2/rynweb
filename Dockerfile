FROM openjdk:11
ADD target/pdm-application.jar pdm-application.jar
ENTRYPOINT ["java","-jar","/pdm-application.jar"]