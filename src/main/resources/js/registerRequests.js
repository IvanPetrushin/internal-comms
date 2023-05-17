import {signInButton, dataSignIn} from "./registerLogic.js"
const url = 'http://localhost:8080/users/'


if (signInButton) {
   // response = await fetch(`${u}`)
    signInButton.addEventListener('click', /*для отлади заменить 'submit' -> 'click' */ async () => {
        let response = await fetch(url, { // Отправляем JSON
            method: 'GET',
            body: dataSignIn,
            headers: {'Content-type': 'application.json'}
        })
        let incomeData = await response.json() // Читаем ответ
        console.log(incomeData)
    })
}
