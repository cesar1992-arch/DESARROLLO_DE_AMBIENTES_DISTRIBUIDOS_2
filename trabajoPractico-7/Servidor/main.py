from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

app = FastAPI()

class Producto(BaseModel):
    id: int
    nombre: str
    precio: float

productos = []

@app.get("/")
def inicio():
    return {"mensaje": "Servidor funcionando"}

@app.get("/productos")
def obtener_productos():
    return productos

@app.post("/productos")
def crear_producto(producto: Producto):

    for p in productos:
        if p.id == producto.id:
            raise HTTPException(
                status_code=400,
                detail="ID existente"
            )

    productos.append(producto)

    return {
        "mensaje": "Producto agregado",
        "producto": producto
    }