<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 10:11 AM
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
    <meta name="description" content="EProject Sem 4 - Softech Aptech, Da Nang">
    <meta name="author" content="HTFeeds">

    <title>
        <tiles:getAsString name="title" />
    </title>

    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>

    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/animate.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/style.css"/>">

    <script src="<c:url value="/static/js/jquery-2.1.1.js"/>"></script>
    <script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>

</head>

<body>

<div id="wrapper">

    <tiles:insertAttribute name="leftmenu" />

    <div id="page-wrapper" class="gray-bg">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="footer" />
    </div>

</div>

<%@ include file="../_LogoutFormPartial.jsp"%>

<c:forEach var="script" items="${javascripts}">
    <script src="<c:url value="${script}"/>"></script>
</c:forEach>

</body>

</html>