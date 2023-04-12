import {createModal, editButtonsLogic} from './modalFilesEdit.js';
import {drawProject} from "./drawProject.js";
import {expandButtonLogic} from "./expandButtonLogic.js";
import {projects} from "./data.js";
import "./headerProfile.js";

drawProject(projects);
expandButtonLogic();
editButtonsLogic(createModal('edit-files'));