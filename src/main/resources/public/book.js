$(document).ready(function () {
    $(".display_book").click(function () {
        $.ajax({
            url: "./book.json"
        }).then(function (data) {
            $('#title').empty().append("Book Catalogue");
            let book = data._embedded.book;
            if (book.length === 0) {
                $('.content').empty().append("Book Catalogue Empty!");
            }
            else {
                let book_content = "";
                for (let i = 0; i < book.length; i++) {
                    book_content += "<div>";
                    book_content += `<p>Name: ${book[i]["name"]} </p><p>Description: ${book[i]["description"]} </p><p>Inventory: ${book[i]["inventory"]} </p><p>ISBN: ${book[i]["isbn"]} </p><p>Price: ${book[i]["price"]} </p><p>Author: ${book[i]["author"]} </p><p>Publisher: ${book[i]["publisher"]} </p><p>SKU: ${book[i]["sku"]} </p>`;
                    book_content += "</div><br><br>";
                }
                $('.content').empty().html(book_content);
            }
        });
        return false;
    });


    $(".create_book").click(function () {

        $.ajax({
            url: "./addbook"
        }).then(function (data) {
            $('.content').empty().html(data);
        });
        return false;
    });
});