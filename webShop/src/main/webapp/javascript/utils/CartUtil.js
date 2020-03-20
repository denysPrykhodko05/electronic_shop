  var increase_amount_button = $(".amountOfProduct");
  var delete_button = $(".delete-button");
  var clear_cart_button = $("#clearCartButton");

  increase_amount_button.change(function(){
     var amount = $(this).val();
     var id = $(this).data(id).id;
     if(amount > 0 && amount <= 15){
         $.ajax({
         url: "/addToCart",
         method: "POST",
         data: ({productId: id,amount:amount}),
         success: responseForAdd
         });
      }
  });

  delete_button.click(function(){
      var id = $(this).data(id).id;
       $.ajax({
          url: "/deleteProductFromCart",
          method: "POST",
          data: ({productId: id}),
          success: responseForDelete
       });
  });

  clear_cart_button.click(function(){
      $.ajax({
        url: "/clearCart",
        method: "POST",
        success:responseForClear
      });
  });

  function responseForDelete(data){
     data = JSON.parse(data);
     var success = data.success;
     var cartPrice = document.getElementById("cartPrice");
     if(success==true){
        var productDiv = document.getElementById(data.productId);
        productDiv.parentNode.removeChild(productDiv);
        cartPrice.textContent = "Price: " + data.cartPrice;
        return;
     }
     alert("Cannot delete product!!!");
  }

  function responseForAdd(data){
  data = JSON.parse(data);
  var cartRef = document.getElementById("cartRef");
  var cartPrice = document.getElementById("cartPrice");
  cartPrice.textContent = "Price: " + data.cartPrice;
  cartRef.text = "Cart(" + data.amount + ")";
  }

  function responseForClear(data){
    data = JSON.parse(data);
    var productInfoBlock = document.getElementById("productInfoBlock");
    var productHolder = document.getElementById("productholder");
    var success = data.success;
    if(success = true){
      productHolder.parentNode.removeChild(productholder);
      productInfoBlock.parentNode.removeChild(productInfoBlock);
      return;
    }
    alert("Cannot clear cart!!!");
  }
