INSERT INTO tipo_usuario(descricao) VALUES ('Vendedor');
INSERT INTO tipo_usuario(descricao) VALUES ('RH');

INSERT INTO usuario(nome, email, senha, id_tipo) VALUES ('Pedro Alves', 'pedro@stling.com.br', '$2a$10$i1yD0UChT69OfFzPieeUUOT06hDtL5QHecpANsk2p9TA1SGqhU6L.', (select id from tipo_usuario where descricao = 'Vendedor'));
INSERT INTO usuario(nome, email, senha, id_tipo) VALUES ('Larissa Ferreira', 'larissa@stling.com.br', '$2a$10$i1yD0UChT69OfFzPieeUUOT06hDtL5QHecpANsk2p9TA1SGqhU6L.', (select id from tipo_usuario where descricao = 'RH'));

