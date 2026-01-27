CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL
) ENGINE=InnoDB;

CREATE TABLE conta (
    numero INT PRIMARY KEY,
    saldo DECIMAL(15,2) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    cliente_id INT NOT NULL,
    CONSTRAINT fk_conta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
) ENGINE=InnoDB;


--drop table banco.cliente