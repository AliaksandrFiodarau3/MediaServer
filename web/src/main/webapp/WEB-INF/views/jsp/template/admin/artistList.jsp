<script id="artistList" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <div>
        <h1 class="page-header"><fmt:message key="admin.table.title.artists"/></h1>
        <a data-toggle="modal" data-target="#newArtist" href="#" class="btn btn-primary btn-xs pull-right"
           onfocus="inputId('{{genreId}}', 'genreId')"><b>+</b>
            <fmt:message key="admin.table.add.artist"/></a>
        <div class="row placeholders">
            {{#each artists}}
            <div class="col-xs-12 col-sm-2 placeholder">
                <div class="card">
                    <div class="card-img-top center-block">
                        <center>
                            <img src="{{image}}" class="img-thumbnail">
                        </center>
                        <center>
                            <div class="btn-group">
                                <a href="#" class="btn btn-default"
                                   onclick="
                                   deleteMethod('admin/artist/{{id}}')">
                                    <span class="glyphicon glyphicon-trash"/></a>
                                <a href="#" class="btn btn-default"
                                   onclick="getByArtist('{{id}}')">
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
    </div>
</script>

