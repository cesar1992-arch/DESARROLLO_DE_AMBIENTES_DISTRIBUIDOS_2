package com.tp8.tp8.service;

import com.tp8.tp8.entity.Producto;
import com.tp8.tp8.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;
    private final JdbcTemplate replicaJdbcTemplate;

    public ProductoService(
            ProductoRepository repository,
            @Qualifier("replicaJdbcTemplate") JdbcTemplate replicaJdbcTemplate) {
        this.repository = repository;
        this.replicaJdbcTemplate = replicaJdbcTemplate;
    }

    public List<Producto> obtenerTodos() {
        return replicaJdbcTemplate.query(
                "SELECT id, nombre, precio FROM productos ORDER BY id",
                (resultSet, rowNumber) -> new Producto(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getBigDecimal("precio")
                )
        );
    }

    public Producto guardar(Producto producto) {
        producto = new Producto(null, producto.getNombre(), producto.getPrecio());
        return repository.save(producto);
    }
}