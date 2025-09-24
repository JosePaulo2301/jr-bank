package app.dao;
import app.model.Cliente;
import app.model.Conta;
import app.model.ContaCorrente;
import app.model.ContaPoupanca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

 private final String url = "jdbc:postgresql://localhost:5432/banco";
    private final String user = "postgres";
    private final String password = "postgres"; // ajuste para sua senha

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void salvar(Conta conta) {
        String sqlCliente = "INSERT INTO cliente (nome, cpf) VALUES (?, ?) RETURNING id";
        String sqlConta = "INSERT INTO conta (numero, saldo, tipo, cliente_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar()) {
            conn.setAutoCommit(false);

            int clienteId;
            try (PreparedStatement ps = conn.prepareStatement(sqlCliente)) {
                ps.setString(1, conta.getTitular().getNome());
                ps.setString(2, conta.getTitular().getCpf());
                ResultSet rs = ps.executeQuery();
                rs.next();
                clienteId = rs.getInt("id");
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