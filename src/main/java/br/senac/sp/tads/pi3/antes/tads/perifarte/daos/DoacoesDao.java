package br.senac.sp.tads.pi3.antes.tads.perifarte.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;


public class DoacoesDao {
	  // insere dados da doacao no banco de dados
    public void addDoacao(Doacao doacao) throws SQLException {
        String sql = "INSERT INTO doacao (doacao_doador_id, doacao_obra_id, doacao_data, doacao_valor, doacao_status) VALUES (?,?,?,?,?)";

        try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
            	stmt.setInt(1, doacao.getIdDoador());
                stmt.setInt(2, doacao.getIdObra());
            	stmt.setDate(3, Date.valueOf(doacao.getData_compra()));
                stmt.setBigDecimal(4, doacao.getValor());
                stmt.setString(5, doacao.getStatus());

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
    public Doacao findByDonatorId(String id) throws SQLException {
        String sql = "SELECT * FROM doacao WHERE doacao_doador_id=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    int doador = rs.getInt("doacao_doador_id");
                    int idObra = rs.getInt("doacao_obra_id");
                    Date data = rs.getDate("doacao_data");
                    BigDecimal valor = rs.getBigDecimal("doacao_valor");
                    String status = rs.getString("doacao_status");
                    
                    OrganizacaoDao orgDao = new OrganizacaoDao();
                    ObraDao obraDao = new ObraDao();
                    Organizacao org = orgDao.findById(String.valueOf(obraDao.findById(String.valueOf(idObra)).getId()));
                  
                    Doacao doacao = new Doacao (doador, idObra, org.getId(), valor);
                    doacao.setStatus(status);
                    //devolve o id da compra existente no banco de dados
                    doacao.setIdCompra(Integer.parseInt(id));
                    return doacao;
                }
            }
        }
        return null;
    }

   
    // procura o valor total de todas as doações feitas	
    public Double totalDonation() throws SQLException {
        String sql = "SELECT SUM(doacao_valor) FROM doacao";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    
                    Double valor = rs.getDouble("SUM(doacao_valor)");
                   
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

                    Double valor = rs.getDouble("total_doado");

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

                    Double valor = rs.getDouble("total_obtido_obra");

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
                    Double valor = rs.getDouble("total_obtido");

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
                    Double valor = rs.getDouble("total_recebido");
                    
                    return valor;
                }
            }
        }
        return null;
    }
 
    // pega informações apenas de obra e status para serem exibidas na tela de meus pedidos
    public List<Doacao> findAllDonationsByDonor(int id) throws SQLException {
        String sql = "select * from doacao where doacao_doador_id = ?";
        List<Doacao> resultados = new ArrayList<>();

        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String idObra = rs.getString("doacao_obra_id");
                    String status = rs.getString("doacao_status");
                    Date data = rs.getDate("doacao_data");
                    
                    Doacao doacao = new Doacao(Integer.parseInt(idObra), status, data.toLocalDate());
                    
                    resultados.add(doacao);
                }
        }
                
        }
        
        return resultados;
    }
}


