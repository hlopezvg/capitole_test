## Asignacion
Price API

## Requisitos
jdk 1.8  Como instalar Win o Linux


## Como ejectuarlo
./gradlew bootRun

## Pruebas/Tests
./gradlew test


## View the Data stored in the Database/Acceder a H2 Data
```
To login
JDBC URL: jdbc:h2:mem:testdb
USER: sa
PASS: don't type anything
http://localhost:8080/h2-console/
```

### Consume Endpoints/ Consumir el API Rest
```
curl -v -X GET localhost:8080/api/prices/2020-06-14-10.00.00/35455/1 | json_pp
curl -v -X GET localhost:8080/api/prices/2020-06-14-16.00.00/35455/1 | json_pp
curl -v -X GET localhost:8080/api/prices/2020-06-14-21.00.00/35455/1 | json_pp
curl -v -X GET localhost:8080/api/prices/2020-06-15-10.00.00/35455/1 | json_pp
curl -v -X GET localhost:8080/api/prices/2020-06-16-11.00.00/35455/1 | json_pp
```

### Important gradle commands / Commandos de gradle importantes para ser usados
```
./gradlew bootRun
./gradlew test
./gradlew bootRun
./gradlew build --refresh-dependencies
```
