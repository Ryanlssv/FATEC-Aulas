const express = require('express');
const {
  listarUsuarios,
  obterUsuario,
  atualizarUsuario,
  deletarUsuario
} = require('../controllers/userController');
const auth = require('../middleware/auth');

const router = express.Router();

router.use(auth);

router.get('/', listarUsuarios);
router.get('/:id', obterUsuario);
router.put('/:id', atualizarUsuario);
router.delete('/:id', deletarUsuario);

module.exports = router;