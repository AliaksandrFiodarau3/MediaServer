window.onload = function () {
    getMethod('user/genres', '#genreList',"content");
    getMethod('user/order', '#userGoodsTable', "order");
}