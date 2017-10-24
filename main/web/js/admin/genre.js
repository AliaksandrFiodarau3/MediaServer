
function addGenre() {
    var msg = $('.add-genre-form').serialize();

    $.ajax({
        type: "POST",
        url: "Controller",
        data: msg,
        success: function () {
            commandShow('show-genre', '#genre-list');
        },
        error: function (xhr, error) {
            console.debug(xhr);
            console.debug(error);
        },
        dataType: "text"
    });
}


function inputGenre(title) {
    var genre = doc.getElementById("genreArtist");

    genre.value = title;
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
            }catch (SyntaxError){
                alert(data.toString());
            }
        },
        dataType: "text"
    });
}

function deleteGenre(id) {
    if (confirm("Вы уверены, что хотите удалить выделенный пункт?\nЭта операция не восстановима.")) {
        $.ajax({
            type: "GET",
            url: "Controller",
            data: {command: "delete-genre", idGenre: id},
            success: function () {
                commandShow('show-genre', '#genre-list');
            },
            dataType: "text"
        });
    }
}

/*
function editGenre(id, title, description) {
   /!* var inputDescription = doc.getElementById("descriptionGenre"),
        inputTitle = doc.getElementById("titleGenre"),
        idGenre = doc.getElementById("idGenre");

    idGenre.value = id;
    inputTitle.value = title;
    inputDescription.value = description;*!/
}*/