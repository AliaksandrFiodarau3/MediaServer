function addAlbum() {

    var msg = $('.add-album-form').serialize();

    $.ajax({
        type: "POST",
        url: "Controller",
        data: msg,
        success: function () {
            commandShow('show-genre', '#genre-list');
        },
        dataType: "text"
    });
}
/*
function editArtist (id,title, description, image) {
    var
        inputLogin = doc.getElementById("inputLogin"),
        inputName = doc.getElementById("inputName"),
        inputSurname = doc.getElementById("inputSurname"),
        idUser = doc.getElementById("idUserTable");

    idUser.value = id;
    inputLogin.value = title;
    inputName.value = description;
    inputSurname.value = image;

}
*/

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

function inputArtist(title) {

    console.log("album" + title);

    var album = doc.getElementById("artistAlbum");

    album.value = title;
}


function inputAlbum(title) {

    console.log("album" + title);

    var album = doc.getElementById("albumSong");

    album.value = title;
}


function deleteAlbum(id) {

    if (confirm("Вы уверены, что хотите удалить выделенный пункт?\nЭта операция не восстановима.")) {
        $.ajax({
            type: "GET",
            url: "Controller",
            data: {command: "delete-album", idAlbum: id},
            success: function (data) {
                commandShow('show-genre', '#genre-list');
            },
            dataType: "text"
        });
    }
}

function deleteSong(id) {
    if (confirm("Вы уверены, что хотите удалить выделенный пункт?\nЭта операция не восстановима.")) {
        $.ajax({
            type: "GET",
            url: "Controller",
            data: {command: "delete-song", idSong: id},
            success: function (data) {
                commandShow('show-genre', '#genre-list');
            },
            dataType: "text"
        });
    }
}