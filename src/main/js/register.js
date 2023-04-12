const inputsPass = document.querySelectorAll('input[type=password]');
const iconsPass = document.querySelectorAll('span[type=img]');
const signUpButton = document.getElementById('sign-up');
iconsPass.forEach(icon => {
    icon.addEventListener('click', () => {
        inputsPass.forEach(input => {
            if (input.getAttribute('type') === 'password') {
                input.setAttribute('type', 'text');
            } else {
                input.setAttribute('type', 'password')
            }
        });
    });
});

let userName = document.getElementById("user_sign-up")
let userSurname = document.getElementById("surname_sign-up")
let shopGroup = document.getElementById("shop-group")
let password = document.getElementById("password-sign-up")
let email = document.getElementById("email-sign-up")
let dataJSON

let passwordSignUp = document.getElementById('password-sign-up')
let passwordSignUpAgain =  document.getElementById('password-sign-up-again')
signUpButton.addEventListener('click', () => {
    if (passwordSignUp.value === passwordSignUpAgain.value) {
        let data = {
            "UserName":userName.value,
            "UserSurname":userSurname.value,
            "shopGroup":shopGroup.value,
            "password":password.value,
            "email":email.value
        }
        dataJSON = JSON.stringify(data)
    } else {
        alert("Не совпадают пароли")
        signUpButton.disabled = true
    }
})

passwordSignUpAgain.addEventListener('change', () => {
    if (passwordSignUpAgain.value === passwordSignUp.value) {
        signUpButton.disabled = false
    }
})

passwordSignUp.addEventListener('change', () => {
    if (passwordSignUpAgain.value === passwordSignUp.value) {
        signUpButton.disabled = false
    }
})
