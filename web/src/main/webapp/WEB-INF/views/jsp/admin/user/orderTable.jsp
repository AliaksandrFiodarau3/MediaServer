<%--Orders table --%>
<script id="orderTable" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header"><fmt:message key="admin.table.title.orders"/></h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th class="text-center"><fmt:message key="admin.table.orders.number"/></th>
            <th class="text-center"><fmt:message key="admin.table.orders.date"/></th>
            <th class="text-center"><fmt:message key="admin.table.orders.time"/></th>
            <th class="text-center"><fmt:message key="admin.table.orders.price"/></th>
            <th class="text-center"><fmt:message key="admin.table.orders.actions"/></th>
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
                    <span class="glyphicon glyphicon glyphicon-search"></span> <fmt:message key="admin.table.show"/>
                </a>
                <a id="delete" onclick="deleteOrder('{{id}}')" href="#" class="btn btn-danger btn-xs">
                    <span class="glyphicon glyphicon-remove"></span><fmt:message key="admin.table.delete"/>
                </a>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
</script>