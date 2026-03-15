const express = require('express');
const app = express();
const db = require('./db/connection');
const PORT = 3000;

const Sequelize = require('sequelize');

const sequelize = new Sequelize({
    dialect:'sqlite',
    storage:'./db/app.db'
});

module.exports = sequelize;
app.listen(PORT, function() {
console.log('Servidor rodando na porta ${PORT}');
});
app.get('/', (req, res)=> {
res.send("Está rodando 3");
});

//db connection
db
.authenticate()
.then(() => {
console.log('sucesso na conexão');
})
.catch(err => {
console.log('Erro', err);
});
//routes
app.get('/', (req, res)=> {
res.send('No ar');
});