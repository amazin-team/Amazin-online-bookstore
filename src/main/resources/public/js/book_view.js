$(document).ready(function () {
        $('#book_table').DataTable({
                "language": {
                        "search": "Filter"
                }
        });
});

function addBook(bookId) {
        $.post("/addToCart/"+bookId, function(data){

        });
}
