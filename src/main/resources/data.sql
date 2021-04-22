
/**create table Cliente(
    codigo int auto_increment primary key,
    nome varchar (255) not null,
    cpf varchar (11) not null,
    data_nascimento varchar (8) not null
);*/
CREATE UNIQUE INDEX nome_cpf ON Cliente  (nome,cpf);
/**create table CadastroEndereco(
    codigo int auto_increment primary key,
    logradouro varchar (255) not null,
    numero int not null,
    complemento varchar (255) not null,
    bairro varchar (100) not null,
    cidade varchar (50) not null,
    estado varchar (50) not null,
    cep varchar (20) not null,
    codigo_cliente int not null

);
ALTER TABLE CadastroEndereco
    ADD FOREIGN KEY (codigo_cliente)
        REFERENCES Cliente(codigo);*/


