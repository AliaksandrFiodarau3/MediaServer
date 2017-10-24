<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <c:set var="locale" value="${pageContext.response.locale}"/>
    <fmt:setBundle basename="locale"/>
    <title>User page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/index/admin.css">
    <link rel="stylesheet" href="css/index/admin/header.css">
    <link rel="stylesheet" href="css/index/admin/menu.css">
    <link rel="stylesheet" href="css/comments.css">
</head>
<body>
<header>
    <%@include file='jsp/user/headerUser.jsp' %>
</header>


    <div class="row">
        <div class="col-md-2"></div>
        <div class="main col-md-8 container">
            <div id="content"></div>
        </div>
        <div id="order" class="col-md-2 left"></div>
    </div>
    <div class="row">
        <div id="comments" class="container"></div>
    </div>

<%@include file='jsp/user/element/userGenreList.jsp' %>
<%@include file='jsp/user/element/userAlbumList.jsp' %>
<%@include file='jsp/user/element/userArtistList.jsp' %>
<%@include file='jsp/user/element/userSongTable.jsp' %>
<%@include file='jsp/user/element/userGoodsList.jsp' %>
<%@include file='jsp/user/element/userCommentShow.jsp' %>

<%@include file='jsp/element/modal/order.jsp' %>
<%@include file='jsp/element/modal/profile.jsp' %>

<script src="js/framework/jquery-3.2.1.js"></script>
<script src="js/framework/bootstrap.js"></script>
<script src="js/framework/handlebars-v4.0.10.js"></script>
<script src="js/user/show.js"></script>
<script src="js/user/comments.js"></script>
</body>
</html>