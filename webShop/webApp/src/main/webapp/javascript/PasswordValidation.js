function dataValidation(password, confirmPassword){
    var regPassword = /^(?=.*[A-Z])(?=.*[0-9])\w{8,16}$/;
    
    var password = password.value;
    var co_password = confirmPassword.value;
    
    if (!regPassword.exec(password)) {
        var error = document.getElementById("password");
        error.setAttribute('class','invalid');
        alert("Incorrect password");
        return false;
    }

    if(password != co_password){
        var pass = document.getElementById("password");
        var clazz = pass.getAttribute("class");
        if(clazz != null && clazz.match("invalid")){
            pass.removeAttribute("class");
            pass.setAttribute('class','valid');
        }
        var error = document.getElementById("confirmPassword");
        error.setAttribute('class','invalid')
        alert("Incorrect confirm password");
        return false;
    }
}