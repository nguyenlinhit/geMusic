<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/6/2018
  Time: 5:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../shared/taglib.jsp"%>

<style>
    .error {
        color: #cc5965;
        display: inline-block;
        padding-left: 5px;
    }
</style>

<form:form id="userForm" method="POST" modelAttribute="user" enctype="multipart/form-data" class="form-horizontal">
    <form:hidden path="id" />

    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-8">
            <h2>Create a new User</h2>
            <ol class="breadcrumb">
                <li><a href="<spring:url value="/admin/"/>">Home</a></li>
                <li><a href="<spring:url value="/admin/user/"/>">User Management</a></li>
                <li class="active"><strong>Create User</strong></li>
            </ol>
        </div>
        <div class="col-lg-4">
            <div class="title-action">
                <a href="<spring:url value="list"/>" class="btn btn-white">Go Back</a>
                <input type="submit" name="save" class="btn btn-primary" value="Create and Edit" />
                <input type="submit" name="save" class="btn btn-success" value="Create" />
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
                                        <label class="col-sm-2 control-label">Fullname</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="fullname" class="form-control" placeholder="Fullname" />
                                            <form:errors path="fullname" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Username</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="username" class="form-control" placeholder="Username" />
                                            <form:errors path="username" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Password</label>
                                        <div class="col-sm-10">
                                            <form:input type="password" path="password" class="form-control" placeholder="Password" />
                                            <form:errors path="password" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Email</label>
                                        <div class="col-sm-10">
                                            <form:input type="email" path="email" class="form-control" placeholder="Email" />
                                            <form:errors path="email" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Phone number</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="phoneNumber" class="form-control" placeholder="Phone number" />
                                            <form:errors path="phoneNumber" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group" id="birth_date">
                                        <label class="col-sm-2 control-label">Birthday</label>
                                        <div class="col-sm-10 input-group date" style="padding-right:15px;padding-left:15px;">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                            <form:input type="text" path="birthDate" class="form-control" />
                                        </div>
                                        <form:errors path="birthDate" cssClass="error col-md-10 col-md-offset-2" cssStyle="padding-left:20px" />
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Avatar</label>
                                        <div class="col-sm-10">
                                            <input type="file" id="image" name="image" class="form-control" accept="image/*"/>
                                            <span class="error"><c:out value="${imgError}" /></span>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Gender</label>
                                        <div class="col-sm-10">
                                            <div class="checkbox-inline i-checks">
                                                <label>
                                                    <form:radiobutton path="sex" value="Male" /> <i></i> Male </label>
                                            </div>
                                            <div class="checkbox-inline i-checks">
                                                <label>
                                                    <form:radiobutton path="sex" value="Female" /> <i></i> Female </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">State</label>
                                        <div class="col-lg-4">
                                            <form:select path="state" items="${states}" class="form-control" />
                                            <form:errors path="state" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Roles</label>
                                        <div class="col-lg-4">
                                            <form:select path="roles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control" />
                                            <form:errors path="roles" cssClass="error" />
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

</form:form>

<script>
    $(document).ready(function() {
        $('#birth_date .input-group.date').datepicker({
            startView: 1,
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            autoclose: true,
            format: "dd/mm/yyyy"
        });
        $("#userForm").validate({
            rules: {
                fullname: {
                    required: true,
                    minlength: 6,
                },
                username: {
                    required: true,
                    minlength: 6,
                    remote: {
                        url: "<spring:url value='/rest/available/username' />",
                        type: "get",
                        data: {
                            username: function() {
                                return $("#username").val();
                            },
                            id: function() {
                                return $("#id").val();
                            }
                        }
                    }
                },
                password: {
                    required: true,
                    minlength: 6
                },
                email: {
                    required: true,
                    email: true,
                    remote: {
                        url: "<spring:url value='/rest/available/email' />",
                        type: "get",
                        data: {
                            username: function() {
                                return $("#email").val();
                            },
                            id: function() {
                                return $("#id").val();
                            }
                        }
                    }
                },
                birthDate: {
                    required: false,
                },
                phoneNumber: {
                    required: false,
                    number: true,
                    minlength: 10,
                    maxlength: 12
                },
                state: {
                    required: true
                }
            }
        });

    });
</script>