<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key = "error-page.title" var = "title"/>
<fmt:message key = "error-page.ohh" var = "ohh"/>
<fmt:message key = "error-page.page-not-here" var = "page_not_here"/>
<fmt:message key = "error-page.home" var = "home"/>


<!DOCTYPE HTML>
<html>
	<head>
		<title>${title}</title>
		<meta name="keywords" content="404 iphone web template, Andriod web template, Smartphone web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
		<link href="/resources/css/404-style.css" rel="stylesheet" type="text/css"  media="all" />
	</head>
	<body>
		<!--start-wrap--->
		<div class="wrap">
			<!---start-header---->
				<div class="header">
					<div class="logo">
						<h1><a href="#">404</a></h1>
					</div>
				</div>
			<!---End-header---->
			<!--start-content------>
			<div class="content">
				<img src="/resources/img/error-img.png" title="error" />
				<p><span>${ohh}</span>${page_not_here}</p>
				<a href="/home">${home}</a>
   			</div>
			<!--End-Cotent------>
		</div>
		<!--End-wrap--->
	</body>
</html>

