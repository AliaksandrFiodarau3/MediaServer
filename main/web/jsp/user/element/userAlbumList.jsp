<script id="albumList" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <div>
        <h1 class="page-header">Albums</h1>
        <div class="row placeholders">
            {{#each albums}}
            <div class="col-xs-12 col-sm-2 placeholder">
                <div class="card">
                    <div class="card-img-top center-block">
                        <center>
                            <img src="{{image}}" class="img-thumbnail">
                        </center>
                        <center>
                                <a href="#" class="btn btn-default" onclick="showSong('{{title}}')">
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

<%--    <div class="container-fluid">
        <div class="card">
            <ul>
                <div class="row">
                    {{#each albums}}
                    <li class="col-md-2 block img-figure">
                        <div class="product-item">
                            <div class="pi-img-wrapper">
                                <img class="img-responsive round" src="{{image}}">
                                <div>
                                    <a href="#" class="btn" onclick="showSong('{{title}}')">
                                        <span class="glyphicon glyphicon-search"/></a>
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
