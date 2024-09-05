<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Categoria"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
List<Categoria> listaCategoria= new ArrayList<Categoria>();
if(request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIA)!=null){
	listaCategoria=(List<Categoria>)request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIA);
}
%>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Categoría</title>
		<link rel="stylesheet" href ="styles/style.css">
	</head>
	
	<body>
		<%@ include file="includes/menu.jsp"%>
			
		
		<form action="InsertarCategoria" method="get">
			<label for="p_categoria">Categoria:</label><br>
			<input type="text" id="p_categoria" name="p_categoria" required><br>
			
			<input type="submit" value="Insertar categoria"><br><br>
		</form>	
		
			    <form action="BorrarCategoria" method="get">
						<table border="1">
							<thead>
								<th></th>
								<th>Categoria</th>
								<th>Opciones</th>
							</thead>
						
						<tbody>
					
					<%for(Categoria elem: listaCategoria) {%>
						<tr>
							<td>
							<input type="checkbox" value="<%=elem.getId()%>" name="p_id">
							</td>
			                <td><%=elem.getCategoria()%></td>
			                <td>
			                	<a href="BorrarCategoria?p_id=<%=elem.getId()%>">Borrar</a>
			                	<a href="FormModificarCat?p_id=<%=elem.getId()%>">Modificar</a>  
	
			                </td>
			            </tr> 
			            <%} %>
			            
			             
					</tbody>
			    </table><br>
		  
				<input type="submit" value="Borrar seleccionados">
			  </form>		
		
	</body>
</html>