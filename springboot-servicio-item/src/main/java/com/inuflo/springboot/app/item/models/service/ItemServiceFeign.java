package com.inuflo.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inuflo.springboot.app.item.cliente.ProductoClienteRest;
import com.inuflo.springboot.app.item.models.Item;
import com.inuflo.springboot.app.commons.models.entity.Producto;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {

	@Autowired
	ProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {
		return clienteFeign.listarProductos().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long productoId, Integer cantidad) {
		return new Item(clienteFeign.obtenerProducto(productoId), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return clienteFeign.crearProducto(producto);
	}

	@Override
	public Producto update(Producto producto, Long productoId) {
		// TODO Auto-generated method stub
		return clienteFeign.actualizarProducto(producto, productoId);
	}

	@Override
	public void delete(Long productoId) {
		clienteFeign.eliminarProducto(productoId);
	}

}
