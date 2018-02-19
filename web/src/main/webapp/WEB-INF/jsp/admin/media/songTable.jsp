<%--Song table --%>
<script id="songTable" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header"><fmt:message key="admin.table.title.songs"/></h1>
    <a data-toggle="modal" data-target="#newSong" href="#" onfocus="inputAlbum('{{album.title}}')"
       class="btn btn-primary btn-xs pull-right"><b>+</b>
        <fmt:message key="admin.table.add.song"/></a>

    <table class="table">
        <thead>
        <tr>
            <th class="text-center">#</th>
            <th class="text-center"><fmt:message key="admin.table.song.title"/></th>
            <th class="text-center"><fmt:message key="admin.table.song.duration"/></th>
            <th class="text-center"><fmt:message key="admin.table.orders.price"/></th>
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
                <a id="delete" onclick="deleteSong('{{id}}')" href="#" class="btn btn-danger btn-xs">
                    <span class="glyphicon glyphicon-remove"></span> <fmt:message key="admin.table.delete"/>
                </a>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
</script>