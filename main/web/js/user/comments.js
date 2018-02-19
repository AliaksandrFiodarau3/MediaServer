$(document).ready(function () {
    $("[data-toggle=tooltip]").tooltip();
});



function showComments(id) {
    $.ajax({
        type: "POST",
        url: "Controller",
        data: {command: 'show-comment', idSong: id},
        success: function (data) {

            var json = JSON.parse(data),
                list = $('#commentsList').html(),
                compileTemplate = Handlebars.compile(list),
                result = compileTemplate(json),
                content = doc.getElementById("comments");

            content.innerHTML = result;
        },
        dataType: "text"
    });
}

function sendMessage() {

    var msg = $('.send-message-form').serialize();

    $.ajax({
        type: "POST",
        url: "Controller",
        data: msg,
        success: function (data) {
        },
        dataType: "text"
    });

}