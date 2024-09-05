package com.ipartek.modelo;

public interface I_Conexion {
	
	// Constantes para la BD
	String BASE_DATOS = "vuelta_al_cole";
	String DRIVER = "com.mysql.jdbc.Driver";
	String CONEXION = "jdbc:mysql://localhost:3306/" + BASE_DATOS;
	String USUARIO = "root";
	String PASS = "1234";
	
	//Archivos JSP
	String JSP_INDEX = "index.jsp";
	String JSP_FORM_MODIFICAR ="form_modificar.jsp";
	String JSP_FORM_MODIFICAR_CAT ="form_modificar_cat.jsp";
	String JSP_CATEGORIA = "categoria.jsp";
	
	// Tablas de la BD

	String TABLA_PRODUCTOS = "productos";
	String PRODUCTOS_ID = "id";
	String PRODUCTOS_NOMBRE = "nombre";
	String PRODUCTOS_PRECIO = "precio";
	String PRODUCTOS_FK_CATEGORIA = "FK_categoria";

	String TABLA_CATEGORIAS = "categorias";
	String CATEGORIAS_ID = "id";
	String CATEGORIAS_CATEGORIA = "categoria";

	String VISTA_PRODUCTOS = "v_productos";
	String V_PRODUCTOS_ID = "id";
	String V_PRODUCTOS_NOMBRE = "nombre";
	String V_PRODUCTOS_PRECIO = "precio";
	String V_PRODUCTOS_FK_CATEGORIA = "FK_categoria";
	String V_PRODUCTO_CATEGORIA = "categoria";
	
	// stored procedures

	String SP_OBTENER_TODOS_PRODUCTOS = "call sp_obtener_todos_productos();";
	String SP_INSERTAR_PRODUCTO = "call sp_insertar_producto(?, ?, ?);";
	String SP_BORRAR_PRODUCTO="call sp_borrar_producto(?);";
	String SP_OBTENER_PRODUCTO_POR_ID = "call sp_obtener_productos_por_id(?);";
	String SP_MODIFICAR_PRODUCTO = "call sp_modificar_producto(?, ?, ?, ?);";
	
	String SP_OBTENER_TODAS_CATEGORIAS = "call sp_obtener_productos_por_FK(?);";
	String SP_OBTENER_CATEGORIAS = "call sp_obtener_todas_categorias();";
	String SP_INSERTAR_CATEGORIA="call sp_insertar_categoria(?);";
	String SP_BORRAR_CATEGORIA="call sp_borrar_categoria(?);";
	String SP_OBTENER_CATEGORIA_POR_ID = "call sp_obtener_categorias_por_id(?);";
	String SP_MODIFICAR_CATEGORIA = "call sp_modificar_categoria(?, ?);";
	
	//Atributos
	String ATR_LISTA_CATEGORIA = "atr_lista_categoria";
	String ATR_LISTA_PRODUCTO ="atr_lista_producto";
	String ATR_PRODUCTO = "atr_producto";
	String S_PAGINA_ACTUAL ="s_pagina_actual";
	
	
}
