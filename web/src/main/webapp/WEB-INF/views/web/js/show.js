var doc = document;

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

                   if (typeof(form) !== "undefined" && form != null) data = $(form).serialize();

                   var json =  JSON.parse(data),
                       list = $(table).html(),
                       compileTemplate = Handlebars.compile(list),
                       result = compileTemplate(json),
                       content = doc.getElementById(element);
                  content.innerHTML = result;
               },
               dataType: "text"
           });
}