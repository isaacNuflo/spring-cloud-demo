package com.inuflo.springboot.app.item.cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.inuflo.springboot.app.commons.models.entity.Producto;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
	@GetMapping("/productos")
	public List<Producto> listarProductos();

	@GetMapping("/productos/{productoId}")
	public Producto obtenerProducto(@PathVariable Long productoId);
	
	@PostMapping("/productos")
	public Producto crearProducto(@RequestBody Producto producto);
	
	@PutMapping("/productos/{productoId}")
	public Producto actualizarProducto(@RequestBody Producto producto, @PathVariable Long productoId);
	
	@DeleteMapping("/productos/{productoId}")
	public Producto eliminarProducto(@PathVariable Long productoId);
}
