const URL = 'http://localhost:8080'

class Group {
    constructor(name, description, isHead = false, mail = 'example@magnit.ru') {
        this.name = name;
        this.description = description;
        this.isHead = isHead;
        this.mail = mail;
    }
}

class User {
    constructor(id, name, group, isHead = false) {
        this.id = id;
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

let project = new Project (
    'Проект среднего размера, сотрудников до 500 человек.',
    3,
    '14.04.2023',
    true,
    `<h4>Цель проекта: описание всех ключевых бизнес-процессов и разработка комплексной электронной модели (бизнес-архитектуры) банка в рамках стратегии ускоренного развития.</h4>
                    <ul>
                        <li>Команда проекта: 3 главных бизнес-аналитика плюс более 30 аналитиков в подразделениях, каждый из которых прорабатывает свой собственный бизнес-процесс (но это не основная их деятельность по должностным инструкциям). Есть полная поддержка высшего руководства и выделены все необходимые ресурсы.</li>
                        <li>Цель проекта была успешно достигнута за 1 год, разработано более 1300 графических моделей бизнес-процессов в нотации Cross Functional FlowChart, создана электронная база знаний (веб-сайт) по всей деятельности банка для всех сотрудников. Большое внимание уделялось обучению и консультациям не только бизнес-аналитиков, но и всех исполнителей бизнес-процессов.</li>
                    </ul>
                    <p>На второй год банк поставил более сложные задачи и тоже успешно их выполнил: проработка операционных рисков в процессах, разработка показателей KPI и их постоянный мониторинг, автоматизация процессов и развитие ИТ-архитектуры, расчёт себестоимости процессов и снижение издержек, применение методик менеджмента качества и много другое.</p>`,
    ['file.jpg','another-file.pdf'],
    new User('Тропик Т. Д.', 'Отдел кайфа'),
    [
        new User('Ехоров Н. А.', 'Магнит на Лесном'),
        new User('Маковкин Н. В..', 'Магнит тоже где-то есть да'),
        new User('Петручин И. И.', 'Магнит в другом городе'),
    ],
    [
        new UserToFiles((new User('Ехоров Н. А.', 'Магнит на Лесном')), ['file.jpg', 'another-file.pdf', 'other-file-final.docx']),
        new UserToFiles((new User('Маковкин Н. В.', 'Магнит тоже где-то есть да')), []),
        new UserToFiles((new User('Петручин И. И.', 'Магнит в другом городе')), [])
    ]
);
let projects = [project, structuredClone(project), structuredClone(project)];
projects[1].expired = false;
projects[2].expired = false;
projects[2].loadedFiles[0].files = ['print.txt','tested.svg'];

// /task?groupID=509
let response = await fetch(URL + '/tasks/679');
let tempProject = JSON.parse(JSON.stringify(await response.json()));
tempProject.ownerFiles = [];
tempProject.groups = {123: ['file.jpg', 'another.pdf'], 302: []};
console.log(typeof tempProject.loadedFiles)
projects = [tempProject];
response = await fetch(URL + '/tasks/355');
tempProject = JSON.parse(JSON.stringify(await response.json()));
tempProject.ownerFiles = [];
tempProject.groups = {123: ['another.pdf'], 32: ['file.png']};
projects.push(tempProject);
console.log(projects);

const currentUser = new User(13, 'Ехоров Н. А.', 'Магнит на Лесном', true);
const currentGroup = new Group('Магнит на Лесном', 'Подразделение №1942, Супермаркет Магнит, СПб, Лесной пр-кт, 1А', true);
const executorsList = [
    new User(123,'Маковкин Н. В.', 'Магнит тоже где-то есть да'),
    new User(302, 'Петручин И. И.', 'Магнит в другом городе'),
    new User(964, 'Тропинин Н. Д.', 'Какой-нибудь еще магнит'),
    new User(32, 'Тропич Т. Д.', 'Магнит косметик')
];

export {equalsEmp, currentUser, projects, executorsList, currentGroup, URL};