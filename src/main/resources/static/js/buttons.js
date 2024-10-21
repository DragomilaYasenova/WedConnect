const toggleButton = document.getElementById('toggle-btn');
const sidebar = document.getElementById('sidebar');

function toggleSidebar() {
    sidebar.classList.toggle('close');
    toggleButton.classList.toggle('rotate');

    const openSubmenus = Array.from(sidebar.getElementsByClassName('show'));
    openSubmenus.forEach(submenu => {
        submenu.classList.remove('show');
        submenu.previousElementSibling.classList.remove('rotate');
    });
}

function toggleSubMenu(button) {
    const submenu = button.nextElementSibling;
    
    submenu.classList.toggle('show');
    button.classList.toggle('rotate');

    if (sidebar.classList.contains('close')) {
        sidebar.classList.remove('close');
        toggleButton.classList.remove('rotate');
    }
}
