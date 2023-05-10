import "./headerProfile.js";
import {currentUser, currentGroup} from "./data.js";

document.querySelector('.profile-group').textContent = currentUser.group;
document.querySelector('.profile-group-description').textContent = currentGroup.description;
document.querySelector('.profile-group-mail').textContent = `e-mail: ${currentGroup.mail}`;
