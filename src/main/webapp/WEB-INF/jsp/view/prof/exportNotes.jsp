<%@page import="com.gsnotes.web.models.PersonModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/profHeader.jsp" />


<div class="container">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">

			<jsp:include page="../fragments/profmenu.jsp" />

		</div>
	</nav>


<br> <br>
	<div>
		<h3>
			Télécharger le fichier des notes des étudiants 		
		</h3>
	</div>
	
	<div>

<br>


		<f:form action="${pageContext.request.contextPath}/prof/exportFileNote"
			method="POST" modelAttribute="moduleModel">


			<div class="row">

				<div class="col">
					<label>Choisissez le module : </label> <br>


					<f:select path = "idModule" multiple="false" size="1" class="form-control">
						<f:options items="${modulesList}" itemValue="idModule" itemLabel="titre" />
					</f:select>

<br>
				</div>
				<div class="row">

				<div class="col">
					<label>Choisissez la session : </label> <br>

					<f:select path="session" multiple="false" size="1" class="form-control">
						<f:options items="${sessions}"/>
					</f:select>


				</div>
			</div>
			</div> <br>


			<div style="text-align: right">
				<button type="submit" class="btn btn-primary">Générer le fichier Excel</button>
			</div>

		</f:form>
	</div>
	

	<div>


	<jsp:include page="../fragments/userfooter.jsp" />