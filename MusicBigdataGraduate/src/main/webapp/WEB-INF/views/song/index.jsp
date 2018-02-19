<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../shared/taglib.jsp"%>

<!-- main content area -->
<main>
    <section id="content">
        <div class="container">

            <ul class="isotope-filters album-filter" style="margin-bottom:25px">
                <c:choose>
                    <c:when test="${currentGenre eq 'All'}">
                        <li class="current"><a href="<c:url value="/song/"/>">ALL SONGS</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value="/song/"/>">ALL</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach items="${genres}" var="genre">
                    <c:choose>
                        <c:when test="${currentGenre eq genre.name }">
                            <li class="current"><i class="fa fa-ellipsis-v"></i><a href="<c:url value="/song/${genre.name}/1"/>">${genre.name}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><i class="fa fa-ellipsis-v"></i><a href="<c:url value="/song/${genre.name}/1"/>">${genre.name}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>

            <div class="row album-info">
                <div class="col-xs-12 col-md-10 col-md-offset-1">

                    <!-- tracks -->
                    <div id="songs" style="margin:0">

                        <ul class="songs" style="margin:0px">
                            <c:forEach items="${page.content}" var="song">
                                <li>
                                    <div class="track-meta" style="width:50%;margin:18px 0 18px 20px;">
                                        <h5 style="line-height:24px;margin-bottom:0">
                                            <a href="${songUrl}/${song.id}" class="name-song">${song.name}</a>
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

        <ul class="pagination">
            <c:choose>
                <c:when test="${currentIndex == 1}"></c:when>
                <c:otherwise>
                    <c:url var="prevUrl" value="/song/${currentGenre}/${currentIndex - 1}" />
                    <li><a href="${prevUrl}"><i class="fa fa-angle-double-left"></i></a></li>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                <c:url var="pageUrl" value="/song/${currentGenre}/${i}" />
                <c:choose>
                    <c:when test="${i == currentIndex}">
                        <li class="current"><a href="${pageUrl}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageUrl}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentIndex == page.totalPages}"></c:when>
                <c:otherwise>
                    <c:url var="nextUrl" value="/song/${currentGenre}/${currentIndex + 1}" />
                    <li><a href="${nextUrl}"><i class="fa fa-angle-double-right"></i></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </section>
</main>
<!-- end main content area -->