// models/Cliente.js
const mongoose = require('mongoose');

const ClienteSchema = new mongoose.Schema({
  nome: { type: String, required: true },
  email: { type: String, required: true, unique: true },
  telefone: String,
  dataCadastro: { type: Date, default: Date.now },
});

module.exports = mongoose.model('Cliente', ClienteSchema);
