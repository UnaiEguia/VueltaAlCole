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


@WebServlet("/BorrarCategoria")
public class BorrarCategoria extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       

    public BorrarCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int[] id = {};
		if (request.getParameterValues("p_id") != null) {
			try {
				String[] p_idArrayString = request.getParameterValues("p_id");

				id = new int[p_idArrayString.length];

				for (int i = 0; i < p_idArrayString.length; i++) {
					id[i] = Integer.parseInt(p_idArrayString[i]);
				}
			} catch (NumberFormatException e) {

				id = new int[0];
			}
		}

		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();

		for (int i : id) {
			int resultadoBorrar = db.borrarCategoria(con, i);
			System.out.println("borró?" + resultadoBorrar);
		}

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
