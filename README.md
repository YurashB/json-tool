# How to run backend 

## Step 1
In **application.yml** set yours db properties:
```
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/jsontool
    username: postgres
    password: password
```

## Step 2
You can use flyway or disable 
```
  flyway:
    enabled: true
    driverClassName: org.postgresql.Driver
    user: postgres
    password: password
    url: jdbc:postgresql://localhost:5432/jsontool
    schemas: public
    baseline-on-migrate: true
```

If you want to disable, then just set 
```
flyway:
    enabled: false
```

## Step 3
Run command to migrate db or skip this step and do it with yourself.
SQL commands are placed in _[src/main/resources/db/migration](src/main/resources/db/migration)_
```
mvn flyway:migrate
```

## Step 4 
Run backend
```
 mvn spring-boot:run
```

