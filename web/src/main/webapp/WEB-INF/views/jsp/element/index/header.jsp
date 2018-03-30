<jsp:useBean id="_csrf" scope="request" type="org.springframework.web.bind.MissingServletRequestParameterException"/>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-left center-block">
            <a class="navbar-brand" href=""><img src="web/img/logo.png"></a>
        </div>
        <div class="navbar-collapse collapse">
            <!-- Sign in form -->
            <div class=" navbar-right center-block navbar-form">
                <ul>
                    <form action="/login" method="post">       1
                      <%--  <c:if test="${param.error != null}">        2
                            <p>
                                Invalid username and password.
                            </p>
                        </c:if>
                        <c:if test="${param.logout != null}">       3
                            <p>
                                You have been logged out.
                            </p>
                        </c:if>--%>
                        <p>
                            <label for="username">Username</label>
                            <input type="text" id="username" name="username"/>	4
                        </p>
                        <p>
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password"/>	5
                        </p>
                        <input type="hidden"                        6
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <button type="submit" class="btn">Log in</button>
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