<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="login.title" var="title"/>
<fmt:message key="login.username" var="username"/>
<fmt:message key="login.password" var="password"/>
<fmt:message key="login.login-button" var="logbutton"/>
<fmt:message key="login.to-registration" var="registration"/>
<fmt:message key="login.wrong-username" var="w_username"/>
<fmt:message key="login.wrong-password" var="wpassword"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login</title>
    <%@ include file="/resources/jspf/setup.jspf" %>
    <link rel="stylesheet" type="text/css" href="/resources/css/login-style.css"/>
    <script src="/resources/js/lib/jquery.form-error.js"></script>
    <script src="/resources/js/login.js"></script>
</head>
<body>
<a href="/login?lang=ua">ua</a> | <a href="/login?lang=en">en</a>

<div class="container-fluid">
    <div class="col-md-offset-3 col-md-6 form">
        <form id="contactform" class="form-horizontal" method="post">
            <h2 class="title">${title}</h2>

            <div class="form-group col-md-11">
                <label for="username">${username}</label>
                <input class="form-control" id="username" name="username" placeholder="${username}" required="true"
                       tabindex="4" type="text" value="${fusername}">
            </div>

            <div class="form-group col-md-11">
                <label for="password">${password}</label>
                <input class="form-control" type="password" class="pass" id="password" name="password" required="true"
                       placeholder="${password}" value="${fpassword}">
            </div>

            <div class="form-group">
                <div class="col-md-4">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>
        </form>
        <div class="row form-link">
            <div class="col-md-10">
                <a href="/register">${registration}</a>
            </div>
        </div>
    </div>
</div>

<c:if test="${fail eq 'wrong-username' || fail eq 'wrong-password'}">
    <p id="iusername">${w_username}</p>
    <p id="ipassword">${wpassword}</p>
</c:if>
</body>
<c:if test="${fail == 'wrong-username'}">
    <script type="text/javascript">
        invalid($("#username"), $("#iusername").text());
    </script>
</c:if>

<c:if test="${fail == 'wrong-password'}">
    <script type="text/javascript">
        invalid($("#password"), $("#ipassword").text());
    </script>
</c:if>
</html>