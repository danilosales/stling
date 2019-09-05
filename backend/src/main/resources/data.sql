INSERT INTO tipo_usuario(descricao) VALUES ('Vendedor');
INSERT INTO tipo_usuario(descricao) VALUES ('RH');

INSERT INTO usuario(nome, email, senha, id_tipo) VALUES ('Pedro Alves', 'pedro@stling.com.br', '$2a$10$i1yD0UChT69OfFzPieeUUOT06hDtL5QHecpANsk2p9TA1SGqhU6L.', (select id from tipo_usuario where descricao = 'Vendedor'));
INSERT INTO usuario(nome, email, senha, id_tipo) VALUES ('Larissa Ferreira', 'larissa@stling.com.br', '$2a$10$i1yD0UChT69OfFzPieeUUOT06hDtL5QHecpANsk2p9TA1SGqhU6L.', (select id from tipo_usuario where descricao = 'RH'));

INSERT INTO cliente(nome,cpfCnpj, endereco) VALUES ('Luís Carlos', '44216714013', 'Rua São gonçalo, 206');
INSERT INTO cliente(nome,cpfCnpj, endereco) VALUES ('Thaís Silva', '61318323070', 'SIA Sul Qd 4, 206');

INSERT INTO produto(descricao, valor) VALUES ('Leitor biomêtrico', 240.86);
INSERT INTO produto(descricao, valor) VALUES ('Camêra Domo', 120.00);
INSERT INTO produto(descricao, valor) VALUES ('Alarme de janela', 80.00);
INSERT INTO produto(descricao, valor) VALUES ('HD 1 tera', 300.00);
