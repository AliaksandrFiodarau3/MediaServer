<%--Song table --%>
<script id="songTable" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header">Songs</h1>
    <a data-toggle="modal" data-target="#newSong" href="#" onfocus="inputAlbum('{{album.title}}')" class="btn btn-primary btn-xs pull-right"><b>+</b>
        Add new song</a>

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
                    <a id="delete" onclick="deleteSong('{{id}}')" href="#" class="btn btn-danger btn-xs">
                        <span class="glyphicon glyphicon-remove"></span> Del
                    </a>
                </td>
            </tr>
            {{/each}}
            </tbody>
        </table>
</script>