package com.tp8.tp8.repository;

import com.tp8.tp8.entity.Producto;


import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {

    private final List<Producto> productos = new ArrayList<>();

    public List<Producto> obtenerTodos() {
        return productos;
    }

    public void guardar(Producto producto) {
        productos.add(producto);
    }
}
