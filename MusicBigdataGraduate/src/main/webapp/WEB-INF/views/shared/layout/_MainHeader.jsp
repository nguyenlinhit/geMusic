<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ include file="../taglib.jsp"%>

<tilesx:useAttribute name="current" />

<!-- TOP-BAR -->
<div id="top-bar">
    <div class="container">
        <div id="site-description">Welcome to GEMusic site.</div>
        <ul id="top-links">
            <c:url var="uploadUrl" value="/playlist/upload" />
            <security:authorize access="! isAuthenticated()">
                <li class="login"><a href="" role="button" data-toggle="modal" data-target="#login-modal"><i class="fa fa-lock"></i><span>Login</span></a></li>
                <li class="register"><a href="<c:url value="/register"/>"><i class="fa fa-user"></i><span>Register</span></a></li>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <li class="show_user_box dropdown">
                    <a href="#" data-toggle="dropdown" class="link_user">
                        <c:if test="${not empty loginModel.imageUrl}">
                            <img class="avt" src="<c:url value="${loginModel.imageUrl}" />">
                        </c:if>
                        <c:if test="${empty loginModel.imageUrl}">
                            <img class="avt" src="<c:url value="/static/img/user/empty_avatar.jpg" />">
                        </c:if>
                        <span>${loginModel.fullname}</span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight">
                        <security:authorize access="hasRole('ADMIN')">
                            <li><a href="<c:url value="/admin/"/> ">Admin</a></li>
                        </security:authorize>
                        <li><a href="<c:url value="/user/${loginModel.username}"/> ">Profile</a></li>
                        <li><a href="#">Account</a></li>
                        <li><a href="javascript:logout()">Log out</a></li>
                    </ul>
                </li>
            </security:authorize>
            <li class="upload"><a href="" data-login="${loginModel == null ? 'false' : 'true'}"></a></li>
        </ul><!-- end top-links -->
    </div><!-- end container -->
</div> <!-- end top-bar -->

<header>
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Logo and toggle for navigation on mobile devices -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-nav">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value="/"/>"><img style="display:initial" src="<c:url value="/static/img/logo.png"/>" alt="GEMusic" /></a>
            </div>

            <!-- Navigation links -->
            <div class="collapse navbar-collapse" id="main-nav">
                <ul class="nav navbar-nav navbar-right">
                    <li class="${current == 'home' ? 'current' : ''}"><a href="<c:url value="/"/>">Home</a></li>
                    <li class="${current == 'song' ? 'current' : ''}"><a href="<c:url value="/song/"/>">Songs</a></li>
                    <li class="${current == 'album' ? 'current' : ''}"><a href="<c:url value="/playlist/"/>">Albums</a></li>
                    <li class="${current == 'top' ? 'current' : ''}"><a href="<c:url value="/top/"/>">Top 100</a></li>
                    <li class="${current == 'artist' ? 'current' : ''}"><a href="<c:url value="/artist/"/>">Artists</a></li>
                    <li class="${current == 'contact' ? 'current' : ''}"><a href="<c:url value="/contact/"/>">Contact Us</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div>
    </nav>
</header>