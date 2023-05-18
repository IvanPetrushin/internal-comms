const URL = 'http://localhost:8080'

class Group {
    constructor(id, name, description, isHead = false, mail = 'example@magnit.ru') {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isHead = isHead;
        this.mail = mail;
    }
}

class User {
    constructor(id, name, groupid, isHead = false) {
        this.id = id;
        this.name = name;
        this.groupid = groupid;
        this.isHead = isHead;
    }
}

function equalsEmp(a, b) {
    return a.name == b.name && a.group == b.group;
}

class UserToFiles {
    constructor(user, files) {
        this.user = user;
        this.files = files;
    }
}

class Project {
    constructor(name, priority, expires, expired = false, description, creatorFiles, creator, executors, loadedFiles) {
        this.name = name;
        this.priority = priority;
        this.expires = expires;
        this.expired = expired;
        this.description = description;
        this.ownerFiles = creatorFiles;
        this.owner = creator;
        this.executors = executors;
        this.loadedFiles = loadedFiles;
    }
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

let response = await fetch(`${URL}/groups/${currentUser.group.id}`);
let tempProject = JSON.parse(JSON.stringify(await response.json()));
console.log(tempProject);
let projects = tempProject.executableTasks;
projects.push.apply(projects, tempProject.createdTasks);
console.log(projects);

response = await fetch(`${URL}/groups/`);
const executorsList = JSON.parse(JSON.stringify(await response.json()));

export {equalsEmp, currentUser, projects, executorsList, currentGroup, URL};