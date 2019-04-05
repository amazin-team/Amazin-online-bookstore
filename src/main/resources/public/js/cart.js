
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

            if(data.itemCount != null){
                if($('.cart-banner-counter').length == 0){
                    $('.cart-icon').append('<span class="badge badge-danger badge-counter cart-banner-counter">' + data.itemCount+ '</span>');
                }else{
                    $('.cart-banner-counter')[0].innerText = data.itemCount;
                }
            }

        },
        error:function(error){
            console.log(error);
        }
    });
}
