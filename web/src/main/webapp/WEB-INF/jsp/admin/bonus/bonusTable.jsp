<%--Bonus table --%>
<script id="bonus-table" type="text/x-handlebars-template">
    <br>
    <br>
    <br>
    <h1 class="page-header"><fmt:message key="admin.table.title.bonuses"/></h1>
    <a href="#" data-toggle="modal" data-target="#newBonus"
       class="btn btn-primary btn-xs pull-right"><b>+</b><fmt:message key="admin.table.add.bonus"/></a>
    <table class="table">
        <thead>
        <tr>
            <th class="text-center">#</th>
            <th class="text-center"><fmt:message key="admin.table.bonuses.title"/></th>
            <th class="text-center"><fmt:message key="admin.table.bonus.description"/></th>
            <th class="text-center"><fmt:message key="admin.table.bonus.discount"/></th>
            <th class="text-center"><fmt:message key="admin.table.bonuses.code"/></th>
            <th class="text-center"><fmt:message key="admin.table.bonuses.actions"/></th>
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
                    <span class="glyphicon glyphicon-remove"></span><fmt:message key="admin.table.delete"/>
                </a>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
</script>