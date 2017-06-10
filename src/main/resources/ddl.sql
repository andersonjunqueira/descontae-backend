drop table TB_AVALIACAO;
drop table TB_CONSUMO;
drop table TB_OFERTA_UNIDADE;
drop table TB_OFERTA;
drop table TB_REVISTA;
drop table TB_TELEFONE;
drop table TB_ASSINATURA;
drop table TB_PLANO;
drop table TB_IMAGEM;
drop table TB_UNIDADE;
drop table TB_EMPREENDIMENTO;
drop table TB_PESSOA_FISICA;
drop table TB_ENDERECO;
drop table TB_CATEGORIA;
drop table TB_CIDADE;
drop table TB_MARCA_FRANQUIA;
drop table TB_TIPO_PESSOA;
drop table TB_UF;

/*==============================================================*/
/* Table: TB_ASSINATURA                                         */
/*==============================================================*/
create table TB_ASSINATURA (
   ID_ASSINATURA        INT8                 not null,
   ID_PESSOA            INT8                 not null,
   ID_PLANO             INT8                 not null,
   CODIGO_CARTAO        VARCHAR(30)          not null,
   INICIO_VIGENCIA      DATE                 not null,
   FIM_VIGENCIA         DATE                 null,
   constraint PK_TB_ASSINATURA primary key (ID_ASSINATURA)
);

/*==============================================================*/
/* Table: TB_AVALIACAO                                          */
/*==============================================================*/
create table TB_AVALIACAO (
   ID_AVALIACAO         INT8                 not null,
   ID_PESSOA            INT8                 not null,
   ID_UNIDADE           INT8                 not null,
   NOTA_PRECO           INT4                 null,
   NOTA_SATISFACAO      INT4                 null,
   constraint PK_TB_AVALIACAO primary key (ID_AVALIACAO)
);

/*==============================================================*/
/* Table: TB_CATEGORIA                                          */
/*==============================================================*/
create table TB_CATEGORIA (
   ID_CATEGORIA         INT8                 not null,
   NOME                 VARCHAR(30)          not null,
   constraint PK_TB_CATEGORIA primary key (ID_CATEGORIA)
);

/*==============================================================*/
/* Table: TB_CIDADE                                             */
/*==============================================================*/
create table TB_CIDADE (
   ID_CIDADE            INT8                 not null,
   ID_UF                INT8                 null,
   NOME                 VARCHAR(100)         not null,
   constraint PK_TB_CIDADE primary key (ID_CIDADE)
);

/*==============================================================*/
/* Table: TB_CONSUMO                                            */
/*==============================================================*/
create table TB_CONSUMO (
   ID_CONSUMO           INT8                 not null,
   ID_OFERTA            INT8                 null,
   ID_ASSINATURA        INT8                 null,
   DATA_CONSUMO         DATE                 not null,
   constraint PK_TB_CONSUMO primary key (ID_CONSUMO)
);

/*==============================================================*/
/* Table: TB_EMPREENDIMENTO                                     */
/*==============================================================*/
create table TB_EMPREENDIMENTO (
   ID_EMPREENDIMENTO    INT8                 not null,
   ID_CATEGORIA         INT8                 not null,
   ID_MARCA_FRANQUIA    INT8                 not null,
   ID_PESSOA            INT8                 not null,
   CNPJ                 VARCHAR(14)          not null,
   NOME_FANTASIA        VARCHAR(100)         not null,
   NOME                 VARCHAR(100)         not null,
   EMAIL                VARCHAR(100)         null,
   DATA_CADASTRO        date                 not null,
   DATA_ULTIMA_ATUALIZACAO date                 not null,
   constraint PK_TB_EMPREENDIMENTO primary key (ID_EMPREENDIMENTO)
);

