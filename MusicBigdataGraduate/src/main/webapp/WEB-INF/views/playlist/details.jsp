<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ include file="../shared/taglib.jsp"%>

<c:url var="addPlaylistUrl" value="/playlist/add" />

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
    <section id="content" style="padding-top:15px;">
        <div class="container">

            <h2>Album <span>${playlist.name} - ${playlist.artist.name}</span><span class="views"><fmt:formatNumber type="number" value="${playlist.totalViews}" /> views</span></h2>

            <!-- playlist details -->
            <div class="row artist-info" style="margin-top:0;">
                <div class="col-sm-4 col-md-3">
                    <div class="latest-content">
                        <div class="latest-content-image">
                            <img src="<c:url value="${playlist.imageUrl}"/> " alt="" />
                        </div>
                        <div class="latest-content-info">
                            <div class="meta">
                                <div class="icon">
                                    <i class="fa fa-headphones"></i>
                                </div>
                                <h4>${playlist.name}</h4>
                                <p>${playlist.artist.name}</p>
                            </div>
                        </div>
                    </div>
                    <a id="btnAddPlaylist" class="btn btn-sm btn-outline" style="text-transform:none">Add to..</a>
                    <div id="addsuccess" class="alert alert-success" style="display:none">Well done! You successfully add this playlist.</div>
                    <div id="addfail" class="alert alert-danger" style="display:none">You already have this playlist.</div>
                </div>

                <div class="col-sm-8 col-md-9">

                    <div class="album-content col-xs-12">
                        <div id="favoriteplay" class="box_content_wrapper">
                            <div class="box_content"><div class="player_box dark_shadow"><div class="player_area"></div></div></div>
                        </div>
                    </div>

                    <div class="row album-info">
                        <div class="col-xs-12">
                            <h3>Album Description</h3>
                            <p>Mauris iaculis porttitor posuere. Praesent id metus massa, ut blandit odio. Proin quis tortor orci. Etiam
                                at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu
                                ullamcorper orci. Quisque eget odio ac lectus vestibulum faucibus eget in metus. In pellentesque faucibus
                                vestibulum. Nulla at nulla justo, eget luctus tortor. Nulla facilisi. Duis aliquet egestas purus in blandit.
                                Curabitur vulputate, ligula lacinia scelerisque tempor, lacus lacus ornare ante, ac egestas est urna sit amet
                                arcu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed molestie.</p>
                            <p>Mauris iaculis porttitor posuere. Praesent id metus massa, ut blandit odio. Proin quis tortor orci. Etiam
                                at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu
                                ullamcorper orci. Quisque eget odio ac lectus vestibulum faucibus eget in metus. In pellentesque faucibus
                                vestibulum. Nulla at nulla justo, eget luctus tortor. Nulla facilisi. Duis aliquet egestas purus in blandit.
                                Curabitur vulputate, ligula lacinia scelerisque tempor, lacus lacus ornare ante, ac egestas est urna sit amet
                                arcu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed molestie.</p>
                        </div>
                    </div>

                    <div class="row album-info">
                        <div class="col-xs-12">
                            <h3>Song Lyric</h3>
                            <p>Mauris iaculis porttitor posuere. Praesent id metus massa, ut blandit odio. Proin quis tortor orci. Etiam
                                at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu
                                ullamcorper orci. Quisque eget odio ac lectus vestibulum faucibus eget in metus. In pellentesque faucibus
                                vestibulum. Nulla at nulla justo, eget luctus tortor. Nulla facilisi. Duis aliquet egestas purus in blandit.
                                Curabitur vulputate, ligula lacinia scelerisque tempor, lacus lacus ornare ante, ac egestas est urna sit amet
                                arcu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed molestie.</p>
                            <p>Mauris iaculis porttitor posuere. Praesent id metus massa, ut blandit odio. Proin quis tortor orci. Etiam
                                at risus et justo dignissim congue. Donec congue lacinia dui, a porttitor lectus condimentum laoreet. Nunc eu
                                ullamcorper orci. Quisque eget odio ac lectus vestibulum faucibus eget in metus. In pellentesque faucibus
                                vestibulum. Nulla at nulla justo, eget luctus tortor. Nulla facilisi. Duis aliquet egestas purus in blandit.
                                Curabitur vulputate, ligula lacinia scelerisque tempor, lacus lacus ornare ante, ac egestas est urna sit amet
                                arcu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed molestie.</p>
                        </div>
                    </div>

                    <div class="row album-info">
                        <h3 class="col-xs-12">${playlist.artist.name}'s Albums</h3>
                        <c:forEach items="${relatedPlaylists}" var="pl">
                            <div class="col-sm-6 col-md-3 album">
                                <div class="latest-content">
                                    <a href="#">
                                        <div class="latest-content-image"><img src="<c:url value="${pl.imageUrl}"/>" alt="" /></div>
                                        <div class="latest-content-info">
                                            <div class="meta">
                                                <div class="icon"><i class="fa fa-headphones"></i></div>
                                                <h4>${pl.name}</h4><p>${pl.artist.name}</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="row album-info">
                        <h3 class="col-xs-12">${playlist.artist.name}'s Songs</h3>
                        <div id="songs" class="col-xs-12" style="margin-top:0">
                            <ul class="songs" style="margin-top:0">
                                <c:forEach items="${relatedSongs}" var="song">
                                    <li>
                                        <div class="track-meta" style="width:50%;margin:18px 0 18px 20px;">
                                            <h5 style="line-height:24px;margin-bottom:0">
                                                <a href="#" class="name-song">${song.name}</a>
                                                <c:out value=" - "/>
                                                <a href="#" class="name-single">
                                                    <c:forEach items="${song.artists}" var="artist" varStatus="loop">
                                                        <c:out value="${artist.name}" />
                                                        <c:if test="${loop.index == 0 && loop.index lt fn:length(song.artists) - 1}">
                                                            <c:out value="ft."/>
                                                        </c:if>
                                                        <c:if test="${loop.index > 0 && loop.index lt fn:length(song.artists) - 1}">
                                                            <c:out value="&"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </a>
                                            </h5>
                                        </div>
                                        <span class="views"><i class="fa fa-headphones"></i> <fmt:formatNumber type="number" value="${song.totalView}" /></span>
                                        <span class="audiojs">
									      <a class="play"><i class="fa fa-play icon"></i></a>
									      <a class="error"><i class="fa fa-plus icon"></i></a>
									      <a class="error"><i class="fa fa-clone icon"></i></a>
									</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>

            <!-- playlist involved -->


            <!-- comment -->
            <div id="leave-comment">
                <h2>Leave A <span>Comment</span></h2>
            </div>
            <c:url var="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}" />
            <div class="fb-comments" data-colorscheme="dark" data-mobile="true" data-href="http://localhost:8088${currentUrl}"></div>

        </div>
    </section>
