<%@page import="com.ipartek.modelo.dto.V_Producto"%>
<%@page import="com.ipartek.modelo.dto.Categoria"%>
<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Producto"%>
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
V_Producto producto= new V_Producto();
if(request.getAttribute(I_Conexion.ATR_PRODUCTO) !=null) {
	producto=(V_Producto)request.getAttribute(I_Conexion.ATR_PRODUCTO);	
}else {
	//TODO later
}
%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Modificazzione</title>
	</head>
	
	<body>
		
		<main>
	
				<section>
					<form action="ModificarProducto" method="post" >
				        <label for="p_id">Id:</label>
				        <input type="text" id="p_id" name="p_id" readonly value="<%=producto.getId()%>"><br>
				        
				        <label for="p_nombre">Producto:</label><br>
				        <input type="text" id="p_nombre" name="p_nombre" required value="<%=producto.getNombre()%>"><br>
				        
				        <label for="p_precio">Precio (Euros):</label><br>
				        <input type="number" id="p_precio" name="p_precio" min="0" step="0.05" required value="<%=producto.getPrecio()%>"><br>
				        
				        <label for="p_FK_categoria">Categoria:</label><br>
				        <select id="p_FK_categoria" name="p_FK_categoria" required >
				            <%for(Categoria elem:listaCategoria){ 
				            	if(elem.getId()!=producto.getFk_categoria()){
						     %>	
					           <option value="<%=elem.getId()%>"> <%=elem.getCategoria()%></option> 
				            <% 	
				            }else{
				            %>	 	
					            
					            
					            <option value="<%=elem.getId()%>" selected> <%=elem.getCategoria()%></option> 
					        <% 
					        }
				            } %> 	 
				           
				        </select><br>
				        
				        
				        <input type="submit" value="Modificar producto">
				    </form>	
				</section>
			
		</main>
	</body>
</html>