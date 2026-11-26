package com.tp8.tp8.controller;

import com.tp8.tp8.entity.Producto;
import com.tp8.tp8.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> obtenerProductos() {
        return service.obtenerTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crearProducto(@RequestBody Producto producto) {
        return service.guardar(producto);
    }
}