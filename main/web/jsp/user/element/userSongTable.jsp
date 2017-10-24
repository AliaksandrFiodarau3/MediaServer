<%--Song table --%>
<script id="songTable" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header">Songs</h1>
    <table class="table">
        <thead>
        <tr>
            <th class="text-center">id</th>
            <th class="text-center">Title</th>
            <th class="text-center">Duration</th>
            <th class="text-center">Price</th>
            <th class="text-center">Actions</th>
        </tr>
        </thead>
        <tbody>
        {{#each songs}}
        <tr>
            <td class="text-center">{{id}}</td>
            <td class="text-center">{{title}}</td>
            <td class="text-center">{{duration}}</td>
            <td class="text-center">{{price}}</td>
            <td class="text-center">
                <a id="showComments" class='btn btn-info btn-xs' href="#"  onclick="showComments('{{id}}')">
                    <span class="glyphicon  glyphicon-comment"></span> Show comments
                </a>
                <a id="addToCart" onclick="addGood('{{id}}')" href="#" class="btn  btn-info btn-xs">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Add to cart
                </a>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>

    <%--<div  class="row col-md-10 col-md-offset-2 custyle">
        <table class="table table-striped custab">
            <thead>
            <tr>
                <th class="text-center">id</th>
                <th class="text-center">Title</th>
                <th class="text-center">Duration</th>
                <th class="text-center">Price</th>
                <th class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            {{#each songs}}
            <tr>
                <td class="text-center">{{id}}</td>
                <td class="text-center">{{title}}</td>
                <td class="text-center">{{duration}}</td>
                <td class="text-center">{{price}}</td>
                <td class="text-center">
                    <a id="showComments" class='btn btn-info btn-xs' href="#">
                        <span class="glyphicon  glyphicon-comment"></span> Show comments
                    </a>
                    <a id="addToCart" onclick="addGood('{{id}}')" href="#" class="btn  btn-info btn-xs">
                        <span class="glyphicon glyphicon-shopping-cart"></span> Add to cart
                    </a>
                </td>
            </tr>
            {{/each}}
            </tbody>
        </table>
    </div>--%>
</script>