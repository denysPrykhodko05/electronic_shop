$(document).ready( function () {
  var increase_amount_button = $(".amountOfProduct");
  var delete_button = $(".delete-button");
  var clear_cart_button = $("#clearCartButton");
  var make_order_button = $("#makeOrderButton");

  increase_amount_button.change(function () {
    var amount = $(this).val();
    var id = $(this).data(id).id;

    if (amount > 0 && amount <= 15) {
      $.ajax({
        url: "/addToCart.do",
        method: "POST",
        data: ({productId: id,amount:amount}),
        success: responseForAdd
      });
    }
  });

  delete_button.click(function () {
    var id = $(this).data(id).id;

    $.ajax({
      url: "/deleteProductFromCart.do",
      method: "POST",
      data: ({productId: id}),
      success: responseForDelete
    });
  });

  clear_cart_button.click(function () {
    $.ajax({
      url: "/clearCart.do",
      method: "POST",
      success:responseForClear
    });
  });

  make_order_button.click(function () {
    $.ajax({
      url: "/makeOrderUserCheck.do",
      success: responseForMakeOrder
    });
  });

  function responseForDelete(data) {
    data = JSON.parse(data);
    var success = data.success;
    var cartPrice = document.getElementById("cartPrice");
    var cartRef = document.getElementById("cartRef");

    if (cartPrice != undefined) {
      var productDiv = document.getElementById(data.productId);
      productDiv.parentNode.removeChild(productDiv);
      cartPrice.textContent = "Price: " + data.cartPrice;

      if(data.amount==1){
        cartRef.text = cartRef.text + "(" + data.amount + ")";
      }

      var name = cartRef.text.replace(/\(\d+\)/g,"(" + data.amount + ")");
      cartRef.text = name;
      return;
    }
    alert("Cannot delete product!!!");
  }

  function responseForAdd(data) {
    data = JSON.parse(data);
    var cartRef = document.getElementById("cartRef");
    var cartPrice = document.getElementById("cartPrice");
    cartPrice.textContent = "Price: " + data.cartPrice;

    if(data.amount==1){
          cartRef.text = cartRef.text + "(" + data.amount + ")";
    }
    var name = cartRef.text.replace(/\(\d+\)/g,"(" + data.amount + ")");
    cartRef.text = name;
    prev = data.amount;
  }

  function responseForClear(data) {
    data = JSON.parse(data);
    var productInfoBlock = document.getElementById("productInfoBlock");
    var productHolder = document.getElementById("productholder");
    var cartRef = document.getElementById("cartRef");
    var success = data.success;

    if (success = true) {
      productHolder.parentNode.removeChild(productholder);
      productInfoBlock.parentNode.removeChild(productInfoBlock);
      cartRef.text = "Cart";
      return;
    }

    alert("Cannot clear cart!!!");
  }

  function responseForMakeOrder(data) {
    data = JSON.parse(data);
    var success = data.success;

    if (success == true) {
      window.location = "/makeOrder.do";
      return;
    }

    window.location = "/login.do";
  }
});