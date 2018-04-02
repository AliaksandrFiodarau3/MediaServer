<script id="artistList" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <div>
        <h1 class="page-header"><fmt:message key="admin.table.title.artists"/></h1>
        <div class="row placeholders">
            {{#each artists}}
            <div class="col-xs-12 col-sm-2 placeholder">
                <div class="card">
                    <div class="card-img-top center-block">
                        <center>
                            <img src="{{image}}" class="img-thumbnail">
                        </center>
                        <center>
                            <a href="#" class="btn btn-default" onclick="getMethod('user/genres/{{genre.id}}/artists/{{id}}/albums', '#albumList','content')">
                                <span class="glyphicon glyphicon-search"/></a>
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
