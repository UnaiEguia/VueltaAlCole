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
import com.ipartek.modelo.dto.V_Producto;


@WebServlet("/FormModificar")
public class FormModificar extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       

    public FormModificar() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {int id=0;
	if(request.getParameter("p_id")!=null) {
		try {
			id=Integer.parseInt(request.getParameter("p_id"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			id=0;
		}
	}
	
	

	DB_Helper db= new DB_Helper();
	Connection con = db.conectar();
	
	V_Producto Producto = db.obtenerProductoPorId(con, id);
	List<Categoria>listaCategorias=db.obtenerTodasCategorias(con);
	
	db.desconectar(con);
	
	request.setAttribute(ATR_PRODUCTO, Producto);
	request.setAttribute(ATR_LISTA_CATEGORIA, listaCategorias);

	request.getRequestDispatcher(JSP_FORM_MODIFICAR).forward(request, response);}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
