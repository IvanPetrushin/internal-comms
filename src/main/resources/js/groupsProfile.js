import "./headerProfile.js";
import {URL} from "./data.js";

const tempLink = location.href.split('/');
const group = tempLink[tempLink.length-1];
let response = await fetch(`${URL}/groups/${group}` );
let groupObj = JSON.parse(JSON.stringify(await response.json()));
console.log(groupObj);

document.querySelector('.profile-group').textContent = groupObj.name;
document.querySelector('.profile-group-description').textContent = groupObj.description;
document.querySelector('.profile-group-mail').textContent = `e-mail: ${groupObj.mail}`;