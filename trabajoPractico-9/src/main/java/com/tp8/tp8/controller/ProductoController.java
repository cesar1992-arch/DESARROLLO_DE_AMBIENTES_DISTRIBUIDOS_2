package com.tp8.tp8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    @GetMapping("/productos")
    public String obtenerProductos() {
        return "Listado de productos";
    }
}