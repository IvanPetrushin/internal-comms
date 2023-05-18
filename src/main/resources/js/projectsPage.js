import {createModal, editButtonsLogic} from './modalFilesEdit.js';
import {drawProject} from "./drawProject.js";
import {expandButtonLogic} from "./expandButtonLogic.js";
import {projects, currentGroup, currentUser, URL} from "./data.js";
import "./headerProfile.js";

if (currentGroup.creatable) {
    document.querySelector('.new-project').classList.remove('hidden');
}

drawProject(projects, currentUser, URL);
expandButtonLogic();
editButtonsLogic(createModal('edit-files'));