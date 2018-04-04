<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <c:set var="locale" value="${pageContext.response.locale}"/>
    <fmt:setBundle basename="locale"/>

    <title>Admin page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/index/admin.css">
    <link rel="stylesheet" href="resources/css/index/admin/sidebar.css">
    <link rel="stylesheet" href="resources/css/index/admin/header.css">
    <link rel="stylesheet" href="resources/css/index/admin/menu.css">

</head>
<body>
<header>
    <div id="user-nav" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <ul class="navbar-left">
                <li>
                    <a href=""><img class="center navbar-brand" src="resources/img/logo.png"></a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                </li>
                <li>
                    <a href="#" data-toggle="modal" data-target="#profileModal"><i
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

<div class="container-fluid">
    <div class="row">
        <div class="sidebar">
            <ul class="nav left">
                <li>
                    <a class="item">
                        <span class="glyphicon glyphicon-menu-left"></span>
                    </a>
                </li>
                <li>
                    <a id="users" class="item" href="#users">
                        <span class="glyphicon glyphicon-user"></span>
                        <fmt:message key="admin.link.user"/></a>
                </li>
                <li>
                    <a id="bonuses" class="item" href="#bonuses">
                        <span class="glyphicon glyphicon-gift"></span>
                        <fmt:message key="admin.link.bonus"/></a>
                </li>
                <li>
                    <a id="genres" class="item" href="#genres">
                        <span class="glyphicon glyphicon-music"></span>
                        <fmt:message key="admin.link.music"/></a>
                </li>
            </ul>
        </div>
        <div class=" col-sm-offset-3 col-md-offset-2 main">
            <div id="content"></div>
        </div>
    </div>
</div>

<%@include file='jsp/template/admin/goodsTable.jsp' %>
<%@include file='jsp/template/admin/userTable.jsp' %>
<%@include file='jsp/template/admin/orderTable.jsp' %>
<%@include file='jsp/template/admin/bonusTable.jsp' %>
<%@include file='jsp/template/admin/genreList.jsp' %>
<%@include file='jsp/template/admin/artistList.jsp' %>
<%@include file='jsp/template/admin/albumList.jsp' %>
<%@include file='jsp/template/admin/songTable.jsp' %>


<%@include file='jsp/modal/editUser.jsp' %>
<%@include file='jsp/modal/editArtist.jsp' %>
<%@include file='jsp/modal/newUser.jsp' %>
<%@include file='jsp/modal/newGenre.jsp' %>
<%@include file='jsp/modal/newArtist.jsp' %>

<%@include file='jsp/modal/newSong.jsp' %>
<%@include file='jsp/modal/newAlbum.jsp' %>
<%@include file='jsp/modal/newBonus.jsp' %>
<%@include file='jsp/modal/profile.jsp' %>

<script src="resources/js/framework/jquery-3.2.1.js"></script>
<script src="resources/js/framework/bootstrap.js"></script>
<script src="resources/js/framework/handlebars-v4.0.10.js"></script>

<script src="resources/js/ajax-query.js"></script>
<script src="resources/js/admin.js"></script>

</body>
</html>
