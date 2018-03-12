<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <c:set var="locale" value="${pageContext.response.locale}"/>
    <fmt:setBundle basename="locale"/>

    <title>Admin page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../web/css/index/admin.css">
    <link rel="stylesheet" href="../../web/css/index/admin/sidebar.css">
    <link rel="stylesheet" href="../../web/css/index/admin/header.css">
    <link rel="stylesheet" href="../../web/css/index/admin/menu.css">

</head>
<body>
<header>
    <%@include file='/WEB-INF/jsp/admin/headerAdmin.jsp' %>
</header>

<div class="container-fluid">
    <div class="row">
        <div class="sidebar">
            <%@include file='/WEB-INF/jsp/admin/sidebar.jsp' %>
        </div>
        <div class=" col-sm-offset-3 col-md-offset-2 main">
            <div id="content"></div>
        </div>
    </div>
</div>

<%@include file='/WEB-INF/jsp/admin/user/goodsTable.jsp' %>
<%@include file='/WEB-INF/jsp/admin/user/userTable.jsp' %>
<%@include file='/WEB-INF/jsp/admin/user/orderTable.jsp' %>
<%@include file='/WEB-INF/jsp/admin/bonus/bonusTable.jsp' %>
<%@include file='/WEB-INF/jsp/admin/media/genreList.jsp' %>
<%@include file='/WEB-INF/jsp/admin/media/artistList.jsp' %>
<%@include file='/WEB-INF/jsp/admin/media/albumList.jsp' %>
<%@include file='/WEB-INF/jsp/admin/media/songTable.jsp' %>


<%@include file='/WEB-INF/jsp/element/modal/editUser.jsp' %>
<%@include file='/WEB-INF/jsp/element/modal/editArtist.jsp' %>
<%@include file='/WEB-INF/jsp/element/modal/newUser.jsp' %>
<%@include file='/WEB-INF/jsp/element/modal/newGenre.jsp' %>
<%@include file='/WEB-INF/jsp/element/modal/newArtist.jsp' %>

<%@include file='/WEB-INF/jsp/element/modal/newSong.jsp' %>
<%@include file='/WEB-INF/jsp/element/modal/newAlbum.jsp' %>
<%@include file='/WEB-INF/jsp/element/modal/newBonus.jsp' %>
<%@include file='/WEB-INF/jsp/element/modal/profile.jsp' %>

<script src="../../web/js/framework/jquery-3.2.1.js"></script>
<script src="../../web/js/framework/bootstrap.js"></script>
<script src="../../web/js/framework/handlebars-v4.0.10.js"></script>
<script src="../../web/js/admin/genre.js"></script>
<script src="../../web/js/admin/order.js"></script>
<script src="../../web/js/admin/user.js"></script>
<script src="../../web/js/admin/admin.js"></script>
<script src="../../web/js/admin/artist.js"></script>
<script src="../../web/js/admin/album.js"></script>
<script src="../../web/js/admin/song.js"></script>
<script src="../../web/js/admin/bonus.js"></script>
<script src="../../web/js/framework/pagination.js"></script>
<script src="../../web/js/framework/underscore.js"></script>
</body>
</html>
