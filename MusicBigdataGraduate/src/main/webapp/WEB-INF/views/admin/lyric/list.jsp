<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/6/2018
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../shared/taglib.jsp"%>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Lyric Management</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/lyric/"/>">Lyric Management</a></li>
            <li class="active"><strong>List</strong></li>
        </ol>
    </div>
    <div class="col-lg-2">
        <div class="title-action">
            <a href="<c:url value='create' />" class="btn btn-primary ">Add a new Lyric</a>
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
                            <th data-hide="phone">Title</th>
                            <th data-hide="phone">Date added</th>
                            <th data-hide="phone">Date modified</th>
                            <th data-hide="phone,tablet">Content</th>
                            <th class="text-right">Action</th>

                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${lyrics}" var="lyric">
                            <tr>
                                <td>${lyric.id}</td>
                                <td>
                                    <c:out value="${lyric.title}" />
                                </td>
                                <td>
                                    <fmt:formatDate type="date" value="${lyric.creationTime}" />
                                </td>
                                <td>
                                    <fmt:formatDate type="date" value="${lyric.modificationTime}" />
                                </td>
                                <td>
                                    <c:out value="${lyric.body}" />
                                </td>
                                <td class="text-right">
                                    <div class="btn-group">
                                        <a href="<c:url value='details-${lyric.id}' />" class="btn-white btn btn-xs">View</a> <a href="<c:url value='edit-${lyric.id}' />" class="btn-white btn btn-xs">Edit</a> <a href="<c:url value='delete-${lyric.id}' />" class="btn-white btn btn-xs delete-lyric">Delete</a>
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
        $('.delete-lyric').click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            swal({
                title : "Are you sure?",
                text : "You will not be able to recover this lyric!",
                type : "warning",
                showCancelButton : true,
                confirmButtonColor : "#DD6B55",
                confirmButtonText : "Yes, delete it!",
                closeOnConfirm : false
            }, function() {
                $.get(href, function() {
                    swal("Deleted!", "Lyric has been deleted.", "success");
                    window.location.href = "list";
                }).fail(function() {
                    swal("Error", "Lyric could not be deleted", "error");
                });
            });
        });
    });

</script>