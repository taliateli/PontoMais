CREATE DATABASE IF NOT EXISTS pontoMais

CREATE TABLE IF NOT EXISTS pontomais.colaborador (
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL UNIQUE,
	funcao VARCHAR(50) NOT NULL, 
	dtInclusao DATETIME,
	dtExclusao DATETIME,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pontomais.entrada (
	colaborador INT NOT NULL,
	dataHora DATETIME 
);

CREATE TABLE IF NOT EXISTS pontomais.entrada_almoco (
	colaborador INT NOT NULL,
	dataHora DATETIME 
);

CREATE TABLE IF NOT EXISTS pontomais.saida_almoco (
	colaborador INT NOT NULL,
	dataHora DATETIME 
);

CREATE TABLE IF NOT EXISTS pontomais.saida (
	colaborador INT NOT NULL,
	dataHora DATETIME 
);

ALTER TABLE `entrada` 
ADD CONSTRAINT `fk_colaborador` 
FOREIGN KEY (`colaborador`) 
REFERENCES `colaborador` (`id`);

ALTER TABLE `entrada_almoco` 
ADD CONSTRAINT `fk_colaborador_ea` 
FOREIGN KEY (`colaborador`) 
REFERENCES `colaborador` (`id`);

ALTER TABLE `saida_almoco` 
ADD CONSTRAINT `fk_colaborador_sa` 
FOREIGN KEY (`colaborador`) 
REFERENCES `colaborador` (`id`);

ALTER TABLE `saida` 
ADD CONSTRAINT `fk_colaborador_s` 
FOREIGN KEY (`colaborador`) 
REFERENCES `colaborador` (`id`);