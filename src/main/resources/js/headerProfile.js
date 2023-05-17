import {currentGroup, currentUser} from "./data.js";

document.querySelector('.profile').href = "../resources/profile.html";
document.querySelector('.profile .user').textContent = currentUser.username;
document.querySelector('.profile .user-group').textContent = currentGroup.name;
if (currentGroup.creatable) {
    document.querySelector('.profile .user-group').classList.add('su');
}