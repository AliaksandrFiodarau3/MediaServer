<%--User table --%>
<script id="user-table" type="text/x-handlebars-template">
   <br>
   <br>
   <br>
    <h1 class="page-header">Users</h1>
    <a data-toggle="modal" data-target="#newUser" href="#" class="btn btn-primary btn-xs pull-right"><b>+</b>
        Add new user</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th class="text-center">Login</th>
            <th class="text-center">Name</th>
            <th class="text-center">Surname</th>
            <th class="text-center">Email</th>
            <th class="text-center">Photo</th>
            <th class="text-center">Root</th>
            <th class="text-center">Action</th>
        </tr>
        </thead>
        <tbody>
        {{#each users}}
        <tr>
            <td class="text-center">{{id}}</td>
            <td class="text-center">{{login}}</td>
            <td class="text-center">{{name}}</td>
            <td class="text-center">{{surname}}</td>
            <td class="text-center">{{email}}</td>
            <td class="text-center"><img src="{{photo}}" class="round" style="width: 100px; height: 100px;"></td>
            <td class="text-center">{{adminRoot}}</td>
            <td class="text-center">
                <a class='btn btn-info btn-xs edit' href="#" data-toggle="modal" data-target="#editUser"
                   onmouseover="editUser('{{id}}','{{login}}','{{name}}','{{surname}}','{{email}}','{{adminRoot}}')">

                    <span class="glyphicon glyphicon-edit"></span> Edit
                </a>
                <a href="#" class="btn btn-danger btn-xs" onclick="deleteUser('{{id}}')">
                    <span class="glyphicon glyphicon-remove"></span> Del
                </a>
                <a href="#" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#orderList"
                   onclick="userOrder('{{id}}')">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Orders
                </a>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>

    <%-- <div class="row col-md-10 col-md-offset-2 custyle">

         <table class="table table-striped custab">
             <thead>
             <a data-toggle="modal" data-target="#newUser" href="#" class="btn btn-primary btn-xs pull-right"><b>+</b>
                 Add new user</a>
             <tr>
                 <th class="text-center">Id</th>
                 <th class="text-center">Login</th>
                 <th class="text-center">Name</th>
                 <th class="text-center">Surname</th>
                 <th class="text-center">Email</th>
                 <th class="text-center">Photo</th>
                 <th class="text-center">Root</th>
                 <th class="text-center">Action</th>
             </tr>
             </thead>
             <tbody>
             {{#each users}}
             <tr>
                 <td class="text-center">{{id}}</td>
                 <td class="text-center">{{login}}</td>
                 <td class="text-center">{{name}}</td>
                 <td class="text-center">{{surname}}</td>
                 <td class="text-center">{{email}}</td>
                 <td class="text-center"><img src="{{photo}}" class="round" style="width: 100px; height: 100px;"></td>
                 <td class="text-center">{{adminRoot}}</td>
                 <td class="text-center">
                     <a class='btn btn-info btn-xs edit' href="#" data-toggle="modal" data-target="#editUser"
                        onmouseover="editUser('{{id}}','{{login}}','{{name}}','{{surname}}','{{email}}','{{adminRoot}}')">

                         <span class="glyphicon glyphicon-edit"></span> Edit
                     </a>
                     <a href="#" class="btn btn-danger btn-xs" onclick="deleteUser('{{id}}')">
                         <span class="glyphicon glyphicon-remove"></span> Del
                     </a>
                     <a href="#" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#orderList"
                        onclick="userOrder('{{id}}')">
                         <span class="glyphicon glyphicon-shopping-cart" ></span> Orders
                     </a>
                 </td>
             </tr>
             {{/each}}
             </tbody>
         </table>
     </div>--%>
</script>