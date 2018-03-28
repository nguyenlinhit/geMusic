<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/6/2018
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../shared/taglib.jsp"%>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Playlist Management</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/playlist/"/>">Playlist Management</a></li>
            <li class="active"><strong>List</strong></li>
        </ol>
    </div>
    <div class="col-lg-2">
        <div class="title-action">
            <a href="<c:url value='create' />" class="btn btn-primary ">Add a new Playlist</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-content">

                    <input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="Search for User">

                    <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15" data-filter=#filter>
                        <thead>
                        <tr>

                            <th>ID</th>
                            <th data-hide="phone">Name</th>
                            <th data-hide="phone">Artist</th>
                            <th data-hide="phone">TotalViews</th>
                            <th data-hide="phone,tablet">On homepage</th>
                            <th data-hide="phone,tablet">Slide Actived</th>
                            <th data-hide="phone">Date added</th>
                            <th class="text-right">Action</th>

                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${playlists}" var="playlist">
                            <tr>
                                <td>${playlist.id}</td>
                                <td>
                                    <c:out value="${playlist.name}" />
                                </td>
                                <td>
                                    <c:out value="${playlist.artist.name}" />
                                </td>
                                <td>
                                    <fmt:formatNumber type="number" value="${playlist.totalViews}" />
                                </td>
                                <td>
                                    <c:if test="${playlist.onHome eq true}">
                                        <span class="label label-info">Yes</span>
                                    </c:if>
                                    <c:if test="${(playlist.onHome eq false)  || (playlist.onHome eq null)}">
                                        <span class="label label-warning">No</span>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${playlist.slideActived eq true}">
                                        <span class="label label-info">Yes</span>
                                    </c:if>
                                    <c:if test="${(playlist.slideActived eq false)  || (playlist.slideActived eq null)}">
                                        <span class="label label-warning">No</span>
                                    </c:if>
                                </td>
                                <td>
                                    <fmt:formatDate type="date" value="${playlist.creationTime}" />
                                </td>
                                <td class="text-right">
                                    <div class="btn-group">
                                        <a href="<c:url value='details-${playlist.id}' />" class="btn-white btn btn-xs">View</a> <a href="<c:url value='edit-${playlist.id}' />" class="btn-white btn btn-xs">Edit</a> <a href="<c:url value='delete-${playlist.id}' />" class="btn-white btn btn-xs delete-playlist">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="7">
                                <ul class="pagination pull-right"></ul>
                            </td>
                        </tr>
                        </tfoot>
                    </table>

                </div>
            </div>
        </div>
    </div>


</div>

<script>
    $(document).ready(function() {
        $('.footable').footable();
        $('.delete-playlist').click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            swal({
                title : "Are you sure?",
                text : "You will not be able to recover this playlist!",
                type : "warning",
                showCancelButton : true,
                confirmButtonColor : "#DD6B55",
                confirmButtonText : "Yes, delete it!",
                closeOnConfirm : false
            }, function() {
                $.get(href, function() {
                    swal("Deleted!", "Playlist has been deleted.", "success");
                    window.location.href = "list";
                }).fail(function() {
                    swal("Error", "Playlist could not be deleted", "error");
                });
            });
        });
    });
</script>