// 1 -
console.log("--1")
let a = "abobora";
console.log( a.length);
console.log( a.toUpperCase());

// 2 - 
console.log("--2")
let b = null;
let c = '';
console.log(b);
console.log(c);

//3 -
console.log("--3")
let d = "abobora";
let e = 32;
let f = false;
let frase = `a quantidade de ${d} equivale a ${e} ? : ${f}`
console.log(frase)

//4 -
console.log("--4")
let g = 100;
let h = "50";

// Conversão: Número para String
let i = String(g);
console.log(typeof i); // "string"

// Conversão: String para Número
let j = Number(h);
console.log(typeof j); // "number"


// 5 
console.log("--5")

let texto = "  JavaScript Completo  ";

// .toLowerCase() e .trim() para limpar e minúsculo
let modificado = texto.trim().toLowerCase(); 
console.log(modificado); // "javascript completo"

// .slice() para pegar parte da string
let subTexto = modificado.slice(0, 10);
console.log(subTexto); // "javascript"


// 6

console.log("--6")

const readline = require('readline-sync');

let num = parseInt(readline.question("Digite um numero para a tabuada: "));

console.log(`Tabuada de ${num}:`);
for (let i = 1; i <= 10; i++) {
    console.log(`${num} x ${i} = ${num * i}`);
}