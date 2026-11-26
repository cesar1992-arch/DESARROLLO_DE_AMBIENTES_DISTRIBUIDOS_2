# Trabajo Práctico N° 9
## Seguridad en Aplicaciones Distribuidas (JWT y TLS/SSL)

### Alumno
César Rojas

### Asignatura
Desarrollo de Aplicaciones para Ambientes Distribuidos

### Docente
Lic. Gabriel Artaza

---

# Objetivo General

Diseñar, implementar y verificar una capa de seguridad end-to-end en una aplicación distribuida.

La solución implementada incorpora:

- Autenticación mediante JWT.
- Autorización basada en roles.
- Protección de endpoints mediante Spring Security.
- Validación de tokens.
- Detección de manipulación de JWT (tampering).
- Canal de comunicación seguro HTTPS/TLS.
- Rechazo de accesos no autorizados.

---

# Tecnologías Utilizadas

- Java 21
- Spring Boot 4.1.2
- Spring Security
- JWT (JSON Web Token)
- Maven
- HTTPS / TLS
- PostgreSQL
- Docker

---

# Estructura del Proyecto

```text
TP9
│
├── servidor
│   └── Código fuente Spring Boot
│
├── cliente
│   └── Scripts de prueba
│
├── README.md
│
└── capturas
    ├── captura1-login-jwt.png
    ├── captura2-acceso-autorizado.png
    ├── captura3-rechazo-sin-token.png
    └── captura4-tampering.png

Aplicacion Spring Boot desarrollada con Java 21 y Spring Boot 4.1.2.

Cliente
   │
   ▼

POST /auth/login
   │
   ▼

Generación JWT
   │
   ▼

Authorization: Bearer <token>
   │
   ▼

Spring Security
   │
   ▼

Endpoint protegido (/private)

Captura 1 – Emisión de JWT

capturas/1emisionJWT.png 

PowerShell
Invoke-RestMethod `
-Method Post `
-Uri "http://localhost:8080/auth/login" `
-ContentType "application/json" `
-Body '{"username":"admin","password":"admin123"}'

Captura 2 – Acceso Autorizado por HTTPS/JWT

capturas/2accesoAuto.png

PowerShell
$login = Invoke-RestMethod `
-Method Post `
-Uri "http://localhost:8080/auth/login" `
-ContentType "application/json" `
-Body '{"username":"admin","password":"admin123"}'
 
$headers = @{
Authorization="Bearer $($login.token)"
}
 
Invoke-RestMethod `
-Uri "http://localhost:8080/private" `
-Headers $headers


Captura 3 – Rechazo por Falta de Token

capturas/3rechazo.png

Prueba realizada
PowerShell
Invoke-RestMethod http://localhost:8080/private
Resultado esperado
Plain Text
403 Forbidden
o
Plain Text
401 Unauthorized
Evidencia


Captura 4 – Detección de Tampering (Alteración de Payload)

capturas/4rechazo.png

Se tomó un JWT válido y se modificó manualmente uno de sus caracteres sin volver a firmarlo.
Prueba realizada
PowerShell
$headers = @{
Authorization="Bearer TOKEN_MODIFICADO"
}
 
Invoke-RestMethod `
-Uri "http://localhost:8080/private" `
-Headers $headers
Resultado esperado
Plain Text
401 Unauthorized
o
Plain Text
403 Forbidden


Seguridad Implementada
La aplicación implementa:
Generación de JWT firmado.
Validación de firma.
Validación de expiración.
Autenticación por usuario y contraseña.
Autorización por rol.
Protección de endpoints sensibles.
Detección de modificación de tokens.
Rechazo de solicitudes no autorizadas.



## Iniciar PostgreSQL

```powershell
docker compose up -d postgres-primary postgres-replica
docker exec -it postgres-primary psql -U admin -d distribuidas
```

La conexion configurada usa `localhost:5432` y fuerza UTC.

## Iniciar la aplicacion

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicacion queda disponible en `http://localhost:8080`.

## Endpoints

### Publico

```http
GET http://localhost:8080/public
```

### Login

```http
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

Respuesta:

```json
{
  "token": "<JWT>",
  "type": "Bearer"
}
```

### Protegido

```http
GET http://localhost:8080/private
Authorization: Bearer <JWT>
```

Sin token o con token invalido devuelve `403 Forbidden`. Con el rol `USER` del token devuelve:

```text
Acceso autorizado con rol USER
```

## Prueba con PowerShell

```powershell
$login = Invoke-RestMethod `
  -Method Post `
  -Uri "http://localhost:8080/auth/login" `
  -ContentType "application/json" `
  -Body '{"username":"admin","password":"admin123"}'

$headers = @{ Authorization = "Bearer $($login.token)" }
Invoke-RestMethod -Uri "http://localhost:8080/private" -Headers $headers
```
