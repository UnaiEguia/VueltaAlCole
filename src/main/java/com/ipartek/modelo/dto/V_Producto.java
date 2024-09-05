package com.ipartek.modelo.dto;
/**
 * <b>Clase V_Producto</b>
 * <p>Clase v_producto que referencia la tabla categorias en la BD</p>
 * 
 * @author Unai
 *
 */
public class V_Producto extends Producto{
	
	private String categoria;

	public V_Producto(int id, String nombre, double precio, int fk_categoria, String categoria) {
		super(id, nombre, precio, fk_categoria);
		this.categoria = categoria;
	}

	public V_Producto() {
		super();
		this.categoria = "";
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "V_Producto [categoria=" + categoria + "]";
	}

	
	
	

}
