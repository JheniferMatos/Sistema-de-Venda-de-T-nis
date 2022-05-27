INSERT INTO mar_marca (mar_nome) VALUES ('Adidas');
INSERT INTO mar_marca (mar_nome) VALUES ('Nike');
INSERT INTO mar_marca (mar_nome) VALUES ('New Balance');
INSERT INTO mar_marca (mar_nome) VALUES ('Olympikus');
INSERT INTO mar_marca (mar_nome) VALUES ('Klin');
INSERT INTO mar_marca (mar_nome) VALUES ('Melissa');
INSERT INTO mar_marca (mar_nome) VALUES ('Converse');
INSERT INTO mar_marca (mar_nome) VALUES ('Vans');

INSERT INTO mte_modelo_tenis(mte_descricao, mte_marca, mte_preco) values ('Forum', 1, 200.00);
INSERT INTO mte_modelo_tenis(mte_descricao, mte_marca, mte_preco) values ('Superstar', 1, 300.00);
INSERT INTO mte_modelo_tenis(mte_descricao, mte_marca, mte_preco) values ('Air MAx', 2, 800.00);
INSERT INTO mte_modelo_tenis(mte_descricao, mte_marca, mte_preco) values ('Mercurial', 2, 180.00);
INSERT INTO mte_modelo_tenis(mte_descricao, mte_marca, mte_preco) values ('Hierro V6', 3, 250.00);
INSERT INTO mte_modelo_tenis(mte_descricao, mte_marca, mte_preco) values ('Elite V2', 3, 230.00);
INSERT INTO mte_modelo_tenis(mte_descricao, mte_marca, mte_preco) values ('Old School', 8, 300.00);
INSERT INTO mte_modelo_tenis(mte_descricao, mte_marca, mte_preco) values ('Sk8-Hi', 8, 400.00);
INSERT INTO mte_modelo_tenis(mte_descricao, mte_marca, mte_preco) values ('All-Star', 7, 200.00);

INSERT INTO fun_funcionario(fun_usuario, fun_senha, fun_nome, fun_cpf, fun_email,fun_administrador) values ('admin', 'senha.123', 'João da Silva Pereira', '123.456.789-10', 'jspereira@gmail.com', 1);
INSERT INTO fun_funcionario(fun_usuario, fun_senha, fun_nome, fun_cpf, fun_email,fun_administrador) values ('lohan.toledo', 'senha.lohan', 'Lohan Toledo Tosta', '404.331.057-22', 'lohan.ltt@gmail.com', 0);
INSERT INTO fun_funcionario(fun_usuario, fun_senha, fun_nome, fun_cpf, fun_email,fun_administrador) values ('roberth.fontes', 'senha.roberth', 'Roberth Fontes', '865.325.123-32', 'roberthf@outlook.com', 0);
INSERT INTO fun_funcionario(fun_usuario, fun_senha, fun_nome, fun_cpf, fun_email,fun_administrador) values ('roberth.analista', 'senha.roberth', 'Roberth Fontes Analista', '000.000.000-00', 'roberthf@teste.com', 0);

INSERT INTO cli_cliente(cli_nome, cli_cpf, cli_logradouro, cli_numero, cli_cidade, cli_estado, cli_bairro, cli_cep, cli_email) values ('Joventino da Silva Sauro', '789.456.123-11', 'Rua dos Bobos', 0, 'Itapeva', 'SP', 'Centro', '12345-678', 'joveamajuma@gmail.com');
INSERT INTO cli_cliente(cli_nome, cli_cpf, cli_logradouro, cli_numero, cli_cidade, cli_estado, cli_bairro, cli_cep, cli_email) values ('Gil do Vigor', '246.135.876-34', 'AV Ricardo Brandão', 1234, 'Campo Grande', 'MS', 'Chácara Cachoeira', '76543-210', 'aibrasil@gmail.com');
INSERT INTO cli_cliente(cli_nome, cli_cpf, cli_logradouro, cli_numero, cli_cidade, cli_estado, cli_bairro, cli_cep, cli_email) values ('Marlene', '111.222.333-45', 'Rua Ceará', 3000, 'Campo Grande', 'MS', 'Jardim dos Estados', '78564-123', 'marlene1000grau@gmail.com');
INSERT INTO cli_cliente(cli_nome, cli_cpf, cli_logradouro, cli_numero, cli_cidade, cli_estado, cli_bairro, cli_cep, cli_email) values ('Lerolero', '111.222.333-44', 'Rua Brebrobom', 3000, 'Campo Grande', 'MS', 'Jardim dos Estados', '78564-896', 'marlene180grau@gmail.com');

