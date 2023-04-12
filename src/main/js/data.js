class Group {
    constructor(name, isHead) {
        this.name = name;
        this.isHead = isHead;
    }
}

class Employee {
    constructor(name, group, isHead = false) {
        this.name = name;
        this.group = group;
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
    constructor(name, priority, expires, expired, description, creatorFiles, creator, executors, loadedFiles) {
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

let project = new Project (
    'Проект для жоского отдыха',
    3,
    '14.04.2023',
    true,
    `<h4>В проекте надо сделать:</h4>
                    <ul>
                        <li>В начале этой статьи объясняется, что свойства выравнивания, которые в настоящее время содержатся в спецификации Flexbox Level 1, также включены в спецификацию Box Alignment Level 3, которая в дальнейшем может расширить эти свойства и значения. Мы уже видели, как это произошло с введением значения space-evenly для свойств align-content и justify-content.</li>
                        <li>Sadly, column boxes cannot be styled at present. The anonymous boxes that make up your columns can't be targeted in any way, meaning it isn't possible to change a box's background color or have one column larger than the others. Perhaps in future versions of the specification it might be. For now, however, we are able to change the spacing and add lines between columns.</li>
                    </ul>
                    <p>Также потом там чето еще ну в общем ладно всем хорошего настроения дасведвния всем пака.</p>`,
    ['file.jpg','another-file.pdf'],
    new Employee('Тропик Т. Д.', 'Отдел кайфа'),
    [
        new Employee('Ехоров Н. А.', 'Магнит на Лесном'),
        new Employee('Маковкин Н. В..', 'Магнит тоже где-то есть да'),
        new Employee('Петручин И. И.', 'Магнит в другом городе'),
    ],
    [
        new UserToFiles((new Employee('Ехоров Н. А.', 'Магнит на Лесном')), ['file.jpg', 'another-file.pdf', 'other-file-final.docx']),
        new UserToFiles((new Employee('Маковкин Н. В.', 'Магнит тоже где-то есть да')), []),
        new UserToFiles((new Employee('Петручин И. И.', 'Магнит в другом городе')), [])
    ]
);
let projects = [project, structuredClone(project), structuredClone(project)];
projects[1].expired = false;
projects[2].expired = false;
projects[2].loadedFiles[0].files = ['print.txt','tested.svg'];

const currentUser = new Employee('Ехоров Н. А.', 'Магнит на Лесном', true);
const executorsList = [
    new Employee('Маковкин Н. В.', 'Магнит тоже где-то есть да'),
    new Employee('Петручин И. И.', 'Магнит в другом городе'),
    new Employee('Тропинин Н. Д.', 'Какой-нибудь еще магнит'),
    new Employee('Тропич Т. Д.', 'Магнит косметик')
];

export {equalsEmp, currentUser, projects, executorsList};