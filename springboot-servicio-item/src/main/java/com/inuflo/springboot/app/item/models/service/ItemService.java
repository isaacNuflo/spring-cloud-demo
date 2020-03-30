package com.inuflo.springboot.app.item.models.service;

import java.util.List;

import com.inuflo.springboot.app.commons.models.entity.Producto;
import com.inuflo.springboot.app.item.models.Item;

public interface ItemService {
	public List<Item> findAll();
	public Item findById(Long productoId, Integer cantidad);
	public Producto save(Producto producto);
	public Producto update(Producto producto, Long productoId);
	public void delete(Long productoId);
}
