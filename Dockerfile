FROM maven:3.5-jdk-8-onbuild-alpine
CMD ["java","-jar","/usr/src/app/target/kafka-stream-processor-1-jar-with-dependencies.jar"]