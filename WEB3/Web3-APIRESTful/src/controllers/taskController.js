const Task = require('../models/Task');

// Listar todas as tasks do usuário
exports.listarTasks = async (req, res) => {
  try {
    const { status, prioridade } = req.query;
    const filtro = { usuario: req.user._id };

    if (status) filtro.status = status;
    if (prioridade) filtro.prioridade = prioridade;

    const tasks = await Task.find(filtro).sort({ createdAt: -1 });
    res.json(tasks);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Obter task por ID
exports.obterTask = async (req, res) => {
  try {
    const task = await Task.findOne({ _id: req.params.id, usuario: req.user._id });
    if (!task) {
      return res.status(404).json({ error: 'Task não encontrada' });
    }
    res.json(task);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Criar task
exports.criarTask = async (req, res) => {
  try {
    const { titulo, descricao, prioridade, dataVencimento } = req.body;

    const task = await Task.create({
      titulo,
      descricao,
      prioridade,
      dataVencimento,
      usuario: req.user._id
    });

    res.status(201).json(task);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Atualizar task
exports.atualizarTask = async (req, res) => {
  try {
    const { titulo, descricao, status, prioridade, dataVencimento } = req.body;

    const task = await Task.findOne({ _id: req.params.id, usuario: req.user._id });
    if (!task) {
      return res.status(404).json({ error: 'Task não encontrada' });
    }

    if (titulo !== undefined) task.titulo = titulo;
    if (descricao !== undefined) task.descricao = descricao;
    if (status !== undefined) task.status = status;
    if (prioridade !== undefined) task.prioridade = prioridade;
    if (dataVencimento !== undefined) task.dataVencimento = dataVencimento;

    await task.save();
    res.json(task);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Deletar task
exports.deletarTask = async (req, res) => {
  try {
    const task = await Task.findOneAndDelete({ _id: req.params.id, usuario: req.user._id });
    if (!task) {
      return res.status(404).json({ error: 'Task não encontrada' });
    }
    res.json({ message: 'Task deletada com sucesso' });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};