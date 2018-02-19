<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-left center-block">
            <a class="navbar-brand" href="Controller?command=home"><img src="../../web/img/logo.png"></a>
        </div>
        <div class="navbar-collapse collapse">
            <!-- Sign in form -->
            <div class=" navbar-right center-block navbar-form">
                <ul>
                    <form method="post" action="/Controller" role="form">
                        <li class="btn-group">
                            <a class="btn btn-default" href="Controller?command=change-locale&lang=ru"><fmt:message
                                    key="button.ru"/></a>
                            <a class="btn btn-default" href="Controller?command=change-locale&lang=en"><fmt:message
                                    key="button.en"/></a>
                        </li>

                        <input type="hidden" name="command" value="sign-in">
                        <li>
                            <div class="form-group">
                                <input name="loginUser" tabindex="1" type="text"
                                       placeholder="<fmt:message key="label.login"/>"
                                       class="form-control" required>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <input name="passwordUser" type="password" tabindex="2"
                                       placeholder="<fmt:message key="label.password"/>"
                                       class="form-control" required>
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
                    </form>

                </ul>
            </div>
        </div>
    </div>
</div>