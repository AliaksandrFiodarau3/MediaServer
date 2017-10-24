<%--Bonus table --%>
<script id="bonus-table" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header">Bonuses</h1>
    <a href="#" data-toggle="modal" data-target="#newBonus" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new bonus</a>
    <table class="table">
        <thead>
        <tr>
            <th class="text-center">Id</th>
            <th class="text-center">Title</th>
            <th class="text-center">Description</th>
            <th class="text-center">Discount</th>
            <th class="text-center">Code</th>
            <th class="text-center">Actions</th>
        </tr>
        </thead>
        <tbody>
        {{#each bonuses}}
        <tr>
            <td class="text-center">{{id}}</td>
            <td class="text-center">{{title}}</td>
            <td class="text-center">{{description}}</td>
            <td class="text-center">{{discount}}</td>
            <td class="text-center">{{code}}</td>
            <td class="text-center">
                <a onclick="deleteBonus('{{id}}')" href="#" class="btn btn-danger btn-xs">
                    <span class="glyphicon glyphicon-remove"></span> Del
                </a>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
</script>