<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../taglib.jsp"%>

<tiles:importAttribute name="stylesheets" />
<tiles:importAttribute name="javascripts" />

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="icon" type="image/png" href="<c:url value="/static/favicon.png" />" />

    <title>
        <tiles:getAsString name="title" />
    </title>

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Dosis:400,500,600,700' rel='stylesheet' type='text/css' />

    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}" />">
    </c:forEach>

    <script src="<c:url value="/static/js/jquery-2.1.1.js"/>"></script>
    <script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>

</head>

<body class="<tiles:getAsString name="bodyClass" />">

<tiles:insertAttribute name="header" />

<tiles:insertAttribute name="body" />

<tiles:insertAttribute name="footer" />

<div id="authentication">
    <security:authorize access="! isAuthenticated()">
        <%@ include file="../_LoginFormPartial.jsp"%>
    </security:authorize>

    <security:authorize access="isAuthenticated()">
        <%@ include file="../_LogoutFormPartial.jsp"%>
    </security:authorize>
</div>

<c:forEach var="script" items="${javascripts}">
    <script src="<c:url value="${script}"/>"></script>
</c:forEach>

</body>

</html>