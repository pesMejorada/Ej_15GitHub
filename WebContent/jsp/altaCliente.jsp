<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="../template/header.jsp" %>
	<form action="/Ej_15GitHub/Tienda/altaCliente" method="post" enctype="text/html"> 
	  <fieldset>
	  <legend style="font-size: 1.3em">ALTA TIENDA</legend>
	    <div class="texto">
	      <label for="nombres">NOMBRE</label>
	    </div>
	    <div class="cuadro">
	      <input type="text" name="nombres" id="nombres"  />
	    </div>
	    
	    <div class="texto">
	      <label for="apellidos">APELLIDO</label>
	    </div>
	    <div class="cuadro">
	      <input type="text" name="apellidos" id="apellidos" />
	    </div>
	    
	    <div class="texto">
	      <label for="dni">DNI:</label>
	    </div>
	    <div class="cuadro">
	      <input type="text"  id="dni" name="dni" />
	    </div>
	    <input type="hidden" name="id" id="id"/>
	   </fieldset>
	    
	    <div class="texto">
	     <input type="submit" name="enviar" id="enviar" value="Enviar" /> 
	     <input type="reset" name="reiniciar" id="reiniciar" value="Reiniciar" />
	    </div>
	  </form>
	 <%@ include file="../template/footer.jsp" %>