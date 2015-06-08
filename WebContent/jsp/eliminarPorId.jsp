<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Eliminar por id:</title>
<link rel="stylesheet" href="../css/estilos.css"/>
</head>
<body>
   <form method="post"  action="${pageContext.request.contextPath}/Tienda/eliminarPorId" name="eliminarPorId" >
    <fieldset>
     <label>Id:</label>
     <input type="text" name="id" id="id"/>
     <input type="submit" name="eliminar" value="Eliminar"/> 
     <input type="reset" name="reiniciar" value="Reiniciar"/>
     </fieldset>
     </form>

</body>
</html>