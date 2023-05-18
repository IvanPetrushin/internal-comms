const inputsPass = document.querySelectorAll('input[type=password]');
const iconsPass = document.querySelectorAll('span[type=img]');
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

let dataSignIn

const emailSignIn = document.getElementById("email-sign-in")
const passwordSignIn =  document.getElementById('password-sign-in')

if (signInButton) {
    signInButton.addEventListener('click', (evt) => {
        evt.preventDefault();
        dataSignIn = {
            "mail": String(emailSignIn.value).trim(),
            "password": String(passwordSignIn.value).trim()
        }
        console.log(dataSignIn);
    })
}

export {dataSignIn}
