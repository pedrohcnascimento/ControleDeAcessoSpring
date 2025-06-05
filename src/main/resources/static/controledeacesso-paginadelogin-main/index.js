document.addEventListener('DOMContentLoaded', () => {
    const passwordInput = document.getElementById('senha');
    const toggleButton = document.querySelector('.toggle-password');
    
    toggleButton.addEventListener('click', () => {
        const type = passwordInput.type === 'password' ? 'text' : 'password';
        passwordInput.type = type;
        
        const icon = toggleButton.querySelector('i');
        icon.className = type === 'password' ? 'fas fa-eye' : 'fas fa-eye-slash';
    });
});

