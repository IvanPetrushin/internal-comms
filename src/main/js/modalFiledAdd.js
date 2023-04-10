import {currentFiles} from "./newProject.js";
import {drawFileList} from "./modalFilesEdit.js";

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
                drawFileList(currentFiles);
            });
    }
}

//function такое же про список людей

export {editButtonsLogic};