function genreInput(genre) {
    var genreArtist = doc.getElementById("genreArtist");
    genreArtist.value = genre;
}

function addArtist() {

    var msg = $('.add-artist-form').serialize();

    $.ajax({
               type: "POST",
               url: "Controller",
               data: msg,
               success: function (data) {
                   commandShow('show-genre', '#genre-list');
               },
               dataType: "text"
           });
}

function inputArtistEditForm(id, title, description, image) {
    var inputIdArtist = doc.getElementById("idArtist"),
        inputTitleArtist = doc.getElementById("titleArtist"),
        inputDescriptionArtist = doc.getElementById("descriptionArtist"),
        inputImageArtist = doc.getElementById("imageArtist");

    inputIdArtist.value = id;
    inputTitleArtist.value = title;
    inputDescriptionArtist.value = description;
    inputImageArtist.value = image;
}

function editArtist() {

    var msg = $('#edit-artist-form').serialize();

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

function deleteArtist(id) {
    if (confirm("Вы уверены, что хотите удалить выделенный пункт?\nЭта операция не восстановима.")) {
        $.ajax({
                   type: "GET",
                   url: "Controller",
                   data: {command: "delete-artist", idArtist: id},
                   success: function (data) {
                       commandShow('show-genre', '#genre-list');
                   },
                   dataType: "text"
               });
    }
}
