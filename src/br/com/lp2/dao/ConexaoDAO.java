package br.com.lp2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConexaoDAO {
    private static ConexaoDAO instance;
    private Connection connection;
    private final List<String> connectionLogs;

    private ConexaoDAO() {
        this.connectionLogs = new ArrayList<>();
        try {
            String dsn = "bichoDB";
            String user = "postgres";
            String senha = "postgres";
            String url = "jdbc:postgresql://localhost:5433/" + dsn;

            DriverManager.registerDriver(new org.postgresql.Driver());
            this.connection = DriverManager.getConnection(url, user, senha);
            this.connection.setAutoCommit(false);

            logConnection("Conexão criada com sucesso.");
        } catch (Exception e) {
            logConnection("Erro ao criar conexão: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static ConexaoDAO getInstance() {
        if (instance == null) {
            synchronized (ConexaoDAO.class) {
                if (instance == null) {
                    instance = new ConexaoDAO();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        logConnection("Conexão requisitada.");
        return this.connection;
    }

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                logConnection("Conexão fechada.");
            }
        } catch (Exception e) {
            logConnection("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    private void logConnection(String message) {
        this.connectionLogs.add(message);
        System.out.println(message); // Exibir no console também
    }

    public List<String> getConnectionLogs() {
        return new ArrayList<>(this.connectionLogs); // Retorna uma cópia para proteger a lista original
    }
}
