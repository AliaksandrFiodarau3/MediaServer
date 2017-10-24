<%--Orders table --%>
<script id="orderTable" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header">Orders</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th class="text-center">Number</th>
            <th class="text-center">Date</th>
            <th class="text-center">Time</th>
            <th class="text-center">Price</th>
            <th class="text-center">Actions</th>
        </tr>
        </thead>
        <tbody>
        {{#each orders}}
        <tr>
            <td class="text-center">{{number}}</td>
            <td class="text-center">{{date}}</td>
            <td class="text-center">{{time}}</td>
            <td class="text-center">{{price}}</td>
            <td class="text-center">
                <a id="showGoods" class='btn btn-info btn-xs' href="#"
                   data-userId="{{number}}" onclick="orderGoods('{{id}}')">
                    <span class="glyphicon glyphicon glyphicon-search"></span> Show
                </a>
                <a id="delete" onclick="deleteOrder('{{id}}')" href="#" class="btn btn-danger btn-xs">
                    <span class="glyphicon glyphicon-remove"></span> Del
                </a>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
</script>