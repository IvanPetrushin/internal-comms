import {createModal, editButtonsLogic} from './modalFilesEdit.js';
import {drawProject} from "./drawProject.js";
import {expandButtonLogic} from "./expandButtonLogic.js";
import {projects, currentGroup} from "./data.js";
import "./headerProfile.js";

if (currentGroup.creatable) {
    document.querySelector('.new-project').classList.remove('hidden');
}

drawProject(projects);
expandButtonLogic();
editButtonsLogic(createModal('edit-files'));