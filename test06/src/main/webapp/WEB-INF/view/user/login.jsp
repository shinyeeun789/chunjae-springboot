<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 로그인 </title>
    <jsp:include page="../layout/head.jsp"/>
    <c:if test="${not empty msg}">
        <script>
            alert("${msg}");
        </script>
    </c:if>
</head>
<body class="bg-body-tertiary">
<jsp:include page="../layout/header.jsp"/>

<div class="w-25 m-auto mt-5 pt-5" style="min-height: 50vh">
    <main class="form-signin w-100 m-auto">
        <form action="${path}/auth" method="post">
            <h1 class="h3 mb-3 fw-normal"> Login </h1>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div class="form-floating">
                <input type="text" class="form-control" id="name" name="name" placeholder="아이디를 입력해주세요" autocomplete="off" required>
                <label for="name"> ID </label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력해주세요" autocomplete="off" required>
                <label for="password">Password</label>
            </div>

            <div class="form-check text-start my-3">
                <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Remember me
                </label>
            </div>
            <button class="btn btn-primary w-100 py-2" type="submit"> 로그인 </button>
            <p class="d-flex justify-content-center mt-3"> 회원이 아니신가요? &nbsp; <a href="${path}/join" class="text-secondary"> 회원가입하기 </a></p>
        </form>
    </main>
</div>

<jsp:include page="../layout/footer.jsp" />
</body>
</html>
