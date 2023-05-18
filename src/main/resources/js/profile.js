import "./headerProfile.js";
import {URL} from "./data.js";

const tempLink = location.href.split('/');
const user = tempLink[tempLink.length-1];
let response = await fetch(`${URL}/users/${user}` );
let userObj = JSON.parse(JSON.stringify(await response.json()));
console.log(userObj);

document.querySelector('.profile-username').textContent = userObj.username;
document.querySelector('.profile-group').innerHTML =
    `<a class="profile-link" href="${URL}/group/${userObj.group.id}">${userObj.group.name}</a>`;
document.querySelector('.profile-group-mail').textContent = `e-mail: ${userObj.mail}`;
