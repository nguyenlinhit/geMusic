<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/6/2018
  Time: 5:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../shared/taglib.jsp"%>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-8">
        <h2>User Details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/user/"/>">User Management</a></li>
            <li class="active"><strong>User details</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list"/>" class="btn btn-white">Go back</a>
            <a href="<spring:url value="edit-${user.id}"/>" class="btn btn-warning">Edit</a>
            <a href="<spring:url value="delete-${user.id}"/>" class="btn btn-danger delete-user">Delete</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">User Info</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <fieldset class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Id</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${user.id}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Fullname</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${user.fullname}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Username</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${user.username}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Password</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${user.password}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Email</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${user.email}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Phone Number</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${user.phoneNumber}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Birthday</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="date" value="${user.birthDate}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Gender</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${user.sex}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">ImageUrl</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${user.imageUrl}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">State</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${user.state}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Roles</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:forEach items="${user.roles}" var="role" varStatus="loop">
                                                <c:out value="${role.type}" />
                                                <c:if test="${loop.index lt fn:length(user.roles) - 1}">
                                                    <c:out value="," />
                                                </c:if>
                                            </c:forEach>
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Date added</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="both" value="${user.creationTime}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Date modified</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="both" value="${user.modificationTime}" />
                                        </p>
                                    </div>
                                </div>

                            </fieldset>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
        $('.delete-user').click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            swal({
                    title: "Are you sure?",
                    text: "You will not be able to recover this user!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, delete it!",
                    closeOnConfirm: false
                },
                function() {
                    $.get(href,function(){
                        swal("Deleted!", "User has been deleted.", "success");
                        window.location.href = "list";
                    }).fail(function(){
                        swal("Error", "User could not be deleted", "error");
                    });
                });
        });
    });
</script>