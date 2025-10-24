const Cliente = require('../model/Cliente');

module.exports = {
  async criar(dados) {
    return await Cliente.create(dados);
  },

  async listar() {
    return await Cliente.find();
  },

  async buscarPorId(id) {
    return await Cliente.findById(id);
  },

  async atualizar(id, dados) {
    return await Cliente.findByIdAndUpdate(id, dados, { new: true });
  },

  async deletar(id) {
    return await Cliente.findByIdAndDelete(id);
  },
};
