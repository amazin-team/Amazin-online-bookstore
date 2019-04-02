$(document).ready(function(){
    //var cartTable = $('#shopping-cart-table');
    //cartTable.DataTable();
    //cartTable.addClass("stripe");

});


function decrementQuantity(bookId) {
    var token = $('#_csrf').attr('content');
    var header = $('#_csrf_header').attr('content');

    $.ajax({
        url:'/cart/decrement/'+bookId,
        type:'POST',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        //data:post_data,
        success:function(data){
            document.getElementById('cart-quantity-'+data.book.id).innerText = "Qty: " + data.quantity;

            if(data.quantity == 1){
                $('.cart-decrement-'+data.book.id)[0].setAttribute("disabled", true);
            }

            $('.cart-banner-counter')[0].innerText = data.itemCount;
        },
        error:function(error){
            console.log(error);
        }
    });
}

function incrementQuantity(bookId){
    var token = $('#_csrf').attr('content');
    var header = $('#_csrf_header').attr('content');

    $.ajax({
        url:'/cart/increment/'+bookId,
        type:'POST',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        //data:post_data,
        success:function(data){
            $('.cart-decrement-'+data.book.id).removeAttr("disabled");
            document.getElementById('cart-quantity-'+data.book.id).innerText = "Qty: " + data.quantity;
            document.getElementById('cart-subtotal-'+data.book.id).innerText = "Subtotal: $" + data.subtotal;

            $('.cart-banner-counter')[0].innerText = data.itemCount;
        },
        error:function(error){
            console.log(error);
        }
    });
}