</main>
<!-- end main content area -->

<script>
    $(document).ready(function() {
        //data 
        var data = ${songs};
        //create playlist
        var myPlaylist = [];
        $(data).each(function(i, sp) {
            var artists = "";
            for (var i = 0; i < sp.song.artists.length; i++) {
                if (i == 0) {
                    artists += sp.song.artists[i].name;
                } else if (i == 1) {
                    artists += " ft. " + sp.song.artists[i].name;
                } else if (i > 1) {
                    artists += " and " + sp.song.artists[i].name;
                }
            }
            myPlaylist.push({
                mp3: '/GEMusic'+sp.song.url,
                title: sp.song.name + ' - ' + artists,
                buy: '',
                price: "<a href='#' target='_blank' title='Download'><i class='fa fa-download'></i></a> <a href='#' target='_blank' title='Add'><i class='fa fa-plus'></i></a> <a href='#' target='_blank' title='Share'><i class='fa fa-share-alt'></i></a>  <a href='#' target='_blank' title='Go to...'><i class='fa fa-arrow-right'></i></a>",
            });
        });

        //player
        $('.player_area').ttwMusicPlayer(myPlaylist, {
            autoplay: true,
            tracksToShow: 7,
            jPlayer: {
                swfPath: '<c:url value="/static/js/player"/>'
            }
        });

        //add button
        $("#btnAddPlaylist").click(function(e) {
            e.preventDefault();
            $.get("${addPlaylistUrl}", {
                id: "${playlist.id}"
            }, function(data, status) {
                if (data == 'unauthorized') {
                    $('#login-modal').modal();
                } else if (data == 'fail') {
                    $("#addfail").show().delay(4000).fadeOut();
                } else {
                    $("#addsuccess").show().delay(4000).fadeOut();
                }
            });
        });
    });
</script>