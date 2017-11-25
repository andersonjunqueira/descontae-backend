insert into tb_uf(id_uf, sigla, nome) Values(12, 'AC', 'Acre');
insert into tb_uf(id_uf, sigla, nome) Values(27, 'AL', 'Alagoas'); 
insert into tb_uf(id_uf, sigla, nome) Values(13, 'AM', 'Amazonas');
insert into tb_uf(id_uf, sigla, nome) Values(16, 'AP', 'Amapá');
insert into tb_uf(id_uf, sigla, nome) Values(29, 'BA', 'Bahia');
insert into tb_uf(id_uf, sigla, nome) values (23, 'CE', 'Ceará');  
insert into tb_uf(id_uf, sigla, nome) values (53, 'DF', 'Distrito Federal');
insert into tb_uf(id_uf, sigla, nome) values (32, 'ES', 'Espírito Santo'); 
insert into tb_uf(id_uf, sigla, nome) values (52, 'GO', 'Goiás');  
insert into tb_uf(id_uf, sigla, nome) values (21, 'MA', 'Maranhão');
insert into tb_uf(id_uf, sigla, nome) values (31, 'MG', 'Minas Gerais');
insert into tb_uf(id_uf, sigla, nome) values (50, 'MS', 'Mato Grosso do Sul'); 
insert into tb_uf(id_uf, sigla, nome) values (51, 'MT', 'Mato Grosso');
insert into tb_uf(id_uf, sigla, nome) values (15, 'PA', 'Pará');
insert into tb_uf(id_uf, sigla, nome) values (25, 'PB', 'Paraíba');
insert into tb_uf(id_uf, sigla, nome) values (26, 'PE', 'Pernambuco'); 
insert into tb_uf(id_uf, sigla, nome) values (22, 'PI', 'Piauí');  
insert into tb_uf(id_uf, sigla, nome) values (41, 'PR', 'Paraná'); 
insert into tb_uf(id_uf, sigla, nome) values (33, 'RJ', 'Rio de Janeiro'); 
insert into tb_uf(id_uf, sigla, nome) values (24, 'RN', 'Rio Grande do Norte');
insert into tb_uf(id_uf, sigla, nome) values (11, 'RO', 'Rondônia');
insert into tb_uf(id_uf, sigla, nome) values (14, 'RR', 'Roraima');
insert into tb_uf(id_uf, sigla, nome) values (43, 'RS', 'Rio Grande do Sul');  
insert into tb_uf(id_uf, sigla, nome) values (42, 'SC', 'Santa Catarina'); 
insert into tb_uf(id_uf, sigla, nome) values (28, 'SE', 'Sergipe');
insert into tb_uf(id_uf, sigla, nome) values (35, 'SP', 'São Paulo');  
insert into tb_uf(id_uf, sigla, nome) values (17, 'TO', 'Tocantins');  

insert into tb_cidade(id_cidade, nome, id_uf) values (1, 'Brasília', 53);  
insert into tb_cidade(id_cidade, nome, id_uf) values (2, 'Goiânia', 52);
insert into tb_cidade(id_cidade, nome, id_uf) values (3, 'Porto Alegre', 43);  
insert into tb_cidade(id_cidade, nome, id_uf) values (4, 'São Paulo', 35); 

insert into tb_categoria(id_categoria, nome) values (1, 'Lanches');
insert into tb_categoria(id_categoria, nome) values (2, 'Sorvetes');

insert into tb_tipo_pessoa(id_tipo_pessoa, nome) values (1, 'Administrador');  
insert into tb_tipo_pessoa(id_tipo_pessoa, nome) values (2, 'Parceiro');
insert into tb_tipo_pessoa(id_tipo_pessoa, nome) values (3, 'Usuario');

insert into tb_marca_franquia(id_marca_franquia, nome, imagem_logo, imagem_fundo_app, imagem_thumbnail) values (1, 'SUBWAY', 'http://descontae.com.br/img/logos/17_thumb.jpg', 'http://descontae.com.br/img/logos/17_thumb.jpg', 'http://descontae.com.br/img/logos/17_thumb.jpg');
insert into tb_marca_franquia(id_marca_franquia, nome, imagem_logo, imagem_fundo_app, imagem_thumbnail) values (2, 'Chiquinho Sorvetes', 'http://descontae.com.br/img/logos/70_thumb.jpg', 'http://descontae.com.br/img/logos/70_thumb.jpg', 'http://descontae.com.br/img/logos/70_thumb.jpg');
insert into tb_marca_franquia(id_marca_franquia, nome, imagem_logo, imagem_fundo_app, imagem_thumbnail) values (3, 'Burger King', 'http://descontae.com.br/img/logos/68_thumb.jpg', 'http://descontae.com.br/img/logos/68_thumb.jpg', 'http://descontae.com.br/img/logos/68_thumb.jpg');  

insert into tb_configuracao(id_configuracao, chave, valor) values (1, 'exibe_cadastro_completo', 'false');