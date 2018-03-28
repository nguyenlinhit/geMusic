<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../shared/taglib.jsp"%>

<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-lg-3">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-success pull-right">Monthly</span>
                    <h5>Income</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">40 886,200</h1>
                    <div class="stat-percent font-bold text-success">
                        98% <i class="fa fa-bolt"></i>
                    </div>
                    <small>Total income</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-info pull-right">Annual</span>
                    <h5>Upload</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">275,800</h1>
                    <div class="stat-percent font-bold text-info">
                        20% <i class="fa fa-level-up"></i>
                    </div>
                    <small>New uploads</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-primary pull-right">Today</span>
                    <h5>Vistits</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">106,120</h1>
                    <div class="stat-percent font-bold text-navy">
                        44% <i class="fa fa-level-up"></i>
                    </div>
                    <small>New visits</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-danger pull-right">Low value</span>
                    <h5>User activity</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">80,600</h1>
                    <div class="stat-percent font-bold text-danger">
                        38% <i class="fa fa-level-down"></i>
                    </div>
                    <small>In first month</small>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>New Upload Song </h5>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-9 m-b-xs">
                            <div data-toggle="buttons" class="btn-group">
                                <label class="btn btn-sm btn-white"> <input type="radio" id="option1" name="options"> Day </label>
                                <label class="btn btn-sm btn-white active"> <input type="radio" id="option2" name="options"> Week </label>
                                <label class="btn btn-sm btn-white"> <input type="radio" id="option3" name="options"> Month </label>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="input-group"><input type="text" placeholder="Search" class="input-sm form-control"> <span class="input-group-btn">
                        <button type="button" class="btn btn-sm btn-primary"> Go!</button> </span>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Name </th>
                                <th>Artists </th>
                                <th>Genre </th>
                                <th>Date added </th>
                                <th>Upload by</th>
                                <th>Play </th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${songs}" var="song" varStatus="loop">
                                <tr>
                                    <td><c:out value="${loop.index + 1}" /></td>
                                    <td><c:out value="${song.name}" /></td>
                                    <td>
                                        <c:forEach items="${song.artists}" var="artist" varStatus="loop">
                                            <c:out value="${artist.name}" />
                                            <c:if test="${loop.index == 0 && loop.index lt fn:length(song.artists) - 1}">
                                                <c:out value="ft."/>
                                            </c:if>
                                            <c:if test="${loop.index > 0 && loop.index lt fn:length(song.artists) - 1}">
                                                <c:out value="&"/>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td><c:out value="${song.genre.name}" /></td>
                                    <td><fmt:formatDate type="date" value="${song.creationTime}" /></td>
                                    <td><c:out value="${song.createdByUser}" /></td>
                                    <td><audio src="<c:url value="${song.url}"/>" preload="none"></audio></td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <a href="<c:url value='song/details-${song.id}' />" class="btn-white btn btn-xs">View</a> <a href="<c:url value='song/edit-${song.id}' />" class="btn-white btn btn-xs">Edit</a> <a href="<c:url value='song/delete-${song.id}' />" class="btn-white btn btn-xs delete-song">Delete</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('.delete-song').click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            swal({
                title : "Are you sure?",
                text : "You will not be able to recover this song!",
                type : "warning",
                showCancelButton : true,
                confirmButtonColor : "#DD6B55",
                confirmButtonText : "Yes, delete it!",
                closeOnConfirm : false
            }, function() {
                $.get(href, function() {
                    swal("Deleted!", "Song has been deleted.", "success");
                    window.location.href = "<c:url value="/admin/"/>";
                }).fail(function() {
                    swal("Error", "Song could not be deleted", "error");
                });
            });
        });
    });
</script>