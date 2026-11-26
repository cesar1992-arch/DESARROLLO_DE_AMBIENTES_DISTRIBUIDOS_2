# Trabajo Práctico - API REST Cliente/Servidor

## Materia

Desarrollo de Aplicaciones para Ambientes Distribuidos

## Alumno

César Rojas

---

# Descripción

Se desarrolló una aplicación distribuida basada en arquitectura Cliente-Servidor utilizando Python.

El servidor expone una API REST mediante FastAPI y el cliente consume dicha API mediante solicitudes HTTP.

La comunicación se realiza utilizando JSON como formato de intercambio de datos, práctica habitual en servicios web REST. 【1-ae10fb】【2-fd8432】【3-dd31c4】

---

# Tecnologías Utilizadas

- Python
- FastAPI
- Uvicorn
- Requests
- JSON

## 3. Arquitectura del Sistema
```mermaid
graph LR
A[Cliente Tkinter] -->|HTTP JSON| B[API REST FastAPI]
B --> C[(Productos)]
C --> B
B -->|Respuesta JSON| A


## 4. Dominio Implementado
Se implementó un Catálogo de Productos.
Cada producto posee:
ID
Nombre
Precio
5. Endpoints Implementados
GET /productos
Obtiene la lista completa de productos.
POST /productos
Permite agregar un nuevo producto.
Ejemplo:
{
  "id": 1,
  "nombre": "Mouse",
  "precio": 4000
}

## 6. Marshalling y Unmarshalling
El sistema intercambia información utilizando JSON.
Marshalling
Conversión de los datos generados en el cliente a JSON para enviarlos por la red.
Unmarshalling
Reconstrucción de los datos recibidos en el servidor para su procesamiento.
Estos mecanismos son fundamentales para la comunicación entre componentes distribuidos.

## 7. Control de Errores
Se implementó manejo de errores mediante códigos HTTP.
Error 400 - Bad Request
Se genera cuando se intenta registrar un producto con un identificador ya existente.
Error 404 - Not Found
Se genera cuando se intenta acceder a un recurso inexistente.
El control de errores permite mejorar la robustez de la comunicación distribuida. 

## 8. Capturas de Pantalla
Captura 1 - Invocación Exitosa
(capturas/productoAgregado.png)
capturas/captura1.png

Captura 2 - Estructura de Datos JSON
(capturas/estructura2.png)
capturas/captura2.png

Captura 3 - Manejo de Errores
(capturas/errores3.png)
capturas/captura3.png

Captura 4 - Interfaz Gráfica
(capturas/interrfaz4.png)
capturas/captura4.png

Captura 5 - Swagger
(capturas/fastapi5.png)
capturas/captura5.png

## 9. Estructura del Proyecto
Plain Text
TP7
│
├── Servidor
│ └── main.py
│
├── Cliente
│ ├── cliente.py
│ └── interfaz.py
│
├── requirements.txt
│
└── README.md



11. Conclusión
Se desarrolló una aplicación distribuida basada en una arquitectura Cliente-Servidor utilizando una API REST construida con FastAPI y una interfaz gráfica desarrollada con Tkinter. La comunicación entre componentes se realiza mediante HTTP y JSON permitiendo aplicar conceptos de servicios distribuidos, serialización de datos, marshalling, unmarshalling y manejo de errores vistos durante la cursada. Este modelo corresponde al consumo de servicios REST por parte de clientes independientes, uno de los enfoques más utilizados actualmente en aplicaciones distribuidas.


# Instalación

Instalar dependencias:

```bash
pip install -r requirements.txt
```

---

# Ejecución del Servidor

Ingresar a la carpeta servidor:

```bash
cd Servidor
```

Ejecutar:

```bash
uvicorn main:app --reload
```

Servidor disponible en:

```text
http://127.0.0.1:8000
```

Swagger:

```text
http://127.0.0.1:8000/docs
```

---
ejecutar cliente 
cd cliente 
python interfaz.py 