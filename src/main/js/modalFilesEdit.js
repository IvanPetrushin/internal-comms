const editFilesElement = document.querySelector('.modal__temps .edit-files');
const editFilesModal = $modal({
    title: 'Изменение файлов',
    content: editFilesElement.innerHTML,
    footerButtons: [
        {class: 'btn btn__cancel', text: 'Отмена', handler: 'modalHandlerCancel'},
        {class: 'btn btn__ok', text: 'Сохранить', handler: 'modalHandlerOk'}
    ]
});

const editButton = document.querySelector('.action-file.edit');
editButton.addEventListener('click',
    function (evt){
    editFilesModal.show();
    drawFileList();
});

// тестовый массив файлов, потом надо как-то связать с лежащими на  сервере файлами
let files = ['file.jpg', 'another-file.pdf', 'other-file-final.docx'];

function drawFileList() {
    const list = document.querySelector('.modal__body .file-list');
    list.innerHTML = ``;
    for (let file of files) {
        const element = document.createElement('li');
        element.classList.add('file');
        element.innerHTML = `<span class="file-download">${file}</span><span class="file-delete">удалить</span>`
        list.appendChild(element);
    }
}
