$(document).ready(function() {
  var field = '#amountOfProductsOnPage';
  $(field).change(function(e) {
        var temp = e.currentTarget.valueAsNumber;
        if(temp<=0){
           e.currentTarget.value = 0;
           $(field).removeClass(VALID_STRING).addClass(INVALID_STRING);
           return;
        }
           $(field).removeClass(INVALID_STRING).addClass(VALID_STRING);
  });
  $('#amountOfProductsForm').on('submit',function(){
      var valid = $(field).hasClass(INVALID_STRING);
      if(valid){
        event.preventDefault();
        return;
      }
      $(field).removeClass(INVALID_STRING).addClass(VALID_STRING);
      $('#amountOfProductsForm').submit();
  });
});