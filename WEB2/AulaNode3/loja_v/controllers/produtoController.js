const Produto = require('../model/Produtos');

module.exports = {
  async criar(dados) {
    return await Produto.create(dados);
  },

  async listar() {
    return await Produto.find();
  },

  async buscarPorId(id) {
    return await Produto.findById(id);
  },

  async atualizar(id, dados) {
    return await Produto.findByIdAndUpdate(id, dados, { new: true });
  },

  async deletar(id) {
    return await Produto.findByIdAndDelete(id);
  },
};
