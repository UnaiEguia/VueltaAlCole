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


@WebServlet("/ModificarCategoria")
public class ModificarCategoria extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       

    public ModificarCategoria() {
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

		String categoria = "";
		if (request.getParameter("p_categoria") != null) {
			categoria = request.getParameter("p_categoria");
			if (categoria.length() > 45) {
				categoria = categoria.substring(0, 45);
			}
		}

		
		Categoria cat = new Categoria (id, categoria);

		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();

		int resultadoModificar = db.modificarCategoria(con, cat);
		System.out.println("modif?" + resultadoModificar);

		List<Categoria> listaCategoria = db.obtenerTodasCategorias(con);

		db.desconectar(con);

		request.setAttribute(ATR_LISTA_CATEGORIA, listaCategoria);

		request.getRequestDispatcher(JSP_CATEGORIA).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
