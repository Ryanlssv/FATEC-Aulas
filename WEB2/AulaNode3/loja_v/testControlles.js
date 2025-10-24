// testControllers.js
require('./config/database'); // conecta ao MongoDB
const produtoController = require('./controllers/produtoController');
const clienteController = require('./controllers/clienteController');

(async () => {
  try {
    console.log('🧪 Testando CRUD dos Controllers...\n');

    // Criar cliente
    const cliente = await clienteController.criar({
      nome: 'João da Silva',
      email: 'joao@email.com',
      telefone: '11999999999',
    });
    console.log('Cliente criado:', cliente);

    // Criar produto com categoria
    const produto = await produtoController.criar({
      nome: 'Notebook Dell',
      preco: 4500,
      categoria: { nome: 'Informática', descricao: 'Equipamentos eletrônicos' },
      estoque: 5,
    });
    console.log('Produto criado:', produto);

    // Listar produtos
    const produtos = await produtoController.listar();
    console.log('\nLista de produtos:', produtos);

    // Atualizar produto
    const atualizado = await produtoController.atualizar(produto._id, { estoque: 10 });
    console.log('\nProduto atualizado:', atualizado);

    // Deletar cliente
    await clienteController.deletar(cliente._id);
    console.log('\nCliente deletado.');

    process.exit(0);
  } catch (err) {
    console.error('Erro nos testes:', err);
    process.exit(1);
  }
})();
