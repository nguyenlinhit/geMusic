<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../shared/taglib.jsp"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="icon" type="image/png" href="<c:url value="/static/favicon.png" />" />

    <title>Register Successful | GEMusic</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/font-awesome/css/font-awesome.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/animate.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/style.css"/>">

</head>

<body class="gray-bg">


<div class="container loginscreen animated fadeInDown" style="padding-top:40px">
    <div>
        <h5 class="logo-name" style="font-size:100px">GEMusic</h5>
    </div>
    <div>
        <h3>Registration Successful</h3>
        <p>Congratulations your event registration has been successful.</p>
        <p>An email has been sent to your address confirming your participation.</p>
        <p>If you do not receive the email please check your spam filter or contact us.</p>
        <a class="btn btn-primary" href="<c:url value="/"/>">Comeback to Homepage</a>
    </div>
</div>

</body>

</html>