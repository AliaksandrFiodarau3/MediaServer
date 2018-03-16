<div id="user-nav" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <ul class="navbar-left">
            <li>
                <a href="#" onclick="commandShow('user/genres', '#genreList')"><img class="center navbar-brand" src="web/img/logo.png"></a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
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