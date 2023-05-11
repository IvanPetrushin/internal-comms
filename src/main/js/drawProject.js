import {equalsEmp, currentUser} from "./data.js";

function drawProject(projects) {
    for (let [index, project] of projects.entries()) {

        let block = document.createElement('div');
        block.classList.add('block', 'project', `${index}`);

        const projectHeader = document.createElement('div');
        projectHeader.classList.add('project-header');

        const headerItems = document.createElement('div');
        headerItems.classList.add('project-header__items');
        headerItems.innerHTML += `<a class="project-name">${project.name} </a>`;
        const tempString = '✦'.repeat(project.priority);
        headerItems.innerHTML += `<a class="priority">приоритет: ${tempString} </a>`;
        headerItems.innerHTML += `<a class="expires">до ${project.deadline}</a>`;

        projectHeader.appendChild(headerItems);
        projectHeader.innerHTML += `<a class="expand collapsed"><</a>`;

        const projectContent = document.createElement('div');
        projectContent.classList.add('project-content', 'hidden');

        const description = document.createElement('pre');
        description.classList.add('project-description');
        description.innerHTML = project.description;

        const initFiles = document.createElement('div');
        initFiles.classList.add('block', 'project-files', 'init');

        let div = document.createElement('div');
        div.textContent = 'Связанные файлы: '
        for (let file of project.ownerFiles) {
            div.innerHTML += `<a href="/server/${file}">${file}</a>, `;
        }

        initFiles.appendChild(div);
        initFiles.innerHTML += `<a class="action-file download">Скачать все</a>`;

        const userBlocks = document.createElement('div');
        // Отрисовка разного интерфейса для созданных и исполняемых проектов
        if (project.owner.id !== currentUser.id) {
            const userFiles = document.createElement('div');
            userFiles.classList.add('block', 'project-files', 'user');

            div = document.createElement('div');
            div.textContent = 'Файлы Вашего отчета: '
            let currentUserFiles = [];
            for (let key of Object.keys(project.groups)) {
                if (key === currentUser.id) {
                    currentUserFiles = project.groups[key];
                }
            }
            for (let file of currentUserFiles) {
                div.innerHTML += `<a href="/server/${file}">${file}</a>, `;
            }

            userFiles.appendChild(div);
            userFiles.innerHTML += `<a class="action-file edit">Изменить</a>`;
            userBlocks.appendChild(userFiles);
        }
        else {
            initFiles.style.marginBottom = '15px';
            for (let key of Object.keys(project.groups)) {
                const userFiles = document.createElement('div');
                userFiles.classList.add('block', 'project-files', 'user');

                div = document.createElement('div');
                div.textContent = `Отчет ${key}: `

                let currentUserFiles = project.groups[key];
                for (let file of currentUserFiles) {
                    div.innerHTML += `<a href="/server/${file}">${file}</a>, `;
                }

                userFiles.appendChild(div);
                userFiles.innerHTML += `<a class="action-file edit">Принять</a>`;
                userBlocks.appendChild(userFiles);
            }
        }

        const projectBottom = document.createElement('div');
        projectBottom.classList.add('project-bottom');

        const projectBottomInfo = document.createElement('ul');
        projectBottomInfo.classList.add('project-bottom__info');
        projectBottomInfo.innerHTML = `<li class="owner">Заказчик задачи: ${project.owner.name} (${project.owner.group})</li>`;
        projectBottomInfo.innerHTML += `<li class="executor">Исполнитель: ${currentUser.name} (${currentUser.group})</li>`;

        const projectButtons = document.createElement('div');
        projectButtons.classList.add('project-buttons');
        projectButtons.innerHTML = `<a class="block button" href="../resources/forum.html">Вопросы</a><a class="block button">Отправить</a>`;

        projectBottom.appendChild(projectBottomInfo);
        projectBottom.appendChild(projectButtons);

        const projectContentInner = document.createElement('div');
        projectContentInner.classList.add('project-content__inner');

        projectContentInner.appendChild(description);
        projectContentInner.appendChild(initFiles);
        projectContentInner.appendChild(userBlocks);

        projectContent.appendChild(projectContentInner)
        projectContent.appendChild(projectBottom);

        block.appendChild(projectHeader);
        block.appendChild(projectContent);


        let projectWindow;
        if (Date.parse(project.deadline) < Date.now()) {
            projectWindow = document.querySelector('.projects-window.ended');
            block.querySelector('.action-file.edit').textContent = '';
            block.querySelector('.project-buttons').textContent = '';
        }
        else {
            projectWindow = document.querySelector('.projects-window.current');
        }
        projectWindow.appendChild(block);
    }
    if (projects.filter(item => Date.parse(item.deadline) < Date.now()).length === 0) {
        let window = document.querySelector('.projects-window.ended');
        window.innerHTML = `<div class="block empty">В данной категории еще нет проектов</div>`;
    }
    if (projects.filter(item => Date.parse(item.deadline) > Date.now()).length === 0) {
        let window = document.querySelector('.projects-window.current');
        window.innerHTML = `<div class="block empty">В данной категории еще нет проектов</div>`;
    }
}

export {drawProject};