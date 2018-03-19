var doc = document;
var lastParametr;
var loginUser = doc.getElementById("loginUser");

window.onload = function () {
    ajaxRequest('user/genres', '#genreList');
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

function getMethod(url, table) {
    ajaxRequest(url, table, "GET");
}

function postMethod(url, table) {
    ajaxRequest(url, table, "POST");
}

function putMethod(url, table) {
    ajaxRequest(url, table, "PUT");
}

function deleteMethod(url, table) {
    ajaxRequest(url, table, "DELETE");
}

function ajaxRequest(url, table, method) {
    $.ajax({
               type: method,
               url: url,

               success: function (data) {
                   var json =  JSON.parse(data),
                       list = $(table).html(),
                       compileTemplate = Handlebars.compile(list),
                       result = compileTemplate(json),
                       content = doc.getElementById("content");
                   console.log(JSON.parse(data));
                   content.innerHTML = result;
               },
               dataType: "text"
           });
}