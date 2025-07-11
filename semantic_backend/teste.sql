-- --------------------------------------------------------
-- Servidor:                     C:\Users\felip\DOUTORADO\Desenvolvimento Web\Trabalho\Desenvolvimento\PMX-Net\semantic_backend\ontop_project\pmxnet.db
-- Versão do servidor:           3.45.3
-- OS do Servidor:               
-- HeidiSQL Versão:              12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para pmxnet
CREATE DATABASE IF NOT EXISTS "pmxnet";
;

-- Copiando estrutura para tabela pmxnet.ferramenta
CREATE TABLE IF NOT EXISTS ferramenta (
        id integer,
        link varchar(255),
        nome varchar(255),
        nome_usuario varchar(255),
        tipo varchar(255),
        token varchar(255),
        projeto_id bigint,
        primary key (id)
    );

-- Copiando dados para a tabela pmxnet.ferramenta: -1 rows
/*!40000 ALTER TABLE "ferramenta" DISABLE KEYS */;
INSERT INTO "ferramenta" ("id", "link", "nome", "nome_usuario", "tipo", "token", "projeto_id") VALUES
	(1, 'asdf', 'asdf', 'asdf', 'GitHub', 'sadf', 1),
	(2, 'link ferramenta felipe', 'ferramenta felipe', 'felipe', 'GitHub', 'token felipe', 2);
/*!40000 ALTER TABLE "ferramenta" ENABLE KEYS */;

-- Copiando estrutura para tabela pmxnet.informacao
CREATE TABLE IF NOT EXISTS informacao (
        id integer,
        conteudo varchar(255),
        data timestamp,
        projeto_id bigint,
        primary key (id)
    );

-- Copiando dados para a tabela pmxnet.informacao: -1 rows
/*!40000 ALTER TABLE "informacao" DISABLE KEYS */;
INSERT INTO "informacao" ("id", "conteudo", "data", "projeto_id") VALUES
	(1, '{"tipo": "github", "pessoas": ["Juliana", "André", "João"], "anotacao": "atualiza documentação", "no": "Nó 1"}', '2024-01-03 00:00:00', 1),
	(2, '{"tipo": "github", "pessoas": ["Mariana", "Lucas", "Carlos", "André"], "anotacao": "atualiza documentação", "no": "Nó 2"}', '2024-01-04 00:00:00', 1),
	(3, '{"tipo": "github", "pessoas": ["Juliana", "Lucas"], "anotacao": "refatora classe Y", "no": "Nó 3"}', '2024-01-05 00:00:00', 1),
	(78, '{"tipo": "github", "pessoas": ["Mariana", "Felipe"], "anotacao": "corrige warnings", "no": "Nó 78"}', '2024-03-20 00:00:00', 1),
	(99, '{"tipo": "github", "pessoas": ["Lucas", "Ana"], "anotacao": "implementa endpoint", "no": "Nó 99"}', '2024-04-10 00:00:00', 1),
	(100, '{"tipo": "github", "pessoas": ["Carlos", "João"], "anotacao": "atualiza documentação", "no": "Nó 100"}', '2024-04-11 00:00:00', 1);
/*!40000 ALTER TABLE "informacao" ENABLE KEYS */;

-- Copiando estrutura para tabela pmxnet.participante
CREATE TABLE IF NOT EXISTS participante (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nome TEXT NOT NULL
);

-- Copiando dados para a tabela pmxnet.participante: 5 rows
/*!40000 ALTER TABLE "participante" DISABLE KEYS */;
INSERT INTO "participante" ("id", "nome") VALUES
	(1, 'Felipe'),
	(2, 'João'),
	(3, 'Andre'),
	(5, 'Alice'),
	(6, 'Bob');
/*!40000 ALTER TABLE "participante" ENABLE KEYS */;

-- Copiando estrutura para tabela pmxnet.pessoa
CREATE TABLE IF NOT EXISTS "pessoa" (
  id INTEGER PRIMARY KEY NOT NULL,
  nome TEXT, 
  email text
);

