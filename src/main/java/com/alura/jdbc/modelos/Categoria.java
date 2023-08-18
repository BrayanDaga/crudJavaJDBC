package com.alura.jdbc.modelos;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private Integer id;
	private String nombre;
	private List<Producto> productos;

	public Categoria(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return this.id;
	}
	@Override
	public String toString() {
		return this.nombre;
	}

	public void agregarProducto(Producto producto) {
		if(this.productos ==null) {
			this.productos = new ArrayList<Producto>();
		}
		this.productos.add(producto);
	}

	public List<Producto> getProductos() {

		return this.productos;
	}
}
