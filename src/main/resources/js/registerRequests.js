import {signInButton, dataSignIn} from "./registerLogic.js"
const url = 'http://localhost:8080/users/'


if (signInButton) {
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
