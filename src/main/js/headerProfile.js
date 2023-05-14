import {currentUser} from "./data.js";

document.querySelector('.profile').href = "../resources/profile.html";
document.querySelector('.profile .user').textContent = currentUser.name;
document.querySelector('.profile .user-group').textContent = currentUser.group;
if (currentUser.isHead) {
    document.querySelector('.profile .user-group').classList.add('su');
}