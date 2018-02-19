<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="icon" type="image/png" href="<c:url value="/static/favicon.png" />" />

    <title>Login | FMusic</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/font-awesome/css/font-awesome.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/plugins/iCheck/custom.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/animate.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/style.css"/>">

</head>

<body class="gray-bg">

<div class="loginColumns animated fadeInDown">
    <div class="row">

        <div class="col-md-6">
            <h2 class="font-bold">Welcome to FMusic</h2>
            <p>Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.</p>
            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</p>
            <p>When an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
            <p><small>It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</small></p>
        </div>
        <div class="col-md-6">
            <div class="ibox-content">
                <c:url var="loginUrl" value="/login" />
                <form class="m-t" role="form" action="${loginUrl}" method="post">
                    <c:if test="${param.error != null}">
                        <p class="text-danger">Invalid username and password.</p>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <p class="text-info">You have been logged out.</p>
                    </c:if>
                    <div class="form-group">
                        <input type="text" name="username" class="form-control" placeholder="Username" required>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Password" required>
                    </div>
                    <div class="form-group">
                        <div class="checkbox i-checks">
                            <label style="padding: 0">
                                <input type="checkbox" name="remember-me"><i></i> Remember me </label>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

                    <a href="#"> <small>Forgot password?</small>
                    </a>

                    <p class="text-muted text-center">
                        <small>Do not have an account?</small>
                    </p>
                    <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
                </form>
                <p class="m-t">
                    <small>Register from today to get more fun on FMusic with Free VIP.</small>
                </p>
            </div>
        </div>
    </div>
    <hr />
    <div class="row">
        <div class="col-md-12 text-right">
            <small>© 2015-2016</small>
        </div>
    </div>
</div>

<script src="<c:url value="/static/js/jquery-2.1.1.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/js/plugins/iCheck/icheck.min.js"/>"></script>
<script>
    $(document).ready(function(){
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>

</body>

</html>