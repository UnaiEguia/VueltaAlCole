<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Categoria"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<%
Categoria categoria= new Categoria();
if(request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIA) !=null) {
	categoria=(Categoria)request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIA);	
}else {
	//TODO later
}
%>  

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Modificazzione</title>
	</head>
	
	<body>
		
		<main>
	
				<section>
					<form action="ModificarCategoria" method="post" >
				        <label for="p_id">Id:</label>
				        <input type="text" id="p_id" name="p_id" readonly value="<%=categoria.getId()%>"><br>
				        
				         <label for="p_categoria">Producto:</label><br>
				        <input type="text" id="p_categoria" name="p_categoria" required value="<%=categoria.getCategoria()%>"><br>
				        
				        
				        <input type="submit" value="Modificar producto">
				    </form>	
				</section>
			
		</main>
	</body>
</html>