package com.ipartek.menu;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Conexion;
import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.V_Producto;


@WebServlet("/MenuProductos")
public class MenuProductos extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       

    public MenuProductos() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		List<V_Producto>listaProductos = db.obtenerTodosProductos(con);
		List<Categoria>listaCategoria = db.obtenerTodasCategorias(con);
		
		db.desconectar(con);
		
		HttpSession session = request.getSession();
		session.setAttribute(S_PAGINA_ACTUAL, JSP_INDEX);
		
		
		
		request.setAttribute(ATR_LISTA_PRODUCTO, listaProductos);
		request.setAttribute(ATR_LISTA_CATEGORIA, listaCategoria);
		
		request.getRequestDispatcher(JSP_INDEX).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
