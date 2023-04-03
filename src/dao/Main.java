package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Cliente;
import model.Produto;
import model.Usuario;
import model.Venda;

public class Main {

    public static void main(String[] args) throws SQLException {
        // Conecta ao banco de dados
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancoFarmacia", "root", "root");

        // Cria objetos DAO para cada tabela do banco de dados
        ClienteDAO clienteDAO = new ClienteDAO(conexao);
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        VendaDAO vendaDAO = new VendaDAO(conexao);
//
//        // Insere um novo cliente no banco de dados
//        Cliente cliente1 = new Cliente(0, "João Silva", "12345678901", "Rua A, 123", "(11) 99999-9999");
//        clienteDAO.inserirCliente(cliente1);
//
//        // Insere um novo produto no banco de dados
//        Produto produto1 = new Produto(0, "Caneta", "Caneta esferográfica preta", 2.50f, 100);
//        produtoDAO.inserirProduto(produto1);
//
//        // Insere um novo usuário no banco de dados
//        Usuario usuario1 = new Usuario(0, "Fulano de Tal", "fulano@example.com", "123456","vendedor");
//        usuarioDAO.inserirUsuario(usuario1);

        // Realiza uma nova venda
        Cliente cliente = clienteDAO.buscarClientePorId(1); // recupera o cliente criado anteriormente
        Produto produto = produtoDAO.buscarProdutoPorId(1); // recupera o produto criado anteriormente
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(1); // recupera o usuário criado anteriormente
        Venda venda = new Venda(0, cliente, produto, usuario, new Date(), 10, produto.getPrecoUnitario() * 10);
        vendaDAO.inserirVenda(venda);


        // Busca todas as vendas realizadas pelo cliente
        List<Venda> vendasDoCliente = vendaDAO.buscarVendasPorCliente(cliente);
        for (Venda v : vendasDoCliente) {
            System.out.println(v);
        }

        // Busca todas as vendas realizadas pelo usuário
        List<Venda> vendasDoUsuario = vendaDAO.buscarVendasPorUsuario(usuario);
        for (Venda v : vendasDoUsuario) {
            System.out.println(v);
        }

        // Fecha a conexão com o banco de dados
        conexao.close();
    }
}

