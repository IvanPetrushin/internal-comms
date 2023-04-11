const inputsPass = document.querySelectorAll('input[type=password]');
const iconsPass = document.querySelectorAll('span[type=img]');
const inputsSignIn = [].slice.call(document.querySelectorAll('input[data-rule="sign-in"]'));
const inputsSignUp = [].slice.call(document.querySelectorAll('input[data-rule="sign-up"]'));
const signInButton = document.getElementById('sign-in');
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
inputsSignIn.forEach(function(el){
    el.addEventListener('input', checkInputsSignIn, false);
});
function checkInputsSignIn(){
    let empty = inputsSignIn.filter(function(el){
        return el.value.trim() === '';
    }).length;
    signInButton.disabled = empty !== 0;
}

inputsSignUp.forEach(function(el){
    el.addEventListener('input', checkInputsSignUp, false);
});
function checkInputsSignUp(){
    let empty = inputsSignUp.filter(function(el){
        return el.value.trim() === '';
    }).length;
    signUpButton.disabled = empty !== 0;
}

// signUpButton.addEventListener('click', () => {
//     if (document.getElementById('password-sign-up').value === document.getElementById('password-sign-up-again').value) {
//         return true
//     } else {
//         alert("Не совпадают пароли")
//         signUpButton.disabled = true
//         return false
//     }
// })

checkInputsSignUp()
checkInputsSignIn()



