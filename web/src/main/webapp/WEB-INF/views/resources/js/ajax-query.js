var doc = document;



function getByGenre(id) {
    getMethod('admin/genre/'+ id +'/artists', '#artistList','content');
}

function getNode(nameClass) {
    if(nameClass[0] ==='.') {
        return document.querySelector(nameClass);
    }
    return document.getElementById(nameClass);
}

function getMethod(url, table, content, form) {
    ajaxRequest(url, table, "GET", content, form);
}

function postMethod(url, table, content, form) {
    ajaxRequest(url, table, "POST", content, form);
}

function putMethod(url, table, content, form) {
    ajaxRequest(url, table, "PUT", content, form);
}

function deleteMethod(url, table, content, form) {
    ajaxRequest(url, table, "DELETE", content, form);
}

function ajaxRequest(url, table, method, element, form) {
    $.ajax({
               type: method,
               url: url,
               success: function (data) {

                   if (form != null || form != undefined) {
                       data = $(form).serialize()
                   }

                   var json = JSON.parse(data),
                       list = $(table).html(),
                       compileTemplate = Handlebars.compile(list),
                       result = compileTemplate(json),
                       content = doc.getElementById(element);
                   content.innerHTML = result;
               },
               dataType: "text"
           });
}