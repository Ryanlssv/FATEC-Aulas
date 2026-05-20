const express = require('express');
const {
  listarTasks,
  obterTask,
  criarTask,
  atualizarTask,
  deletarTask
} = require('../controllers/taskController');
const auth = require('../middleware/auth');

const router = express.Router();

router.use(auth);

router.get('/', listarTasks);
router.get('/:id', obterTask);
router.post('/', criarTask);
router.put('/:id', atualizarTask);
router.delete('/:id', deletarTask);

module.exports = router;