FROM ubuntu:latest as build

RUN apt-get update 
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mnv clean install 

expose 8080

copy --from=build /target/java-todo-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]