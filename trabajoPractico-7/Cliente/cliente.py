import requests

URL = "http://127.0.0.1:8000"

def listar_productos():
    respuesta = requests.get(f"{URL}/productos")
    print(respuesta.json())

def buscar_producto():
    id_producto = input("ID: ")
    respuesta = requests.get(f"{URL}/productos/{id_producto}")
    print(respuesta.json())

def agregar_producto():

    datos = {
        "id": int(input("ID: ")),
        "nombre": input("Nombre: "),
        "precio": float(input("Precio: "))
    }

    respuesta = requests.post(
        f"{URL}/productos",
        json=datos
    )

    print(respuesta.json())

def actualizar_producto():

    id_producto = int(
        input("ID a actualizar: ")
    )

    datos = {
        "id": id_producto,
        "nombre": input("Nombre nuevo: "),
        "precio": float(
            input("Precio nuevo: ")
        )
    }

    respuesta = requests.put(
        f"{URL}/productos/{id_producto}",
        json=datos
    )

    print(respuesta.json())

def eliminar_producto():

    id_producto = input("ID: ")

    respuesta = requests.delete(
        f"{URL}/productos/{id_producto}"
    )

    print(respuesta.json())

while True:

    print("\n===== MENU =====")
    print("1- Listar")
    print("2- Buscar")
    print("3- Agregar")
    print("4- Actualizar")
    print("5- Eliminar")
    print("0- Salir")

    opcion = input("Opción: ")

    if opcion == "1":
        listar_productos()

    elif opcion == "2":
        buscar_producto()

    elif opcion == "3":
        agregar_producto()

    elif opcion == "4":
        actualizar_producto()

    elif opcion == "5":
        eliminar_producto()

    elif opcion == "0":
        break