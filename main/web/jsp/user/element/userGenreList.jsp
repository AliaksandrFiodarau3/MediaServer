<script id="genreList" type="text/x-handlebars-template">


    <br>
    <br>
    <br>
    <h1 class="page-header">Genres</h1>
    <div class="row placeholders">
        {{#each genres}}
        <div class="col-xs-12 col-sm-2 placeholder">
            <div class="card">
                <div class="card-img-top center-block">
                    <center>
                        <img src="{{image}}" class="img-thumbnail">
                    </center>
                    <center>
                            <a href="#" class="btn btn-default" onclick="showArtist('{{title}}')">
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


    <%--<div class="container-fluid">
        <div class="card">
            <ul>
                <div class="row">
                    {{#each genres}}
                    <li class="col-md-2 block img-figure">
                        <div class="product-item">
                            <div href="#" class="pi-img-wrapper" onclick="showArtist('{{title}}')">
                                <img class="img-responsive round" src="{{image}}" >
                                <div>
                                    &lt;%&ndash;<a href="#" class="btn" onclick="showArtist('{{title}}')">
                                        <span class="glyphicon glyphicon-search"/></a>&ndash;%&gt;
                                </div>
                            </div>
                        </div>
                    </li>
                    {{/each}}
                </div>
            </ul>
        </div>
    </div>--%>


</script>