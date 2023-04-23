create table t_pn_registro (
    id bigint not null auto_increment,
    data date not null,
    descricao varchar(255) not null,
    titulo varchar(255) not null,
    usuario_id bigint, primary key (id)
);
create table t_pn_usuario (
    id bigint not null auto_increment,
    email varchar(255) not null,
    nome varchar(255) not null,
    senha varchar(20) not null,
    primary key (id)
);

alter table t_pn_usuario drop index UK_3orwr8unufk7qv45cxud09ddb;
alter table t_pn_usuario add constraint UK_3orwr8unufk7qv45cxud09ddb unique (email);
alter table t_pn_registro add constraint FK27r7js9h6eqgu1dkhf8pr7u9l foreign key (usuario_id) references t_pn_usuario (id);

INSERT INTO t_pn_usuario (email, nome, senha) VALUES
    ('ana.silva@gmail.com', 'Ana Silva', 'senha123'),
    ('carlos.rodrigues@yahoo.com', 'Carlos Rodrigues', '12minhasenha'),
    ('julia.almeida@hotmail.com', 'Julia Almeida', 'senha5segura'),
    ('marcio.santos@gmail.com', 'Marcio Santos', 'senha1234'),
    ('maria.pereira@gmail.com', 'Maria Pereira', 'senhasecret123');

INSERT INTO t_pn_registro (data, descricao, titulo, usuario_id) VALUES
    ('2023-04-23', 'Compra de mantimentos', 'Supermercado', 1),
    ('2023-04-22', 'Reunião com clientes', 'Agenda', 1),
    ('2023-04-21', 'Pagamento da conta de luz', 'Contas', 1);

INSERT INTO t_pn_registro (data, descricao, titulo, usuario_id) VALUES
    ('2023-04-23', 'Ligação para fornecedor', 'Negócios', 2),
    ('2023-04-22', 'Elaboração de relatório', 'Trabalho', 2),
    ('2023-04-21', 'Compra de equipamentos', 'Suprimentos', 2);

INSERT INTO t_pn_registro (data, descricao, titulo, usuario_id) VALUES
    ('2023-04-23', 'Consulta médica', 'Saúde', 3),
    ('2023-04-22', 'Visita à família', 'Pessoal', 3),
    ('2023-04-21', 'Estudo para concurso', 'Estudos', 3);

INSERT INTO t_pn_registro (data, descricao, titulo, usuario_id) VALUES
    ('2023-04-23', 'Reunião com equipe', 'Trabalho', 4),
    ('2023-04-22', 'Compras de materiais', 'Suprimentos', 4),
    ('2023-04-21', 'Elaboração de projeto', 'Trabalho', 4);

INSERT INTO t_pn_registro (data, descricao, titulo, usuario_id) VALUES
    ('2023-04-23', 'Consulta ao dentista', 'Saúde', 5),
    ('2023-04-22', 'Visita ao banco', 'Finanças', 5),
    ('2023-04-21', 'Compra de presentes', 'Pessoal', 5);

