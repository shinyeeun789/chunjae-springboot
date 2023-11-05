<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
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
    <div class="w-50 m-auto">
        <form action="${path}/free/insert" method="post" class="mt-5 mb-5">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <label for="title" class="form-label"> 제목 </label>
            <input type="text" id="title" name="title" class="form-control" maxlength="50">

            <label for="content" class="form-label mt-2"> 내용 </label>
            <textarea id="content" name="content" class="form-control" rows="20" maxlength="2000"></textarea>

            <input type="submit" class="btn btn-primary mt-3 w-100" value="작성하기">
        </form>
    </div>
</div>

<jsp:include page="../layout/footer.jsp" />
</body>
</html>
