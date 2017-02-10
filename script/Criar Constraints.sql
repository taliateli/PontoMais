
ALTER TABLE entrada 
ADD CONSTRAINT `fk_colaborador` 
FOREIGN KEY (`colaborador`) 
REFERENCES `colaborador` (`id`);

ALTER TABLE entrada_almoco 
ADD CONSTRAINT `fk_colaborador_ea` 
FOREIGN KEY (`colaborador`) 
REFERENCES `colaborador` (`id`);

ALTER TABLE saida_almoco 
ADD CONSTRAINT `fk_colaborador_sa` 
FOREIGN KEY (`colaborador`) 
REFERENCES `colaborador` (`id`);

ALTER TABLE saida 
ADD CONSTRAINT `fk_colaborador_s` 
FOREIGN KEY (`colaborador`) 
REFERENCES `colaborador` (`id`);



