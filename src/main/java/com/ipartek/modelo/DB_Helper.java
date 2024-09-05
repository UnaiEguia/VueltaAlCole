package com.ipartek.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.Producto;
import com.ipartek.modelo.dto.V_Producto;

public class DB_Helper implements I_Conexion, I_Metodos{
	
	@Override
	public Connection conectar() {

		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(CONEXION, USUARIO, PASS);

			System.out.println("BD conectada");

		} catch (ClassNotFoundException e) {
			System.out.println("ERROR DE BD");
			System.out.println("No se encontró el Driver");
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("ERROR DE BD");
			System.out.println("No se pudo conectar a la BD");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR DE BD");
			System.out.println("Error desconocido");
			System.out.println(e.getMessage());
		}

		return con;
	}
	
	@Override
	public void desconectar(Connection con) {

		try {
			con.close();
			System.out.println("BD desconectada");
		} catch (SQLException e) {
			System.out.println("ERROR DE BD");
			System.out.println("No se pudo desconectar de la BD");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR DE BD");
			System.out.println("Error desconocido");
			System.out.println(e.getMessage());
		}

	}
	
	@Override
	public List<V_Producto> obtenerTodosProductos(Connection con) {

		List<V_Producto> lista = new ArrayList<>();

		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_TODOS_PRODUCTOS);

			boolean tieneSelect = cStmt.execute();

			if (tieneSelect == true) {
				// carga la lista

				ResultSet rs = cStmt.getResultSet();

				while (rs.next()) {

					V_Producto prod = new V_Producto();

					// algo para rellenar el prod

					prod.setId(rs.getInt(V_PRODUCTOS_ID));
					prod.setNombre(rs.getString(V_PRODUCTOS_NOMBRE));
					prod.setPrecio(rs.getDouble(V_PRODUCTOS_PRECIO));
					prod.setFk_categoria(rs.getInt(V_PRODUCTOS_FK_CATEGORIA));
					prod.setCategoria(rs.getString(V_PRODUCTO_CATEGORIA));

					lista.add(prod);

				}

				System.out.println(lista);

				return lista;

			} else {
				// mostrar mensaje de error

				System.out.println("No se pudo obtener una lista de productos");
				System.out.println("El Stored Procedure no tiene RESULTSET");

				return new ArrayList<V_Producto>();
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener lista de todos los productos");
			System.out.println(e.getMessage());

			return new ArrayList<V_Producto>();
		}

	}

	@Override
	public List<V_Producto> obtenerProductosPorCategoria(Connection con, int fk_categ) {

		List<V_Producto> lista = new ArrayList<>();

		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_TODAS_CATEGORIAS);
			cStmt.setInt(1, fk_categ);

			boolean tieneSelect = cStmt.execute();

			if (tieneSelect == true) {
				// carga la lista

				ResultSet rs = cStmt.getResultSet();

				while (rs.next()) {

					V_Producto prod = new V_Producto();

					// algo para rellenar el prod

					prod.setId(rs.getInt(V_PRODUCTOS_ID));
					prod.setNombre(rs.getString(V_PRODUCTOS_NOMBRE));
					prod.setPrecio(rs.getDouble(V_PRODUCTOS_PRECIO));
					prod.setFk_categoria(rs.getInt(V_PRODUCTOS_FK_CATEGORIA));
					prod.setCategoria(rs.getString(V_PRODUCTO_CATEGORIA));

					lista.add(prod);

				}

				System.out.println(lista);

				return lista;

			} else {
				// mostrar mensaje de error

				System.out.println("No se pudo obtener una lista de productos");
				System.out.println("El Stored Procedure no tiene RESULTSET");

				return new ArrayList<V_Producto>();
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener lista de todos los productos");
			System.out.println(e.getMessage());

			return new ArrayList<V_Producto>();
		}

	}

	@Override
	public List<Categoria> obtenerTodasCategorias(Connection con) {

		List<Categoria> lista = new ArrayList<>();

		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_CATEGORIAS);

			boolean tieneSelect = cStmt.execute();

			if (tieneSelect == true) {
				// carga la lista

				ResultSet rs = cStmt.getResultSet();

				while (rs.next()) {

					Categoria cate = new Categoria();

					// algo para rellenar el prod

					cate.setId(rs.getInt(CATEGORIAS_ID));
					cate.setCategoria(rs.getString(CATEGORIAS_CATEGORIA));

					lista.add(cate);

				}

				System.out.println(lista);

				return lista;

			} else {
				// mostrar mensaje de error

				System.out.println("No se pudo obtener una lista de categorias");
				System.out.println("El Stored Procedure no tiene RESULTSET");

				return new ArrayList<Categoria>();
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener lista de todas las categorias");
			System.out.println(e.getMessage());

			return new ArrayList<Categoria>();
		}

	}

	@Override
	public int insertarProducto(Connection con, Producto prod) {

		try {
			CallableStatement cStmt = con.prepareCall(SP_INSERTAR_PRODUCTO);

			cStmt.setString(1, prod.getNombre());
			cStmt.setDouble(2, prod.getPrecio());
			cStmt.setInt(3, prod.getFk_categoria());

			return cStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("ERROR DE BD:INSERTAR");
			System.out.println("No se pudo insertar el producto");
			return 0;
		}
		
		

	}
	
	@Override
	public int borrarProducto(Connection con, int id) {

		try {
			CallableStatement cStmt = con.prepareCall(SP_BORRAR_PRODUCTO);

			cStmt.setInt(1, id);

			return cStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("ERROR DE BD:BORRAR");
			System.out.println("No se pudo borrar el producto");
			return 0;
		}

	}
	
	@Override
	public V_Producto obtenerProductoPorId(Connection con, int id) {

		V_Producto prod = new V_Producto();

		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_PRODUCTO_POR_ID);

			cStmt.setInt(1, id);

			boolean tieneSelect = cStmt.execute();

			if (tieneSelect == true) {
				// carga la lista

				ResultSet rs = cStmt.getResultSet();

				while (rs.next()) {

					// algo para rellenar el prod

					prod.setId(rs.getInt(V_PRODUCTOS_ID));
					prod.setNombre(rs.getString(V_PRODUCTOS_NOMBRE));
					prod.setPrecio(rs.getDouble(V_PRODUCTOS_PRECIO));
					prod.setFk_categoria(rs.getInt(V_PRODUCTOS_FK_CATEGORIA));
					prod.setCategoria(rs.getString(V_PRODUCTO_CATEGORIA));

				}

				System.out.println("Producto por ID obtenido");

				return prod;

			} else {
				// mostrar mensaje de error

				System.out.println("No se pudo obtener el producto por ID");
				System.out.println("El Stored Procedure no tiene RESULTSET");

				return new V_Producto();
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener lista de todos los productos");
			System.out.println(e.getMessage());

			return new V_Producto();
		}

	}
	
	@Override
	public int modificarProducto(Connection con, Producto prod) {

		try {
			CallableStatement cStmt = con.prepareCall(SP_MODIFICAR_PRODUCTO);

			cStmt.setInt(1, prod.getId());
			cStmt.setString(2, prod.getNombre());
			cStmt.setDouble(3, prod.getPrecio());
			cStmt.setInt(4, prod.getFk_categoria());

			System.out.println(prod);
			return cStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("ERROR DE BD:MODIFICAR");
			System.out.println("No se pudo insertar el producto");
			e.printStackTrace();

			return 0;

		}

	}
	
	@Override
	public int insertarCategoria(Connection con, Categoria cat) {

		try {
			CallableStatement cStmt = con.prepareCall(SP_INSERTAR_CATEGORIA);

			cStmt.setString(1, cat.getCategoria());

			return cStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("ERROR DE BD:INSERTAR");
			System.out.println("No se pudo insertar la categoria");
			return 0;
		}
		
		

	}
	
	@Override
	public int borrarCategoria(Connection con, int id) {

		try {
			CallableStatement cStmt = con.prepareCall(SP_BORRAR_CATEGORIA);

			cStmt.setInt(1, id);

			return cStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("ERROR DE BD:BORRAR");
			System.out.println("No se pudo borrar la categoria");
			return 0;
		}

	}
	
	@Override
	public Categoria obtenerCategoriaPorId(Connection con, int id) {

		Categoria cat = new Categoria();

		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_CATEGORIA_POR_ID);

			cStmt.setInt(1, id);

			boolean tieneSelect = cStmt.execute();

			if (tieneSelect == true) {
				// carga la lista

				ResultSet rs = cStmt.getResultSet();

				while (rs.next()) {

					// algo para rellenar la cat

					cat.setId(rs.getInt(CATEGORIAS_ID));
					cat.setCategoria(rs.getString(CATEGORIAS_CATEGORIA));

				}

				System.out.println("Categoria por ID obtenido");

				return cat;

			} else {
				// mostrar mensaje de error

				System.out.println("No se pudo obtener la categoria por ID");
				System.out.println("El Stored Procedure no tiene RESULTSET");

				return new Categoria();
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener lista de todos las categorias");
			System.out.println(e.getMessage());

			return new Categoria();
		}

	}
	
	@Override
	public int modificarCategoria(Connection con, Categoria cat) {

		try {
			CallableStatement cStmt = con.prepareCall(SP_MODIFICAR_CATEGORIA);

			cStmt.setInt(1, cat.getId());
			cStmt.setString(2, cat.getCategoria());

			System.out.println(cat);
			return cStmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("ERROR DE BD:MODIFICAR");
			System.out.println("No se pudo insertar la categoria");
			e.printStackTrace();

			return 0;

		}

	}
	
	

}
