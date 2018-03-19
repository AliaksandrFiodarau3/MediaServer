function addSong() {

    var msg = $('.add-song-form').serialize();

    $.ajax({
               type: "POST",
               url: "Controller",
               data: msg,
               success: function () {
                   ajaxRequest('show-genre', '#genre-list');
               },
               dataType: "text"
           });
}