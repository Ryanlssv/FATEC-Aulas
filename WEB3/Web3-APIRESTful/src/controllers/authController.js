const jwt = require('jsonwebtoken');
const User = require('../models/User');

// Gerar token JWT
const gerarToken = (id) => {
  return jwt.sign({ id }, process.env.JWT_SECRET || 'seu_secret_jwt_aqui', {
    expiresIn: process.env.JWT_EXPIRES_IN || '7d'
  });
};

// Registrar usuário
exports.registrar = async (req, res) => {
  try {
    const { nome, email, senha } = req.body;

    // Verificar se usuário já existe
    const usuarioExistente = await User.findOne({ email });
    if (usuarioExistente) {
      return res.status(400).json({ error: 'Email já cadastrado' });
    }

    // Criar usuário
    const usuario = await User.create({ nome, email, senha });

    // Gerar token
    const token = gerarToken(usuario._id);

    res.status(201).json({
      message: 'Usuário criado com sucesso',
      token,
      usuario: {
        id: usuario._id,
        nome: usuario.nome,
        email: usuario.email
      }
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Login
exports.login = async (req, res) => {
  try {
    const { email, senha } = req.body;

    // Buscar usuário
    const usuario = await User.findOne({ email });
    if (!usuario) {
      return res.status(401).json({ error: 'Credenciais inválidas' });
    }

    // Verificar senha
    const senhaValida = await usuario.compararSenha(senha);
    if (!senhaValida) {
      return res.status(401).json({ error: 'Credenciais inválidas' });
    }

    // Gerar token
    const token = gerarToken(usuario._id);

    res.json({
      message: 'Login realizado com sucesso',
      token,
      usuario: {
        id: usuario._id,
        nome: usuario.nome,
        email: usuario.email
      }
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Perfil do usuário logado
exports.perfil = async (req, res) => {
  res.json({ usuario: req.user });
};