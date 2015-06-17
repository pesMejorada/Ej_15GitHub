<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="template/header.jsp" %>
 <h1>Loging</h1>
        <form action="${pageContext.request.contextPath}/Tienda/login" method="post">
            <label for="userName">Usuario:</label>
            <input type="text" name="userName" id="userName" value=""/> <br/>
            <label for="password">Contraseña:</label>
            <input type="password" name="password" id="password" value=""/><br/>
            <input type="submit" name="login" id="login" value="Login"/><br/>
        </form>
<!--  footer.jsp -->
<%@ include file="template/footer.jsp" %>