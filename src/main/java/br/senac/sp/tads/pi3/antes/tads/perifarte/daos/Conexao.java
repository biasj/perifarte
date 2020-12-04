/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beatrizsato
 */

// ALTERAR ESSA CLASSE PARA AJUSTAR A CONEXÃO PARA O BANCO DE DADOS
public class Conexao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://perifarte.mysql.database.azure.com:3306/perifarte";
    private static final String USER = "antes_tads@perifarte";
    private static final String PASS = "BiaMaGa1";

    public static Connection obterConexao() throws SQLException {
        // 1) Declarar o driver JDBC de acordo com o Banco de dados usado
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        
        // 2) Abrir a conexão
        Connection conn = DriverManager.getConnection(
                URL + "?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC",
                USER, // Usuário de conexão no BD
                PASS); // Senha
        return conn ;
    }
}
