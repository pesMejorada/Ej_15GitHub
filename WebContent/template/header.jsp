<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Tienda</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css"/>
</head>
<body>
<% 
	response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
%>
 <% HttpSession miSession = request.getSession();  %>
 <% if(miSession.getAttribute("userName")!=null){ %>
     <a href="${pageContext.request.contextPath}/Tienda/altaCliente">Alta Cliente</a>
     <a href="${pageContext.request.contextPath}/Tienda/listarTodos">Listar Todos</a>
     <a href="${pageContext.request.contextPath}/Tienda/buscarPorNombre">Buscar por nombre</a>
     <a href="${pageContext.request.contextPath}/Tienda/eliminarPorId">Eliminar Cliente por id</a>
     <a href="${pageContext.request.contextPath}/Tienda/logout">logOut</a>
      <p> Usuario: <%=miSession.getAttribute("userName") %></p>
      <p> ¡Hola!  <%=miSession.getAttribute("nombreCompleto") %></p> 
      <p> Máximo período de Inactividad: <%=miSession.getMaxInactiveInterval() %> segundos</p>
   <%}else
       { %>
     <a href="${pageContext.request.contextPath}/Tienda/login">login</a>
   <%} %>  
   
   















