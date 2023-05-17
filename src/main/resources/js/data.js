const URL = 'http://localhost:8080'


function equalsEmp(a, b) {
    return a.name == b.name && a.group == b.group;
}

function sortProjects(projects, keyField, way = true) {
    let result = structuredClone(projects);
    for (let i = 0; i < projects.length-1; i++) {
        if ((projects[i][keyField] > projects[i+1][keyField]) && way) {
            let swap = projects[i];
            projects[i] = projects[i+1];
            projects[i+1] = swap;
        }
        else {
            let swap = projects[i+1];
            projects[i+1] = projects[i];
            projects[i] = swap;
        }
    }
    return result
}

const currentUserId = 13;
const currentUser = JSON.parse(JSON.stringify(await (await fetch(`${URL}/users/${currentUserId}`)).json()));
console.log(currentUser);
const currentGroup = JSON.parse(JSON.stringify(await (await fetch(`${URL}/groups/${currentUser.group.id}`)).json()));
console.log(currentGroup);

// let response = await fetch(`${URL}/groups/${currentUser.group.id}`);
// let tempProject = JSON.parse(JSON.stringify(await response.json()));
// console.log(tempProject);
let projects = currentGroup.executableTasks;
projects.push.apply(projects, currentGroup.createdTasks);
console.log(projects);

let response = await fetch(`${URL}/groups/`);
const executorsList = JSON.parse(JSON.stringify(await response.json()));

export {equalsEmp, currentUser, projects, executorsList, currentGroup, URL};