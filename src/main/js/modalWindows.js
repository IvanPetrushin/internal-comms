var editFilesElement = document.querySelector('.modal__temps .edit-files')
var editFilesModal = $modal({
    title: 'Изменение файлов',
    content: editFilesElement.innerHTML,
    footerButtons: [
        {class: 'btn btn__cancel', text: 'Отмена', handler: 'modalHandlerCancel'},
        {class: 'btn btn__ok', text: 'Сохранить', handler: 'modalHandlerOk'}
    ]
});

var editButton = document.querySelector('.action-file.edit');
editButton.addEventListener('click', function (evt){editFilesModal.show()});

console.log(document.querySelector('.btn__ok'));