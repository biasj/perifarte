/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaobd;

import br.senac.sp.tads.pi3.antes.tads.perifarte.classes.Organizacao;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author beatrizsato
 */
public class testeBD {
    public static void main(String[] args) throws SQLException {
        OrganizacaoDao dao = new OrganizacaoDao();

        List<Organizacao> resultados = dao.findAll();
        System.out.println("VALORES NO BANCO DE DADOS");
        for (Organizacao org : resultados) {
            printInfo(org);
        }
        
        // Construtor: String nome, String email, String senha, String cnpj, String telefone
        Organizacao org1 = new Organizacao("Criança Esperança", "crianca@gmail.com", "esperanca", "111222333112233", "11999999999");
        
//        dao.addOrganizacao(org1);
        
        resultados = dao.findAll();
        
        System.out.println("VALORES NO BANCO DE DADOS");
        for (Organizacao org : resultados) {
            printInfo(org);
        }
        
        System.out.println("");
        System.out.println("LOGIN");
        printInfo(dao.findAccount("crianca@gmail", "esperanca"));
        
        Organizacao org2 = dao.findAccount("crianca@gmail.com", "esperanca");
        
    }
    
    private static void printInfo(Organizacao org) {
        System.out.println("ID: " + org.getNumeroConta());
        System.out.println("Nome: " + org.getNome());
        System.out.println("CNPJ: " + org.getCnpj());
        System.out.println("E-mail: " + org.getEmail());
    }
}
