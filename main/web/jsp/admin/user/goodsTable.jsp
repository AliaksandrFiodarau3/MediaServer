<%--Orders table --%>
<script id="goodsTable" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header">Goods</h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="text-center">Number</th>
                <th class="text-center">Song</th>
                <th class="text-center">Price</th>
                <th class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            {{#each goods}}
            <tr>
                <td class="text-center">{{id}}</td>
                <td class="text-center">{{song.title}}</td>
                <td class="text-center">{{song.price}}</td>
                <td class="text-center">
                    <a id="delete" data-userId="{{id}}" href="#" class="btn btn-danger btn-xs"
                       onclick="deleteGood('{{id}}')">
                        <span class="glyphicon glyphicon-remove"></span> Del
                    </a>
                </td>
            </tr>
            {{/each}}
            </tbody>
        </table>
</script>
