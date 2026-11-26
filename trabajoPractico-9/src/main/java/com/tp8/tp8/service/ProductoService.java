package com.tp8.tp8.service;

import com.tp8.tp8.entity.Producto;
import com.tp8.tp8.repository.ProductoRepository;

import java.util.List;

public class ProductoService {

    private final ProductoRepository repository = new ProductoRepository();

    public List<Producto> obtenerTodos() {
        return repository.obtenerTodos();
    }

    public void guardar(Producto producto) {
        repository.guardar(producto);
    }
}