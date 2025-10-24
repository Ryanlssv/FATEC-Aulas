// config/database.js
const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/loja_virtual', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

mongoose.connection.on('connected', () => {
  console.log('✅ Conectado ao MongoDB!');
});

mongoose.connection.on('error', (err) => {
  console.error('❌ Erro na conexão com o MongoDB:', err);
});

module.exports = mongoose;
