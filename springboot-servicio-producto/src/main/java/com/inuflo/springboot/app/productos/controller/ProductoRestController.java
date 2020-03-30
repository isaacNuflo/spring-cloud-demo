package com.inuflo.springboot.app.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inuflo.springboot.app.commons.models.entity.Producto;
import com.inuflo.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoRestController {

	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IProductoService productoService;

	@GetMapping("/productos")
	public List<Producto> listarProductos() {
		return productoService.findAll().stream().map(p -> {
			p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}

	@GetMapping("/productos/{productoId}")
	public Producto obtenerProducto(@PathVariable Long productoId) {
		Producto producto = productoService.findById(productoId);
		producto.setPort(port);
//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return producto;
	}
	
	@PostMapping("/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crearProducto(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto actualizarProducto(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoBD = productoService.findById(id);
		productoBD.setNombre(producto.getNombre());
		productoBD.setPrecio(producto.getPrecio());
		return productoService.save(productoBD);
	}
	
	@DeleteMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarProducto(@PathVariable Long id) {
		productoService.deleteById(id);
	}
}
