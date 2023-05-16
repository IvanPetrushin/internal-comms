import {currentUser, URL} from "./data.js";
import {expandButtonLogic} from "./expandButtonLogic.js";

const task = '679';
let response = await fetch(`${URL}/tasks/${task}` );
let project = JSON.parse(JSON.stringify(await response.json()));
project.ownerFiles = [];
project.groups = {123: [['file.jpg', 'another.pdf'], true], 302: [[], false]};
console.log(project);

document.getElementById('project-name').textContent = project.name;
document.querySelector('title').textContent = project.name;
expandButtonLogic();
document.querySelector('.expand').click();

document.querySelector('.pre-description').innerHTML = project.description;


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
        await fetch(`${URL}/messages`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: {
                'text': textarea.innerHTML,
                'time': (new Date).toISOString().slice(0,16).replace('T', ' '), // Формат 2023-05-13 18:36
                'user': currentUser.id,
                'task': project.id
            },
        });
        console.log(textarea.innerHTML);
        textarea.innerHTML = '';
        // Обновление окна сообщений
        await drawMessages();
    }
    else {textarea.focus();}
};

async function drawMessages() {
    response = await fetch(`${URL}/tasks/${task}` );
    let project = JSON.parse(JSON.stringify(await response.json()));
    project.ownerFiles = [];
    project.groups = {123: [['file.jpg', 'another.pdf'], true], 302: [[], false]};
    console.log(project);
    const chatWindow = document.querySelector('.chat-window');
    chatWindow.innerHTML = '';
    for (const message of project.messages) {
        const messageTemplate = document.getElementById('message-template').content.cloneNode(true);
        if (message.user.id !== currentUser.id) {
            messageTemplate.querySelector('.message-field').classList.add('foe');
        }
        if (message.user.group.id === project.owner.id) {
            messageTemplate.querySelector('.message.block').classList.add('admin');
        }
        messageTemplate.querySelector('.message-owner').innerHTML = `<a href="${URL}/users/${message.user.id}"></a>`;
        messageTemplate.querySelector('.message-text').innerHTML = message.text;
        messageTemplate.querySelector('.message-time').innerHTML = message.time;

        chatWindow.appendChild(messageTemplate);
        // const messageField = document.createElement('div');
        // messageField.classList.add('message-field');
        // if (message.user.id !== currentUser.id) {messageField.classList.add('foe')}
        //
        // const messageBlock = document.createElement('div');
        // messageBlock.classList.add('block', 'message');
        // if (message.user.group.id === project.owner.id) {messageBlock.classList.add('admin')}


    }
}