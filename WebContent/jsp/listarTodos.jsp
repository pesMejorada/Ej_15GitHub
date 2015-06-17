<%@page import="es.curso.model.entity.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../template/header.jsp" %>
	<script type="text/javascript">
	  function enviar(boton){
		 
		   var formulario= document.getElementById("formulario" +boton.name.substring(3));
		   // alterar el action según el botón pulsado
		    if(boton.value=="Actualizar")
		    	formulario.action="${pageContext.request.contextPath}/Tienda/actualizar";
		    if(boton.value=="Eliminar")
		    		formulario.action=
		    			"${pageContext.request.contextPath}/Tienda/eliminarPorId";
		    formulario.submit();	
	  }
	</script>
      <h1><%= request.getAttribute("titulo") %>    </h1>
     <!--  tabla html   dinámicamente -->
         
     <span><%=LocalDateTime.now() %></span>
     <span> Valor enviado desde el servlet Tienda  IVA:</span>
     <span> <%=request.getAttribute("iva") %>   </span>
     <%  ArrayList<Cliente> clientes= (ArrayList<Cliente>) request.getAttribute("clientes"); %>
     
     <table>
        <tr>
           <th>ID</th>
           <th>Nombres</th>
           <th>Apellidos</th>
           <th>DNI</th>
           <th></th>
           
          </tr>
        <%for(Cliente c: clientes){ %>  
          <form id ="formulario<%= c.getId()%>" action="#"  method="post" onsubmit="return false;">
          <tr id="<%= c.getId()%>">
             <td><input type="text"  name="id" value ="<%= c.getId() %>" /></td>
             <td><input type="text" name="nombres" value="<%= c.getNombres() %>"  /></td>
             <td><input type="text" name="apellidos" value="<%= c.getApellidos() %>" /></td>
             <td><input type="text" name="dni" value="<%= c.getDni() %>"/></td>
               <td><input class="btnSinBordes" 
	               type="submit" value="Eliminar"
	                 name="btn<%= c.getId()%>" onclick="enviar(this);"/> 
               </td>
               <td><input class="btnSinBordes" 
                   type="submit" value="Actualizar"
                     name="btn<%= c.getId()%>" onclick="enviar(this);"/> 
               </td>
              
          </tr>
        </form>
       
        <% } %>
     </table>
     <%@ include file="../template/footer.jsp" %>




