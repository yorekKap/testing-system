<%@ include file="/resources/jspf/jsp-setup.jspf" %>


<fmt:message key="profile-info.title" var="title"/>
<fmt:message key="profile-info.name" var="name"/>
<fmt:message key="profile-info.username" var="username"/>
<fmt:message key="profile-info.phone" var="phone"/>
<fmt:message key="profile-info.email" var="email"/>
<fmt:message key="profile-info.type" var="type"/>

<html>
<head>

    <%@ include file="/resources/jspf/setup.jspf" %>

    <title>${title}</title>

</head>
<body>
<div id="wrapper" class="toggled">

    <%@ include file="/resources/jspf/sidebar.jspf" %>

    <div id="profile-info">
        <div class="panel panel-default">
            <div class="panel-heading">${title}</div>
            <div class="panel-body">
                <div class="row">${name} : ${user.firstName} ${user.lastName}</div>
                <div class="row">${username} : ${user.username}</div>
                <div class="row">${phone} : ${user.phone}</div>
                <div class="row">${email} : ${user.email}</div>
                <div class="row">${type} : ${user.role}</div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
