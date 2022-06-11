<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
  <!-- Basic -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <!-- Mobile Metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <!-- Site Metas -->
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  
  
  
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styleIndex/css/bootstrap.css" />
 	<link href="https://fonts.googleapis.com/css?family=Poppins:400,600,700&display=swap" />	
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styleIndex/css/slick-theme.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styleIndex/css/font-awesome.min.css" />
  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styleIndex/css/style.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styleIndex/css/responsive.css" />
  
  	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/styleIndex/images/10.06.2022_23.18.44_REC.png" />
	
  <title>ENSAH</title>


</head>

<body>

  <section class="about_section layout_padding">
    <div class="container  ">
      <div class="row">
        <div class="col-md-6 ">
          <div class="img-box">
            <img src="${pageContext.request.contextPath}/resources/styleIndex/images/about-img.jpg" alt="">
          </div>
        </div>
        <div class="col-md-6">
          <div class="detail-box">
            <div class="heading_container">
              <h2>
                Bienvenue dans notre <span>Application Web de Gestion des Notes</span>
              </h2>
            </div>
            <p>
              Cette application a pour objectif de faciliter la saisie des notes, le calcul des 
moyennes et la gestion des délibérations en respectant les cahiers des charges 
de chaque filière.  </p>
            <a href="${pageContext.request.contextPath}/showMyLoginPage"> Se connecter            </a>
          </div>
        </div>
      </div>
    </div>
  </section>

 

  <!-- footer section -->
  <footer class="footer_section">
    <div class="container">
      <div class="footer_box">
        <p>
          &copy; <span id="displayYear"></span> All Rights Reserved 
        </p>
        <p>
Fatima Ezzahra ET-TOUIZI        </p>
      </div>
    </div>
  </footer>


	<script src="${pageContext.request.contextPath}/resources/styleIndex/js/jquery-3.4.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/styleIndex/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/styleIndex/js/custom.js"></script>
	


</body>

</html>