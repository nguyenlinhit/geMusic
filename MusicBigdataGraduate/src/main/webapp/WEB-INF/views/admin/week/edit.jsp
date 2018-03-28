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
        <h2>Edit Week details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/week/"/>">Week Management</a></li>
            <li class="active"><strong>Week edit</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list "/>" class="btn btn-white">Go Back</a>
            <a href="<spring:url value="details-${week.id}"/>" class="btn btn-info">View Details</a>
            <a href="<spring:url value="delete-${week.id}"/>" class="btn btn-danger delete-week">Delete</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">Week Info</a></li>
                </ul>

                <div class="tab-content">

                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <form:form id="editWeekForm" method="POST" modelAttribute="week" class="form-horizontal">
                                <form:hidden path="id" />

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
                                        <label class="col-sm-2 control-label">Week No</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" path="no" class="form-control" placeholder="Week No." />
                                            <form:errors path="no" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Name</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="name" class="form-control" placeholder="Week Name" />
                                            <form:errors path="name" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group" id="range_select">
                                        <label class="col-sm-2 control-label">Range select</label>
                                        <div class="input-daterange input-group" id="datepicker" style="padding-left:15px;padding-right:15px;">
                                            <form:input type="text" path="startDate" class="input-sm form-control" />
                                            <span class="input-group-addon">to</span>
                                            <form:input type="text" path="endDate" class="input-sm form-control" />
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
                                    <div class="hr-line-dashed"></div>
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
        $('#range_select .input-daterange').datepicker({
            keyboardNavigation: false,
            forceParse: false,
            autoclose: true,
            format: "dd/mm/yyyy"
        });

        $('.delete-week').click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            swal({
                    title: "Are you sure?",
                    text: "You will not be able to recover this week!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, delete it!",
                    closeOnConfirm: false
                },
                function() {
                    $.get(href,function(){
                        swal("Deleted!", "Week has been deleted.", "success");
                        window.location.href = "list";
                    }).fail(function(){
                        swal("Error", "Week could not be deleted", "error");
                    });
                });
        });
        $("#editWeekForm").validate({
            rules: {
                name: {
                    required: true
                }
            }
        });

    });
</script>