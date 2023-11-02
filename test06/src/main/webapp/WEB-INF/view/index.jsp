<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!-- implementation 'org.springframework.security:spring-security-taglibs'로 불러옴 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <!-- principal 변수에 로그인 여부 저장 -->
    <sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 메인페이지 </title>
    <jsp:include page="./layout/head.jsp" />
</head>
<body>
<jsp:include page="./layout/header.jsp" />

<jsp:include page="./layout/footer.jsp" />
</body>
</html>
