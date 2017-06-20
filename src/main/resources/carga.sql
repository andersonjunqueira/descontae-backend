delete from TB_AVALIACAO;  
delete from TB_CONSUMO;
delete from TB_OFERTA_UNIDADE; 
delete from TB_OFERTA; 
delete from TB_REVISTA;
delete from TB_TELEFONE;
delete from TB_ASSINATURA; 
delete from TB_PLANO;  
delete from TB_CARTAO;
delete from TB_CLIENTE;
delete from TB_IMAGEM; 
delete from TB_UNIDADE;
delete from TB_PARCEIRO; 
delete from TB_PESSOA_FISICA;  
delete from TB_ENDERECO;
delete from TB_CATEGORIA;  
delete from TB_CIDADE; 
delete from TB_MARCA_FRANQUIA; 
delete from TB_TIPO_PESSOA;
delete from TB_UF; 

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

insert into tb_marca_franquia(id_marca_franquia, nome, imagem_logo, imagem_fundo_app, imagem_thumbnail) values (1, 'SUBWAY', 'http://descontae.com.br/img/logos/17_thumb.jpg', 'http://descontae.com.br/img/logos/17_thumb.jpg', 'http://descontae.com.br/img/logos/17_thumb.jpg');
insert into tb_marca_franquia(id_marca_franquia, nome, imagem_logo, imagem_fundo_app, imagem_thumbnail) values (2, 'Chiquinho Sorvetes', 'http://descontae.com.br/img/logos/70_thumb.jpg', 'http://descontae.com.br/img/logos/70_thumb.jpg', 'http://descontae.com.br/img/logos/70_thumb.jpg');
insert into tb_marca_franquia(id_marca_franquia, nome, imagem_logo, imagem_fundo_app, imagem_thumbnail) values (3, 'Burger King', 'http://descontae.com.br/img/logos/68_thumb.jpg', 'http://descontae.com.br/img/logos/68_thumb.jpg', 'http://descontae.com.br/img/logos/68_thumb.jpg');  

insert into tb_categoria(id_categoria, nome) values (1, 'Lanches');
insert into tb_categoria(id_categoria, nome) values (2, 'Sorvetes');

insert into tb_tipo_pessoa(id_tipo_pessoa, nome) values (1, 'Administrador');  
insert into tb_tipo_pessoa(id_tipo_pessoa, nome) values (2, 'Parceiro');
insert into tb_tipo_pessoa(id_tipo_pessoa, nome) values (3, 'Usuario');

-- REVISTA 
insert into tb_revista(id_revista, edicao, descricao, inicio_vigencia, fim_vigencia, pdf, imagem)
values (1, 'JUN/2017', 'REVISTA DE JUN/2017', to_date('01/06/2017', 'DD/MM/YYYY'), to_date('30/07/2017', 'DD/MM/YYYY'), '', '');

-- PARCEIRO 1  
insert into tb_endereco(id_endereco, cep, logradouro, complemento, numero, bairro, id_cidade, loc_latitude, loc_longitude) 
values (1, '70735110', 'SQN 303 Bloco K', 'Apto', '101', 'Asa Norte', 1, null, null);  

insert into tb_pessoa_fisica(id_pessoa, nome, email, cpf, telefone, facebook, instagram, id_tipo_pessoa, id_endereco, data_cadastro, data_ultima_atualizacao)  
values(1, 'Parceiro 1', 'nao@teconto.com.br', '12353786243', '6112345678', null, null, 2, 1, '08/06/2017', '08/06/2017');  

insert into tb_parceiro(id_parceiro, nome, nome_fantasia, email, cnpj, id_categoria, id_pessoa, id_marca_franquia, data_cadastro, data_ultima_atualizacao) 
values(1, 'SUBWAY CNPJ 1', 'SUBWAY', 'nao@teconto.com', '63279427000190', 1, 1, 1, '08/06/2017', '08/06/2017');

-- SUBWAY 304N 
insert into tb_endereco(id_endereco, cep, logradouro, complemento, numero, bairro, id_cidade, loc_latitude, loc_longitude) 
values (2, '70736520', 'SCN 304 Bloco B', 'Loja', '7', 'Asa Norte', 1, -15.774597, -47.885590);

insert into tb_unidade(id_unidade, nome, id_parceiro, inicio_expediente, fim_expediente, id_endereco)  
values (1, 'SUBWAY 304N', 1, null, null, 2); 

insert into tb_oferta(id_oferta, descricao, regras, imagem_site, valor, desconto, id_pessoa, situacao)  
values (1, 'DESCONTO 10%', 'REGRAS', '', 5.00, 0.1, 1, 'A'); 

insert into tb_oferta_unidade(id_oferta_unidade, id_oferta, id_unidade, id_revista)
values (1, 1, 1, 1);