-- Copiando dados para a tabela pmxnet.pessoa: -1 rows
/*!40000 ALTER TABLE "pessoa" DISABLE KEYS */;
INSERT INTO "pessoa" ("id", "nome", "email") VALUES
	(1, 'felipe', 'felipefo@gmail.com');
/*!40000 ALTER TABLE "pessoa" ENABLE KEYS */;

-- Copiando estrutura para tabela pmxnet.projeto
CREATE TABLE IF NOT EXISTS projeto (
        id integer,
        descricao varchar(255),
        nome varchar(255),
        criador_id bigint,
        primary key (id)
    );

-- Copiando dados para a tabela pmxnet.projeto: -1 rows
/*!40000 ALTER TABLE "projeto" DISABLE KEYS */;
INSERT INTO "projeto" ("id", "descricao", "nome", "criador_id") VALUES
	(1, 'Projeto Teste 1', 'Projeto Teste 3', 1),
	(2, 'Teste1', 'Teste1', 2),
	(3, 'Projeto Teste 2', 'Projeto Teste 2', 1);
/*!40000 ALTER TABLE "projeto" ENABLE KEYS */;

-- Copiando estrutura para tabela pmxnet.projeto_ferramentas
CREATE TABLE IF NOT EXISTS projeto_ferramentas (
        projeto_id bigint not null,
        ferramentas_id bigint not null unique
    );

-- Copiando dados para a tabela pmxnet.projeto_ferramentas: -1 rows
/*!40000 ALTER TABLE "projeto_ferramentas" DISABLE KEYS */;
/*!40000 ALTER TABLE "projeto_ferramentas" ENABLE KEYS */;

-- Copiando estrutura para tabela pmxnet.projeto_informacoes
CREATE TABLE IF NOT EXISTS projeto_informacoes (
        projeto_id bigint not null,
        informacoes_id bigint not null unique
    );

-- Copiando dados para a tabela pmxnet.projeto_informacoes: -1 rows
/*!40000 ALTER TABLE "projeto_informacoes" DISABLE KEYS */;
/*!40000 ALTER TABLE "projeto_informacoes" ENABLE KEYS */;

-- Copiando estrutura para tabela pmxnet.relaciona
CREATE TABLE IF NOT EXISTS "relaciona" (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  "conhece" INTEGER NOT NULL DEFAULT 0, "conhecido" INTEGER NOT NULL DEFAULT 0);

-- Copiando dados para a tabela pmxnet.relaciona: 1 rows
/*!40000 ALTER TABLE "relaciona" DISABLE KEYS */;
INSERT INTO "relaciona" ("id", "conhece", "conhecido") VALUES
	(11, 1, 2);
/*!40000 ALTER TABLE "relaciona" ENABLE KEYS */;

-- Copiando estrutura para tabela pmxnet.tipo
CREATE TABLE IF NOT EXISTS tipo (
        id integer,
        nome varchar(255),
        primary key (id)
    );

-- Copiando dados para a tabela pmxnet.tipo: -1 rows
/*!40000 ALTER TABLE "tipo" DISABLE KEYS */;
/*!40000 ALTER TABLE "tipo" ENABLE KEYS */;

-- Copiando estrutura para tabela pmxnet.usuario
CREATE TABLE IF NOT EXISTS usuario (
        id integer,
        email varchar(255),
        nome varchar(255),
        senha varchar(255),
        primary key (id)
    );

-- Copiando dados para a tabela pmxnet.usuario: -1 rows
/*!40000 ALTER TABLE "usuario" DISABLE KEYS */;
INSERT INTO "usuario" ("id", "email", "nome", "senha") VALUES
	(1, 'string', 'string', 'string'),
	(2, 'felipefo@gmail.com', 'felipe', '$2a$10$I9MrefvsMhw3WcPFIn3C/uNbhZfhs1ELxYT6KIqp1A05bPewQ4Ca.'),
	(3, 'joao@gmail.com', 'joao', '$2a$10$B5qffwVTKWeHjRNJcwgi5eTMJjWEmth5/KPljQi.EjC6G2EmUimeG');
/*!40000 ALTER TABLE "usuario" ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
