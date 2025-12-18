# Clientes Cuentas Microservicio

Microservicio para la gestión de clientes y cuentas bancarias utilizando Arquitectura Hexagonal.

## Prerrequisitos

- Java 17

## Construcción

Para compilar y empaquetar el proyecto (recomendado para la primera vez):

```bash
./mvnw clean install
```

## Ejecución

Para ejecutar la aplicación, utilice el siguiente comando en el directorio raíz:

```bash
./mvnw spring-boot:run
```

La aplicación se iniciará en el puerto 8080.
La consola H2 está disponible en: http://localhost:8080/h2-console
- URL JDBC: jdbc:h2:mem:testdb
- Usuario: sa
- Contraseña: (vacía)

## Pruebas

Para ejecutar las pruebas, utilice:

```bash
./mvnw test
```

## Endpoints Disponibles

### Clientes

- **GET /clientes**: Obtener todos los clientes.
- **GET /clientes/mayores-de-edad**: Obtener clientes mayores de 18 años.
- **GET /clientes/con-cuenta-superior-a/{cantidad}**: Obtener clientes con saldo total superior a la cantidad especificada.
- **GET /clientes/{dni}**: Obtener un cliente específico por DNI.

### Cuentas

- **POST /cuentas**: Crear una nueva cuenta bancaria.
- **PUT /cuentas/{idCuenta}**: Actualizar el saldo de una cuenta existente.

## Ejemplos de Peticiones

### Obtener todos los clientes

```bash
curl -X GET http://localhost:8080/clientes
```

### Crear Cuenta

**POST /cuentas**

Body:
```json
{
  "dniCliente": "12345678Z",
  "tipoCuenta": "NORMAL",
  "total": 1000.0
}
```

Curl:
```bash
curl -X POST http://localhost:8080/cuentas \
-H "Content-Type: application/json" \
-d '{
  "dniCliente": "12345678Z",
  "tipoCuenta": "NORMAL",
  "total": 1000.0
}'
```

### Actualizar Saldo de Cuenta

**PUT /cuentas/{idCuenta}**

Body:
```json
{
  "total": 5000.0
}
```

Curl:
```bash
curl -X PUT http://localhost:8080/cuentas/1 \
-H "Content-Type: application/json" \
-d '{
  "total": 5000.0
}'
```
