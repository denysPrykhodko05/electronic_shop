 $(document).ready(function () {
  var buy_button = $(".buy-button");

  buy_button.click(function () {
    var id = $(this).data(id).id;

    $.ajax({
      url: "/addToCart",
      method: "POST",
      data: ({productId: id}),
      success: response
    });
  });

  function response(data) {
    data = JSON.parse(data);
    var cartRef = document.getElementById("cartRef");

    if(data.amount==1){
      cartRef.text = cartRef.text + "(" + data.amount + ")";
    }
    var name = cartRef.text.replace(/\(\d+\)/g,"(" + data.amount + ")");
      cartRef.text = name;
    amount = data.amount;
  }
});