/*==============================================================*/
/* Table: TB_ENDERECO                                           */
/*==============================================================*/
create table TB_ENDERECO (
   ID_ENDERECO          INT8                 not null,
   ID_CIDADE            INT8                 null,
   CEP                  VARCHAR(8)           not null,
   LOC_LATITUDE         FLOAT8               null,
   LOC_LONGITUDE        FLOAT8               null,
   LOGRADOURO           VARCHAR(100)         not null,
   COMPLEMENTO          VARCHAR(30)          null,
   NUMERO               VARCHAR(5)           null,
   BAIRRO               VARCHAR(50)          not null,
   constraint PK_TB_ENDERECO primary key (ID_ENDERECO)
);

/*==============================================================*/
/* Table: TB_IMAGEM                                             */
/*==============================================================*/
create table TB_IMAGEM (
   ID_IMAGEM            INT8                 not null,
   ID_UNIDADE           INT8                 null,
   IMAGEM               VARCHAR(200)         not null,
   constraint PK_TB_IMAGEM primary key (ID_IMAGEM)
);

/*==============================================================*/
/* Table: TB_MARCA_FRANQUIA                                     */
/*==============================================================*/
create table TB_MARCA_FRANQUIA (
   ID_MARCA_FRANQUIA    INT8                 not null,
   NOME                 VARCHAR(100)         not null,
   IMAGEM_LOGO          VARCHAR(200)         not null,
   IMAGEM_FUNDO_APP     VARCHAR(200)         not null,
   IMAGEM_THUMBNAIL     VARCHAR(200)         not null,
   constraint PK_TB_MARCA_FRANQUIA primary key (ID_MARCA_FRANQUIA)
);

/*==============================================================*/
/* Table: TB_OFERTA                                             */
/*==============================================================*/
create table TB_OFERTA (
   ID_OFERTA            INT8                 not null,
   DESCRICAO            VARCHAR(500)         not null,
   IMAGEM_SITE          VARCHAR(200)         not null,
   VALOR                FLOAT8               not null,
   DESCONTO             FLOAT8               not null,
   ID_PESSOA            INT8                 not null,
   SITUACAO             VARCHAR(1)           not null,
   REGRAS               VARCHAR(1000)        not null,
   constraint PK_TB_OFERTA primary key (ID_OFERTA)
);

/*==============================================================*/
/* Table: TB_OFERTA_UNIDADE                                     */
/*==============================================================*/
create table TB_OFERTA_UNIDADE (
   ID_OFERTA_UNIDADE    INT8                 not null,
   ID_OFERTA            INT8                 not null,
   ID_UNIDADE           INT8                 not null,
   ID_REVISTA           INT8                 not null,
   constraint PK_TB_OFERTA_UNIDADE primary key (ID_OFERTA_UNIDADE)
);

/*==============================================================*/
/* Index: UK1_OFERTA_UNIDADE                                    */
/*==============================================================*/
create unique index UK1_OFERTA_UNIDADE on TB_OFERTA_UNIDADE (
ID_OFERTA,
ID_UNIDADE,
ID_REVISTA
);

/*==============================================================*/
/* Table: TB_PESSOA_FISICA                                      */
/*==============================================================*/
create table TB_PESSOA_FISICA (
   ID_PESSOA            INT8                 not null,
   ID_TIPO_PESSOA       INT8                 not null,
   ID_ENDERECO          INT8                 null,
   NOME                 VARCHAR(100)         not null,
   EMAIL                VARCHAR(100)         not null,
   CPF                  VARCHAR(11)          not null,
   TELEFONE             VARCHAR(15)          null,
   INSTAGRAM            VARCHAR(50)          null,
   FACEBOOK             VARCHAR(50)          null,
   DATA_CADASTRO        date                 not null,
   DATA_ULTIMA_ATUALIZACAO date                 not null,
   constraint PK_TB_PESSOA_FISICA primary key (ID_PESSOA)
);

/*==============================================================*/
/* Table: TB_PLANO                                              */
/*==============================================================*/
create table TB_PLANO (
   ID_PLANO             INT8                 not null,
   TITULO               VARCHAR(30)          not null,
   DESCRICAO            VARCHAR(500)         null,
   VALOR                FLOAT8               not null,
   SITUACAO             VARCHAR(1)           not null,
   IMAGEM               VARCHAR(200)         not null,
   constraint PK_TB_PLANO primary key (ID_PLANO)
);

