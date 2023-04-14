class Group {
    constructor(name, description, isHead = false, mail = 'example@magnit.ru') {
        this.name = name;
        this.description = description;
        this.isHead = isHead;
        this.mail = mail;
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
const currentGroup = new Group('Магнит на Лесном', 'Подразделение №1942, Супермаркет Магнит, СПб, Лесной пр-кт, 1А', true);
const executorsList = [
    new Employee('Маковкин Н. В.', 'Магнит тоже где-то есть да'),
    new Employee('Петручин И. И.', 'Магнит в другом городе'),
    new Employee('Тропинин Н. Д.', 'Какой-нибудь еще магнит'),
    new Employee('Тропич Т. Д.', 'Магнит косметик')
];

export {equalsEmp, currentUser, projects, executorsList, currentGroup};