// models/Categoria.js
const mongoose = require('mongoose');

const CategoriaSchema = new mongoose.Schema({
  nome: { type: String, required: true },
  descricao: String,
});

module.exports = CategoriaSchema; // exporta como sub-schema
