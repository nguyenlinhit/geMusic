<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../shared/taglib.jsp"%>

<!-- main content area -->
<main>
    <section id="content">
        <div class="container">

            <ul class="isotope-filters album-filter" style="margin-bottom:25px">
                <c:choose>
                    <c:when test="${currentGenre eq 'All'}">
                        <li class="current"><a href="<c:url value="/playlist/"/>">ALL ALBUMS</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value="/playlist/"/>">ALL ALBUMS</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach items="${genres}" var="genre">
                    <c:choose>
                        <c:when test="${currentGenre eq genre.name }">
                            <li class="current"><i class="fa fa-ellipsis-v"></i><a href="<c:url value="/playlist/${genre.name}/1"/>">${genre.name}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><i class="fa fa-ellipsis-v"></i><a href="<c:url value="/playlist/${genre.name}/1"/>">${genre.name}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>

            <div class="row albums">
                <!-- albums -->
                <c:forEach items="${page.content}" var="pl" varStatus="status">
                    <div class="col-sm-6 col-md-2 ${(status.index == 0 || (status.index mod 5) == 0) ? 'col-md-offset-1' : '' } album">
                        <div class="latest-content">
                            <a href="${playlistUrl}/${pl.id}">
                                <div class="latest-content-image"><img src="<c:url value="${pl.imageUrl}"/>" alt="" /></div>
                                <div class="latest-content-info">
                                    <div class="meta">
                                        <h4>${pl.name}</h4><p>${pl.artist.name != null ? pl.artist.name : 'Updating'}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <ul class="pagination">
            <c:choose>
                <c:when test="${currentIndex == 1}"></c:when>
                <c:otherwise>
                    <c:url var="prevUrl" value="/playlist/${currentGenre}/${currentIndex - 1}" />
                    <li><a href="${prevUrl}"><i class="fa fa-angle-double-left"></i></a></li>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                <c:url var="pageUrl" value="/playlist/${currentGenre}/${i}" />
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
                    <c:url var="nextUrl" value="/playlist/${currentGenre}/${currentIndex + 1}" />
                    <li><a href="${nextUrl}"><i class="fa fa-angle-double-right"></i></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </section>
</main>
<!-- end main content area -->
