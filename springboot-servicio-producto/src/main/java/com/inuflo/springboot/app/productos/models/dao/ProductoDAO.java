package com.inuflo.springboot.app.productos.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inuflo.springboot.app.commons.models.entity.Producto;

public interface ProductoDAO extends JpaRepository<Producto, Long>{

}
