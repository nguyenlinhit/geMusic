<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/6/2018
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../shared/taglib.jsp"%>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-8">
        <h2>Playlist Details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/playlist/"/>">Playlist Management</a></li>
            <li class="active"><strong>Playlist details</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list"/>" class="btn btn-white">Go back</a>
            <a href="<spring:url value="edit-${playlist.id}"/>" class="btn btn-warning">Edit</a>
            <a href="<spring:url value="delete-${playlist.id}"/>" class="btn btn-danger delete-playlist">Delete</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">Playlist Info</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <fieldset class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Id</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.id}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Name</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.name}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Total Views</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatNumber type="number" value="${playlist.totalViews}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Week Views</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.weekViews}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Country</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.country}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Image Url</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.imageUrl}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Slide Image Url</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.slideImageUrl}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Artist</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.artist.name}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Genre</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.genre.name}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Type</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.type}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Week</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.week.name}" />
                                            (<fmt:formatDate pattern="dd-MM-yyyy" value="${playlist.week.startDate}" /> - <fmt:formatDate pattern="dd-MM-yyyy" value="${playlist.week.endDate}" />)
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Date added</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="both" value="${playlist.creationTime}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Date modified</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="both" value="${playlist.modificationTime}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Show on Homepage</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:if test="${(playlist.onHome eq false)  || (playlist.onHome eq null)}">
                                                <c:out value="No" />
                                            </c:if>
                                            <c:if test="${playlist.onHome eq true}">
                                                <c:out value="Yes" />
                                            </c:if>
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Slide Actived</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:if test="${(playlist.slideActived eq false)  || (playlist.slideActived eq null)}">
                                                <c:out value="No" />
                                            </c:if>
                                            <c:if test="${playlist.slideActived eq true}">
                                                <c:out value="Yes" />
                                            </c:if>
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Created By</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.createdByUser}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Modified By</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlist.modifiedByUser}" />
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
        $('.delete-playlist').click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            swal({
                    title: "Are you sure?",
                    text: "You will not be able to recover this playlist!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, delete it!",
                    closeOnConfirm: false
                },
                function() {
                    $.get(href,function(){
                        swal("Deleted!", "Playlist has been deleted.", "success");
                        window.location.href = "list";
                    }).fail(function(){
                        swal("Error", "Playlist could not be deleted", "error");
                    });
                });
        });
    });
</script>