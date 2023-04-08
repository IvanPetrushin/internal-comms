import './projectButtons.js';
import './modalFilesEdit.js';
import {drawProject} from "./drawProject.js";

class Employee {
    constructor(name, group) {
        this.name = name;
        this.group = group;
    }
}

function equalsEmp(user1, user2) {
    return user1.name == user2.name && user1.group == user2.group;
}

class UserFiles {
    constructor(user, files) {
        this.user = user;
        this.files = files;
    }
}

let projects = [{
    name: 'Проект для жоского отдыха',
    priority: 3,
    expires: '14.04.2023',
    expired: false,
    description: `<h4>В проекте надо сделать:</h4>
                    <ul>
                        <li>В начале этой статьи объясняется, что свойства выравнивания, которые в настоящее время содержатся в спецификации Flexbox Level 1, также включены в спецификацию Box Alignment Level 3, которая в дальнейшем может расширить эти свойства и значения. Мы уже видели, как это произошло с введением значения space-evenly для свойств align-content и justify-content.</li>
                        <li>Sadly, column boxes cannot be styled at present. The anonymous boxes that make up your columns can't be targeted in any way, meaning it isn't possible to change a box's background color or have one column larger than the others. Perhaps in future versions of the specification it might be. For now, however, we are able to change the spacing and add lines between columns.</li>
                    </ul>
                    <p>Также потом там чето еще ну в общем ладно всем хорошего настроения дасведвния всем пака.</p>`,
    ownerFiles: ['file.jpg','another-file.pdf'],
    owner: new Employee('Тропик Т. Д.', 'Отдел кайфа'),
    executors: [
        new Employee('Ехоров Н. А.', 'Магнит на Лесном'),
        new Employee('Маковкин Н. В..', 'Магнит тоже где-то есть да'),
        new Employee('Петручин И. И.', 'Магнит в другом городе'),
    ],
    loadedFiles: [
        new UserFiles((new Employee('Ехоров Н. А.', 'Магнит на Лесном')), []),
        new UserFiles((new Employee('Маковкин Н. В..', 'Магнит тоже где-то есть да')), []),
        new UserFiles((new Employee('Петручин И. И.', 'Магнит в другом городе')), [])
    ]
}];

const currentUser = new Employee('Ехоров Н. А.', 'Магнит на Лесном');

export {equalsEmp, currentUser};

drawProject(projects);