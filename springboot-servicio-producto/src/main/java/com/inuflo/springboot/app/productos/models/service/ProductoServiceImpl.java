package com.inuflo.springboot.app.productos.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inuflo.springboot.app.productos.models.dao.ProductoDAO;
import com.inuflo.springboot.app.commons.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private ProductoDAO productoDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return productoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		Optional<Producto> optional = productoDAO.findById(id);

		if (!optional.isPresent()) {
			throw new RuntimeException("Producto con id - " + id + " no encontrado");
		}
		return optional.get();
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDAO.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDAO.deleteById(id);
	}

}
