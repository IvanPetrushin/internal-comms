import {projects, equalsEmp, currentUser} from "./main.js";

const editFilesElement = document.querySelector('.modal__temps .edit-files');
const editFilesModal = $modal({
    title: 'Изменение файлов',
    content: editFilesElement.innerHTML,
    footerButtons: [
        {class: 'btn btn__cancel', text: 'Отмена', handler: 'modalHandlerCancel'},
        {class: 'btn btn__ok', text: 'Сохранить', handler: 'modalHandlerOk'}
    ]
});

function editButtonsLogic() {
    const editButtons = document.querySelectorAll('.action-file.edit');
    for (let button of editButtons) {
        button.addEventListener('click',
            function () {
                editFilesModal.show();
                const index = Number(button.parentElement.parentElement.parentElement.classList.item(2));
                drawFileList(projects[index].loadedFiles.find(value => equalsEmp(value.user, currentUser)).files);
            });
    }
}

function drawFileList(files) {
    const list = document.querySelector('.modal__body .file-list');
    list.innerHTML = ``;
    for (let file of files) {
        const element = document.createElement('li');
        element.classList.add('file');
        element.innerHTML = `<span class="file-download">${file}</span><span class="file-delete">удалить</span>`
        list.appendChild(element);
    }
}

export {editButtonsLogic}