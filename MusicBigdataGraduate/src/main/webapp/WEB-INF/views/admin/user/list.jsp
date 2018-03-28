<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/6/2018
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../shared/taglib.jsp"%>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>User Management</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/user/"/>">User Management</a></li>
            <li class="active"><strong>List</strong></li>
        </ol>
    </div>
    <div class="col-lg-2">
        <div class="title-action">
            <a href="<c:url value='create' />" class="btn btn-primary ">Add a new User</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-content">

                    <input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="Search for User">

                    <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15" data-filter=#filter>
                        <thead>
                        <tr>

                            <th>ID</th>
                            <th data-hide="phone">Username</th>
                            <th data-hide="phone">Fullname</th>
                            <th data-hide="phone">Email</th>
                            <th data-hide="phone">Date added</th>
                            <th data-hide="phone">Status</th>
                            <th data-hide="phone,tablet">Phone number</th>
                            <th data-hide="phone,tablet">Date modified</th>
                            <th class="text-right">Action</th>

                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>
                                    <c:out value="${user.username}" />
                                </td>
                                <td>
                                    <c:out value="${user.fullname}" />
                                </td>
                                <td>
                                    <c:out value="${user.email}" />
                                </td>
                                <td>
                                    <fmt:formatDate type="date" value="${user.creationTime}" />
                                </td>
                                <td>
                                    <c:if test="${user.state eq 'Active'}">
                                        <span class="label label-primary">${user.state}</span>
                                    </c:if>
                                    <c:if test="${user.state eq 'Inactive'}">
                                        <span class="label label-info">${user.state}</span>
                                    </c:if>
                                    <c:if test="${user.state eq 'Deleted'}">
                                        <span class="label label-danger">${user.state}</span>
                                    </c:if>
                                    <c:if test="${user.state eq 'Locked'}">
                                        <span class="label label-warning">${user.state}</span>
                                    </c:if>
                                </td>
                                <td>
                                    <c:out value="${user.phoneNumber}" />
                                </td>
                                <td>
                                    <fmt:formatDate type="date" value="${user.modificationTime}" />
                                </td>
                                <td class="text-right">
                                    <div class="btn-group">
                                        <a href="<c:url value='details-${user.id}' />" class="btn-white btn btn-xs">View</a> <a href="<c:url value='edit-${user.id}' />" class="btn-white btn btn-xs">Edit</a> <a href="<c:url value='delete-${user.id}' />" class="btn-white btn btn-xs delete-user">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="7">
                                <ul class="pagination pull-right"></ul>
                            </td>
                        </tr>
                        </tfoot>
                    </table>

                </div>
            </div>
        </div>
    </div>


</div>

<script>
    $(document).ready(function() {
        $('.footable').footable();
        $('.delete-user').click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            swal({
                title : "Are you sure?",
                text : "You will not be able to recover this user!",
                type : "warning",
                showCancelButton : true,
                confirmButtonColor : "#DD6B55",
                confirmButtonText : "Yes, delete it!",
                closeOnConfirm : false
            }, function() {
                $.get(href, function() {
                    swal("Deleted!", "User has been deleted.", "success");
                    window.location.href = "list";
                }).fail(function() {
                    swal("Error", "User could not be deleted", "error");
                });
            });
        });
    });
</script>