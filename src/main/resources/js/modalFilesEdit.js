import {projects} from "./data.js";

function createModal(template) {
    const editFilesElement = document.querySelector(`.modal__temps .${template}`);
    const elementHtml = structuredClone(editFilesElement.innerHTML);
    editFilesElement.innerHTML = '';
    return $modal({
        title: 'Изменение файлов',
        content: elementHtml
    })
}

function editButtonsLogic(modalWin) {
    const editButtons = document.querySelectorAll('.action-file.edit');
    for (let button of editButtons) {
        button.addEventListener('click',
            function () {
                modalWin.show();
                const index = Number(button.parentElement.parentElement.parentElement.classList.item(2));
                drawFileList(projects[index].loadedFiles.find(value => value).files);
            });
    }
}

function drawFileList(files) {
    const list = document.querySelector('.modal__body .file-list');
    list.innerHTML = '';
    for (let file of files) {
        const element = document.createElement('li');
        element.classList.add('file');
        element.innerHTML = `<span class="file-download">${file}</span><span class="file-delete">удалить</span>`
        list.appendChild(element);
    }
}

export {editButtonsLogic, drawFileList, createModal};