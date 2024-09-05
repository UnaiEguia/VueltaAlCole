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

@WebServlet("/MenuCategoria")
public class MenuCategoria extends HttpServlet implements I_Conexion {
    private static final long serialVersionUID = 1L;
    private static final String CORRECT_PASSWORD = "1234";  // Contraseña requerida

    public MenuCategoria() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar si la contraseña ha sido validada
        HttpSession session = request.getSession();
        Boolean isVerified = (Boolean) session.getAttribute("passwordVerified");

        if (isVerified != null && isVerified) {
            // Si la contraseña ha sido verificada, procedemos a obtener las categorías
            DB_Helper db = new DB_Helper();
            Connection con = db.conectar();

            List<Categoria> listaCategoria = db.obtenerTodasCategorias(con);

            db.desconectar(con);

            session.setAttribute(S_PAGINA_ACTUAL, JSP_CATEGORIA);
            request.setAttribute(ATR_LISTA_CATEGORIA, listaCategoria);

            request.getRequestDispatcher(JSP_CATEGORIA).forward(request, response);
        } else {
            // Si no ha sido verificada, redirigir a la página principal o mostrar un error
            response.sendRedirect(JSP_INDEX);  // O cualquier otra página que prefieras
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Validar la contraseña
        String password = request.getParameter("password");

        if (CORRECT_PASSWORD.equals(password)) {
            // Si la contraseña es correcta, guardamos esta información en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("passwordVerified", true);
            //response.getWriter().write("success");  // Responder con éxito
            System.out.println("success");
        } else {
            //response.getWriter().write("error");  // Responder con error
            System.out.println("error");
        }
        
        doGet(request, response);
    }
}

