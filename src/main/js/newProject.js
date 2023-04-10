import {editButtonsLogic} from "./modalFiledAdd.js";

let currentDate = new Date;
currentDate.setTime(Date.now());

document.getElementById('expires').min = currentDate.toISOString().slice(0, 10);

document.getElementById('prior-range').oninput = function () {document.getElementById('range-value').textContent = this.value};

let currentFiles = [];
export {currentFiles};

editButtonsLogic();