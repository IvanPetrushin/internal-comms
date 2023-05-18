const URL = 'http://localhost:8080'

if (!(document.cookie.includes('loggedUserId'))) {
    location.replace(URL+'/login');
}
console.log(document.cookie);

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

const currentUserId = getCookie().loggedUserId;
const currentUser = JSON.parse(JSON.stringify(await (await fetch(`${URL}/users/${currentUserId}`)).json()));
console.log(currentUser);
// const currentGroup = JSON.parse(JSON.stringify(await (await fetch(`${URL}/groups/${currentUser.group.id}`)).json()));
// console.log(currentGroup);

// let response = await fetch(`${URL}/groups/${currentUser.group.id}`);
// let tempProject = JSON.parse(JSON.stringify(await response.json()));
// console.log(tempProject);
let projects = currentUser.group.executableTasks;
projects.push.apply(projects, currentUser.group.createdTasks);
console.log(projects);

function getCookie() {
    return document.cookie.split('; ').reduce((acc, item) => {
        const [name, value] = item.split('=')
        acc[name] = value
        return acc
    }, {})
}

const currentGroup = currentUser.group;
export {equalsEmp, currentUser, projects, currentGroup, URL};