/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaobd;

import br.senac.sp.tads.pi3.antes.tads.perifarte.classes.Organizacao;
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
                String email = rs.getString("organizacao_email");
                String senha = rs.getString("organizacao_senha");
                String cnpj = rs.getString("organizacao_cnpj");
                String telefone = rs.getString("organizacao_telefone");
                
                // Construtor: String nome, String email, String senha, String cnpj, String telefone
                Organizacao org = new Organizacao(nome, email, senha, cnpj, telefone);
                resultados.add(org);
            }
        }
        return resultados;
    }
    
    public void addOrganizacao(Organizacao org) throws SQLException {
        String sql = "INSERT INTO organizacao (organizacao_cnpj, organizacao_nome, organizacao_email, "
                + "organizacao_senha, organizacao_telefone, organizacao_status) VALUES (?,?,?,?,?,?)";

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
                    
                    Organizacao org = new Organizacao(nome, email, senha, cnpj, telefone);
                    org.setStatus(rs.getString("organizacao_status"));
                    
                    return org;
                }
            }
        }
        return null;
    }
}
