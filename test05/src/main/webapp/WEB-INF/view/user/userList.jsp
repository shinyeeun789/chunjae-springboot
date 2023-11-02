<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 회원 목록 </title>
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
                <h1> 회원 목록 </h1>
                <h2> 회원가입한 회원의 목록을 확인하세요 </h2>
            </div>

            <div class="content">
                <h2 class="content-subhead"> 회원 목록 </h2>
                <table class="pure-table pure-table-bordered">
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
                    <c:forEach var="detail" items="${userList}">
                        <tr onclick="javascript: location.href='${path}/user/detail.do?name=${detail.name}'" style="cursor: pointer">
                            <td> ${detail.id} </td>
                            <td> ${detail.name} </td>
                            <td> ${detail.username} </td>
                            <td> ${detail.email} </td>
                            <td> ${detail.address} </td>
                            <td> ${detail.tel} </td>
                            <td> ${detail.regdate} </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="${path}/resources/js/ui.js"></script>
</body>
</html>
