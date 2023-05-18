import {signInButton, dataSignIn} from "./registerLogic.js"
const url = 'http://localhost:8080' //users/login'

//let response = await fetch(url)

if (signInButton) {
    signInButton.addEventListener('click', async (evt) => {
        evt.preventDefault();
        let response = await fetch(`${url}/users/login?mail=${dataSignIn.mail}&password=${dataSignIn.password}`);
        if (response.status === 200) {
            let userObj = JSON.parse(JSON.stringify(await response.json()));
            console.log(userObj);
            document.cookie = `loggedUserId=${userObj.id};max-age=3600`;
            location.replace(url+'/projects');
        } else {
            alert('Неверная почта или пароль');
        }
    } )
}
