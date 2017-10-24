<script id="commentsList" type="text/x-handlebars-template">

    <h1 class="page-header">Comments song {{song.title}}</h1>
    <%--Comments--%>
    {{#each comments}}
    <div class="row">
        <div class="col-sm-1">
            <div class="thumbnail">
                <img class="img-responsive user-photo" src="{{user.photo}}">
            </div><!-- /thumbnail -->
        </div><!-- /col-sm-1 -->

        <div class="col-sm-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                   <%-- <strong>{{user.name}} {{user.surname}}</strong>--%>
                    <span class="text-muted">Date: {{commentDate}} Time: {{commentTime}}</span>
                </div>
                <div class="panel-body">
                    {{commentText}}
                </div><!-- /panel-body -->
            </div><!-- /panel panel-default -->
        </div><!-- /col-sm-5 -->
    </div>
    {{/each}}
    <%--Close comments--%>

    <%-- Input Message --%>
    <div class="row">
        <div class="col-md-6">
            <div class="widget-area no-padding blank">
                <div class="status-upload">
                    <form class="send-message-form" method="POST" action="javascript:void(null);" onsubmit="sendMessage()">
                        <input name="command" value="send-message" hidden>
                        <input name="idSong" value="{{song.id}}" hidden>
                        <textarea name="textMessage" placeholder="Your message..."></textarea>
                        <button type="submit" class="btn btn-success green" ><i class="fa fa-share"></i>Send</button>
                    </form>
                </div><!-- Status Upload  -->
            </div><!-- Widget Area -->
        </div>
    </div>
    <%--Close input--%>

</script>