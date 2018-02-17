<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="taglib.jsp"%>

<security:authorize access="isAuthenticated()">
    <li class="show_user_box dropdown">
        <a href="#" data-toggle="dropdown" class="link_user">
            <jsp:useBean id="loginModel" scope="request" type="vn.edu.tdmu.models.Playlist"/>
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