-- SUBWAY 706N 
insert into tb_endereco(id_endereco, cep, logradouro, complemento, numero, bairro, id_cidade, loc_latitude, loc_longitude) 
values (3, '70235520', 'SCN 706/707 Bloco F', 'Loja', '36/39', 'Asa Norte', 1, -15.769196, -47.890580);

insert into tb_unidade(id_unidade, nome, id_parceiro, inicio_expediente, fim_expediente, id_endereco)  
values (2, 'SUBWAY 706N', 1, null, null, 3); 

insert into tb_oferta_unidade(id_oferta_unidade, id_oferta, id_unidade, id_revista)
values (2, 1, 2, 1);

-- PARCEIRO 2  
insert into tb_endereco(id_endereco, cep, logradouro, complemento, numero, bairro, id_cidade, loc_latitude, loc_longitude) 
values (4, '71505220', 'SHIN QI 03 Conjunto 02', 'Casa', '8', 'Lago Norte', 1, null, null);

insert into tb_pessoa_fisica(id_pessoa, nome, email, cpf, telefone, facebook, instagram, id_tipo_pessoa, id_endereco, data_cadastro, data_ultima_atualizacao)  
values(2, 'Parceiro 2', 'nao@teconto.com.br', '50561648875', '6198765432', null, null, 2, 2, '08/06/2017', '08/06/2017');  

insert into tb_parceiro(id_parceiro, nome, nome_fantasia, email, cnpj, id_categoria, id_pessoa, id_marca_franquia, data_cadastro, data_ultima_atualizacao) 
values(2, 'SUBWAY CNPJ 2', 'SUBWAY', 'nao@teconto.com', '94033097000182', 1, 2, 1, '08/06/2017', '08/06/2017');

-- SUBWAY 405s 
insert into tb_endereco(id_endereco, cep, logradouro, complemento, numero, bairro, id_cidade, loc_latitude, loc_longitude) 
values (5, '70239500', 'SCS 405 Bloco A', 'Loja', '6', 'Asa Sul', 1, -15.814477, -47.889956);  

insert into tb_unidade(id_unidade, nome, id_parceiro, inicio_expediente, fim_expediente, id_endereco)  
values (3, 'SUBWAY 405S', 2, null, null, 5); 

insert into tb_oferta(id_oferta, descricao, regras, imagem_site, valor, desconto, id_pessoa, situacao)
values (2, 'Subway Club por R$5,00', '', '', 5.00, 0.2, 2, 'A');  

insert into tb_oferta_unidade(id_oferta_unidade, id_oferta, id_unidade, id_revista)
values (3, 2, 3, 1);

-- PARCEIRO 3  
insert into tb_endereco(id_endereco, cep, logradouro, complemento, numero, bairro, id_cidade, loc_latitude, loc_longitude) 
values (6, '71505220', 'SHIN QI 03 Conjunto 02', 'Casa', '2', 'Lago Norte', 1, null, null);

insert into tb_pessoa_fisica(id_pessoa, nome, email, cpf, telefone, facebook, instagram, id_tipo_pessoa, id_endereco, data_cadastro, data_ultima_atualizacao)  
values(3, 'Parceiro 3', 'nao@teconto.com.br', '82153342880', '6198765432', null, null, 2, 6, '08/06/2017', '08/06/2017');  

insert into tb_parceiro(id_parceiro, nome, nome_fantasia, email, cnpj, id_categoria, id_pessoa, id_marca_franquia, data_cadastro, data_ultima_atualizacao) 
values(3, 'Chiquinho Sorvetes', 'CHIQUINHO SORVETES', 'nao@teconto.com', '04233588000184', 2, 3, 2, '08/06/2017', '08/06/2017');

-- CHIQUINHO 706N  
insert into tb_endereco(id_endereco, cep, logradouro, complemento, numero, bairro, id_cidade, loc_latitude, loc_longitude) 
values (7, '70736520', 'SCN 304 Bloco G', 'Loja', '13', 'Asa Norte', 1, -15.769257, -47.890767);

insert into tb_unidade(id_unidade, nome, id_parceiro, inicio_expediente, fim_expediente, id_endereco)  
values (4, 'CHIQUINHO 304N', 3, null, null, 7);  

insert into tb_oferta(id_oferta, descricao, regras, imagem_site, valor, desconto, id_pessoa, situacao)
values (3, 'Compre 1 casquinha e leve 2', '', '', 1.00, 0.2, 3, 'A'); 

insert into tb_oferta_unidade(id_oferta_unidade, id_oferta, id_unidade, id_revista)
values (4, 3, 4, 1);

insert into tb_avaliacao(id_avaliacao, id_pessoa, id_unidade, nota_satisfacao) 
select p.id_pessoa * 2 + u.id_unidade * 10, p.id_pessoa, u.id_unidade, trunc(random() * 5) 
  from tb_unidade u,
tb_pessoa_fisica p  
left join tb_avaliacao a on p.id_pessoa = a.id_pessoa
