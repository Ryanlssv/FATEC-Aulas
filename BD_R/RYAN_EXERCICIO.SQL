-- 1
CREATE TABLE clientes (
id_cliente SERIAL PRIMARY KEY, 
nome VARCHAR(100) NOT NULL, 
email VARCHAR(150) NOT NULL UNIQUE , 
created_at DATE DEFAULT NOW() ,
updated_at DATE DEFAULT NOW()
);

-- 2
CREATE TABLE produtos(
id_produtos SERIAL PRIMARY KEY NOT NULL,
nome VARCHAR(100) NOT NULL,
cost NUMERIC(10,2),
quantity INT NOT NULL,
created_at DATE DEFAULT NOW() ,
updated_at DATE DEFAULT NOW()
);

-- 3
CREATE TABLE pedidos(
id_pedidos SERIAL PRIMARY KEY NOT NULL ,
id_cliente INT REFERENCES clientes(id_cliente), 
id_produtos INT REFERENCES produtos(id_produtos),
quantity INT CHECK(quantity > 0 ),
created_at DATE DEFAULT NOW()
);
