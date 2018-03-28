<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:08 AM
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

<form:form id="roleForm" method="POST" modelAttribute="role" class="form-horizontal">
    <form:hidden path="id" />

    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-8">
            <h2>Create a new Role</h2>
            <ol class="breadcrumb">
                <li><a href="<spring:url value="/admin/"/>">Home</a></li>
                <li><a href="<spring:url value="/admin/role/"/>">Role Management</a></li>
                <li class="active"><strong>Create Role</strong></li>
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
                        <li class="active"><a data-toggle="tab" href="#tab-1">Role Info</a></li>
                    </ul>

                    <div class="tab-content">

                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">

                                <fieldset class="form-horizontal">

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Type</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="type" class="form-control" placeholder="Role name" />
                                            <form:errors path="type" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Description</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="description" class="form-control" placeholder="Description" />
                                            <form:errors path="description" cssClass="error" />
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
        $("#roleForm").validate({
            rules: {
                type: {
                    required: true,
                    maxlength: 15
                },
                description: {
                    required: false
                }
            }
        });

    });
</script>