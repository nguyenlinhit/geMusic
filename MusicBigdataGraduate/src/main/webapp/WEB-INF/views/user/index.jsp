<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../shared/taglib.jsp"%>

<div id="fb-root"></div>
<script>
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id))
            return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5&appId=785058958214714";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>
<!-- main content area -->
<main>
    <section id="content">
        <div class="container">

            <!-- user details -->
            <div class="row artist-info">
                <div class="col-sm-4 col-md-3">
                    <div class="latest-content">
                        <div class="latest-content-image"><img src="<c:url value="${user.imageUrl}"/> " alt="" /></div>
                        <div class="latest-content-info">
                            <div class="meta">
                                <div class="icon" style="width:60px"><i class="fa fa-user"></i></div>
                                <h4>${user.fullname}</h4><p>${user.sex != null ? user.sex : 'Unknown'}</p>
                            </div>
                        </div>
                        <div class="event-info">
                            <dl class="dl-horizontal">
                                <dt>Date of Birth :</dt><dd><fmt:formatDate type="date" value="${user.birthDate}" /></dd>
                                <dt>Email :</dt><dd>${user.email}</dd>
                                <dt>Profile Views : Unknown</dt><dd></dd>
                                <dt>Playlists Views : Unknown</dt><dd></dd>
                            </dl>
                        </div>
                    </div>

                    <ul class="share clearfix">
                        <li><a href="#"><i class="fa fa-lg fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-lg fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-lg fa-flickr"></i></a></li>
                        <li><a href="#"><i class="fa fa-lg fa-google-plus"></i></a></li>
                    </ul>
                </div>

                <div class="col-sm-8 col-md-9">
                    <div class="row">
                        <div class="col-xs-12"><h3>Playlists<c:if test="${loginModel.username == user.username && userPlaylists.size() > 0}"><a href="#" class="view-all" style="margin-top:-5px"><span>Edit</span> <i class="fa fa-pencil view-all-icon"></i></a></c:if></h3></div>
                        <c:forEach items="${userPlaylists}" var="pl">
                            <div class="col-sm-6 col-md-3 album">
                                <div class="latest-content">
                                    <a href="#">
                                        <div class="latest-content-image"><img src="<c:url value="${pl.imageUrl}"/>" alt="" /></div>
                                        <div class="latest-content-info">
                                            <div class="meta"><h4>${pl.name}</h4><p>${pl.artist.name != null ? pl.artist.name : 'Updating'}</p></div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>

            <!-- comment -->
            <div id="leave-comment"><h2>Comments</h2></div>
            <c:url var="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}" />
            <div class="fb-comments" data-colorscheme="dark" data-mobile="true" data-href="http://localhost:8088${currentUrl}"></div>

        </div>
    </section>
</main>
<!-- end main content area -->