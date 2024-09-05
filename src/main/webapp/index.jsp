<%@page import="com.ipartek.modelo.dto.V_Producto"%>
<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Categoria"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
List<Categoria> listaCategoria= new ArrayList<Categoria>();
if(request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIA)!=null){
	listaCategoria=(List<Categoria>)request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIA);
}
%>

<%
List<V_Producto> listaProductos= new ArrayList<V_Producto>();
if(request.getAttribute(I_Conexion.ATR_LISTA_PRODUCTO) !=null) {
	listaProductos=(List<V_Producto>)request.getAttribute(I_Conexion.ATR_LISTA_PRODUCTO);	
}else {
	//TODO later
}
%> 
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Vuelta al Cole</title>
		<link rel="stylesheet" href ="styles/style.css">	
	</head>
	 
	 <header>
	 	<h1>VUELTA AL COLE</h1>
	 </header>
	 
	<%@ include file="includes/menu.jsp"%>
	
	<body>
		
		<form action="InsertarProducto" method="get">
			        <label for="p_nombre">Producto:</label><br>
			        <input type="text" id="p_nombre" name="p_nombre" required><br>
			        
			        <label for="p_precio">Precio (Euros):</label><br>
			        <input type="number" id="p_precio" name="p_precio" min="0" step="0.05" required><br>
			        
			        <label for="p_FK_categoria">Categoria:</label><br>
			        <select id="p_FK_categoria" name="p_FK_categoria" required>
			            <%for(Categoria elem:listaCategoria){ 

				        %>	
				            	<option value="<%=elem.getId()%>"> <%=elem.getCategoria()%></option> 
				        <% 
				        }
			            %> 	 
			           
			        </select><br><br>		        
			        
			        <input type="submit" value="Insertar producto"><br><br>
			    </form>	
			    
			    <form action="BorrarProducto" method="get">
						<table border="1">
							<thead>
								<th></th>
								<th>Producto</th>
								<th>Precio</th>
								<th>Categoria</th>
								<th>Opciones</th>
							</thead>
						
						<tbody>
					
					<%for(V_Producto elem: listaProductos) {%>
						<tr>
							<td>
							<input type="checkbox" value="<%=elem.getId()%>" name="p_id">
							</td>
			                <td><%=elem.getNombre()%></td>
			                <td><%=elem.getPrecio()%> Euros</td>
			                <td><%=elem.getCategoria()%></td>
			                <td>
			                	<a href="BorrarProducto?p_id=<%=elem.getId()%>">Borrar</a>
			                	<a href="FormModificar?p_id=<%=elem.getId()%>">Modificar</a>  
	
			                </td>
			            </tr> 
			            <%} %>
			            
			             
					</tbody>
			    </table><br>
		  
				<input type="submit" value="Borrar seleccionados">
			  </form>
	</body>
</html>