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
    .chosen-container {
        width: 100%!important;
    }
    #selectCountry .chosen-container {
        width: 350px!important;
    }
</style>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-8">
        <h2>Edit Song details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/song/"/>">Song Management</a></li>
            <li class="active"><strong>Song edit</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list "/>" class="btn btn-white">Go Back</a>
            <a href="<spring:url value="details-${song.id}"/>" class="btn btn-info">View Details</a>
            <a href="<spring:url value="delete-${song.id}"/>" class="btn btn-danger delete-song">Delete</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">Song Info</a></li>
                    <li class=""><a data-toggle="tab" href="#tab-2">Artist mappings</a></li>
                </ul>

                <div class="tab-content">

                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <form:form id="editSongForm" method="POST" modelAttribute="song" enctype="multipart/form-data" class="form-horizontal">
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
                                        <label class="col-sm-2 control-label">Name</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="name" class="form-control" placeholder="Song name" />
                                            <form:errors path="name" cssClass="error" />
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
                                            <div id="selectCountry" class="input-group">
                                                <form:select path="country" data-placeholder="Choose a Country..." class="chosen-select" style="width:350px">
                                                    <option value="${song.country}">${song.country}</option>
                                                    <c:forEach var="country" items="${countries}">
                                                        <option value="${country}">${country}</option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
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
                                                <form:option label="" value="" />
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
                                            <div class="i-checks"><label> <input type="checkbox" name="isPublished" ${song.isPublished eq true ? 'checked' : ''}> <i></i> Published </label></div>
                                            <div class="i-checks"><label> <input type="checkbox" name="onHome" ${song.onHome eq true ? 'checked' : ''}> <i></i> Show On Homepage </label></div>
                                        </div>
                                    </div>

                                </fieldset>
                            </form:form>

                        </div>
                    </div>

                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body">

                            <form:form id="mappingArtistForm" class="form-horizontal m-b">
                                <fieldset class="form-horizontal">

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Select Artist</label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <select id="artistSelect" data-placeholder="Choose a Artist..." class="chosen-select">
                                                    <option value="">Select</option>
                                                    <c:forEach var="artist" items="${artists}">
                                                        <option value="${artist.id}">${artist.name}</option>
                                                    </c:forEach>
                                                </select>
                                                <span class="input-group-btn" style="vertical-align:top">
            										<input type="submit" class="btn btn-primary" value="Add Artist"/>
	            								</span>
                                                <!-- Validate -->
                                            </div>
                                        </div>
                                    </div>

                                </fieldset>
                            </form:form>

                            <div class="table-responsive">
                                <table id="mappingArtistTable" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th></th>
                                        <th>Artist Name</th>
                                        <th>Real Name</th>
                                        <th>Gender</th>
                                        <th>Country</th>
                                        <th>Delete</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${song.artists}" var="artist" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${artist.name}</td>
                                            <td>${artist.realName}</td>
                                            <td>${artist.sex}</td>
                                            <td>${artist.country}</td>
                                            <td><a class="btn btn-primary btn-xs btn-outline" href="javascript:removeArtist(${artist.id})">Delete</a></td>
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
    </div>

</div>

<script>
    $(document).ready(function() {

        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });

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

        //Delete Song
        $('.delete-song').click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            swal({
                    title: "Are you sure?",
                    text: "You will not be able to recover this song!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, delete it!",
                    closeOnConfirm: false
                },
                function() {
                    $.get(href,function(){
                        swal("Deleted!", "Song has been deleted.", "success");
                        window.location.href = "list";
                    }).fail(function(){
                        swal("Error", "Song could not be deleted", "error");
                    });
                });
        });
        //Validat
        $("#editSongForm").validate({
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

        //Ajax submit mappingArtistForm
        $('#mappingArtistForm').submit(function(e) {
            if ($('#artistSelect').val() != '') {
                $.post('${song.id}/AddArtist', {
                    artist: $('#artistSelect').val()
                }, function(artist) {
                    if (artist != null) {
                        loadArtists();
                    }
                });
                $('#artistSelect').val('').trigger('chosen:updated');
            }
            e.preventDefault();
        });

    });

    //Remove Artist function
    function removeArtist(artistId) {
        $.post('${song.id}/RemoveArtist', {
                artist: artistId
            },
            function(response) {
                if (response == 'true') {
                    loadArtists();
                } else {
                    alert('Failure! An error has occurred!');
                }
            });
    }

    //Load Artists function
    function loadArtists() {
        $.get('${song.id}/GetArtists', function(artists) {

            $('#mappingArtistTable').find('tbody').remove();

            for (var i=0; i<artists.length; i++) {
                var row = '<tr>';
                row += '<td>' + (i+1) + '</td>';
                row += '<td>' + artists[i].name + '</td>';
                row += '<td>' + artists[i].realName + '</td>';
                row += '<td>' + artists[i].sex + '</td>';
                row += '<td>' + artists[i].country + '</td>';
                row += '<td><a class="btn btn-primary btn-xs btn-outline" href="javascript:removeArtist(' + artists[i].id + ');">Delete</a></td>';
                row += '</tr>';
                $('#mappingArtistTable').append(row);
            }
        });
    }

</script>
