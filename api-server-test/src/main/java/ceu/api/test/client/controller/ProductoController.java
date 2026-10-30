package ceu.api.test.client.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.api.test.client.model.Producto;

@RequestMapping("/producto")
@RestController
public class ProductoController {

	@GetMapping("/{codBarra}")
	public Producto getProductoByCodBarras(@PathVariable String codBarra) {
		Producto p = new Producto();
		p.setCodBarra(codBarra);
		p.setDescripcion("Galletas Oreo");
		p.setPrecio(BigDecimal.valueOf(32.42));
		p.setFechaCaducidad(LocalDate.of(2027, 2, 21));

		return p;
	}
	
	@GetMapping
	public Producto getProductoByCodBarras2(@RequestParam String codBarra) {
		Producto p = new Producto();
		p.setCodBarra(codBarra);
		p.setDescripcion("Galletas Oreo");
		p.setPrecio(BigDecimal.valueOf(32.42));
		p.setFechaCaducidad(LocalDate.of(2027, 2, 21));
		
		return p;
	}
	
	@PostMapping
	public Producto crearProducto(@RequestBody Producto pr) {
		pr.setCodBarra("111");
		System.out.println("Insertando producto... " + pr);
		
		return pr;
		
	}

}
