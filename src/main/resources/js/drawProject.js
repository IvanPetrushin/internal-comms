import {equalsEmp, currentUser, URL, currentGroup, projects} from "./data.js";

async function drawProject(projects) {
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
        if (project.ownerFiles !== undefined) {
            for (let file of project.ownerFiles) {
                div.innerHTML += `<a href="/server/${file}">${file}</a>, `;
            }
        }

        initFiles.appendChild(div);
        initFiles.innerHTML += `<a class="action-file download">Скачать все</a>`;

        const userBlocks = document.createElement('div');
        // Отрисовка разного интерфейса для созданных и исполняемых проектов
        if (project.owner.id !== currentUser.group.id) {
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
            for (let tempGroup of project.groups) {
                const userFiles = document.createElement('div');
                userFiles.classList.add('block', 'project-files', 'user');

                div = document.createElement('div');
                div.innerHTML = `<i class="status"></i>Отчет ${tempGroup.group.name}<i class="mini">(${tempGroup.group.id})</i>: `

                let currentUserFiles = project.groups[tempGroup];
                if (currentUserFiles !== undefined) {
                    for (let file of currentUserFiles) {
                        div.innerHTML += `<a href="/server/${file}">${file}</a>, `;
                    }
                }

                userFiles.appendChild(div);
                const userFilesBtn = document.createElement('div');

                const cond = {true: 'Отозвать', false: 'Принять'};
                if (tempGroup.condition) {
                    div.querySelector('.status').textContent = '✔️';
                }
                userFilesBtn.innerHTML += `<a class="action-file accept">${cond[tempGroup.condition]}</a>`;
                userFilesBtn.querySelector('.action-file.accept').addEventListener(
                    'click',
                    (evt) => {
                        setCondition(tempGroup, project);
                        if (evt.target.textContent === 'Отозвать') {
                            evt.target.textContent = cond.false;
                            evt.target.parentElement.parentElement.querySelector('.status').textContent = '';
                        } else {
                            evt.target.textContent = cond.true;
                            evt.target.parentElement.parentElement.querySelector('.status').textContent = '✔️';
                        }
                    }
                );

                userFiles.appendChild(userFilesBtn);

                userBlocks.appendChild(userFiles);
            }
        }

        const projectBottom = document.createElement('div');
        projectBottom.classList.add('project-bottom');

        const projectBottomInfo = document.createElement('ul');
        projectBottomInfo.classList.add('project-bottom__info');
        projectBottomInfo.innerHTML = `<li class="owner">Заказчик задачи: ${project.owner.name} (${project.owner.id})</li>`;

        const projectButtons = document.createElement('div');
        projectButtons.classList.add('project-buttons');
        projectButtons.innerHTML = `<a class="block button" href="${URL}/tasks/${project.id}">Подробнее</a>`;

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
        if (projectCompleted(project)) { //Date.parse(project.deadline) < Date.now() ||
            projectWindow = document.querySelector('.projects-window.ended');
            block.querySelector('.project-buttons').textContent = '';
        }
        else {
            projectWindow = document.querySelector('.projects-window.current');
            if (Date.parse(project.deadline) < Date.now()) {
                block.querySelector('.expires').classList.add('expired');
            }
        }
        projectWindow.appendChild(block);
    }
    if (document.querySelector('.projects-window.ended').innerHTML.trim() === '') {
        let window = document.querySelector('.projects-window.ended');
        window.innerHTML = `<div class="block empty">В данной категории еще нет проектов</div>`;
    }
    if (document.querySelector('.projects-window.current').innerHTML.trim() === '') {
        let window = document.querySelector('.projects-window.current');
        window.innerHTML = `<div class="block empty">В данной категории еще нет проектов</div>`;
    }
}

function projectCompleted(project) {
    if (project.owner.id === currentGroup.id) {
        if (project.groups.every(value => value.condition)) {
            console.log(project.groups);
            return true;
        }
    } else {
        if (project.groups.find(value => value.group.id === currentGroup.id).condition) {
            return true;
        }
    }
    return false;
}

async function setCondition(tempGroup, project) {
    const response = await fetch(`${URL}/tasks/${project.id}?groupID=${tempGroup.group.id}&condition=${!tempGroup.condition}`, {method: 'PUT'});
    console.log(response);
}

export {drawProject};