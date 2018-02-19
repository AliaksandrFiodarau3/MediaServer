<script id="genre-list" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header"><fmt:message key="admin.table.title.genres"/></h1>
    <a data-toggle="modal" data-target="#newGenre" href="#" onfocus="inputGenre('{{title}}')" class="btn btn-primary btn-xs pull-right"><b>+</b>
        <fmt:message key="admin.table.add.genre"/></a>
    <div class="row placeholders">
        {{#each genres}}
        <div class="col-xs-12 col-sm-2 placeholder">
            <div class="card">
                <div class="card-img-top center-block">
                    <center>
                        <img src="{{image}}" class="img-thumbnail">
                    </center>
                    <center>
                        <div class="btn-group">
                            <%--<a href="#" class="btn btn-default" &lt;%&ndash;onclick="inputGenre('{{id}','{{title}}','{{description}}')"&ndash;%&gt;>
                                <span class="glyphicon glyphicon-edit"/></a>--%>
                            <a href="#" class="btn btn-default" onclick="deleteGenre('{{id}}')">
                                <span class="glyphicon glyphicon-trash"/></a>
                            <a href="#" class="btn btn-default" onclick="showArtist('{{title}}')">
                                <span class="glyphicon glyphicon-search"/></a>
                        </div>
                    </center>
                </div>
                <div class="card-block">
                    <center>
                        <h4 class="card-title">{{title}}</h4>
                    </center>
                    <div class="card-text size">
                        <span class="text-muted">{{description}}</span>
                    </div>

                </div>
            </div>
        </div>
        {{/each}}
    </div>
</script>