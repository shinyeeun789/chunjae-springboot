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
                <td class="text-left"> ${free.title} </td>
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

    <div class="d-flex justify-content-end">
        <a href="${path}/free/insert" class="btn btn-dark mt-3"> 글쓰기 </a>
    </div>
</div>

<jsp:include page="../layout/footer.jsp" />
</body>
</html>
