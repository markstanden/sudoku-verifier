FROM maven AS build
RUN git clone https://github.com/markstanden/sudoku-verifier.git /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk
COPY --from=build /usr/src/app/target/sudoku-verifier-1.0-SNAPSHOT.jar /usr/app/sudoku-verifier.jar
EXPOSE 8080
ENTRYPOINT ["java","-cp","/usr/app/sudoku-verifier.jar", "Main"]