/*==============================================================*/
/* Table: TB_REVISTA                                            */
/*==============================================================*/
create table TB_REVISTA (
   ID_REVISTA           INT8                 not null,
   EDICAO               VARCHAR(10)          not null,
   DESCRICAO            VARCHAR(200)         not null,
   INICIO_VIGENCIA      DATE                 not null,
   FIM_VIGENCIA         DATE                 not null,
   PDF                  VARCHAR(200)         not null,
   IMAGEM               VARCHAR(200)         not null,
   constraint PK_TB_REVISTA primary key (ID_REVISTA)
);

/*==============================================================*/
/* Table: TB_TELEFONE                                           */
/*==============================================================*/
create table TB_TELEFONE (
   ID_TELEFONE          INT8                 not null,
   ID_EMPREENDIMENTO    INT8                 null,
   ID_UNIDADE           INT8                 null,
   ID_PESSOA            INT8                 null,
   NUMERO               VARCHAR(30)          not null,
   constraint PK_TB_TELEFONE primary key (ID_TELEFONE)
);

/*==============================================================*/
/* Table: TB_TIPO_PESSOA                                        */
/*==============================================================*/
create table TB_TIPO_PESSOA (
   ID_TIPO_PESSOA       INT8                 not null,
   NOME                 VARCHAR(30)          not null,
   constraint PK_TB_TIPO_PESSOA primary key (ID_TIPO_PESSOA)
);

/*==============================================================*/
/* Table: TB_UF                                                 */
/*==============================================================*/
create table TB_UF (
   ID_UF                INT8                 not null,
   NOME                 VARCHAR(30)          not null,
   SIGLA                VARCHAR(2)           not null,
   constraint PK_TB_UF primary key (ID_UF)
);

/*==============================================================*/
/* Table: TB_UNIDADE                                            */
/*==============================================================*/
create table TB_UNIDADE (
   ID_UNIDADE           INT8                 not null,
   ID_ENDERECO          INT8                 not null,
   ID_EMPREENDIMENTO    INT8                 not null,
   NOME                 VARCHAR(50)          not null,
   INICIO_EXPEDIENTE    TIME                 null,
   FIM_EXPEDIENTE       TIME                 null,
   constraint PK_TB_UNIDADE primary key (ID_UNIDADE)
);

alter table TB_ASSINATURA
   add constraint FK_TB_ASSIN_REFERENCE_TB_PESSO foreign key (ID_PESSOA)
      references TB_PESSOA_FISICA (ID_PESSOA)
      on delete restrict on update restrict;

alter table TB_ASSINATURA
   add constraint FK_TB_ASSIN_REFERENCE_TB_PLANO foreign key (ID_PLANO)
      references TB_PLANO (ID_PLANO)
      on delete restrict on update restrict;

alter table TB_AVALIACAO
   add constraint FK_TB_AVALI_REFERENCE_TB_PESSO foreign key (ID_PESSOA)
      references TB_PESSOA_FISICA (ID_PESSOA)
      on delete restrict on update restrict;

alter table TB_AVALIACAO
   add constraint FK_TB_AVALI_REFERENCE_TB_UNIDA foreign key (ID_UNIDADE)
      references TB_UNIDADE (ID_UNIDADE)
      on delete restrict on update restrict;

alter table TB_CIDADE
   add constraint FK_TB_CIDAD_REFERENCE_TB_UF foreign key (ID_UF)
      references TB_UF (ID_UF)
      on delete restrict on update restrict;

alter table TB_CONSUMO
   add constraint FK_TB_CONSU_REFERENCE_TB_OFERT foreign key (ID_OFERTA)
      references TB_OFERTA (ID_OFERTA)
      on delete restrict on update restrict;

alter table TB_CONSUMO
   add constraint FK_TB_CONSU_REFERENCE_TB_ASSIN foreign key (ID_ASSINATURA)
      references TB_ASSINATURA (ID_ASSINATURA)
      on delete restrict on update restrict;

