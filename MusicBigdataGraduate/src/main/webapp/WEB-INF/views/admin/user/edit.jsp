<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/6/2018
  Time: 5:24 PM
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
    .success {
        padding-top: 7px;
        margin-bottom: 0;
        color: #1ab394;
    }
    .failed {
        padding-top: 7px;
        margin-bottom: 0;
        color: #ed5565;
    }
</style>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-8">
        <h2>Edit User details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/user/"/>">User Management</a></li>
            <li class="active"><strong>User edit</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list "/>" class="btn btn-white">Go Back</a>
            <a href="<spring:url value="details-${user.id}"/>" class="btn btn-info">View Details</a>
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
                    <li class=""><a data-toggle="tab" href="#tab-2">Others</a></li>
                </ul>

                <div class="tab-content">

                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <form:form id="editUserForm" method="POST" modelAttribute="user" class="form-horizontal">
                                <form:hidden path="id" />
                                <form:hidden path="password" />
                                <form:hidden path="imageUrl" />

                                <fieldset class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-lg-8">
                                            <c:if test="${success != null}">
                                                <p class="success">
                                                    <c:out value="${success}" />
                                                </p>
                                            </c:if>
                                            <c:if test="${failed != null}">
                                                <p class="failed">
                                                    <c:out value="${failed}" />
                                                </p>
                                            </c:if>
                                        </label>
                                        <div class="col-lg-4" style="text-align: right">
                                            <input type="submit" name="save" class="btn btn-primary" value="Save and Continue" />
                                            <input type="submit" name="save" class="btn btn-success" value="Save" />
                                        </div>
                                    </div>

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
                                            <form:input type="text" path="username" readonly="true" class="form-control" placeholder="Username" />
                                            <form:errors path="username" cssClass="error" />
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
                            </form:form>

                        </div>
                    </div>


                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body">

                            <form:form id="avatarForm" action="update-avatar-${user.id}" method="POST" enctype="multipart/form-data" class="form-horizontal">
                                <fieldset class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">New Image</label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="file" id="newImage" name="newImage" class="form-control" accept="image/*"/>
                                                <span class="input-group-btn" style="vertical-align:top">
            										<input type="submit" class="btn btn-primary" value="Change avatar"/>
	            								</span>
                                                <!-- Validate -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                </fieldset>
                            </form:form>

                            <form:form id="passwordForm" action="update-password-${user.id}" method="POST" class="form-horizontal">
                                <fieldset>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">New Password</label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="text" id="newPassword" name="newPassword" placeholder="New password" class="form-control">
                                                <span class="input-group-btn" style="vertical-align:top">
													<input type="submit" class="btn btn-primary" value="Change password"/>
		            							</span>
                                                <!-- Validate -->
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </form:form>

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
        $('#birth_date .input-group.date').datepicker({
            startView: 1,
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            autoclose: true,
            format: "dd/mm/yyyy"
        });
        $("#editUserForm").validate({
            rules: {
                fullname: {
                    required: true,
                    minlength: 6,
                },
                email: {
                    required: true,
                    email: true
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

        $("#passwordForm").validate({
            rules: {
                newPassword: {
                    required: true,
                    minlength: 6
                }
            }
        });

        $("#avatarForm").validate({
            rules: {
                newImage: {
                    required: true,
                }
            }
        });

    });
</script>