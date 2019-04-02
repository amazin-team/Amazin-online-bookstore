$(document).ready(function () {
        $('#book_table').DataTable({
                "language": {
                        "search": "Filter"
                }
        });
});

function addBookToCart(bookId) {
        var token = $('#_csrf').attr('content');
        var header = $('#_csrf_header').attr('content');
        var withCredentials = true;

        $.ajax({
                url:'/cart/addToCart/'+bookId,
                type:'POST',
                beforeSend: function(xhr) {
                        xhr.setRequestHeader(header, token, withCredentials);
                },
                success:function(data){
                        console.log(data)
                },
                error:function(error){
                        console.log(error);
                }
        });
}
