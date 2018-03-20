window.onload = function () {
    getMethod('admin/genres', '#genre-list', "content");

    doc.querySelector('#users').onclick = function () {
        getMethod('admin/users', '#user-table', "content");
    }
    doc.querySelector('#bonuses').onclick = function () {
        getMethod('admin/bonuses', '#bonus-table', "content");
    }
    doc.querySelector('#genres').onclick = function () {
        getMethod('admin/genres', '#genre-list', "content");
    }
}