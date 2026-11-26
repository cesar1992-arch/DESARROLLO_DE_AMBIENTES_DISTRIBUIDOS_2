import tkinter as tk
from tkinter import messagebox
from tkinter.scrolledtext import ScrolledText
import requests

URL = "http://127.0.0.1:8000"


def agregar_producto():
    try:
        datos = {
            "id": int(entry_id.get()),
            "nombre": entry_nombre.get(),
            "precio": float(entry_precio.get())
        }

        respuesta = requests.post(
            f"{URL}/productos",
            json=datos
        )

        resultado.delete("1.0", tk.END)
        resultado.insert(tk.END, str(respuesta.json()))

    except Exception as e:
        messagebox.showerror("Error", str(e))


def listar_productos():
    try:
        respuesta = requests.get(
            f"{URL}/productos"
        )

        resultado.delete("1.0", tk.END)
        resultado.insert(
            tk.END,
            str(respuesta.json())
        )

    except Exception as e:
        messagebox.showerror("Error", str(e))


# =====================
# VENTANA
# =====================

ventana = tk.Tk()
ventana.title("TP7 - Gestión de Productos")
ventana.geometry("600x500")

titulo = tk.Label(
    ventana,
    text="Gestión de Productos",
    font=("Arial", 16, "bold")
)

titulo.pack(pady=10)

# ID

tk.Label(
    ventana,
    text="ID:"
).pack()

entry_id = tk.Entry(
    ventana,
    width=30
)

entry_id.pack()

# Nombre

tk.Label(
    ventana,
    text="Nombre:"
).pack()

entry_nombre = tk.Entry(
    ventana,
    width=30
)

entry_nombre.pack()

# Precio

tk.Label(
    ventana,
    text="Precio:"
).pack()

entry_precio = tk.Entry(
    ventana,
    width=30
)

entry_precio.pack()

# Botones

btn_agregar = tk.Button(
    ventana,
    text="Agregar Producto",
    width=25,
    command=agregar_producto
)

btn_agregar.pack(pady=10)

btn_listar = tk.Button(
    ventana,
    text="Listar Productos",
    width=25,
    command=listar_productos
)

btn_listar.pack(pady=5)

# Área resultados

resultado = ScrolledText(
    ventana,
    width=70,
    height=15
)

resultado.pack(pady=15)

ventana.mainloop()