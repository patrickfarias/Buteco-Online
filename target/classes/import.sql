# Informamos os ID para facilitar a leitura e os relacionamentos
# Cenario de testes

insert into ingrediente (id, nome, unidade_medida, preco_unitario) values (1, 'Leite', 'Lt', 2.00);
insert into ingrediente (id, nome, unidade_medida, preco_unitario) values (2, 'Cafe', 'Kg', 12.00);
insert into ingrediente (id, nome, unidade_medida, preco_unitario) values (3, 'Acucar', 'Kg', 6.00);
insert into ingrediente (id, nome, unidade_medida, preco_unitario) values (4, 'Massa de Cookie', 'Kg', 8.00);
insert into ingrediente (id, nome, unidade_medida, preco_unitario) values (5, 'Pao', 'Kg', 14.00);

insert into componente (id, quantidade, ingrediente_id) values (1, 0.200, 1); 
insert into componente (id, quantidade, ingrediente_id) values (2, 0.100, 1); 
insert into componente (id, quantidade, ingrediente_id, descricao) values (3, 0.050, 2, '50 Gramas de Café'); 
insert into componente (id, quantidade, ingrediente_id) values (4, 0.050, 3); 
insert into componente (id, quantidade, ingrediente_id) values (5, 0.500, 4); 
insert into componente (id, quantidade, ingrediente_id) values (6, 0.050, 5); 

insert into produto (id, nome, preco) values (1, 'Leite', 1.00);
insert into produto (id, nome, preco) values (2, 'Cafe', 2.00);
insert into produto (id, nome, preco) values (3, 'Cafe Pingado', 3.00);
insert into produto (id, nome, preco) values (4, 'Cafe com Pao', 5.00);
insert into produto (id, nome, preco) values (5, 'Cafe Pingado com Pao', 6.00);
insert into produto (id, nome, preco) values (6, 'Cookie', 8.00);


insert into produto_componentes (produto_id, componentes_id) values (1,1);
insert into produto_componentes (produto_id, componentes_id) values (2,3), (2,4);
insert into produto_componentes (produto_id, componentes_id) values (3,2), (3,3), (3,4);
insert into produto_componentes (produto_id, componentes_id) values (4,3), (4,4), (4,6);
insert into produto_componentes (produto_id, componentes_id) values (5,1);
insert into produto_componentes (produto_id, componentes_id) values (6,1);


insert into estoque (quantidade_atual, ingrediente_id) values (10,1);
insert into estoque (quantidade_atual, ingrediente_id) values (15,2);
insert into estoque (quantidade_atual, ingrediente_id) values (17,3);
insert into estoque (quantidade_atual, ingrediente_id) values (1,4);
insert into estoque (quantidade_atual, ingrediente_id) values (1,5);


# Senha da Maria  123
insert into usuario (login, senha) values ('Maria', '$2a$10$SN4DEiq/fIw7LToGiY1YpeCKoJXSNPJECfMJl950aSSJqZes8Gyy.');

