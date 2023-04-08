const inputsPass = document.querySelectorAll('input[type=password]');
const iconsPass = document.querySelectorAll('span[type=img]');
const buttons = document.querySelectorAll('input[type=submit]');
const form = document.querySelector('form');

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

// form.addEventListener('change', changeFormHandler)
//
// function changeFormHandler() {
//     console.log(form.checkValidity());
//     if (form.checkValidity()) {
//         buttons.forEach(button => {
//             button.removeAttribute('disabled');
//         });
//     }
// }


