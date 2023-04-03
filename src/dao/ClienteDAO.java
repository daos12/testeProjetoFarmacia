package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conexao;

    // Construtor da classe ClienteDAO
    public ClienteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Insere um novo cliente no banco de dados
    public void inserirCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpfCnpj, endereco, telefone) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpfCnpj());
        stmt.setString(3, cliente.getEndereco());
        stmt.setString(4, cliente.getTelefone());
        stmt.execute();
        stmt.close();
    }

    // Atualiza um cliente existente no banco de dados
    public void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome=?, cpfCnpj=?, endereco=?, telefone=? WHERE id_cliente=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpfCnpj());
        stmt.setString(3, cliente.getEndereco());
        stmt.setString(4, cliente.getTelefone());
        stmt.execute();
        stmt.close();
    }

    // Retorna um cliente a partir do seu ID
    public Cliente buscarClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id_cliente=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Cliente cliente = new Cliente(
                                rs.getInt("id_cliente"),
                                rs.getString("nome"),
                                rs.getString("cpfCnpj"),
                                rs.getString("endereco"),
                                rs.getString("telefone")
            );
            return cliente;
        }
        rs.close();
        stmt.close();
        return null;
    }

// Retorna uma lista com todos os clientes cadastrados no banco de dados
    public List<Cliente> buscarTodosClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Cliente cliente = new Cliente(
                                rs.getInt("id_cliente"),
                                rs.getString("nome"),
                                rs.getString("cpfCnpj"),
                                rs.getString("endereco"),
                                rs.getString("telefone")
            );
            clientes.add(cliente);
        }
        rs.close();
        stmt.close();
        return clientes;
    }

}
