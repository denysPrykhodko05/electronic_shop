$(document).ready(function() {
  numberValidator($('#amountOfProductsOnPage'));
  numberValidator($('#minPrice'));
  numberValidator($('#maxPrice'));

  $('#amountOfProductsForm').on('submit',function(){
      var valid = $(field).hasClass(INVALID_STRING);

      if(valid){
        event.preventDefault();
        return;
      }

      $(field).removeClass(INVALID_STRING).addClass(VALID_STRING);
      $('#amountOfProductsForm').submit();
  });

  function numberValidator(data){
     data.change(function(e) {
            var temp = e.currentTarget.valueAsNumber;

            if(temp<=0){
               e.currentTarget.value = 0;
               data.removeClass(VALID_STRING).addClass(INVALID_STRING);
               return;
            }

               data.removeClass(INVALID_STRING).addClass(VALID_STRING);
      });
    }
});