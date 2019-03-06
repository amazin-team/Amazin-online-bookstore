$(document).ready(function() {
    $(".display_book").click(function() {
        $.ajax({
            url: "./book.json"
        }).then(function (data) {
            $('#title').empty().append("Available books");
            let book = data._embedded.book;
            if (book.length === 0 ) {
                $('.content').empty().append("No books were found");
            } else {
                let book_content= "<ul>";
                for (let i =0 ; i < book.length ; i++) {
                    book_content += `<li><code>${JSON.stringify(book[i], null, 2)}</code></li>`;
                }
                book_content += "</ul>"
                $('.content').empty().append(book_content);
            }
        });
        return false;
    });

    $(".create_book").click(function() {
        $.ajax({
            url: "./book"
        }).then(function(data) {
            $('#title').empty();
            $('.content').empty().append(data);
        });
        return false;
    });
});
