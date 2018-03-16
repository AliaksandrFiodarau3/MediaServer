var doc = document;

window.onload = function () {
    commandShow('user/genres', '#genre-list');

    doc.querySelector('#users').onclick = function () {
        commandShowWithPage('show-user-list', '#user-table', 1);
    }
    doc.querySelector('#bonuses').onclick = function () {
        commandShow('show-bonus', '#bonus-table');
    }
    doc.querySelector('#genres').onclick = function () {
        commandShow('show-genre', '#genre-list');
    }
}

function call() {

    var msg = $('#edit-user-form').serialize();

    $.ajax({
               type: "POST",
               url: "Controller",
               data: msg,
               success: function (data) {
                   commandShow('show-user', '#user-table');
               },
               dataType: "text"
           });
}

function commandShow(command, template) {

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: command},
               success: function (data) {

                   var json = JSON.parse(data),
                       table = $(template).html(),
                       compileTemplate = Handlebars.compile(table),
                       result = compileTemplate(json),
                       content = doc.getElementById("content");
                   content.innerHTML = result;
               },
               dataType: "text"
           });
}

function commandShowWithPage(command, template, page) {

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: command, userPage: page},
               success: function (data) {
                   var json = JSON.parse(data),
                       table = $(template).html(),
                       compileTemplate = Handlebars.compile(table),
                       result = compileTemplate(json),
                       content = doc.getElementById("content");
                   content.innerHTML = result;
               },
               dataType: "text"
           });
}

function commandShow(command, template, genre) {

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: command, genre: genre},
               success: function (data) {
                   try {
                       var json = JSON.parse(data),
                           list = $(template).html(),
                           compileTemplate = Handlebars.compile(list),
                           result = compileTemplate(json),
                           content = doc.getElementById("content");
                       content.innerHTML = result;

                       var arrEditButton = doc.querySelectorAll('.edit');
                       editEvents(arrEditButton, data);
                   } catch (err) {
                       var error = doc.getElementById("error");
                       error.innerHTML = data.toString();
                   }
               },
               dataType: "text"
           });
}

function editEvents(arrEditButton, data) {

    arrEditButton.forEach(function (item, index) {
        item.onclick = function (event) {
            var target = event.target;
            var id = target.dataset.id;
            var idUser = doc.getElementById('idUser');
            idUser.value = id;
        }
    });
}