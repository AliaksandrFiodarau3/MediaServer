<%--Song table --%>
<script id="songTable" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header"><fmt:message key="admin.link.song"/></h1>
    <table class="table">
        <thead>
        <tr>
            <th class="text-center">id</th>
            <th class="text-center"><fmt:message key="admin.table.song.title"/></th>
            <th class="text-center"><fmt:message key="admin.table.song.duration"/></th>
            <th class="text-center"><fmt:message key="admin.table.song.price"/></th>
            <th class="text-center"><fmt:message key="admin.table.songs.actions"/></th>
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
                <a id="showComments" class='btn btn-info btn-xs' href="#" onclick="showComments('{{id}}')">
                    <span class="glyphicon  glyphicon-comment"></span><fmt:message key="admin.table.show"/>
                </a>
                <a id="addToCart" onclick="addGood('{{id}}')" href="#" class="btn  btn-info btn-xs">
                    <span class="glyphicon glyphicon-shopping-cart"></span><fmt:message
                        key="admin.table.songs.add.cart"/>
                </a>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
</script>