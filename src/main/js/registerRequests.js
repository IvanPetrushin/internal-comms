import {signUpButton, signInButton, dataSignIn, dataSignUp} from "./registerLogic.js"
const url = 'https://httpbin.org/anything'

if (signUpButton) {
    signUpButton.addEventListener('click', /*для отлади заменить 'submit' -> 'click' */ async () => {
        let response = await fetch(url, { // Отправляем JSON
            method: 'POST',
            body: dataSignUp,
            headers: {'Content-type': 'application.json'}
        })
        let incomeData = await response.json() // Читаем ответ
        console.log(incomeData)
    })
}

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
