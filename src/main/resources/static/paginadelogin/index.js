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

    const loginForm = document.querySelector(".login-form");
    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

    const username = document.getElementById("email-ou-cpf").value
    const password = document.getElementById("senha").value

        try {
            const response = await fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: username,
                    password: password
                })
            });

            if (response.ok) {
                const data = await response.json();
                // Salvar o token no localStorage
                localStorage.setItem('token', data.token);
                console.log(data.token)
                // Redirecionar para a página principal após login
               alert("login realizado com sucesso!")
            } else {
                alert('Credenciais inválidas. Por favor, tente novamente.');
            }
        } catch (error) {
            console.error('Erro ao fazer login:', error);
            alert('Erro ao fazer login. Por favor, tente novamente.');
        }

           });
