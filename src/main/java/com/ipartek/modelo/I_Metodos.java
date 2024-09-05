package com.ipartek.modelo;

import java.sql.Connection;
import java.util.List;

import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.Producto;
import com.ipartek.modelo.dto.V_Producto;

public interface I_Metodos {

	Connection conectar();

	void desconectar(Connection con);

	List<V_Producto> obtenerTodosProductos(Connection con);

	List<V_Producto> obtenerProductosPorCategoria(Connection con, int fk_categ);

	List<Categoria> obtenerTodasCategorias(Connection con);

	int insertarProducto(Connection con, Producto prod);

	int borrarProducto(Connection con, int id);

	V_Producto obtenerProductoPorId(Connection con, int id);

	int modificarProducto(Connection con, Producto prod);

	int insertarCategoria(Connection con, Categoria cat);

	int borrarCategoria(Connection con, int id);

	Categoria obtenerCategoriaPorId(Connection con, int id);

	int modificarCategoria(Connection con, Categoria cat);

}
