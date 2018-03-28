<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../shared/taglib.jsp"%>

<style>
    .chosen-choices {
        border: none !important;
        border-radius: 0 !important;
        background: #2a2a2a !important;
    }
    .chosen-container {
        margin-bottom: 25px;
    }
    #genre, #file {
        margin-bottom: 25px;
        border: none;
        background: #2a2a2a;
        color: #777777;
    }
    #genre:focus {
        border: none;
        box-shadow: none;
    }
</style>

<!-- main content area -->
<main>
    <section id="content">
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-1 contact-desc center">
                    <p>Nullam eu lectus et tellus malesuada bibendum sed a nisl. Praesent iaculis sem nisi, sed molestie est ullamcorper vitae. Praesent ac tincidunt eros. Duis eget urna convallis, faucibus magna vel, auctor justo. Proin et scelerisque dui. Lorem ipsum dolor sit amet, consectetur adipiscing elit. </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-3 col-sm-6">
                    <h2>Song Upload</h2>
                    <c:url var="uploadUrl" value="/upload" />
                    <form:form id="contact-form" action="${uploadUrl}" method="POST" modelAttribute="song" enctype="multipart/form-data">
                        <label for="name">Song Name <span>*</span></label>
                        <form:errors path="name" cssClass="error" />
                        <form:input type="text" path="name" placeholder="Song name" />
                        <label for="artists">Artists</label>
                        <form:select path="artists" data-placeholder="Choose a Artist..." class="chosen-select" style="width:555px;" tabindex="4" multiple="true">
                            <option value="">Select</option>
                            <form:options items="${artists}" itemLabel="name" itemValue="id" />
                        </form:select>
                        <label for="genre">Genre</label>
                        <form:select path="genre" class="form-control">
                            <form:option label="Select Genre" value="" />
                            <form:options items="${genres}" itemLabel="name" itemValue="id" />
                        </form:select>
                        <form:errors path="genre" cssClass="error" />
                        <label for="name">Choose file <span>*</span></label>
                        <c:if test="${fileError != null}">
                            <span class="error"><c:out value="${fileError}" /></span>
                        </c:if>
                        <input type="file" id="file" name="file" class="form-control" accept="audio/*">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="submit" name="sendmessage" id="sendmessage" value="Upload Song" />
                            </div>
                            <div class="col-sm-6 dynamic"></div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</main>
<!-- end main content area -->

<script>
    $(document).ready(function() {
        var config = {
            '.chosen-select' : {},
            '.chosen-select-deselect' : {
                allow_single_deselect : true
            },
            '.chosen-select-no-single' : {
                disable_search_threshold : 10
            },
            '.chosen-select-no-results' : {
                no_results_text : 'Oops, nothing found!'
            },
            '.chosen-select-width' : {
                width : "95%"
            }
        };
        for ( var selector in config) {
            $(selector).chosen(config[selector]);
        }
    });
</script>