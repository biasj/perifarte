/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.daos;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doador;
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
public class DoadorDao {
	
	boolean checkdoador = false;
	
    // insere doador no banco de dados
    public void addDoador(Doador doador) throws SQLException {
        String sql = "INSERT INTO doador (doador_nome, doador_email, doador_senha) VALUES (?,?,?)";

        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, doador.getNome());
                stmt.setString(2, doador.getEmail());
                stmt.setString(3, doador.getSenha());

                int resultados = stmt.executeUpdate();
                
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    while(rs.next()) {
                        // pega o id gerado pelo banco e atribui ao objeto passado
                        Integer idGerado = rs.getInt(1);
                        doador.setId(idGerado);
                    }
                }
                
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // procura conta de doador no banco (login)
    public Doador findAccount(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM doador WHERE doador_email=? and doador_senha=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String nome = rs.getString("doador_nome");
                    String id = rs.getString("doador_id");
                    
                    Doador doador = new Doador(nome, email, senha);
                    // atualiza o id que não é inicializado na construção
                    doador.setId(Integer.parseInt(id));
                    checkdoador = true;
                    return doador;
                }
            }
        }
        return null;
    }
    
    public void atualizaDoador(Doador doador) throws SQLException {
        String sql = "update doador set doador_nome=?, doador_email=?, doador_senha=? where doador_id=?";
        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, doador.getNome());
                stmt.setString(2, doador.getEmail());
                stmt.setString(3, doador.getSenha());
                stmt.setInt(4, doador.getId());
                
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // deleta o doador
    public void deletaDoador(String id) throws SQLException {
        String sql = "delete from doador where doador_id = ?";
        
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
    
    // retorna todos os doadores (para mostrar no painel de administrador)
    public List<Doador> findAll() throws SQLException {
        String sql = "select * from doador";
        List<Doador> resultados = new ArrayList<>();

        // try-with-resources (após Java 7 ou superior)
        // conn/stmt/rs são auto-closeable -> São fechados automaticament ao final do bloco try
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // pega os dados das colunas da tabela do bd
                String nome = rs.getString("doador_nome");
                int id = rs.getInt("doador_id");
                String email = rs.getString("doador_email");
                String senha = rs.getString("doador_senha");
  
                // Construtor: String nome, String email, String senha, String portfolio
                Doador doador = new Doador(nome, email, senha);
                // inicializa id pelo id do banco
                doador.setId(id);
                
                resultados.add(doador);
            }
        }
        return resultados;
    }
    
    public Doador findById(String id) throws SQLException {
        String sql = "SELECT * FROM doador WHERE doador_id=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String nome = rs.getString("doador_nome");
                    String email = rs.getString("doador_email");
                    String senha = rs.getString("doador_senha");
                  
                    Doador doador = new Doador(nome, email, senha);
                    
                    return doador;
                }
            }
        }
        return null;
    }
}
