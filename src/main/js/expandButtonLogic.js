function expandButtonLogic() {
    const expandButtons = document.querySelectorAll('.expand');

    for (let button of expandButtons) {
        button.addEventListener('click', function () {
            if (this.classList.contains('collapsed')) {
                this.classList.remove('collapsed');
                this.classList.add('expanded');
                this.parentNode.parentNode.querySelector('.project-content').classList.remove('hidden')
            } else {
                this.classList.remove('expanded');
                this.classList.add('collapsed');
                this.parentNode.parentNode.querySelector('.project-content').classList.add('hidden')
            }
        })
    }
}

export {expandButtonLogic};