<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <c:set var="locale" value="${pageContext.response.locale}"/>
    <fmt:setBundle basename="locale"/>
    <title>User page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/index/admin.css">
    <link rel="stylesheet" href="resources/css/index/admin/header.css">
    <link rel="stylesheet" href="resources/css/index/admin/menu.css">
    <link rel="stylesheet" href="resources/css/comments.css">
</head>
<body>
<header>
    <div id="user-nav" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <ul class="navbar-left">
                <li>
                    <a href="#" onclick="getMethod('user/genres', '#genreList', 'content')"><img class="center navbar-brand" src="resources/img/logo.png"></a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#" onclick="getMethod('user/profile', '#profile', 'profileModal')" data-toggle="modal" data-target="#profileModal"><i
                            class="glyphicon glyphicon-cog"></i><fmt:message key="admin.link.setting"/></a>
                </li>
                <li><a href="signOut"><i class="glyphicon glyphicon-log-out"></i>
                    <span class="text"><fmt:message key="label.sign.out"/></span></a>
                </li>
                <li class="btn-group">
                    <a class="btn btn-default" href="Controller?command=change-locale&lang=ru"><fmt:message
                            key="button.ru"/></a>
                    <a class="btn btn-default" href="Controller?command=change-locale&lang=en"><fmt:message
                            key="button.en"/></a>
                </li>
            </ul>
        </div>
    </div>
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

<%@include file='jsp/template/user/userGenreList.jsp' %>
<%@include file='jsp/template/user/profileList.jsp' %>
<%@include file='jsp/template/user/userAlbumList.jsp' %>
<%@include file='jsp/template/user/userArtistList.jsp' %>
<%@include file='jsp/template/user/userSongTable.jsp' %>
<%@include file='jsp/template/user/userGoodsList.jsp' %>
<%@include file='jsp/template/user/userCommentShow.jsp' %>

<%@include file='jsp/modal/profile.jsp' %>

<script src="resources/js/framework/jquery-3.2.1.js"></script>

<script src="resources/js/framework/bootstrap-select.js"></script>
<script src="resources/js/framework/bootstrap.js"></script>
<script src="resources/js/framework/handlebars-v4.0.10.js"></script>
<script src="resources/js/ajax-query.js"></script>
<script src="resources/js/user.js"></script>
</body>
</html>