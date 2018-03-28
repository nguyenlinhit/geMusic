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

<form:form id="songForm" method="POST" modelAttribute="song" enctype="multipart/form-data" class="form-horizontal">
    <form:hidden path="id" />

    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-8">
            <h2>Create a new Song</h2>
            <ol class="breadcrumb">
                <li><a href="<spring:url value="/admin/"/>">Home</a></li>
                <li><a href="<spring:url value="/admin/song/"/>">Song Management</a></li>
                <li class="active"><strong>Create Song</strong></li>
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
                        <li class="active"><a data-toggle="tab" href="#tab-1">Song Info</a></li>
                    </ul>

                    <div class="tab-content">

                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">

                                <fieldset class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Name</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="name" class="form-control" placeholder="Song name" />
                                            <form:errors path="name" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Artists</label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <form:select path="artists" data-placeholder="Choose a Artist..." class="chosen-select" style="width:555px;" tabindex="4" multiple="true">
                                                    <option value="">Select</option>
                                                    <form:options items="${artists}" itemLabel="name" itemValue="id" />
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Total Views</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" path="totalView" class="form-control" placeholder="Total Views" />
                                            <form:errors path="totalView" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Week Views</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" path="weekViews" class="form-control" placeholder="Week Views" />
                                            <form:errors path="weekViews" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Country</label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <form:select path="country" data-placeholder="Choose a Country..." class="chosen-select" style="width:350px;">
                                                    <option value=""></option>
                                                    <c:forEach var="country" items="${countries}">
                                                        <option value="${country}">${country}</option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Image</label>
                                        <div class="col-sm-10">
                                            <input type="file" id="image" name="image" class="form-control" accept="image/*"/>
                                            <c:if test="${imgError != null}">
                                                <span class="error"><c:out value="${imgError}" /></span>
                                            </c:if>
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

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Genre</label>
                                        <div class="col-lg-4">
                                            <form:select path="genre" class="form-control">
                                                <form:option label="Select Genre" value="" />
                                                <form:options items="${genres}" itemLabel="name" itemValue="id" />
                                            </form:select>
                                            <form:errors path="genre" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Type</label>
                                        <div class="col-lg-4">
                                            <form:select path="type" items="${songTypes}" class="form-control" />
                                            <form:errors path="type" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">File Url</label>
                                        <div class="col-sm-10">
                                            <div class="input-group m-b">
                                                <span class="input-group-addon"> <input name="resource" value="url" type="radio" checked> </span> <form:input type="text" path="url" class="form-control" placeholder="File Url" />
                                            </div>
                                            <form:errors path="url" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Upload to<br/><small class="text-navy">Application</small></label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"> <input name="resource" value="application" type="radio"> </span> <input type="file" id="appFile" name="appFile" class="form-control" accept="audio/*">
                                            </div>
                                            <c:if test="${appFileError != null}">
                                                <span class="error"><c:out value="${appFileError}" /></span>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Upload to<br/><small class="text-navy">Google Drive</small></label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"> <input name="resource" value="google" type="radio"> </span> <input type="file" id="gFile" name="gFile" class="form-control" accept="audio/*">
                                            </div>
                                            <c:if test="${gFileError != null}">
                                                <span class="error"><c:out value="${gFileError}" /></span>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"></label>
                                        <div class="col-lg-4">
                                            <div class="i-checks"><label> <input type="checkbox" name="isPublished"> <i></i> Published</label></div>
                                            <div class="i-checks"><label> <input type="checkbox" name="onHome"> <i></i> Show On Homepage </label></div>
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

        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
        $("#songForm").validate({
            rules: {
                name: {
                    required: true
                },
                totalView: {
                    required: false,
                    number: true
                },
                weekViews: {
                    required: false,
                    number: true
                }
            }
        });

    });
</script>