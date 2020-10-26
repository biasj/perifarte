/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaobd;

import br.senac.sp.tads.pi3.antes.tads.perifarte.classes.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author beatrizsato
 */
public class AdministradorDao {
        public void addAdministrador(Administrador adm) throws SQLException {
        String sql = "INSERT INTO administrador (administrador_id, administrador_nome, administrador_email, administrador_senha) VALUES (?,?,?,?)";

        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, String.valueOf(adm.getNumeroConta()));
                stmt.setString(2, adm.getNome());
                stmt.setString(3, adm.getEmail());
                stmt.setString(4, adm.getSenha());

                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
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
                    
                    Administrador adm = new Administrador(nome, email, senha);
                  
                    
                    return adm;
                }
            }
        }
        return null;
    }
    
}
