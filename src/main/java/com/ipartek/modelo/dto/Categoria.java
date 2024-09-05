package com.ipartek.modelo.dto;

/**
 * <b>Clase Categoria</b>
 * <p>Clase categoria que referencia la tabla categorias en la BD</p>
 * 
 * @author Unai
 *
 */

public class Categoria {
	/**
	 * Atributo ID:
	 * <p>A LA HORA DE INSERTA EN LA BASE DE DATO AUTONUMERA</p>
	 */
	private int id;
	/**
	 * Atributo categoria:
	 * varchar (45) en la BD
	 */
	private String categoria;

	public Categoria(int id, String categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
	}

	public Categoria() {
		super();
		this.id = 0;
		this.categoria = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", categoria=" + categoria + "]";
	}
	
	
	
}
