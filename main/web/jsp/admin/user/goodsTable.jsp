<%--Orders table --%>
<script id="goodsTable" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header"><fmt:message key="admin.table.title.goods"/></h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="text-center"><fmt:message key="admin.table.goods.number"/></th>
                <th class="text-center"><fmt:message key="admin.table.goods.song"/></th>
                <th class="text-center"><fmt:message key="admin.table.goods.price"/></th>
                <th class="text-center"><fmt:message key="admin.table.goods.actions"/></th>
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
                        <span class="glyphicon glyphicon-remove"></span> <fmt:message key="admin.table.delete"/>
                    </a>
                </td>
            </tr>
            {{/each}}
            </tbody>
        </table>
</script>
