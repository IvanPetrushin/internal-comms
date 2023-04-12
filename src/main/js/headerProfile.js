import {currentUser} from "./data.js";

document.querySelector('.profile .user').textContent = currentUser.name;
document.querySelector('.profile .user-group').textContent = currentUser.group;