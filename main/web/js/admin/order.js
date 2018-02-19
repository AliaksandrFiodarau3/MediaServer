function userOrder(id) {

    $.ajax({
        type: "GET",
        url: "Controller",
        data: {command: "show-order", userOrder: id},
        success: function (data) {
            var json = JSON.parse(data),
                list = $('#orderTable').html(),
                compileTemplate = Handlebars.compile(list),
                result = compileTemplate(json),
                content = doc.getElementById("content");
            content.innerHTML = result

        },
        dataType: "text"
    });
}


function deleteOrder(id) {
    $.ajax({
        type: "GET",
        url: "Controller",
        data: {command: "delete-order", idOrder: id},
        success: function (data) {
        },
        dataType: "text"
    });
}

function orderGoods(id) {
    $.ajax({
        type: "POST",
        url: "Controller",
        data: {command: "show-goods", idOrder: id},
        success: function (data) {
            var json = JSON.parse(data),
                list = $('#goodsTable').html(),
                compileTemplate = Handlebars.compile(list),
                result = compileTemplate(json),
                content = doc.getElementById("content");
            content.innerHTML = result;
        },
        dataType: "text"
    });
}

function deleteGood(id) {
    $.ajax({
        type: "GET",
        url: "Controller",
        data: {command: "delete-good", orderSongId: id},
        success: function (data) {
        },
        dataType: "text"
    });
}