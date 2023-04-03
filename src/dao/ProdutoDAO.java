package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {
    private Connection conexao;

    // Construtor da classe ProdutoDAO
    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Insere um novo produto no banco de dados
    public void inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, descricao, preco_unitario, quantidade_estoque) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setFloat(3, produto.getPrecoUnitario());
        stmt.setInt(4, produto.getQuantidadeEstoque());
        stmt.execute();
        stmt.close();
    }

    // Atualiza um produto existente no banco de dados
    public void atualizarProduto(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET nome=?, descricao=?, preco_unitario=?, quantidade_estoque=? WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setFloat(3, produto.getPrecoUnitario());
        stmt.setInt(4, produto.getQuantidadeEstoque());
        stmt.setInt(5, produto.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    // Retorna um produto a partir do seu ID
    public Produto buscarProdutoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Produto produto = new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getFloat("preco_unitario"),
                rs.getInt("quantidade_estoque")
            );
            return produto;
        }
        rs.close();
        stmt.close();
        return null;
    }

    // Retorna uma lista com todos os produtos cadastrados no banco de dados
    public List<Produto> buscarTodosProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Produto produto = new Produto(
                rs.getInt("id_produto"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getFloat("preco_unitario"),
                rs.getInt("quantidade_estoque")
            );
            produtos.add(produto);
        }
        rs.close();
        stmt.close();
        return produtos;
    }

    // Atualiza a quantidade em estoque de um produto após uma venda
    public void atualizarQuantidadeEstoque(Produto produto, int quantidadeVendida) throws SQLException {
        String sql = "UPDATE produtos SET quantidade_estoque=? WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, produto.getQuantidadeEstoque() - quantidadeVendida);
        stmt.setInt(2, produto.getId());
        stmt.executeUpdate();
        stmt.close();
    }
}