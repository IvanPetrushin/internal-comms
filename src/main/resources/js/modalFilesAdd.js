/*
//import {drawFileList} from "./modalFilesEdit.js";
import {currentFiles} from "./newProject.js";

let currentFiles = currentFiles;

function namesOfFiles(files) {
    let names = [];
    for (let file of files) {
        names.push(file[0].name);
    }
    return names;
}

function delFileByName(name) {
    console.log(currentFiles);
    let result = [];
    for (let file of currentFiles) {
        if (file[0].name !== name) {
            result.push(file);
        }
        else{
            console.log(`deleted ${file[0].name}`);
        }
    }
    currentFiles = result;
    console.log(`after ${currentFiles}`);
}

function editButtonsLogic(modalWin) {
    const fileEditButton = document.querySelector('.action-file.edit');
    fileEditButton.addEventListener('click',
        function () {
        modalWin.show();
        const inputFile = document.getElementById('load-file');
        inputFile.onchange = function () {
            if (!(inputFile.files in currentFiles)) {
                currentFiles.push(inputFile.files);
            }
            console.log(currentFiles.length);
            drawFileListAdd();
            //currentFiles = drawFileListAdd();
        };
        drawFileListAdd();
        //currentFiles = structuredClone(drawFileListAdd();
    });
    //return currentFiles;
}

function drawFileListAdd(flag = true) {
    let fileNames = namesOfFiles(currentFiles);
    const list = document.querySelector('.modal__body .file-list');
    list.innerHTML = '';
    for (let name of fileNames) {
        const element = document.createElement('li');
        element.classList.add('file');
        element.innerHTML = `<span class="file-download">${name}</span><span class="file-delete">удалить</span>`;
        if (flag) {
            element.querySelector('.file-delete').onclick = function () {
                console.log(this.parentElement.querySelector('.file-download').textContent);
                delFileByName(element.querySelector('.file-download').textContent)
                //currentFiles = delFileByName(currentFiles, element.querySelector('.file-download').textContent);
                drawFileListAdd(false);
            };
        }
        list.appendChild(element);
    }
    //return currentFiles;
}


//function такое же про список людей

export {editButtonsLogic};*/
