<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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






	<div>
	<br> <br>
		<h3>Bienvenue dans l'Espace Professeur</h3> <br>
		<br>

		<s:authorize access="isAuthenticated()"><strong>Vous êtes connecté en tant que</strong> : <s:authentication property="principal.username" /> <br>
			<strong>Your Email</strong> : <s:authentication property="principal.email" /><br>
			<strong>Your First Name</strong> : <s:authentication property="principal.firstName" /><br>
			<strong>Your Last name </strong> : <s:authentication property="principal.LastName" /><br>
		</s:authorize>
	</div>



<div class="footer">
<jsp:include page="../fragments/userfooter.jsp" />
</div>