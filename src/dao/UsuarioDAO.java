package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO {

    private Connection conexao;

    // Construtor da classe UsuarioDAO
    public UsuarioDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Retorna uma lista com todos os usuários cadastrados no banco de dados
    public List<Usuario> buscarTodosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("telefone")
            );
            usuarios.add(usuario);
        }
        rs.close();
        stmt.close();
        return usuarios;
    }

// Busca um usuário por email e senha
    public Usuario buscarUsuarioPorEmailSenha(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email=? AND senha=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Usuario usuario = new Usuario(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("telefone")
            );
            return usuario;
        }
        rs.close();
        stmt.close();
        return null;
    }
    

    // Retorna um usuario a partir do seu ID
    public Usuario buscarUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Usuario usuario = new Usuario(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("tipo")
            );
            return usuario;
        }
        rs.close();
        stmt.close();
        return null;
    }
    
    
    
    
    // Insere um novo usuario no banco de dados
    public void inserirUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getTipo());
        stmt.execute();
        stmt.close();
    }
    

}
