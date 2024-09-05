package com.ipartek.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Conexion;
import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.Producto;
import com.ipartek.modelo.dto.V_Producto;


@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;

    public ModificarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = 0;
		if (request.getParameter("p_id") != null) {
			try {
				id = Integer.parseInt(request.getParameter("p_id"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				id = 0;
			}
		}

		String nombre = "";
		if (request.getParameter("p_nombre") != null) {
			nombre = request.getParameter("p_nombre");
			if (nombre.length() > 45) {
				nombre = nombre.substring(0, 45);
			}
		}

		double precio = 0.0;
		if (request.getParameter("p_precio") != null) {
			try {
				precio = Double.parseDouble(request.getParameter("p_precio"));
			} catch (NumberFormatException e) {
				precio = 0.0;
			}
		}

		int fk_categoria = 0;
		if (request.getParameter("p_FK_categoria") != null) {
			try {
				fk_categoria = Integer.parseInt(request.getParameter("p_FK_categoria"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				fk_categoria = 0;
			}
		}
		
		Producto prod = new Producto(id, nombre, precio, fk_categoria);

		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();

		int resultadoModificar = db.modificarProducto(con, prod);
		System.out.println("modif?" + resultadoModificar);

		List<V_Producto> listaProductos = db.obtenerTodosProductos(con);
		List<Categoria> listaCategoria = db.obtenerTodasCategorias(con);

		db.desconectar(con);

		request.setAttribute(ATR_LISTA_PRODUCTO, listaProductos);
		request.setAttribute(ATR_LISTA_CATEGORIA, listaCategoria);

		request.getRequestDispatcher(JSP_INDEX).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
