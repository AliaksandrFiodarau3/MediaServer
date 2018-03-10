function editUser(id, login, name, surname, email, root) {

    var
        inputLogin = doc.getElementById("inputLogin"),
        inputName = doc.getElementById("inputName"),
        inputSurname = doc.getElementById("inputSurname"),
        inputEmail = doc.getElementById("inputEmail"),
        inputRoot = doc.getElementById("inputRoot"),
        idUser = doc.getElementById("idUserTable");

    idUser.value = id;
    inputLogin.value = login;
    inputName.value = name;
    inputSurname.value = surname;
    inputEmail.value = email;
    inputRoot.value = root;
}

function addUser() {

    var msg = $('.add-user-form').serialize();

    $.ajax({
               type: "POST",
               url: "Controller",
               data: msg,
               success: function (data) {
               },
               dataType: "text"
           });
}

function deleteUser(id) {

    if (confirm("Вы уверены, что хотите удалить выделенный пункт?\nЭта операция не восстановима.")) {
        $.ajax({
                   type: "GET",
                   url: "Controller",
                   data: {command: "delete-user", idUser: id},
                   success: function (data) {
                       if (data === 'true') {
                           commandShow('show-user', '#user-table');
                       } else {
                           console.log(data.toString());
                       }
                   },
                   dataType: "text"
               });
    }
}

function searchUser(command,  value) {

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: command, searchUser: value},
               success: function (data) {
                   var json = JSON.parse(data),
                       table = $('#user-table').html(),
                       compileTemplate = Handlebars.compile(table),
                       result = compileTemplate(json),
                       content = doc.getElementById("content");
                   content.innerHTML = result;
               },
               dataType: "text"
    });
}
    $(document).keypress(function (e) {
        if (e.which == 13) {
            document.getElementById("search-user").click();
            document.getElementById("my-input").value = "";
            alert("Pressed");
        }
    });