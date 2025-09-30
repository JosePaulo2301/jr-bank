package com.app.jrbaking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.jrbaking.model.Cliente;
import com.app.jrbaking.model.Conta;
import com.app.jrbaking.model.ContaCorrente;
import com.app.jrbaking.model.ContaPoupanca;

public class ContaDAO {

    private final String url = "jdbc:mysql://localhost:3306/banco?useSSL=false&serverTimezone=UTC";
    private final String user = "root";
    private final String password = ""; // ajuste para a senha correta

    private Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL nao encontrado no classpath", e);
        }
        return DriverManager.getConnection(url, user, password);
    }

    public void salvar(Conta conta) {
        String sqlCliente = "INSERT INTO cliente (nome, cpf) VALUES (?, ?)";
        String sqlConta = "INSERT INTO conta (numero, saldo, tipo, cliente_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar()) {
            conn.setAutoCommit(false);

            int clienteId;
            try (PreparedStatement ps = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, conta.getTitular().getNome());
                ps.setString(2, conta.getTitular().getCpf());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        clienteId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        throw new SQLException("Falha ao obter id gerado para cliente");
                    }
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(sqlConta)) {
                ps.setInt(1, conta.getNumero());
                ps.setDouble(2, conta.getSaldo());
                ps.setString(3, conta instanceof ContaCorrente ? "CORRENTE" : "POUPANCA");
                ps.setInt(4, clienteId);
                ps.executeUpdate();
            }

            conn.commit();
            System.out.println("Conta salva no banco: " + conta);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Conta> listar() {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT c.numero, c.saldo, c.tipo, cl.nome, cl.cpf " +
                     "FROM conta c JOIN cliente cl ON c.cliente_id = cl.id";

        try (Connection conn = conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("cpf"));
                Conta conta;
                if (rs.getString("tipo").equals("CORRENTE")) {
                    conta = new ContaCorrente(rs.getInt("numero"), cliente);
                } else {
                    conta = new ContaPoupanca(rs.getInt("numero"), cliente);
                }
                conta.depositar(rs.getDouble("saldo"));
                contas.add(conta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contas;
    }
}
