<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="register.title" var="title"/>
<fmt:message key="register.first-name" var="firstname"/>
<fmt:message key="register.last-name" var="lastname"/>
<fmt:message key="register.phone" var="phone"/>
<fmt:message key="register.email" var="email"/>
<fmt:message key="register.username" var="username"/>
<fmt:message key="register.password" var="password"/>
<fmt:message key="register.user-type" var="userType"/>
<fmt:message key="register.tutor-option" var="tutorOption"/>
<fmt:message key="register.student-option" var="studentOption"/>
<fmt:message key="register.repeat-password" var="repeatpass"/>
<fmt:message key="register.sign-up" var="signup"/>
<fmt:message key="register.to-login" var="tologin"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Register</title>
    <%@ include file="/resources/jspf/setup.jspf" %>
    <link rel="stylesheet" type="text/css" href="resources/css/login-style.css"/>

</head>
<body>
<a href="/register?lang=ua">ua</a> | <a href="/register?lang=en">en</a>
<div class="container-fluid">
    <div class="col-md-offset-3 col-md-6 form">
        <form id="contactform" class="form-horizontal" method="post">
            <h2 class="title">${title}</h2>

            <div class="form-group col-md-11">
                <label for="first-name">${firstname}</label>
                <input class="form-control" id="first-name" name="firstName"
                       placeholder="${firstname}" required="true"
                       tabindex="1" type="text" value="${user.firstName}">
            </div>

            <div class="form-group col-md-11">
                <label for="last-name">${lastname}</label>
                <input class="form-control" id="last-name" name="lastName" placeholder="${lastname}" required="true"
                       tabindex="2" type="text" value="${user.lastName}">
            </div>

            <div class="form-group col-md-11">
                <label for="phone">${phone}</label>
                <input class="form-control" id="phone" name="phone" placeholder="380XXXXXXXXX" required="true"
                       tabindex="3" type="tel" pattern="380[0-9]{9}" value="${user.phone }">
            </div>

            <div class="form-group col-md-11">
                <label for="email">${email}</label>
                <input class="form-control" type="email" id="email" name="email" required="true"
                       value="${user.email}">
            </div>

            <div class="form-group col-md-11">
                <label for="username">${username}</label>
                <input class="form-control" id="username" name="username" placeholder="${username}" required="true"
                       tabindex="4" type="text" value="${user.username}">
            </div>

            <div class="form-group col-md-11">
                <label for="password">${password}</label>
                <input class="form-control" type="password" class="pass" id="password" name="password" required="true"
                placeholder="${password}" value="${user.password}">
            </div>

            <div class="form-group col-md-11">
                <label for="password">${repeatpass}</label>
                <input class="form-control" type="password" class="pass" id="repassword" name="repassword" required=""
                       placeholder="${repeatpass}" value="${user.password}">
            </div>

            <div class="form-group col-md-11">
                <label for="user-type">${userType}</label>
                <select class="form-control" id="user-type" name="userRole">
                    <option value="TUTOR">${tutorOption}</option>
                    <option value="STUDENT">${studentOption}</option>
                </select>
            </div>
            <div class="form-group">
                <div class="col-md-4">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>        </form>
        <div class="row form-link">
            <div class="col-md-10">
                <a href="/login">${tologin}</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
