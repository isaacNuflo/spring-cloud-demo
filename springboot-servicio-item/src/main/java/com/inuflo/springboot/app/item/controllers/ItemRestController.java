package com.inuflo.springboot.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inuflo.springboot.app.item.models.Item;
import com.inuflo.springboot.app.commons.models.entity.Producto;
import com.inuflo.springboot.app.item.models.service.ItemService;

//@RefreshScope
@RestController
public class ItemRestController {

	private static Logger log = LoggerFactory.getLogger(ItemRestController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;

	@Value("${configuracion.texto}")
	private String texto;

	@GetMapping("/items")
	public List<Item> listarItems() {
		return itemService.findAll();
	}

	// @HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/items/{productoId}/cantidad/{cantidad}")
	public Item obtenerItem(@PathVariable Long productoId, @PathVariable Integer cantidad) {
		return itemService.findById(productoId, cantidad);
	}

	@PostMapping("/items/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crearProducto(@RequestBody Producto producto) {
		return itemService.save(producto);
	}

	@PutMapping("/items/productos/{productoId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto actualizarProducto(@RequestBody Producto producto, @PathVariable Long productoId) {
		return itemService.update(producto, productoId);
	}

	@PutMapping("/items/productos/{productoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarProducto(@PathVariable Long productoId) {
		itemService.delete(productoId);
	}

	public Item metodoAlternativo(Long productoId, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();

		item.setCantidad(cantidad);
		producto.setId(productoId);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.0);
		item.setProducto(producto);
		return item;
	}

	@GetMapping("/obtener-config")
	private ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {
		log.info(texto);
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
}
