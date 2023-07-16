# Ejercicio Técnico - Banco

API REST para Registración y Login de un Usuario

## Build

### Requisitos:

- Java 8 o superior
- Gradle 8.1 (incluído en un .jar dentro del proyecto)
- Intellij o cualquier otro IDE
- Postman

## Ejecución

### Inicio

Para ejecutar el programa, abra la raíz del proyecto dentro del IDE, Intellij detectará automáticamente las configuraciones de Spring Boot y bajará las dependencias necesarias. El proyecto iniciará por defecto en el puerto 8080.

Una vez levantado el proyecto, ejecute la herramienta de Postman e importe el archivo de la carpeta docs con el nombre "DemoBanco.postman_collection", se encontran todas las operaciones para realizar.

### Crear un Usuario

POST api/v1/users/sign-up

<u>Request:</u>

```json
{
    "name": "John Doe",
    "email": "jdoe@example.com",
    "password": "a2asfGfdfdf4",
    "phones": [
        {
            "number": 1544446666,
            "citycode": 11,
            "contrycode": "AR"
        }
    ]
}
```

<u>Response - 201 Created:</u>

```json
{
    "id": "b77ae7be-a86e-4488-9611-cf5081364e92",
    "name": "John Doe",
    "email": "jdoe@example.com",
    "password": "encrypted password",
    "created": "jul. 16, 2023 07:52:46 p. m.",
    "lastLogin": null,
    "token": "eyJhbGciOiJub25lIn0.eyJuYW1lIjoiSm9obiBEb2Ui....",
    "phones": [
        {
            "number": 1544446666,
            "citycode": 11,
            "countrycode": null
        }
    ],
    "active": true
}
```

<u>Response - 400 Bad Request:</u>

```json
{
    "code": 400,
    "detail": "Incorrect email",
    "timestamp": "2023-07-16T22:57:30.087432400Z"
}
```

### Login de un Usuario

GET api/v1/users/login?token={*token*}

**parámetro *token* es un String obtenido de la acción de crear usuario.

<u>Response - 200 OK:</u>

```json
{
    "id": "b77ae7be-a86e-4488-9611-cf5081364e92",
    "name": "John Doe",
    "email": "jdoe@example.com",
    "password": "encrypted password",
    "created": "jul. 16, 2023 07:52:46 p. m.",
    "lastLogin": null,
    "token": "eyJhbGciOiJub25lIn0.eyJuYW1lIjoiSm9obiBEb2Ui....",
    "phones": [
        {
            "number": 1544446666,
            "citycode": 11,
            "countrycode": null
        }
    ],
    "active": true
}
```

<u>Response - 404 Not Found:</u>

```json
{
    "code": 404,
    "detail": "Can't find token authorization",
    "timestamp": "2023-07-16T23:00:52.091815Z"
}
```

