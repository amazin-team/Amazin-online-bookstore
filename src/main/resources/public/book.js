$(document).ready(function() {
    $( ".display_book" ).click(function() {
        $.ajax({
            url: "./book.json"
        }).then(function (data) {
            $('#title').empty().append("book");
            let book = data._embedded.book;
            if (book.length === 0 ){
                $('.content').empty().append("No books were found");
            }
            else{
                let book_content= "";
                for(let i =0 ; i<book.length ;i ++){
                    console.log(book[i]);
                    book_content += `<br>${book[i].toString()} <br>`;
                }
                $('.content').empty().html(book_content);
            }
        });
        return false;
    });


    $( ".create_book" ).click(function() {

        $.ajax({
            url: "./book"
        }).then(function(data){
            $('.content').empty().html(data);
        });
        return false;
    });
});