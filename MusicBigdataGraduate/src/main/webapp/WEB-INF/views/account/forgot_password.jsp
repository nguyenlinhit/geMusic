<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:03 AM
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

    <title>Forgot password | GEMusic</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/font-awesome/css/font-awesome.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/animate.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/style.css"/>">

</head>

<body class="gray-bg">

<div class="passwordBox animated fadeInDown">
    <div class="row">
        <div class="col-md-12">
            <div class="ibox-content">
                <h2 class="font-bold">Forgot password</h2>
                <p>Enter your email address and your password will be reset and emailed to you.</p>
                <div class="row">
                    <div class="col-lg-12">
                        <form class="m-t" role="form" action="index.html">
                            <div class="form-group">
                                <input type="email" class="form-control" placeholder="Email address" required="">
                            </div>
                            <button type="submit" class="btn btn-primary block full-width m-b">Send new password</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-md-12 text-right">
            <small>© 2017-2018</small>
        </div>
    </div>
</div>

</body>

</html>