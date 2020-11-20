/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.daos;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Organizacao;
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
public class OrganizacaoDao {
    // insere
    public void addOrganizacao(Organizacao org) throws SQLException {
        String sql = "INSERT INTO organizacao (organizacao_cnpj, organizacao_nome, organizacao_email, "
                + "organizacao_senha, organizacao_telefone, organizacao_status, organizacao_descricao, organizacao_justificativa) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, org.getCnpj());
                stmt.setString(2, org.getNome());
                stmt.setString(3, org.getEmail());
                stmt.setString(4, org.getSenha());
                stmt.setString(5, org.getTelefone());
                stmt.setString(6, org.getStatus());
                stmt.setString(7, org.getDescricao());
                stmt.setString(8, org.getJustificativa());

                int resultados = stmt.executeUpdate();
                
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    while(rs.next()) {
                        Integer idGerado = rs.getInt(1);
                        org.setId(idGerado);
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // atualiza
    public void aprovarOrganizacao(String id) throws SQLException {
        String sql = "update organizacao set organizacao_status = 'aprovado' where organizacao_id=?";
            try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // atualiza
    public void suspenderCadastro(String id) throws SQLException {
        String sql = "update organizacao set organizacao_status = 'suspenso' where organizacao_id=?";
            try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // atualiza
    public void excluirSolicitacao(String id) throws SQLException {
        String sql = "update organizacao set organizacao_status = 'excluido' where organizacao_id=?";
            try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // exclui (não tá sendo chamado)
    public void excluirOrganizacaoBD(String id) throws SQLException {
        String sql = "delete from organizacao where organizacao_id=?";
            try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // métodos de leitura
    public List<Organizacao> findAll() throws SQLException {
        String sql = "select * from organizacao";
        List<Organizacao> resultados = new ArrayList<>();

        // try-with-resources (após Java 7 ou superior)
        // conn/stmt/rs são auto-closeable -> São fechados automaticament ao final do bloco try
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // pega os dados das colunas da tabela do bd
                String nome = rs.getString("organizacao_nome");
                int id = rs.getInt("organizacao_id");
                String email = rs.getString("organizacao_email");
                String senha = rs.getString("organizacao_senha");
                String cnpj = rs.getString("organizacao_cnpj");
                String telefone = rs.getString("organizacao_telefone");
                String status = rs.getString("organizacao_status");
                String descricao = rs.getString("organizacao_descricao");
                String justificativa = rs.getString("organizacao_justificativa");
                
                // Construtor: String nome, String email, String senha, String cnpj, String telefone
                Organizacao org = new Organizacao(nome, email, senha, cnpj, telefone);
                org.setId(id);
                org.setStatus(status);
                org.setDescricao(descricao);
                org.setJustificativa(justificativa);
                
                resultados.add(org);
            }
        }
        return resultados;
    }

    
    public Organizacao findAccount(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM organizacao WHERE organizacao_email=? and organizacao_senha=?";
        try (Connection conn = Conexao.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String nome = rs.getString("organizacao_nome");
                    String cnpj = rs.getString("organizacao_cnpj");
                    String telefone = rs.getString("organizacao_telefone");
                    String descricao = rs.getString("organizacao_descricao");
                    String justificativa = rs.getString("organizacao_justificativa");
                    
                    Organizacao org = new Organizacao(nome, email, senha, cnpj, telefone);
                    // "seta" os atributos que não são inicializados no construtor
                    org.setStatus(rs.getString("organizacao_status"));
                    org.setId(rs.getInt("organizacao_id"));
                    org.setDescricao(descricao);
                    org.setJustificativa(justificativa);
                            
                    
                    return org;
                }
            }
        }
        return null;
    }
    
    public Organizacao findById(String id) throws SQLException {
        String sql = "SELECT * FROM organizacao WHERE organizacao_id=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String nome = rs.getString("organizacao_nome");
                    String email = rs.getString("organizacao_email");
                    String senha = rs.getString("organizacao_senha");
                    String cnpj = rs.getString("organizacao_cnpj");
                    String telefone = rs.getString("organizacao_telefone");
                    String descricao = rs.getString("organizacao_descricao");
                    String justificativa = rs.getString("organizacao_justificativa");
                  
                    Organizacao org = new Organizacao(nome, email, senha, cnpj, telefone);
                    // seta os atributos que não são inicializados no construtor
                    org.setStatus(rs.getString("organizacao_status"));
                    org.setId(rs.getInt("organizacao_id"));
                    org.setDescricao(descricao);
                    org.setJustificativa(justificativa);
                    
                    return org;
                }
            }
        }
        return null;
    }
    
    public int findIdByName(String nome) throws SQLException {
        String sql = "SELECT organizacao_id FROM organizacao WHERE organizacao_nome=?";
        int id;
        
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    id = rs.getInt("organizacao_id");
                    
                    return id;
                }
            }
        }
        return 0;
    }
    
    // devolve todas organizações cujo status == aprovado
    public List<Organizacao> findOrganizacoesAprovadas() throws SQLException {
        String sql = "select * from organizacao where organizacao_status = 'aprovado'";
        List<Organizacao> resultados = new ArrayList<>();

        // try-with-resources (após Java 7 ou superior)
        // conn/stmt/rs são auto-closeable -> São fechados automaticament ao final do bloco try
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // pega os dados das colunas da tabela do bd
                String nome = rs.getString("organizacao_nome");
                int id = rs.getInt("organizacao_id");
                String email = rs.getString("organizacao_email");
                String senha = rs.getString("organizacao_senha");
                String cnpj = rs.getString("organizacao_cnpj");
                String telefone = rs.getString("organizacao_telefone");
                String status = rs.getString("organizacao_status");
                String descricao = rs.getString("organizacao_descricao");
                String justificativa = rs.getString("organizacao_justificativa");
                
                // Construtor: String nome, String email, String senha, String cnpj, String telefone
                Organizacao org = new Organizacao(nome, email, senha, cnpj, telefone);
                org.setId(id);
                org.setStatus(status);
                org.setDescricao(descricao);
                org.setJustificativa(justificativa);
                
                resultados.add(org);
            }
        }
        return resultados;
    }
    
        // atualiza
    public void atualizaConta(Organizacao org) throws SQLException {
        String sql = "update organizacao set organizacao_nome=? organizacao_email=?, organizacao_senha=?, "
                + "organizacao_telefone=?, organizacao_descricao=?, organizacao_justificativa=? where organizacao_id=?";
        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, org.getNome());
                stmt.setString(2, org.getEmail());
                stmt.setString(3, org.getSenha());
                stmt.setString(4, org.getTelefone());
                stmt.setString(5, org.getDescricao());
                stmt.setString(6, org.getJustificativa());
                stmt.setInt(7, org.getId());
                
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        
    }
    
}
