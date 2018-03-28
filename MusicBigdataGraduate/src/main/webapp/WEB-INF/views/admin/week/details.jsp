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
        <h2>Week Details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/week/"/>">Week Management</a></li>
            <li class="active"><strong>Week details</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list"/>" class="btn btn-white">Go back</a>
            <a href="<spring:url value="edit-${week.id}"/>" class="btn btn-warning">Edit</a>
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

                            <fieldset class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Id</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${week.id}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Week No.</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${week.no}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Name</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${week.name}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Start Date</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="date" value="${week.startDate}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">End Date</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="date" value="${week.endDate}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Description</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${week.description}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Date added</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="both" value="${week.creationTime}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Date modified</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="both" value="${week.modificationTime}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Created By</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${week.createdByUser}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Modified By</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${week.modifiedByUser}" />
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
    });
</script>