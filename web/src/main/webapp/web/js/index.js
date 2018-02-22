var doc = document;

window.onload = function () {

    document.querySelector('#inputLogin').onblur = function () {

        var inputLogin = document.getElementById('inputLogin').value;
        checkField('check-login', inputLogin);
    }
    document.querySelector('#inputEmail').onblur = function () {

        var inputEmail = document.getElementById('inputEmail').value;
        checkField('check-email', inputEmail);
    }
}

/*function requestForm() {

    var msg = $('#register-form').serialize();

    $.ajax({
        type: "POST",
        url: "Controller",
        data: msg,
        success: function (data) {
            var error = doc.getElementById("error");
            error.style = "color: red";
            error.innerHTML = data;
        },
        dataType: "text"
    });
}*/

function checkField(command, parameter) {

    var div = doc.getElementById('error');

    $.ajax({
               type: "POST",
               url: "Controller",
               data: {command: command, checkField: parameter},
               success: function (data) {
                   if (data != null) {
                       error.style = "color: red";
                       //div.className = "alert alert-danger";
                       div.innerHTML = data;
                   }
               }, error: function () {

            div.className = "";
        },
               dataType: "text"
           });
}