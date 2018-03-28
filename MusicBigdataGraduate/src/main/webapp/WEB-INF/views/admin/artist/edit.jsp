<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:07 AM
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
        <h2>Edit Artist details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/artist/"/>">Artist Management</a></li>
            <li class="active"><strong>Artist edit</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list "/>" class="btn btn-white">Go Back</a>
            <a href="<spring:url value="details-${artist.id}"/>" class="btn btn-info">View Details</a>
            <a href="<spring:url value="delete-${artist.id}"/>" class="btn btn-danger delete-artist">Delete</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">Artist Info</a></li>
                </ul>

                <div class="tab-content">

                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <form:form id="editArtistForm" method="POST" modelAttribute="artist" class="form-horizontal">
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
                                        <label class="col-sm-2 control-label">Artist Name</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="name" class="form-control" placeholder="Artist Name" />
                                            <form:errors path="name" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Real Name</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="realName" class="form-control" placeholder="Real Name" />
                                            <form:errors path="realName" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Country</label>
                                        <div class="col-lg-4">
                                            <div class="input-group">
                                                <form:select path="country" data-placeholder="Choose a Country..." class="chosen-select" style="width:350px;">
                                                    <option value="${artist.country}">${artist.country}</option>
                                                    <c:forEach var="country" items="${countries}">
                                                        <option value="${country}">${country}</option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Career</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="career" class="form-control" placeholder="Career" />
                                            <form:errors path="career" cssClass="error" />
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
        //Chosen Select
        var config = {
            '.chosen-select'           : {},
            '.chosen-select-deselect'  : {allow_single_deselect:true},
            '.chosen-select-no-single' : {disable_search_threshold:10},
            '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
            '.chosen-select-width'     : {width:"95%"}
        }
        for (var selector in config) {
            $(selector).chosen(config[selector]);
        }

        //
        $('.delete-artist').click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            swal({
                    title: "Are you sure?",
                    text: "You will not be able to recover this artist!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, delete it!",
                    closeOnConfirm: false
                },
                function() {
                    $.get(href,function(){
                        swal("Deleted!", "Artist has been deleted.", "success");
                        window.location.href = "list";
                    }).fail(function(){
                        swal("Error", "Artist could not be deleted", "error");
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
        $("#editArtistForm").validate({
            rules: {
                name: {
                    required: true
                }
            }
        });

    });
</script>