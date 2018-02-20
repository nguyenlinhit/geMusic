
<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logout-form" style="display:none;">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<script>
    function logout() {
        $("#logout-form").submit();
    }
</script>