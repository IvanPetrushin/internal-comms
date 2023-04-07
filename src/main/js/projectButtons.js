const expandButton = document.querySelector('.expand');
expandButton.addEventListener('click', function(){
    if (this.classList.contains('collapsed')) {
        this.classList.remove('collapsed');
        this.classList.add('expanded');
        this.parentNode.parentNode.querySelector('.project-description').classList.remove('hidden')
    }
    else {
        this.classList.remove('expanded');
        this.classList.add('collapsed');
        this.parentNode.parentNode.querySelector('.project-description').classList.add('hidden')
    }
})