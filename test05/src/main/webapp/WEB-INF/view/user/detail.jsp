<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 상세보기 </title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
    <link rel="stylesheet" href="${path}/resources/css/side-menu.css">
</head>
<body>
<div id="layout">
    <a href="#menu" id="menuLink" class="menu-link">
        <span></span>
    </a>

    <div id="menu">
        <div class="pure-menu">
            <a class="pure-menu-heading" href="#company">Company</a>

            <ul class="pure-menu-list">
                <li class="pure-menu-item">
                    <a href="#home" class="pure-menu-link">Home</a>
                </li>
                <li class="pure-menu-item">
                    <a href="${path}/user/list.do" class="pure-menu-link">회원목록</a>
                </li>
                <li class="pure-menu-item">
                    <a href="#" class="pure-menu-link">Services</a>
                </li>
                <li class="pure-menu-item">
                    <a href="#contact" class="pure-menu-link">Contact</a>
                </li>
            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1> 회원 상세정보 </h1>
            <h2> 선택한 회원의 상세정보를 확인하세요 </h2>
        </div>

        <div class="content">
            <h2 class="content-subhead"> 회원 상세정보 </h2>
            <table class="pure-table pure-table-bordered" style="width: 100%">
                <thead>
                <tr>
                    <th> # </th>
                    <th> 아이디 </th>
                    <th> 이름 </th>
                    <th> 이메일 </th>
                    <th> 주소 </th>
                    <th> 전화번호 </th>
                    <th> 가입일 </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td> ${user.id} </td>
                    <td> ${user.name} </td>
                    <td> ${user.username} </td>
                    <td> ${user.email} </td>
                    <td> ${user.address} </td>
                    <td> ${user.tel} </td>
                    <td> ${user.regdate} </td>
                </tr>
                </tbody>
            </table>
            <div class="pure-controls" style="display: flex; justify-content: end;">
                <a href="${path}/user/list.do" class="pure-button pure-button-primary" style="margin-top: 15px"> 회원 목록 </a>
            </div>
        </div>
    </div>
</div>
<script src="${path}/resources/js/ui.js"></script>
</body>
</html>
