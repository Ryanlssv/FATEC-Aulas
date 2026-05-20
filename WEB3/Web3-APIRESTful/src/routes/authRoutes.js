const express = require('express');
const { registrar, login, perfil } = require('../controllers/authController');
const auth = require('../middleware/auth');

const router = express.Router();

router.post('/registrar', registrar);
router.post('/login', login);
router.get('/perfil', auth, perfil);

module.exports = router;