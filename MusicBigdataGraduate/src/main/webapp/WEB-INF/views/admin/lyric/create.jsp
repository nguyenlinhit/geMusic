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

<form:form id="lyricForm" method="POST" modelAttribute="lyric" class="form-horizontal">
    <form:hidden path="id" />

    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-8">
            <h2>Create a new Lyric</h2>
            <ol class="breadcrumb">
                <li><a href="<spring:url value="/admin/"/>">Home</a></li>
                <li><a href="<spring:url value="/admin/lyric/"/>">Lyric Management</a></li>
                <li class="active"><strong>Create Lyric</strong></li>
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
                        <li class="active"><a data-toggle="tab" href="#tab-1">Lyric Info</a></li>
                    </ul>

                    <div class="tab-content">

                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">

                                <fieldset class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Title</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="title" class="form-control" placeholder="Title" />
                                            <form:errors path="title" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Body</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="body" class="form-control" placeholder="Lyric content" />
                                            <form:errors path="body" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

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
        $("#lyricForm").validate({
            rules: {
                title: {
                    required: true
                },
                body: {
                    required: true
                }
            }
        });

    });
</script>