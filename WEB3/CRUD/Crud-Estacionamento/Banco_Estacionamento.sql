create table carros (
  codigo_modelo int not null primary key,
  nome_modelo char(100),
  codigo_marca int not null,
  endereco_cliente char(100),
  valor_aluguel float
)


alter table carros rename to veiculos
alter table veiculos add column nome_marca char(100)
alter table veiculos drop column endereco_cliente

Insert into veiculos (codigo_modelo, nome_modelo, codigo_marca,
valor_aluguel,nome_marca)
values (1, 'Peugeot 206', 29, 40.50, 'Peugeot'),
(2, 'Fusca', 22, 20.75, 'Fabricante X'),
(3, 'Ferrari', 18, 350.0, 'Ferrari'),
(4, 'Camaro', 13, 330.0, 'Camaro'),
(5, 'Gol', 16, 75.50, 'Volkswagem'),
(6, 'Celta', 15, 39.90, 'Fiat'),
(7, 'Uno', 14, 49.90, 'Fiat'),
(8, 'Palio', 13, 85.50, 'Fiat'),
(9, 'Nissan March', 12, 90.30, 'Nissan'),
(10, 'Jipe', 10, 69.99, 'Fabricante Z');


UPDATE veiculos
SET valor_aluguel = 99.99
WHERE nome_marca = 'Fiat';

update veiculos 
SET nome_modelo = 'Molina',codigo_marca = 20 , valor_aluguel = 179.90
where codigo_modelo = 6

DELETE FROM veiculos
WHERE codigo_marca >= 22;


DELETE FROM veiculos
WHERE codigo_modelo = 10;


DELETE FROM veiculos
WHERE valor_aluguel BETWEEN 90.30 AND 99.99;