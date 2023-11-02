<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 회원가입 </title>
    <link rel="stylesheet" href="${path}/resources/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
</head>
<body>
<div>
    <div class="container mt-3">
        <h2 class="mt-2"> 회원정보 수정 </h2>
        <div class="container mt-3">
            <form action="${path}/user/userEdit.do" method="post">
                <input type="hidden" name="_method" value="PUT">
                <label for="name" class="form-label"> 아이디 </label>
                <input type="text" id="name" class="form-control w-75" placeholder="아이디를 입력하세요" value="${user.name}" readonly>

                <label for="password" class="form-label mt-3"> 비밀번호 </label>
                <input type="text" id="password" class="form-control" placeholder="비밀번호를 입력하세요">

                <label for="username" class="form-label mt-3"> 이름 </label>
                <input type="text" id="username" class="form-control" placeholder="이름을 입력하세요" value="${user.username}">

                <label for="email" class="form-label mt-3"> 이메일 </label>
                <input type="text" id="email" class="form-control" placeholder="이메일을 입력하세요" value="${user.email}">

                <label for="tel" class="form-label mt-3"> 전화번호 </label>
                <input type="text" id="tel" class="form-control" placeholder="전화번호를 입력하세요" value="${user.tel}">

                <label for="addr2" class="form-label mt-3"> 주소 </label>
                <input type="text" id="addr1" class="form-control mt-1" placeholder="주소를 입력해주세요" readonly>
                <input type="text" id="addr2" class="form-control mt-1" placeholder="상세 주소를 입력해주세요">
                <div class="d-flex justify-content-between mt-1">
                    <input type="text" id="postcode" class="form-control w-75" placeholder="우편번호를 입력해주세요" readonly>
                    <button class="btn btn-primary w-25" id="findAddr"> 주소 검색 </button>
                </div>
                <input type="hidden" id="address">
                <div class="d-flex justify-content-center mt-5">
                    <input type="submit" class="btn btn-dark btn-lg" value="회원정보 수정">
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(document).ready(() => {
        $("#findAddr").click(() => {
            new daum.Postcode({
                oncomplete:function(data){
                    let roadAddr = data.roadAddress;
                    let jibunAddr = data.jibunAddress;
                    document.getElementById("postcode").value = data.zonecode;
                    if(roadAddr !== ''){
                        document.getElementById("addr1").value = roadAddr;
                    } else if(jibunAddr !== ''){
                        document.getElementById("addr1").value = jibunAddr;
                    }
                    document.getElementById("addr2").focus();
                }
            }).open();
        });
    });
</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>
