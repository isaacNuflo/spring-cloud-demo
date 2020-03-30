package com.inuflo.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inuflo.springboot.app.item.models.Item;
import com.inuflo.springboot.app.commons.models.entity.Producto;

@Service("restTemplate")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays
				.asList(clienteRest.getForObject("http://servicio-productos/productos", Producto[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long productoId, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("productoId", productoId.toString());
		Producto producto = clienteRest.getForObject("http://servicio-productos/productos/{productoId}", Producto.class,
				pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/productos", HttpMethod.POST,
				body, Producto.class);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long productoId) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("productoId", productoId.toString());
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/productos/{productoId}",
				HttpMethod.PUT, body, Producto.class, pathVariables);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public void delete(Long productoId) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("productoId", productoId.toString());
		clienteRest.delete("http://servicio-productos/productos/{productoId}", pathVariables);
	}

}
