<script id="albumList" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <div>
        <h1 class="page-header"><fmt:message key="admin.table.title.albums"/></h1>
        <a data-toggle="modal" data-target="#newAlbum" href="#" class="btn btn-primary btn-xs pull-right"  onfocus="inputArtist('{{artist.title}}')"><b>+</b>
            <fmt:message key="admin.table.add.album"/></a>
        <div class="row placeholders">
            {{#each albums}}
            <div class="col-xs-12 col-sm-2 placeholder">
                <div class="card">
                    <div class="card-img-top center-block">
                        <center>
                            <img src="{{image}}" class="img-thumbnail">
                        </center>
                        <center>
                            <div class="btn-group">
                                <a href="#" class="btn btn-default" onclick="deleteAlbum('{{id}}')">
                                    <span class="glyphicon glyphicon-trash"/></a>
                                <a href="#" class="btn btn-default" onclick="showSong('{{title}}')">
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
