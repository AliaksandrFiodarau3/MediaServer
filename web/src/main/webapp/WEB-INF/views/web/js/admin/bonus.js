function addBonus() {

    var msg = $('.add-bonus-form').serialize();

    $.ajax({
               type: "POST",
               url: "Controller",
               data: msg,
               success: function (data) {
                   if (data != null && data != undefined) {

                       var error = doc.getElementById("errorBonus");
                       error.className = "has-error alert alert-danger";
                       error.innerHTML = data.toString();
                   }
                   ajaxRequest('show-bonus', '#bonus-table');

               },
               dataType: "text"
           });
}

function deleteBonus(id) {

    if (confirm("Вы уверены, что хотите удалить выделенный пункт?\nЭта операция не восстановима.")) {
        $.ajax({
                   type: "GET",
                   url: "Controller",
                   data: {command: "delete-bonus", idBonus: id},
                   success: function () {
                       ajaxRequest('show-bonus', '#bonus-table');

                   },
                   dataType: "text"
               });
    }
}