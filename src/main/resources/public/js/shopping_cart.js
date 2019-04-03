function decrementQuantity(bookId) {
    var token = $('#_csrf').attr('content');
    var header = $('#_csrf_header').attr('content');

    $.ajax({
        url:'/cart/decrement/'+bookId,
        type:'POST',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success:function(data){
            $('.cart-increment-'+data.item.book.id).removeAttr("disabled");
            if(data.item.quantity == 1){
                $('.cart-decrement-'+data.item.book.id)[0].setAttribute("disabled", true);
            }

            updateItemValues(data.item);
            updateItemCount(data.itemCount);
            updateTotal(data.total);

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
        success:function(data){

            $('.cart-decrement-'+data.item.book.id).removeAttr("disabled");

            if(data.item.quantity == data.item.book.inventory){
                $('.cart-increment-'+data.item.book.id)[0].setAttribute("disabled", true);
            }

            updateItemValues(data.item);
            updateItemCount(data.itemCount);
            updateTotal(data.total);

        },
        error:function(error){
            console.log(error);
        }
    });
}

function updateItemValues(item){
    document.getElementById('cart-quantity-'+item.book.id).innerText = item.quantity;
    document.getElementById('cart-subtotal-'+item.book.id).innerText = item.subtotal;
}

function updateItemCount(itemCount){
    $('.cart-banner-counter')[0].innerText = itemCount;
    $('.item-count-value')[0].innerText = itemCount
}

function updateTotal(total){
    $('.cart-total-value')[0].innerText = total;
}
