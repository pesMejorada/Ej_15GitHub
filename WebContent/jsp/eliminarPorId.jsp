<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../template/header.jsp" %>
   <form method="post"  action="${pageContext.request.contextPath}/Tienda/eliminarPorId" name="eliminarPorId" >
    <fieldset>
     <label>Id:</label>
     <input type="text" name="id" id="id"/>
     <input type="submit" name="eliminar" value="Eliminar"/> 
     <input type="reset" name="reiniciar" value="Reiniciar"/>
     </fieldset>
     </form>
<%@ include file="../template/footer.jsp" %>