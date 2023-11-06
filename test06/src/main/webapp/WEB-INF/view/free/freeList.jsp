<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<sec:authorize access="isAuthenticated()">
    <!-- principal 변수에 로그인 여부 저장 -->
    <sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <title> 게시판 </title>
    <jsp:include page="../layout/head.jsp" />
    <c:if test="${not empty msg}">
        <script>
            alert("${msg}");
        </script>
    </c:if>
</head>
<body class="bg-body-tertiary">
<jsp:include page="../layout/header.jsp" />

<div class="container pt-5 pb-5">
    <div class="d-flex justify-content-end">
        <form action="${path}/free/list" class="d-flex justify-content-end" style="width: 40%">
            <select name="type" id="type" class="form-select w-25">
                <option value="T" id="T"> 제목 </option>
                <option value="C" id="C"> 내용 </option>
                <option value="W" id="W"> 작성자 </option>
            </select>
            <input type="text" name="keyword" id="keyword" class="form-control" autocomplete="off" value="${page.keyword}">
            <input type="submit" class="btn btn-dark" value="검색">
        </form>
    </div>
    <table class="table table-hover text-center mt-5 mb-5">
        <thead>
        <tr>
            <th> # </th>
            <th> 제목 </th>
            <th> 작성자 </th>
            <th> 작성일 </th>
            <th> 조회수 </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="free" items="${freeList}">
            <tr onclick="javascript: location.href='${path}/free/detail?fno=${free.fno}&id=${principal}'" style="cursor: pointer">
                <td> ${free.fno} </td>
                <td style="width: 450px;">
                    <div style="width: 450px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; text-align: left"> ${free.title} </div>
                </td>
                <td> ${free.name} </td>
                <td>
                    <fmt:parseDate var="date" value="${free.resdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
                </td>
                <td> ${free.views} </td>
            </tr>
        </c:forEach>
        <c:if test="${empty freeList}">
            <tr>
                <td colspan="5"> 작성된 게시글이 없습니다. </td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <!-- Paging -->
    <nav aria-label="Page navigation example" class="mt-25 mb-30">
        <ul class="pagination justify-content-center">
            <c:if test="${page.curPageNum > 5}">
                <li class="page-item">
                    <a class="page-link" href="${path}/free/list?page=${page.blockStartNum - 1}" aria-label="Previous">
                        <span aria-hidden="true"><<</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="i" begin="${page.blockStartNum}" end="${page.blockLastNum}">
                <c:choose>
                    <c:when test="${i == page.curPageNum}">
                        <li class="page-item active" aria-current="page">
                            <a class="page-link" href="${path}/free/list?page=${i}">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${path}/free/list?page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${page.blockLastNum < page.totalPageCount}">
                <li class="page-item">
                    <a class="page-link" href="${path}/free/list?page=${page.blockLastNum + 1}" aria-label="Next">
                        <span aria-hidden="true">>></span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>

    <div class="d-flex justify-content-end">
        <a href="${path}/free/insert" class="btn btn-dark mt-3"> 글쓰기 </a>
    </div>
</div>

<jsp:include page="../layout/footer.jsp" />

<c:if test="${not empty page.type}">
    <script>
        $(document).ready(() => {
            $("#${page.type}").attr("selected", true);
        });
    </script>
</c:if>
</body>
</html>
