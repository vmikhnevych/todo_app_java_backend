# todo_app_java_backend
A simple app to test how Copilot works with Java

### Run the example
Create Postgres database and role as described in `db-init.sql`. Adjust `application.properties` if needed. The rest will be done by Spring-Boot after application launch.

```
$ mvn clean package
$ java -cp target/todo-1.0.jar com.vmikh.todo.TodoApp
```