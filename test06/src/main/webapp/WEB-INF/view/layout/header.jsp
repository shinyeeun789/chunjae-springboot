<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!-- implementation 'org.springframework.security:spring-security-taglibs'로 불러옴 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <!-- principal 변수에 로그인 여부 저장 -->
    <sec:authentication property="principal" var="principal" />
</sec:authorize>

<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="${path}/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <img src="../resource/images/logo.png" alt="로고" style="width: 45px; margin-right: 10px;">
                <h4 class="m-0"> LOGO </h4>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0" style="margin-left: 35px;">
                <li><a href="#" class="nav-link px-2 text-white"> 게시판 </a></li>
                <li><a href="#" class="nav-link px-2 text-white"> Pricing </a></li>
                <li><a href="#" class="nav-link px-2 text-white"> FAQs </a></li>
                <li><a href="#" class="nav-link px-2 text-white"> About </a></li>
            </ul>

            <div class="text-end">
                <c:if test="${empty principal}">
                    <a href="${path}/join" class="btn btn-outline-light"> 회원가입 </a>
                    <a href="${path}/login" class="btn btn-light"> 로그인 </a>
                </c:if>
                <c:if test="${not empty principal}">
                    <form action="/logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-outline-light"> 로그아웃 </button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</header>
