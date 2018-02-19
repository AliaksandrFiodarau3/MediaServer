<script id="commentsList" type="text/x-handlebars-template">

    <h1 class="page-header"><fmt:message key="comment.title"/> {{song.title}}</h1>
    <%--Comments--%>
    {{#each comments}}
    <div class="row">
        <div class="col-sm-1">
            <div class="thumbnail">
                <img class="img-responsive user-photo" src="{{user.photo}}">
            </div>
        </div>

        <div class="col-sm-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="text-muted"><fmt:message key="admin.table.orders.date"/>: {{commentDate}}
                        <fmt:message key="admin.table.orders.time"/>: {{commentTime}}</span>
                </div>
                <div class="panel-body">
                    {{commentText}}
                </div>
            </div>
        </div>
    </div>
    {{/each}}
    <%--Close comments--%>

    <%-- Input Message --%>
    <div class="row">
        <div class="col-md-6">
            <div class="widget-area no-padding blank">
                <div class="status-upload">
                    <form class="send-message-form" method="POST" action="javascript:void(null);"
                          onsubmit="sendMessage()">
                        <input name="command" value="send-message" hidden>
                        <input name="idSong" value="{{song.id}}" hidden>
                        <textarea name="textMessage" placeholder="<fmt:message key="comment.placeholder"/>"></textarea><%--todo --%>
                        <button type="submit" class="btn btn-success green"><i class="fa fa-share"></i><fmt:message
                                key="admin.table.send"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%--Close input--%>

</script>