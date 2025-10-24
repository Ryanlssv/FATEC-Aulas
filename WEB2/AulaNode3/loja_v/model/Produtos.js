// models/Produto.js
const mongoose = require('mongoose');
const CategoriaSchema = require('./categoria');

const ProdutoSchema = new mongoose.Schema({
  nome: { type: String, required: true },
  preco: { type: Number, required: true },
  categoria: CategoriaSchema, // subdocumento
  estoque: { type: Number, default: 0 },
});

module.exports = mongoose.model('Produto', ProdutoSchema);
