import {currentUser, URL} from "./data.js";
import {expandButtonLogic} from "./expandButtonLogic.js";
import "./headerProfile.js";

const task = '12';
let response = await fetch(`${URL}/tasks/${task}` );
let project = JSON.parse(JSON.stringify(await response.json()));
console.log(project);

document.getElementById('project-name').textContent = project.name;
document.querySelector('title').textContent = project.name;
expandButtonLogic();
document.querySelector('.expand').click();

document.querySelector('.pre-description').innerHTML = project.description;
await drawMessages();
document.querySelector('li.owner').innerHTML = `<li class="owner">Заказчик задачи: ${project.owner.name} (${project.owner.id})</li>`;

const textarea = document.querySelector('.message-textarea');


// Фокус/расфокус поля сообщения
const placeholder = 'Ваше сообщение';
textarea.addEventListener('focusin', ()=> {
    if (textarea.textContent === placeholder) {
        textarea.innerHTML = '';
    }
});
textarea.addEventListener('focusout', ()=> {
    if (textarea.textContent === '') {
        textarea.innerHTML = placeholder;
    }
});

const sendMessageButton = document.querySelector('.send-message');
sendMessageButton.onclick = async function () {
    if (textarea.textContent.trim() !== '' && textarea.textContent.trim() !== placeholder) {
        const body = {
            'text': textarea.textContent.trim(),
            'time': (new Date).toISOString().slice(0,16).replace('T', ' '), // Формат 2023-05-13 18:36
            'user': {id: currentUser.id},
            'task': {id: project.id}
        };
        await fetch(`${URL}/messages`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(body),
        });
        console.log(textarea.innerHTML);
        textarea.innerHTML = '';
        // Обновление окна сообщений
        await drawMessages();
    }
    else {textarea.focus();}
};

async function drawMessages() {
    response = await fetch(`${URL}/tasks/${task}`);
    let project = JSON.parse(JSON.stringify(await response.json()));
    console.log(project);
    const chatWindow = document.querySelector('.chat-window');
    chatWindow.innerHTML = '';
    for (const message of project.messages) {
        const messageTemplate = document.getElementById('message-template').content.cloneNode(true);
        if (message.user.id !== currentUser.id) {
            messageTemplate.querySelector('.message-field').classList.add('foe');
        }
        console.log(message.user.group.id, project.owner.id);
        if (message.user.group.id === project.owner.id) {
            messageTemplate.querySelector('.message.block').classList.add('admin');
        }
        messageTemplate.querySelector('.message-owner').href = "${URL}/users/${message.user.id}";
        messageTemplate.querySelector('.message-owner').textContent = `${message.user.username} | ${message.user.group.name}`;
        messageTemplate.querySelector('.message-text').innerHTML = message.text;
        messageTemplate.querySelector('.message-time').innerHTML = message.time;

        chatWindow.appendChild(messageTemplate);
        chatWindow.scrollTo(0, chatWindow.scrollHeight);
    }
}
