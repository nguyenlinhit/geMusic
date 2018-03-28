<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:09 AM
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
    .jsgrid-table {
        width: 100%!important;
    }
</style>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-8">
        <h2>Edit Playlist details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/playlist/"/>">Playlist Management</a></li>
            <li class="active"><strong>Playlist edit</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list "/>" class="btn btn-white">Go Back</a>
            <a href="<spring:url value="details-${playlist.id}"/>" class="btn btn-info">View Details</a>
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
                    <li><a data-toggle="tab" href="#tab-2">Songs mapping</a></li>
                </ul>

                <div class="tab-content">

                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <form:form id="editPlaylistForm" method="POST" modelAttribute="playlist" class="form-horizontal">
                                <form:hidden path="id" />
                                <form:hidden path="imageUrl" />

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
                                        <label class="col-sm-2 control-label">Name</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="name" class="form-control" placeholder="Playlist name" />
                                            <form:errors path="name" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Total Views</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" path="totalViews" class="form-control" placeholder="Total Views" />
                                            <form:errors path="totalViews" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Week Views</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" path="weekViews" class="form-control" placeholder="Total Views" />
                                            <form:errors path="weekViews" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Country</label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <form:select path="country" data-placeholder="Choose a Country..." class="chosen-select" style="width:350px;">
                                                    <option value="${playlist.country}">${playlist.country}</option>
                                                    <c:forEach var="country" items="${countries}">
                                                        <option value="${country}">${country}</option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Artist</label>
                                        <div class="col-lg-4">
                                            <form:select path="artist" class="form-control">
                                                <form:option label="Select Artist" value="" />
                                                <form:options items="${artists}" itemLabel="name" itemValue="id" />
                                            </form:select>
                                            <form:errors path="artist" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Genre</label>
                                        <div class="col-lg-4">
                                            <form:select path="genre" class="form-control">
                                                <form:option label="Select Genre" value="" />
                                                <form:options items="${genres}" itemValue="id" itemLabel="name" />
                                            </form:select>
                                            <form:errors path="genre" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Week</label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <form:select path="week" data-placeholder="Choose a Week..." class="chosen-select" style="width:350px;">
                                                    <option value="${playlist.week.id}">${playlist.week.name}</option>
                                                    <c:forEach var="week" items="${weeks}">
                                                        <option value="${week.id}">${week.name}</option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Type</label>
                                        <div class="col-lg-4">
                                            <form:select path="type" items="${plTypes}" class="form-control" />
                                            <form:errors path="type" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"></label>
                                        <div class="col-lg-4">
                                            <div class="i-checks"><label> <input type="checkbox" name="onHome" ${playlist.onHome eq true ? 'checked' : ''}> <i></i> Show On Homepage </label></div>
                                            <div class="i-checks"><label> <input type="checkbox" name="slideActived" ${playlist.slideActived eq true ? 'checked' : ''}> <i></i> Active Slide </label></div>
                                        </div>
                                    </div>

                                </fieldset>

                            </form:form>

                        </div>
                    </div>

                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body">

                            <div id="jsGrid"></div>
                            <div class="m-t">
                                <button type="button" id="btnAddSong" class="btn btn-primary">Add new song</button>
                            </div>

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

        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });

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
        $("#editPlaylistForm").validate({
            rules: {
                name: {
                    required: true
                },
                totalViews: {
                    required: false,
                    number: true
                }
            }
        });

    });

    $(function() {
        var grid = new jsGrid.Grid($("#jsGrid"), {
            height: "320px",
            width: "100%",
            editing: true,
            sorting: true,
            autoload: true,
            deleteConfirm: "Do you really want to remove this song?",
            controller: db,
            fields: [
                { name: "id", type: "text", editing: false, visible: false },
                { name: "song.id", type: "text", width: 35, align: "center", title: "Song ID", editing: false, visible: false },
                { name: "song.name", type: "text", width: 200, align: "left", title: "Name", editing: false },
                { name: "song.artists", type: "text", width: 150, align: "center", title: "Artists", editing: false,
                    itemTemplate: function(value) {
                        var artists = "";
                        for (var i = 0; i < value.length; i++) {
                            if (i == 0) {
                                artists += value[i].name;
                            } else if (i == 1) {
                                artists += " ft. " + value[i].name;
                            } else if (i > 1) {
                                artists += " and " + value[i].name;
                            }
                        }
                        return artists;
                    }
                },
                { name: "order", type: "number", width: 50, align: "center", title: "Order", editing: true },
                { type: "control" }
            ]
        });

        $("#btnAddSong").click(function() {
            mywindow = window.open("${playlist.id}/AddSongs", "mywindow", "menubar=1,resizable=1,width=800,height=800");
            mywindow.onbeforeunload = function(){
                //refresh table
                grid.loadData();
            };
        });
    });

    var db = {
        loadData: function(filter) {
            return $.ajax({
                type: "GET",
                url: "${playlist.id}/GetSongPlaylists",
                data: filter,
                dataType: "json"
            })
        },
        updateItem: function(item) {
            $.post("${playlist.id}/UpdateSongPlaylist-" + item.id, {
                order: item.order
            });
        },
        deleteItem: function(item) {
            return $.get("${playlist.id}/RemoveSongPlaylist-" + item.id);
        }
    };

</script>