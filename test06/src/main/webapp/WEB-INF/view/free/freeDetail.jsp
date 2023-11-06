<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<div class="w-50 m-auto pt-5 pb-5">
    <div class="d-flex justify-content-end">
        <div class="btn-group">
            <c:if test="${isAuthor}">
                <a href="${path}/free/edit?fno=${detail.fno}" class="btn btn-outline-dark"> 수정 </a>
                <a href="${path}/free/delete?fno=${detail.fno}" class="btn btn-outline-dark"> 삭제 </a>
            </c:if>
            <a href="${path}/free/list" class="btn btn-dark"> 목록 </a>
        </div>
    </div>
    <div class="row mt-5 mb-5">
        <div class="col-12">
            <label for="title"> 제목 </label>
            <input type="text" class="form-control" id="title" value="${detail.title}" readonly>
        </div>
        <div class="col mt-3">
            <label for="name"> 작성자 </label>
            <input type="text" class="form-control" id="name" value="${detail.name}" readonly>
        </div>
        <div class="col mt-3">
            <label for="resdate"> 작성일 </label>
            <input type="text" class="form-control" id="resdate" value="${detail.resdate}" readonly>
        </div>
        <div class="col-12 mt-3">
            <textarea class="form-control" name="freeContent" id="freeContent" cols="30" rows="10" readonly>${detail.content}</textarea>
        </div>
    </div>
    <div class="d-flex justify-content-between">
        <textarea cols="30" rows="3" id="content" name="content" class="form-control" style="resize: none"></textarea>
        <input type="button" id="addComment" class="btn btn-dark" value="전송">
    </div>
    <div>
        <c:forEach var="comment" items="${commentList}">
            <div class="mt-4">
                <div class="d-flex justify-content-between align-items-baseline">
                    <div class="d-flex justify-content-between align-items-baseline">
                        <h4 style="margin-right: 10px"> ${comment.name} </h4> · <p style="margin-left: 10px"> ${comment.resdate} </p>
                    </div>
                    <button class="border-0 bg-body" id="plusRecommend_${comment.cno}" value="${comment.cno}"> <i class="fa-regular fa-thumbs-up fa-xl"></i> ${comment.recommend} </button>
                </div>
                <p> ${comment.content} </p>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    $(document).ready(() => {
        $("#addComment").on("click", function() {
            $.ajax({
                url: "${path}/freeComment/insert",
                type:"post",
                data:JSON.stringify({ fno : "${detail.fno}", content : $("#content").val() }),
                dataType:"json",
                contentType: "application/json",
                success: function(result) {
                    console.log(result);
                    console.log("성공");
                },
                error: function(err) {
                    console.log(err);
                }
            });
        });

        $(document).on("click", "button[id^='plusRecommend']", function() {
            $.ajax({
                url: "${path}/freeComment/plusRecommend",
                type:"post",
                data:JSON.stringify({ cno : $(this).val() }),
                dataType:"json",
                contentType: "application/json",
                success: function(result) {
                    $("#plusRecommend_" + result.cno).html('<i class="fa-regular fa-thumbs-up fa-xl"></i> ' + result.recommend);
                },
                error: function(err) {
                    console.log(err);
                }
            });
        })
    });
</script>

<jsp:include page="../layout/footer.jsp" />
</body>
</html>
