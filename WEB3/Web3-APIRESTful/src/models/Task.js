const mongoose = require('mongoose');

const TaskSchema = new mongoose.Schema({
  titulo: {
    type: String,
    required: [true, 'Título é obrigatório'],
    trim: true
  },
  descricao: {
    type: String,
    trim: true
  },
  status: {
    type: String,
    enum: ['pendente', 'em_andamento', 'concluida'],
    default: 'pendente'
  },
  prioridade: {
    type: String,
    enum: ['baixa', 'media', 'alta'],
    default: 'media'
  },
  dataVencimento: {
    type: Date
  },
  usuario: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'User',
    required: true
  }
}, {
  timestamps: true
});

module.exports = mongoose.model('Task', TaskSchema);