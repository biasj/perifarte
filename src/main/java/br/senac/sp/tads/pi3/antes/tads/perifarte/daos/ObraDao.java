/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.daos;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;
import java.math.BigDecimal;
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
public class ObraDao {
    // adiciona obra ao banco de dados
    public void addObra(Obra obr, int idOrg, int idArt) throws SQLException {

       String sql = "INSERT INTO obra (obra_titulo, obra_descricao, obra_organizacao_id, obra_artista_id, obra_preco) VALUES (?,?,?,?,?)";

       try (Connection conn = Conexao.obterConexao()) {
           // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
           conn.setAutoCommit(false);
           
           // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
           try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
               stmt.setString(1, obr.getTitulo());
               stmt.setString(2, obr.getDescricao());
               stmt.setInt(3, idOrg);
               stmt.setInt(4, idArt);
               stmt.setBigDecimal(5, obr.getPreco());

               int resultados = stmt.executeUpdate();

               try (ResultSet rs = stmt.getGeneratedKeys()) {
                   while(rs.next()) {
                       Integer idGerado = rs.getInt(1);
                       obr.setId(idGerado);
                   }
               }
               conn.commit();
           } catch (SQLException e) {
               conn.rollback();
               throw e;
           }
       }
   }
   
    // devolve todas as obras de determinado artista
    public List<Obra> findObraByArtista(int idArtista) throws SQLException {
        String sql = "select * from obra where obra_artista_id = ?";
        List<Obra> resultados = new ArrayList<>();

        // try-with-resources (após Java 7 ou superior)
        // conn/stmt/rs são auto-closeable -> São fechados automaticament ao final do bloco try
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idArtista);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String titulo = rs.getString("obra_titulo");
                    int id = rs.getInt("obra_id");
                    String descricao = rs.getString("obra_descricao");
                    int idOrganizacao = rs.getInt("obra_organizacao_id");
                    BigDecimal preco = rs.getBigDecimal("obra_preco");

                    // Construtor: String titulo, String descricao, BigDecimal preco
                    Obra obra = new Obra(titulo, descricao, preco);
                    OrganizacaoDao orgDao = new OrganizacaoDao();
                    obra.setId(id);
                    
                    obra.setOrganizacao(orgDao.findById(String.valueOf(idOrganizacao)));

                    resultados.add(obra);
                }
        }
                
        }
        return resultados;
    }
    
    // retorna determinada obra para ser manipulada na ficha-obra
    public Obra findById(String id) throws SQLException {
        String sql = "SELECT * FROM obra WHERE obra_id=?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String titulo = rs.getString("obra_titulo");
                    String descricao = rs.getString("obra_descricao");
                    int idOrganizacao = rs.getInt("obra_organizacao_id");
                    BigDecimal preco = rs.getBigDecimal("obra_preco");
                    int idArtista = rs.getInt("obra_artista_id");
                  
                    // Construtor: String titulo, String descricao, BigDecimal preco
                    Obra obra = new Obra(titulo, descricao, preco);
                    // seta os atributos que não são inicializados no construtor
                    obra.setId(Integer.parseInt(id));
                    OrganizacaoDao orgDao = new OrganizacaoDao();
                    
                    obra.setOrganizacao(orgDao.findById(String.valueOf(idOrganizacao)));
                    
                    return obra;
                }
            }
        }
        return null;
    }
    
    // devolve todas as obras de determinada organizacao (login de organizacao - FormLoginServlet)
    public List<Obra> findObraByOrganizacao(int idOrganizacao) throws SQLException {
        String sql = "select * from obra where obra_organizacao_id = ?";
        List<Obra> resultados = new ArrayList<>();

        // try-with-resources (após Java 7 ou superior)
        // conn/stmt/rs são auto-closeable -> São fechados automaticament ao final do bloco try
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrganizacao);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    String titulo = rs.getString("obra_titulo");
                    int id = rs.getInt("obra_id");
                    String descricao = rs.getString("obra_descricao");
                    int idArtista = rs.getInt("obra_artista_id");
                    BigDecimal preco = rs.getBigDecimal("obra_preco");

                    // Construtor: String titulo, String descricao, BigDecimal preco
                    Obra obra = new Obra(titulo, descricao, preco);
                    OrganizacaoDao orgDao = new OrganizacaoDao();
                    obra.setId(id);
                    
                    obra.setOrganizacao(orgDao.findById(String.valueOf(idOrganizacao)));

                    resultados.add(obra);
                }
            }
                
        }
        return resultados;
    }
    
    public List<Obra> findAll() throws SQLException {
        String sql = "select * from obra";
        List<Obra> resultados = new ArrayList<>();

        // try-with-resources (após Java 7 ou superior)
        // conn/stmt/rs são auto-closeable -> São fechados automaticament ao final do bloco try
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // pega os dados das colunas da tabela do bd
                String titulo = rs.getString("obra_titulo");
                int id = rs.getInt("obra_id");
                String descricao = rs.getString("obra_descricao");
                int idOrganizacao = rs.getInt("obra_organizacao_id");
                int artistaId = rs.getInt("obra_artista_id");
                BigDecimal preco = rs.getBigDecimal("obra_preco");
                
                // Construtor: String nome, String email, String senha, String cnpj, String telefone
                Obra obra = new Obra(titulo, descricao, preco);
                
                obra.setId(id);
                
                resultados.add(obra);
            }
        }
        return resultados;
    }
    
    // atualiza obra através do ficha-obra (login de artista)/ fichaObra (servlet)
    public void atualizarObra(Obra obra, String organizacao) throws SQLException {
        String sql = "update obra set obra_titulo=?, obra_descricao=?, obra_organizacao_id=?, obra_preco=? where obra_id=?";
            try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, obra.getTitulo());
                stmt.setString(2, obra.getDescricao());
                // recupera id da nova organizacao escolhida
                OrganizacaoDao orgDao = new OrganizacaoDao();
                stmt.setInt(3, orgDao.findIdByName(organizacao));
                
                stmt.setBigDecimal(4, obra.getPreco());
                stmt.setInt(5, obra.getId());
                
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    // exclui obra através do ficha obra/fichaObra (servlet)
    public void excluirObra(int id) throws SQLException {
        String sql = "delete from obra where obra_id=?";
            try (Connection conn = Conexao.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int resultados = stmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    
    public int getArtistaId(int idObra) throws SQLException {
        String sql = "select artista.artista_id from obra join artista on obra_artista_id = artista.artista_id where obra_id = ?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idObra);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    int id = rs.getInt("artista_id");
                    
                    
                    return id;
                }
            }
        }
        return -1;
    }

    public int getOrganizacaoId(int idObra) throws SQLException {
        String sql = "select organizacao.organizacao_id from obra join organizacao on obra_organizacao_id = organizacao.organizacao_id where obra_id = ?";
        try (Connection conn = Conexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idObra);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // pega os dados das colunas da tabela do bd
                    int id = rs.getInt("organizacao_id");
                    
                    return id;
                }
            }
        }
        return -1;
    } 

}
