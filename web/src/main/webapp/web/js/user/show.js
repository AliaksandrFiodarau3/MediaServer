var doc = document;
var lastParametr;
var loginUser = doc.getElementById("loginUser");

window.onload = function () {
    commandShow('show-genre', '#genreList');
}

function Buy() {

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: 'buy'},
               success: function () {
                   userGoodsShow();
               },
               dataType: "text"
           });

}

function useBonus() {

    var codeOrder = doc.getElementById("codeOrder");

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: 'use-bonus', codeBonus: codeOrder.value},
               success: function () {
                   userGoodsShow();
               },
               dataType: "text"
           });

}

function removeSong(id) {
    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: 'delete-user-good', idSong: id},
               success: function () {
                   userGoodsShow();
               },
               dataType: "text"
           });
}

function addOrder(loginUser) {
    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: 'add-order', userOrder: loginUser},
               success: function () {

               },
               dataType: "text"
           });
}

function showOrder(loginUser) {
    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: 'show-order', userOrder: loginUser},
               success: function () {
                   goodsShow();
               },
               dataType: "text"
           });
}

function addGood(id) {

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: 'add-good', idSong: id},
               success: function () {
                   userGoodsShow();
               },
               dataType: "text"
           });

}

function userGoodsShow() {

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: 'show-user-goods'},
               success: function (data) {

                   var json = JSON.parse(data),
                       list = $('#userGoodsTable').html(),
                       compileTemplate = Handlebars.compile(list),
                       result = compileTemplate(json),
                       content = doc.getElementById("order");

                   content.innerHTML = result;
               },
               dataType: "text"
           });
}

function goodsShow() {

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: 'show-goods'},
               success: function (data) {

                   var json = JSON.parse(data),
                       list = $('#goodsTable').html(),
                       compileTemplate = Handlebars.compile(list),
                       result = compileTemplate(json),
                       content = doc.getElementById("order");

                   console.log(content);
                   content.innerHTML = result;
               },
               dataType: "text"
           });
}

function commandShow(command, table) {

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: command},
               success: function (data) {

                   var json = JSON.parse(data),
                       list = $(table).html(),
                       compileTemplate = Handlebars.compile(list),
                       result = compileTemplate(json),
                       content = doc.getElementById("content");

                   content.innerHTML = result;
               },
               dataType: "text"
           });
}

/*function commandShow(command, table, genre) {

    $.ajax({
        type: "GET",
        url: "Controller",
        data: {command: command, genre: genre},
        success: function (data) {
           /!* try {*!/
                var json = JSON.parse(data),
                    list = $(table).html(),
                    compileTemplate = Handlebars.compile(list),
                    result = compileTemplate(json),
                    content = doc.getElementById("content");
                content.innerHTML = result;

                /!*var arrEditButton = doc.querySelectorAll('.edit');
                editEvents(arrEditButton, data);*!/
            /!*} catch (err) {
                var error = doc.getElementById("error");
                error.innerHTML = data.toString();
            }*!/
        },
        dataType: "text"
    });
}*/

function showSong(title) {
    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: "show-song", albumSong: title},
               success: function (data) {
                   var json = JSON.parse(data),
                       list = $('#songTable').html(),
                       compileTemplate = Handlebars.compile(list),
                       result = compileTemplate(json),
                       content = doc.getElementById("content");
                   content.innerHTML = result;
               },
               dataType: "text"
           });
}

function showSong(title) {
    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: "show-song", albumSong: title},
               success: function (data) {
                   var json = JSON.parse(data),
                       list = $('#songTable').html(),
                       compileTemplate = Handlebars.compile(list),
                       result = compileTemplate(json),
                       content = doc.getElementById("content");
                   content.innerHTML = result;
               },
               dataType: "text"
           });
}

function showArtist(title) {
    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: "show-artist", titleGenre: title},
               success: function (data) {
                   try {
                       var json = JSON.parse(data),
                           list = $('#artistList').html(),
                           compileTemplate = Handlebars.compile(list),
                           result = compileTemplate(json),
                           content = doc.getElementById("content");
                       content.innerHTML = result;
                       lastParametr = title;
                   } catch (SyntaxError) {
                       alert(data.toString());
                   }
               },
               dataType: "text"
           });
}

function showAlbum(title) {
    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: "show-album", artistAlbum: title},
               success: function (data) {
                   var json = JSON.parse(data),
                       list = $('#albumList').html(),
                       compileTemplate = Handlebars.compile(list),
                       result = compileTemplate(json),
                       content = doc.getElementById("content");
                   content.innerHTML = result;
                   lastParametr = title;
               },
               dataType: "text"
           });
}

