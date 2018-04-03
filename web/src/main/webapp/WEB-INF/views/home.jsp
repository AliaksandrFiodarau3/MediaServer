<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>

<html>
<head>
    <c:set var="locale" value="${pageContext.response.locale}"/>
    <fmt:setBundle basename="locale"/>
    <title>Media Server</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/index/carousel.css">
    <link rel="stylesheet" href="resources/css/index/index.css">

    <script src="resources/js/framework/jquery-3.2.1.js"></script>
    <script src="resources/js/framework/bootstrap.js"></script>
    <script src="resources/js/index.js"></script>
</head>


<body>
<header>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header navbar-left center-block">
                <a class="navbar-brand" href=""><img src="resources/img/logo.png"></a>
            </div>
            <div class="navbar-collapse collapse">
                <!-- Sign in form -->
                <div class=" navbar-right center-block navbar-form">
                    <ul>
                        <%--<form action="/login" method="post">
                            &lt;%&ndash;  <c:if test="${param.error != null}">
                                  <p>
                                      Invalid username and password.
                                  </p>
                              </c:if>
                              <c:if test="${param.logout != null}">
                                  <p>
                                      You have been logged out.
                                  </p>
                              </c:if>&ndash;%&gt;
                            <p>
                                <label for="username">Username</label>
                                <input type="text" id="username" name="username"/>
                            </p>
                            <p>
                                <label for="password">Password</label>
                                <input type="password" id="password" name="password"/>
                            </p>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                            <button type="submit" class="btn">Log in</button>
                        </form>--%>

                        <form action="/login" method="post">
                            <div class="form-group">
                                <label for="username">UserName:</label> <input type="text"
                                                                               class="form-control" id="username"
                                                                               name="username">
                            </div>
                            <div class="form-group">
                                <label for="pwd">Password:</label> <input type="password"
                                                                          class="form-control" id="pwd" name="password">
                            </div>

                            <button type="submit" class="btn btn-success">Submit</button>
<%--
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>--%>
                        </form>
                        <%--  <form method="POST" action="/login" role="form" >

                             &lt;%&ndash; <li class="btn-group">
                                  <a class="btn btn-default" href="Controller?command=change-locale&lang=ru"><fmt:message
                                          key="button.ru"/></a>
                                  <a class="btn btn-default" href="Controller?command=change-locale&lang=en"><fmt:message
                                          key="button.en"/></a>
                              </li>&ndash;%&gt;

                              <li>
                                  <div class="form-group">
                                      <input name="username" tabindex="1" type="text"
                                             placeholder="<fmt:message key="label.login"/>"
                                             class="form-control" autocomplete="off" required>
                                  </div>
                              </li>
                              <li>
                                  <div class="form-group">
                                      <input name="password" type="password" tabindex="2"
                                             placeholder="<fmt:message key="label.password"/>"
                                             class="form-control" autocomplete="off" required>
                                  </div>
                              </li>
                              <li>
                                  <button type="submit" class="btn btn-success">
                                      <fmt:message key="label.sign.in"/>
                                  </button>
                              </li>
                              <li>
                                  <!-- Button trigger modal -->
                                  <button type="button" class="btn btn-primary"
                                          data-toggle="modal" data-target="#registryModal">
                                      <fmt:message key="label.sign.up"/>
                                  </button>
                              </li>
                          </form>--%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="bs-example">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Carousel indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- Wrapper for carousel items -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="resources/img/index/carousel/slide1.jpg" alt="First Slide">
            </div>
            <div class="item">
                <img src="resources/img/index/carousel/slide2.jpg" alt="Second Slide">
            </div>
            <div class="item">
                <img src="resources/img/index/carousel/slide3.jpg" alt="Third Slide">
            </div>
        </div>
        <!-- Carousel controls -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>
</div>
<%@include file='jsp/modal/registry.jsp' %>

</body>
</html>