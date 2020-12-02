package br.senac.sp.tads.pi3.antes.tads.perifarte.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doacoes;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doador;

public class DoacoesDao {
	  // insere dados da doacao no banco de dados
    public void addDoacao(Doacoes doacao) throws SQLException {
        String sql = "INSERT INTO doacao (doacao_data, doacao_valor, doacao_status) VALUES (?,?,?)";

        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
            	
            	stmt.setDate(1, (Date) doacao.getData_compra());
                stmt.setDouble(2, doacao.getValor());
                stmt.setString(3, doacao.getStatus(sql));

                int resultados = stmt.executeUpdate();
                
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    while(rs.next()) {
                        // pega o id gerado pelo banco e atribui ao objeto passado
                        Integer idGerado = rs.getInt(1);
                        doacao.setIdCompra(idGerado);
                    }
                }
                
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // procura a doacao a partir do ID do Doador	
    public Doacoes findByDonatorId(String id) throws SQLException {
        String sql = "SELECT * FROM doacao WHERE doacao_doador_id=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String doador = rs.getString("doador");
                	String nome = rs.getString("nome");
                	String organizacao = rs.getString("organizacao");
                	Date data = rs.getDate("doacao_data");
                    Double valor = rs.getDouble("doacao_valor");
                    String status = rs.getString("doacao_status");
                  
                    Doacoes doacao = new Doacoes (doador, nome, organizacao, valor);
                    //devolve o id da compra existente no banco de dados
                    doacao.setIdCompra(Integer.parseInt(id));
                    return doacao;
                }
            }
        }
        return null;
    }

   
    // procura o valor total de todas as doações feitas	
    public Double totalDonation(String id) throws SQLException {
        String sql = "SELECT SUM(doacao_valor) FROM doacao";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    
                    Double valor = rs.getDouble("doacao_valor");
                   
                    return valor;
                }
            }
        }
        return null;
    }
    
    
   
    // procura o valor total doações por Id do Doador	
    public Double findDonationbyDonatorId(String id) throws SQLException {
        String sql = "SELECT SUM(doacao_valor) AS total_doado FROM doacao\n"
        		+ "	WHERE doacao_valor IS NOT NULL   \n"
        		+ "	AND doacao_valor != 0.00\n"
        		+ "    AND doacao.doacao_doador_id=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
//                    String doador = rs.getString("doador");
//                	String nome = rs.getString("nome");
//                	String organizacao = rs.getString("organizacao");
//                	Date data = rs.getDate("doacao_data");
//                    String status = rs.getString("doacao_status");
                    Double valor = rs.getDouble("doacao_valor");

//                    Doacoes doacao = new Doacoes (doador, nome, organizacao, valor);
//                    //devolve o id da compra existente no banco de dados
//                    doacao.setIdCompra(Integer.parseInt(id));
//                    return doacao;
                    return valor;
                }
            }
        }
        return null;
    }
    
    
    // procura o valor total doações por Id da Obra	
    public Double findDonationbyArtId(String id) throws SQLException {
        String sql = "SELECT SUM(doacao_valor) AS total_obtido_obra FROM doacao\n"
        		+ "	WHERE doacao_valor IS NOT NULL   \n"
        		+ "	AND doacao_valor != 0.00\n"
        		+ "    AND doacao.doacao_obra_id=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
//                    String doador = rs.getString("doador");
//                	String nome = rs.getString("nome");
//                	String organizacao = rs.getString("organizacao");
//                	Date data = rs.getDate("doacao_data");
//                    String status = rs.getString("doacao_status");
                    Double valor = rs.getDouble("doacao_valor");

//                    Doacoes doacao = new Doacoes (doador, nome, organizacao, valor);
//                    //devolve o id da compra existente no banco de dados
//                    doacao.setIdCompra(Integer.parseInt(id));
//                    return doacao;
                    return valor;
                }
            }
        }
        return null;
    }
    
    
    // procura o valor total doações por Id do Artista	
    public Double findDonationbyArtistId(String id) throws SQLException {
        String sql = "Select Sum(doacao.doacao_valor) as total_obtido\n"
        		+ "from artista\n"
        		+ "inner join obra on obra.obra_artista_id = artista.artista_id\n"
        		+ "inner join doacao on obra.obra_id = doacao.doacao_obra_id\n"
        		+ "where artista.artista_id=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
//                    String doador = rs.getString("doador");
//                	String nome = rs.getString("nome");
//                	String organizacao = rs.getString("organizacao");
//                	Date data = rs.getDate("doacao_data");
//                    String status = rs.getString("doacao_status");
                    Double valor = rs.getDouble("doacao_valor");

//                    Doacoes doacao = new Doacoes (doador, nome, organizacao, valor);
//                    //devolve o id da compra existente no banco de dados
//                    doacao.setIdCompra(Integer.parseInt(id));
                    return valor;
                }
            }
        }
        return null;
    }
    
    
    // procura o valor total doações por Id do Organização	
    public Double findDonationbyOrgId(String id) throws SQLException {
        String sql = "Select Sum(doacao.doacao_valor) as total_recebido\n"
        		+ "from organizacao\n"
        		+ "inner join obra on obra.obra_organizacao_id = organizacao.organizacao_id\n"
        		+ "inner join doacao on obra.obra_id = doacao.doacao_obra_id\n"
        		+ "where organizacao.organizacao_id = ?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
//                    String doador = rs.getString("doador");
//                	String nome = rs.getString("nome");
//                	String organizacao = rs.getString("organizacao");
//                	Date data = rs.getDate("doacao_data");
                    Double valor = rs.getDouble("total_recebido");
//                    String status = rs.getString("doacao_status");
                  
                    
                    return valor;
                }
            }
        }
        return null;
    }
 

}


