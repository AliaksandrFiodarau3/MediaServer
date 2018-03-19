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
                           ajaxRequest('show-user', '#user-table');
                       } else {
                           console.log(data.toString());
                       }
                   },
                   dataType: "text"
               });
    }
}