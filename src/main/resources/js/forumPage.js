import {currentUser, URL} from "./data.js";
import {expandButtonLogic} from "./expandButtonLogic.js";
import "./headerProfile.js";

const task = location.href.split('/')[4]; //'17';
let response = await fetch(`${URL}/tasks/${task}`, {'Content-Type': 'multipart/form-data'});
let project = JSON.parse(JSON.stringify(await response.json()));
console.log(project);

document.getElementById('project-name').textContent = project.name;
document.querySelector('title').textContent = project.name;
document.querySelector('.priority').textContent = 'приоритет: ' + '✦'.repeat(project.priority);
document.querySelector('.expires').textContent = `до ${project.deadline}`;
expandButtonLogic();
document.querySelector('.expand').click();

document.querySelector('.pre-description').innerHTML = project.description;
await drawMessages();
document.querySelector('li.owner').innerHTML = `<li class="owner"> <a class="profile-link" href="${URL}/group/${project.owner.id}">Заказчик задачи: ${project.owner.name}<i class="mini">(${project.owner.id})</i></a></li>`;

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
            'text': textarea.innerHTML.trim(),
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
    console.log(project.messages);
    const chatWindow = document.querySelector('.chat-window');
    if (project.messages.length === 0) {
        chatWindow.innerHTML = '<div class="chat-placeholder"><h3>Здесь пока пусто</h3></div>';
    } else {
        chatWindow.innerHTML = '';
    }
    for (const message of project.messages) {
        const messageTemplate = document.getElementById('message-template').content.cloneNode(true);
        if (message.user.id !== currentUser.id) {
            messageTemplate.querySelector('.message-field').classList.add('foe');
        }
        console.log(message.user.group.id, project.owner.id);
        if (message.user.group.id === project.owner.id) {
            messageTemplate.querySelector('.message.block').classList.add('admin');
        }
        messageTemplate.querySelector('.message-owner').href = `${URL}/user/${message.user.id}`;
        messageTemplate.querySelector('.message-owner').textContent = `${message.user.username} | ${message.user.group.name}`;
        messageTemplate.querySelector('.message-text').innerHTML = message.text;
        messageTemplate.querySelector('.message-time').innerHTML = message.time;

        chatWindow.appendChild(messageTemplate);
        chatWindow.scrollTo(0, chatWindow.scrollHeight);
    }
}
