/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaobd;


import br.senac.sp.tads.pi3.antes.tads.perifarte.classes.Artista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ArtistaDao {
    public void addArtista(Artista art) throws SQLException {
        String sql = "INSERT INTO artista (artista_nome, artista_email, artista_senha, artista_portifolio) VALUES (?,?,?,?)";

        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, art.getNome());
                stmt.setString(2, art.getEmail());
                stmt.setString(3, art.getSenha());
                stmt.setString(4, art.getPortifolio());

                int resultados = stmt.executeUpdate();
                
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    while(rs.next()) {
                        Integer idGerado = rs.getInt(1);
                        art.setId(idGerado);
                    }
                }
                
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    public Artista findAccount(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM artista WHERE artista_email=? and artista_senha=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    int id = rs.getInt("artista_id");
                    String nome = rs.getString("artista_nome");
                    String portifolio = rs.getString("artista_portifolio");
                    
                    Artista art = new Artista(nome, email, senha, portifolio);
                    
                    art.setId(id);
                    
                    return art;
                }
            }
        }
        return null;
    }
    
    // métodos de leitura
    public List<Artista> findAll() throws SQLException {
        String sql = "select * from artista";
        List<Artista> resultados = new ArrayList<>();

        // try-with-resources (após Java 7 ou superior)
        // conn/stmt/rs são auto-closeable -> São fechados automaticament ao final do bloco try
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // pega os dados das colunas da tabela do bd
                String nome = rs.getString("artista_nome");
                int id = rs.getInt("artista_id");
                String email = rs.getString("artista_email");
                String senha = rs.getString("artista_senha");
                String portfolio = rs.getString("artista_portifolio");
  
                // Construtor: String nome, String email, String senha, String portfolio
                Artista artista = new Artista(nome, email, senha, portfolio);
                // inicializa id pelo id do banco
                artista.setId(id);
                
                resultados.add(artista);
            }
        }
        return resultados;
    }
    
    //metodo para consulta de doadores pelo nome para verificar se já existe no banco de dados.
    public Boolean checkIfNameExist(String nome) throws SQLException {
        String sql = "SELECT doador_id FROM doador WHERE doador_nome=?";
        int id =0;
        
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    id = rs.getInt("organizacao_id");
                    
                    }
            }
        }
        if (id != 0 ) {
        	return false;
        }
        return true;
    }

    
}
