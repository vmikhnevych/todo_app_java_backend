# todo_app_java_backend
A simple app to test how Copilot works with Java

### Get the source code
Clone the repository:
```
git clone https://github.com/vmikhnevych/todo_app_java_backend.git
```

### Prerequisites
* Java 17
* Maven 3.8.+
* Postgres 13.+


### Run the application
Create Postgres database and role specified in `db-init.sql`. Adjust `application.properties` if needed. The rest of DB schema setup will be done automatically by Spring-Boot after application launch.

Make sure Maven is installed and on the classpath. Then run the following command:

```
$ mvn spring-boot:run
```
This will start the application on port 8080. You can open it in a browser by navigating to `http://localhost:8080/todo`.

### Run tests
Make sure Maven is installed and on the classpath. Then run the following command:
```
$ mvn test
```