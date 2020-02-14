$(document).ready(function(){

    loginRealTimeValidation();
    emailRealTimeValidation();

    $("#reg").submit(function (){
    loginValidation($("#login"));
    emailValidation($("#email"));
    submit($("#reg"));
  });

  function loginRealTimeValidation(){
    $("#login").on('input',function(){
        var form = $("#reg");
        var input=$(this);
        var is_name=input.val();
        if(is_name.match(/^\w{3,16}$/)){
            input.removeClass("invalid").addClass("valid");
            form.removeClass("formInvalid");
        }
        else{
            input.removeClass("valid").addClass("invalid");
            form.addClass("formInvalid");
        }
        })
  }

  
  function emailRealTimeValidation(){
    $("#email").on('input',function(){
        var pattern = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        var form = $("#reg");
        var input=$(this);
        var is_name=input.val();
        if(is_name.match(pattern)){
            input.removeClass("invalid").addClass("valid");
            form.removeClass("formInvalid");
        }
        else{
            input.removeClass("valid").addClass("invalid");
            form.addClass("formInvalid");
        }
        })
  }

  function loginValidation(login){
    var value = login.val();
    var pattern = /^\w{3,16}$/;
    if(!value.match(pattern)){
        $("#reg").addClass("formInvalid");
        return;
    }
    $("#reg").removeClass("formInvalid");
  }
  function emailValidation(email){
    var value = email.val();
    var pattern =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(!value.match(pattern) || $("#reg").hasClass("formInvalid")){
        $("#reg").addClass("formInvalid");
        return;
    }
    $("#reg").removeClass("formInvalid");
  }
  
function submit(form){
    var valid =  form.hasClass("formInvalid");
    if  (valid){
        alert("Incorrect input")
      event.preventDefault();
      return;
    }
    form.submit();
}
});