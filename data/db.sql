CREATE TABLE IF NOT EXISTS usuario (
	login varchar(255) NOT NULL,
	email varchar(255),
	nome varchar(255),
	senha varchar(255),
	pontos integer,
	CONSTRAINT usuario_pkey PRIMARY KEY (login)
);

CREATE TABLE IF NOT EXISTS topico
(
	id_topico SERIAL,
	titulo text,
	conteudo text,
	login text,
	CONSTRAINT topico_pkey PRIMARY KEY (id_topico),
	CONSTRAINT topico_login_fkey FOREIGN KEY (login)
		REFERENCES usuario (login) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS comentario
(
	id_comentario SERIAL,
	comentario text,
	login text,
	id_topico integer,
	CONSTRAINT comentario_pkey PRIMARY KEY (id_comentario),
	CONSTRAINT comentario_id_topico_fkey FOREIGN KEY (id_topico)
		REFERENCES topico (id_topico) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT comentario_login_fkey FOREIGN KEY (login)
		REFERENCES usuario (login) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);