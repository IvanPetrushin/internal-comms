const inputsPass = document.querySelectorAll('input[type=password]');
const iconsPass = document.querySelectorAll('span[type=img]');
export const signUpButton = document.getElementById('sign-up-button');
export const signInButton = document.getElementById('sign-in-button')
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

const userNameSignUp = document.getElementById("user_sign-up")
const userSurnameSignUp = document.getElementById("surname_sign-up")
const shopGroupSignUp = document.getElementById("shop-group")
const passwordSignUp = document.getElementById("password-sign-up")
const emailSignUp = document.getElementById("email-sign-up")
const passwordSignUpAgain =  document.getElementById('password-sign-up-again')
let dataSignUp
let dataSignIn

if (signUpButton) {
    signUpButton.addEventListener('click', () => {
        if (passwordSignUp.value === passwordSignUpAgain.value) {
            dataSignUp = {
                "UserNameSignUp": String(userNameSignUp.value).trim() + " " + String(userSurnameSignUp.value).trim(),
                "shopGroupSignUp": String(shopGroupSignUp.value).trim(),
                "passwordSignUp": String(passwordSignUp.value).trim(),
                "emailSignUp": String(emailSignUp.value).trim()
            }
            dataSignUp = JSON.stringify(dataSignUp)
        } else {
            alert("Не совпадают пароли")
            signUpButton.disabled = true
        }
    })
}

const emailSignIn = document.getElementById("email-sign-in")
const passwordSignIn =  document.getElementById('password-sign-in')

if (signInButton) {
    signInButton.addEventListener('click', () => {
        dataSignIn = {
            "emailSignIn": String(emailSignIn.value).trim(),
            "passwordSignIn": String(passwordSignIn.value).trim()
        }
        dataSignIn = JSON.stringify(dataSignIn)
    })
}

if (passwordSignUpAgain) {
    passwordSignUpAgain.addEventListener('change', () => {
        if (passwordSignUpAgain.value === passwordSignUp.value) {
            signUpButton.disabled = false
        }
    })
}

if (passwordSignUp) {
    passwordSignUp.addEventListener('change', () => {
        if (passwordSignUpAgain.value === passwordSignUp.value) {
            signUpButton.disabled = false
        }
    })
}

export {dataSignIn, dataSignUp}
