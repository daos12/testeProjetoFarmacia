package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Venda;
import model.Cliente;
import model.Usuario;

public class VendaDAO {

    private Connection conexao;

    // Construtor da classe VendaDAO
    public VendaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Insere uma nova venda no banco de dados
    public void inserirVenda(Venda venda) throws SQLException {
        String sql = "INSERT INTO vendas (cliente_id, produto_id, usuario_id, data_venda, quantidade, valor_total) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt         (1, venda.getCliente().getId());
        stmt.setInt         (2, venda.getProduto().getId());
        stmt.setInt         (3, venda.getUsuario().getId());
        stmt.setTimestamp   (4, new java.sql.Timestamp(venda.getDataVenda().getTime()));
        stmt.setInt         (5, venda.getQuantidade());
        stmt.setFloat       (6, venda.getValorTotal());
        stmt.execute();
        stmt.close();

        // Atualiza a quantidade em estoque do produto vendido
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        produtoDAO.atualizarQuantidadeEstoque(venda.getProduto(), venda.getQuantidade());
    }

    // Retorna todas as vendas realizadas por um determinado cliente
    public List<Venda> buscarVendasPorCliente(Cliente cliente) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, cliente.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Venda venda = new Venda(
                                rs.getInt("id"),
                                cliente,
                                new ProdutoDAO(conexao).buscarProdutoPorId(rs.getInt("id")),
                                new UsuarioDAO(conexao).buscarUsuarioPorId(rs.getInt("id")),
                                new Date(rs.getTimestamp("data_venda").getTime()),
                                rs.getInt("quantidade"),
                                rs.getFloat("valor_total")
            );
            vendas.add(venda);
        }
        rs.close();
        stmt.close();
        return vendas;
    }

    // Retorna todas as vendas realizadas por um determinado usuário
    public List<Venda> buscarVendasPorUsuario(Usuario usuario) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, usuario.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Venda venda = new Venda(
                                rs.getInt("id"),
                                new ClienteDAO(conexao).buscarClientePorId(rs.getInt("id")),
                                new ProdutoDAO(conexao).buscarProdutoPorId(rs.getInt("id")),
                                usuario,
                                new Date(rs.getTimestamp("data_venda").getTime()),
                                rs.getInt("quantidade"),
                                rs.getFloat("valor_total")
            );
            vendas.add(venda);
        }
        rs.close();
        stmt.close();
        return vendas;
    }
}
