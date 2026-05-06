class Livro {
      constructor(id, titulo, autor) {
          this.id = id;               // código único
          this.titulo = titulo;
          this.autor = autor;
          this.emprestado = false;    // true → está emprestado
          this.usuario = null;        // quem pegou o livro
      }
  }

  class Usuario {
      constructor(id, nome) {
          this.id = id;
          this.nome = nome;
      }
  }

  class Biblioteca {
      constructor() {
          // carrega ou inicializa os arrays
          this.livros = JSON.parse(localStorage.getItem('livros')) || [];
          this.usuarios = JSON.parse(localStorage.getItem('usuarios')) || [];
      }

      salvar() {
          localStorage.setItem('livros', JSON.stringify(this.livros));
          localStorage.setItem('usuarios', JSON.stringify(this.usuarios));
      }

      gerarId(collection) {
          return collection.length ? Math.max(...collection.map(o => o.id)) + 1 : 1;
      }

      /* ---- Cadastro ---- */
      cadastrarLivro(titulo, autor) {
          const id = this.gerarId(this.livros);
          this.livros.push(new Livro(id, titulo, autor));
          this.salvar();
          alert(`Livro cadastrado (ID: ${id})`);
      }

      cadastrarUsuario(nome) {
          const id = this.gerarId(this.usuarios);
          this.usuarios.push(new Usuario(id, nome));
          this.salvar();
          alert(`Usuário cadastrado (ID: ${id})`);
      }

      /* ---- Empréstimo / Devolução ---- */
      emprestarLivro(livroId, usuarioId) {
          const livro = this.livros.find(l => l.id === livroId);
          if (!livro) return alert('Livro não encontrado.');
          if (livro.emprestado) return alert('Livro já está emprestado.');

          const usuario = this.usuarios.find(u => u.id === usuarioId);
          if (!usuario) return alert('Usuário não encontrado.');

          livro.emprestado = true;
          livro.usuario = usuario.nome;
          this.salvar();
          alert(`Livro "${livro.titulo}" emprestado a ${usuario.nome}.`);
      }

      devolverLivro(livroId) {
          const livro = this.livros.find(l => l.id === livroId);
          if (!livro) return alert('Livro não encontrado.');
          if (!livro.emprestado) return alert('Livro já está disponível.');

          livro.emprestado = false;
          livro.usuario = null;
          this.salvar();
          alert(`Livro "${livro.titulo}" devolvido.`);
      }

      /* ---- Relatório ---- */
      relatorioLivros() {
          if (!this.livros.length) return 'Nenhum livro cadastrado.';
          return this.livros
              .map(l => `ID: ${l.id} | ${l.titulo} - ${l.autor} | ` +
                  (l.emprestado ? `Emprestado a ${l.usuario}` : 'Disponível'))
              .join('\n');
      }
  }

  /* ------------------------  UI Helpers ------------------------ */
function toggleConsole(){
    const area=document.getElementById('consoleArea');
    const btn=document.getElementById('toggleConsole');
    if(area.style.display==='none'){
        area.style.display='block';
        btn.textContent='Fechar Console';
    } else {
        area.style.display='none';
        btn.textContent='Abrir Console';
    }
}

/* ------------------------  Menu Interativo  ------------------------ */
  const biblioteca = new Biblioteca();

  function mostrarMenu() {
      const op = prompt(
  `=== MENU ===
  1 – Cadastrar Livro
  2 – Cadastrar Usuário
  3 – Emprestar Livro
  4 – Devolver Livro
  5 – Relatório de Livros
  6 – Sair
  Escolha a opção:`
      );

      switch (op) {
          case '1':
              const titulo = prompt('Título do livro:');
              const autor = prompt('Autor do livro:');
              if (titulo && autor) biblioteca.cadastrarLivro(titulo, autor);
              break;
          case '2':
              const nome = prompt('Nome do usuário:');
              if (nome) biblioteca.cadastrarUsuario(nome);
              break;
          case '3':
              const idL = parseInt(prompt('ID do livro a emprestar:'));
              const idU = parseInt(prompt('ID do usuário que receberá:'));
              biblioteca.emprestarLivro(idL, idU);
              break;
          case '4':
              const idD = parseInt(prompt('ID do livro a devolver:'));
              biblioteca.devolverLivro(idD);
              break;
          case '5':
              const rel = biblioteca.relatorioLivros();
              document.getElementById('relatorio').textContent = rel;
              // exibe relatório e fecha o console
              document.getElementById('consoleArea').style.display = 'none';
              document.getElementById('toggleConsole').textContent = 'Abrir Console';
              break;
          case '6':
              return; // encerra
          default:
              alert('Opção inválida.');
      }
      // volta ao menu
      mostrarMenu();
  }

  // inicia a aplicação
  mostrarMenu();