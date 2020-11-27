/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.daos;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author beatrizsato
 */
public class AdministradorDao {
    // insere administrador no banco de dados
    public void addAdministrador(Administrador adm) throws SQLException {
        String sql = "INSERT INTO administrador (administrador_nome, administrador_email, administrador_senha, administrador_status) VALUES (?,?,?,?)";

        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, adm.getNome());
                stmt.setString(2, adm.getEmail());
                stmt.setString(3, adm.getSenha());
                stmt.setString(4, adm.getStatus());

                int resultados = stmt.executeUpdate();
                
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    while(rs.next()) {
                        Integer idGerado = rs.getInt(1);
                        adm.setId(idGerado);
                    }
                }
                
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // procura administrador no banco de dados (login)
    public Administrador findAccount(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM administrador WHERE administrador_email=? and administrador_senha=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String nome = rs.getString("administrador_nome");
                    String status = rs.getString("administrador_status");
                    
                    Administrador adm = new Administrador(nome, email, senha, status);
                    adm.setId(rs.getInt("administrador_id"));
                    
                    return adm;
                }
            }
        }
        return null;
    }
    
    // retorna todos os artistas (para mostrar no painel de administrador)
    public List<Administrador> findAll() throws SQLException {
        String sql = "select * from administrador";
        List<Administrador> resultados = new ArrayList<>();

        // try-with-resources (após Java 7 ou superior)
        // conn/stmt/rs são auto-closeable -> São fechados automaticament ao final do bloco try
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // pega os dados das colunas da tabela do bd
                String nome = rs.getString("administrador_nome");
                int id = rs.getInt("administrador_id");
                String email = rs.getString("administrador_email");
                String senha = rs.getString("administrador_senha");
                String status = rs.getString("administrador_status");
  
                // Construtor: String nome, String email, String senha, String portfolio
                Administrador adm = new Administrador(nome, email, senha, status);
                // inicializa id pelo id do banco
                adm.setId(id);
                
                resultados.add(adm);
            }
        }
        return resultados;
    }
    
    public Administrador findById(String id) throws SQLException {
        String sql = "SELECT * FROM administrador WHERE administrador_id=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String nome = rs.getString("administrador_nome");
                    String email = rs.getString("administrador_email");
                    String senha = rs.getString("administrador_senha");
                    String status = rs.getString("administrador_status");
                  
                    Administrador adm = new Administrador(nome, email, senha, status);
                    adm.setId(Integer.parseInt(id));
                    
                    return adm;
                }
            }
        }
        return null;
    }
    
    // atualiza
    public void atualizaConta(Administrador adm) throws SQLException {
        String sql = "update administrador set administrador_nome=?, administrador_email=?, administrador_senha=? where administrador_id=?";
        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, adm.getNome());
                stmt.setString(2, adm.getEmail());
                stmt.setString(3, adm.getSenha());
                stmt.setInt(4, adm.getId());
                
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        
    }
    
    public void aprovaConta(Administrador adm) throws SQLException {
        String sql = "update administrador set administrador_status=? where administrador_id=?";
        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "aprovado");
                stmt.setInt(2, adm.getId());
                
                adm.setStatus("aprovado");
                
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        
    }
    
    // exclui conta (não está sendo usado)
    public void excluirConta(String id) throws SQLException {
        String sql = "delete from administrador where administrador_id = ?";
        
        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, Integer.parseInt(id));
                
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