alter table TB_EMPREENDIMENTO
   add constraint FK_TB_EMPRE_REFERENCE_TB_MARCA foreign key (ID_MARCA_FRANQUIA)
      references TB_MARCA_FRANQUIA (ID_MARCA_FRANQUIA)
      on delete restrict on update restrict;

alter table TB_EMPREENDIMENTO
   add constraint FK_TB_EMPRE_REFERENCE_TB_PESSO foreign key (ID_PESSOA)
      references TB_PESSOA_FISICA (ID_PESSOA)
      on delete restrict on update restrict;

alter table TB_EMPREENDIMENTO
   add constraint FK_TB_EMPRE_REFERENCE_TB_CATEG foreign key (ID_CATEGORIA)
      references TB_CATEGORIA (ID_CATEGORIA)
      on delete restrict on update restrict;

alter table TB_ENDERECO
   add constraint FK_TB_ENDER_REFERENCE_TB_CIDAD foreign key (ID_CIDADE)
      references TB_CIDADE (ID_CIDADE)
      on delete restrict on update restrict;

alter table TB_IMAGEM
   add constraint FK_TB_IMAGE_REFERENCE_TB_UNIDA foreign key (ID_UNIDADE)
      references TB_UNIDADE (ID_UNIDADE)
      on delete restrict on update restrict;

alter table TB_OFERTA
   add constraint FK_TB_OFERT_REFERENCE_TB_PESSO foreign key (ID_PESSOA)
      references TB_PESSOA_FISICA (ID_PESSOA)
      on delete restrict on update restrict;

alter table TB_OFERTA_UNIDADE
   add constraint FK_TB_OFERT_REFERENCE_TB_OFERT foreign key (ID_OFERTA)
      references TB_OFERTA (ID_OFERTA)
      on delete restrict on update restrict;

alter table TB_OFERTA_UNIDADE
   add constraint FK_TB_OFERT_REFERENCE_TB_UNIDA foreign key (ID_UNIDADE)
      references TB_UNIDADE (ID_UNIDADE)
      on delete restrict on update restrict;

alter table TB_OFERTA_UNIDADE
   add constraint FK_TB_OFERT_REFERENCE_TB_REVIS foreign key (ID_REVISTA)
      references TB_REVISTA (ID_REVISTA)
      on delete restrict on update restrict;

alter table TB_PESSOA_FISICA
   add constraint FK_TB_PESSO_REFERENCE_TB_ENDER foreign key (ID_ENDERECO)
      references TB_ENDERECO (ID_ENDERECO)
      on delete restrict on update restrict;

alter table TB_PESSOA_FISICA
   add constraint FK_TB_PESSO_REFERENCE_TB_TIPO_ foreign key (ID_TIPO_PESSOA)
      references TB_TIPO_PESSOA (ID_TIPO_PESSOA)
      on delete restrict on update restrict;

alter table TB_TELEFONE
   add constraint FK_TB_TELEF_REFERENCE_TB_EMPRE foreign key (ID_EMPREENDIMENTO)
      references TB_EMPREENDIMENTO (ID_EMPREENDIMENTO)
      on delete restrict on update restrict;

alter table TB_TELEFONE
   add constraint FK_TB_TELEF_REFERENCE_TB_UNIDA foreign key (ID_UNIDADE)
      references TB_UNIDADE (ID_UNIDADE)
      on delete restrict on update restrict;

alter table TB_TELEFONE
   add constraint FK_TB_TELEF_REFERENCE_TB_PESSO foreign key (ID_PESSOA)
      references TB_PESSOA_FISICA (ID_PESSOA)
      on delete restrict on update restrict;

alter table TB_UNIDADE
   add constraint FK_TB_UNIDA_REFERENCE_TB_ENDER foreign key (ID_ENDERECO)
      references TB_ENDERECO (ID_ENDERECO)
      on delete restrict on update restrict;

alter table TB_UNIDADE
   add constraint FK_TB_UNIDA_REFERENCE_TB_EMPRE foreign key (ID_EMPREENDIMENTO)
      references TB_EMPREENDIMENTO (ID_EMPREENDIMENTO)
      on delete restrict on update restrict;

