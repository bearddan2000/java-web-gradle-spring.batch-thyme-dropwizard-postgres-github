FROM maven:3-openjdk-17

WORKDIR /usr/src/mymaven

COPY bin .

RUN mvn package

RUN chmod +x /usr/src/mymaven/target/*.jar

CMD ["java", "-jar", "/usr/src/mymaven/target/DropWizardHibernateExample-FINAL.jar", "server", "dev.yml"]
