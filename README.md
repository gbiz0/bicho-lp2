# Jogo do Bicho - Linguagem de Programação II

## Descrição

O trabalho consiste em criar uma aplicação desktop usando a linguagem Java. A aplicação deve conter os seguintes requisitos:

Layout gráfico (Permitido o uso de Window Builder no Eclipse) - 2,0 pontos
- Netbeans - Java Swing

Acesso a um banco de dados (preferência PostgreSQL) - 2,0 pontos
      ```sql
      
        CREATE TABLE bicho (
            idBicho serial,
            nome text,
            CONSTRAINT pk_bicho PRIMARY KEY (idBicho)
        );

        CREATE TABLE contraventor (
            idContraventor serial,
            idBancaEmpregadora integer,
            cpfContraventor text,
            login text,
            senha text,
            nome text,
            tipo text,
            CONSTRAINT pk_contraventor PRIMARY KEY (idContraventor)
        );

        CREATE TABLE banca (
            idBanca serial,
            idContraventorGerente integer,
            cnpjFachada text,
            nomeFantasia text,
            cidade text,
            bairro text,
            logradouro text,
            cep text,
            estado VARCHAR(2),
            CONSTRAINT pk_banca PRIMARY KEY (idBanca),
            CONSTRAINT fk_banca_contraventor
            FOREIGN KEY (idContraventorGerente)
            REFERENCES contraventor
            ON UPDATE CASCADE
            ON DELETE CASCADE
        );

        ALTER TABLE contraventor
        ADD
        CONSTRAINT fk_contraventor_banca
        FOREIGN KEY (idBancaEmpregadora)
        REFERENCES banca
        ON UPDATE CASCADE
        ON DELETE CASCADE;

        CREATE TABLE cliente (
            idCliente serial,
            cpf text,
            rg text,
            bairro text,
            cep text,
            cidade text,
            estado text,
            logradouro text,
            CONSTRAINT pk_cliente PRIMARY KEY (idCliente)
        );

        CREATE table telefone_cliente (
            telefone TEXT,
            idCliente INTEGER,
            CONSTRAINT pk_telefone_cliente PRIMARY KEY (telefone, idCliente),
            CONSTRAINT fk_telefone_cliente FOREIGN KEY (idCliente)
            REFERENCES cliente
        );

        CREATE table aposta (
            idBicho INTEGER,
            idCliente INTEGER,
            idContraventor INTEGER,
            timestampAposta TIMESTAMP DEFAULT NOW(),
            valorInicial NUMERIC(10,2),
            retornoEsperado NUMERIC(10,2),
            retornoConfirmado BOOLEAN DEFAULT FALSE,
            CONSTRAINT pk_aposta PRIMARY KEY (idBicho, idCliente, idContraventor, timestampAposta),
            CONSTRAINT fk_aposta_bicho FOREIGN KEY (idBicho)
            REFERENCES bicho (idBicho)
            ON UPDATE CASCADE,
            CONSTRAINT fk_aposta_cliente FOREIGN KEY (idCliente)
            REFERENCES cliente (idCliente)
            ON UPDATE CASCADE,
            CONSTRAINT fk_aposta_contraventor FOREIGN KEY (idContraventor)
            REFERENCES contraventor (idContraventor)
            ON UPDATE CASCADE
        );

      ```
    Uso de alguma estrutura da Framework Collections (Ex: ArrayList) - 3,0 pontos
        - List/ArrayList
    
    Pelo menos dois padrões de projetos - 3,0 pontos

    O trabalho pode ser feito em grupos de no máximo três pessoas.

    O trabalho deve ser defendido pelos grupos no dia 10/12 ou no dia 17/12.
