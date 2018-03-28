<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/6/2018
  Time: 3:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../shared/taglib.jsp"%>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Artist Management</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/artist/"/>">Artist Management</a></li>
            <li class="active"><strong>List</strong></li>
        </ol>
    </div>
    <div class="col-lg-2">
        <div class="title-action">
            <a href="<c:url value='create' />" class="btn btn-primary ">Add a new Artist</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-content">

                    <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15">
                        <thead>
                        <tr>

                            <th>ID</th>
                            <th data-hide="phone">Artist Name</th>
                            <th data-hide="phone">Gender</th>
                            <th data-hide="phone">Country</th>
                            <th data-hide="phone">Date added</th>
                            <th data-hide="phone,tablet">Real Name</th>
                            <th data-hide="phone,tablet">Date modified</th>
                            <th class="text-right">Action</th>

                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${artists}" var="artist">
                            <tr>
                                <td>${artist.id}</td>
                                <td>
                                    <c:out value="${artist.name}" />
                                </td>
                                <td>
                                    <c:out value="${artist.sex}" />
                                </td>
                                <td>
                                    <c:out value="${artist.country}" />
                                </td>
                                <td>
                                    <fmt:formatDate type="date" value="${artist.creationTime}" />
                                </td>
                                <td>
                                    <c:out value="${artist.realName}" />
                                </td>
                                <td>
                                    <fmt:formatDate type="date" value="${artist.modificationTime}" />
                                </td>
                                <td class="text-right">
                                    <div class="btn-group">
                                        <a href="<c:url value='details-${artist.id}' />" class="btn-white btn btn-xs">View</a> <a href="<c:url value='edit-${artist.id}' />" class="btn-white btn btn-xs">Edit</a> <a href="<c:url value='delete-${artist.id}' />" class="btn-white btn btn-xs delete-artist">Delete</a>
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
        $('.delete-artist').click(
            function(e) {
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
    });
</script>