let modoEdicao = false;

  function carregarUsuarios() {
      fetch('http://localhost:8080/usuarios')
          .then(resposta => resposta.json())
          .then(usuarios => {
              const tabela = document.getElementById('tabela-usuarios');
              tabela.innerHTML = '';

              usuarios.forEach(usuario => {
                  const linha = document.createElement('tr');

                  const celulaCargo =document.createElement('td');
                  celulaCargo.textContent = usuario.cargo;

                  const celulaId = document.createElement('td');
                  celulaId.textContent = usuario.id;

                  const celulaIdAcesso = document.createElement('td');
                  celulaIdAcesso.textContent = usuario.idAcesso

                  const celulaNome = document.createElement('td');
                  celulaNome.textContent = usuario.nome;


                  const celulaAcoes = document.createElement('td');
                  celulaAcoes.innerHTML = `
                      <button onclick="editarUsuario(${usuario.id}, '${usuario.nome}')">Editar</button>
                      <button onclick="deletarUsuario(${usuario.id})">Excluir</button>
                  `;

                  linha.appendChild(celulaCargo)
                  linha.appendChild(celulaId);
                  linha.appendChild(celulaIdAcesso)
                  linha.appendChild(celulaNome);
                  linha.appendChild(celulaAcoes);

                  tabela.appendChild(linha);
              });
          });
  }

  document.getElementById('form-usuario').addEventListener('submit', function (evento) {
      evento.preventDefault();

      const id = parseInt(document.getElementById('usuario-id').value);
      const nome = document.getElementById('usuario-nome').value;

      const metodo = modoEdicao ? 'PUT' : 'POST';
      const url = modoEdicao ? `http://localhost:8080/usuarios/${id}` : 'http://localhost:8080/usuarios';

      fetch(url, {
          method: PUT,
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ id, nome })
      })
      .then(() => {
          alert(`Usuário ${modoEdicao ? 'atualizado' : 'adicionado'} com sucesso!`);
          carregarUsuarios();
          resetForm();
      })
      .catch(() => alert('Erro ao salvar usuário.'));
  });

  function editarUsuario(id, nome) {
      modoEdicao = true;
      document.getElementById('form-titulo').textContent = 'Editar Usuário';
      document.getElementById('usuario-id').value = id;
      document.getElementById('usuario-id').disabled = true;
      document.getElementById('usuario-nome').value = nome;
  }

  function deletarUsuario(id) {
      if (confirm('Deseja excluir este usuário?')) {
          fetch(`http://localhost:8080/usuarios/${id}`, { method: 'DELETE' })
              .then(() => {
                  alert('Usuário deletado com sucesso!');
                  carregarUsuarios();
              })
              .catch(() => alert('Erro ao deletar usuário.'));
      }
  }

  function resetForm() {
      modoEdicao = false;
      document.getElementById('form-titulo').textContent = 'Novo Usuário';
      document.getElementById('usuario-id').value = '';
      document.getElementById('usuario-id').disabled = false;
      document.getElementById('usuario-nome').value = '';
  }

  carregarUsuarios();