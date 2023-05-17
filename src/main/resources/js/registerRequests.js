import {signInButton, dataSignIn} from "./registerLogic.js"
const url = 'http://localhost:8080/users/login'

//let response = await fetch(url)

if (signInButton) {
    async function sendUserInfo() {
       // response = await fetch(url)
        signInButton.addEventListener('click', /*для отлади заменить 'submit' -> 'click' */ async () => {
            let response = await fetch(url, { // Отправляем JSON
                method: 'POST',
                body: dataSignIn,
                headers: {'Content-type': 'application.json'}
            })
            let incomeData = await response.json() // Читаем ответ
            console.log(incomeData)
        })
    }

    async function getUserInfo() {
        let response = await fetch(url);
        signInButton.addEventListener('click', async () => {
            let response = await fetch(url);
            let info = JSON.parse(JSON.stringify(await response.json()));
            if (info == null) {
                alert("Ошибка")
                signInButton.formAction = "";
            }
        } )

    }
}
