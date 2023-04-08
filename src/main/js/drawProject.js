import {equalsEmp, currentUser} from "./main.js";

function drawProject(projects) {
    for (let [index, project] of projects.entries()) {

        let block = document.createElement('div');
        block.classList.add('block', 'project', `${index}`);

        const projectHeader = document.createElement('div');
        projectHeader.classList.add('project-header');

        const headerItems = document.createElement('div');
        headerItems.classList.add('project-header__items');
        headerItems.innerHTML += `<a class="project-name">${project.name} </a>`;
        headerItems.innerHTML += `<a class="priority">приоритет: ${project.priority} </a>`;
        headerItems.innerHTML += `<a class="expires">до ${project.expires}</a>`;

        projectHeader.appendChild(headerItems);
        projectHeader.innerHTML += `<a class="expand collapsed"><</a>`;

        const projectContent = document.createElement('div');
        projectContent.classList.add('project-content', 'hidden');

        const description = document.createElement('div');
        description.classList.add('project-description');
        description.innerHTML = project.description;

        const initFiles = document.createElement('div');
        initFiles.classList.add('block', 'project-files', 'init');

        let div = document.createElement('div');
        div.textContent = 'Файлы заказчика: '
        for (let file of project.ownerFiles) {
            div.innerHTML += `<a href="/server/${file}">${file}</a>, `;
        }

        initFiles.appendChild(div);
        initFiles.innerHTML += `<a class="action-file download">Скачать все</a>`;

        const userFiles = document.createElement('div');
        userFiles.classList.add('block', 'project-files', 'user');

        div = document.createElement('div');
        div.textContent = 'Файлы Вашего отчета: '
        let currentUserFiles = [];
        for (let element of project.loadedFiles) {
            if (equalsEmp(element.user, currentUser)) {
                currentUserFiles = element.files;
            }
        }
        for (let file of currentUserFiles) {
            div.innerHTML += `<a href="/server/${file}">${file}</a>, `;
        }

        userFiles.appendChild(div);
        userFiles.innerHTML += `<a class="action-file edit">Изменить</a>`;

        const projectBottom = document.createElement('div');
        projectBottom.classList.add('project-bottom');

        const projectBottomInfo = document.createElement('ul');
        projectBottomInfo.classList.add('project-bottom__info');
        projectBottomInfo.innerHTML = `<li class="owner">Заказчик задачи: ${project.owner.name} (${project.owner.group})</li>`;
        projectBottomInfo.innerHTML += `<li class="executor">Исполнитель: ${currentUser.name} (${currentUser.group})</li>`;

        const projectButtons = document.createElement('div');
        projectButtons.classList.add('project-buttons');
        projectButtons.innerHTML = `<a class="block button">Вопросы</a><a class="block button">Отправить</a>`;

        projectBottom.appendChild(projectBottomInfo);
        projectBottom.appendChild(projectButtons);

        projectContent.appendChild(description);
        projectContent.appendChild(initFiles);
        projectContent.appendChild(userFiles);
        projectContent.appendChild(projectBottom);

        block.appendChild(projectHeader);
        block.appendChild(projectContent);


        let projectWindow;
        if (project.expired) {
            projectWindow = document.querySelector('.projects-window.ended');
            block.querySelector('.action-file.edit').textContent = '';
            block.querySelector('.project-buttons').textContent = '';
        }
        else {
            projectWindow = document.querySelector('.projects-window.current');
        }
        projectWindow.appendChild(block);
    }
    if (projects.filter(item => item.expired).length === 0) {
        let window = document.querySelector('.projects-window.ended');
        window.innerHTML = `<div class="block empty">В данной категории еще нет проектов</div>`;
    }
    if (projects.filter(item => !item.expired).length === 0) {
        let window = document.querySelector('.projects-window.current');
        window.innerHTML = `<div class="block empty">В данной категории еще нет проектов</div>`;
    }
}

export {drawProject};