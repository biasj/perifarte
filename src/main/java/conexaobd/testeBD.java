/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaobd;

import br.senac.sp.tads.pi3.antes.tads.perifarte.classes.Administrador;
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

        System.out.println(dao.findById("1"));
        printInfo(dao.findById("1"));
        
        dao.excluirSolicitacao("1");
        dao.aprovarOrganizacao("1");
        dao.suspenderCadastro("1");
        List<Organizacao> resultados = dao.findAll();
        System.out.println("VALORES NO BANCO DE DADOS");
        for (Organizacao org : resultados) {
            printInfo(org);
        }
        ;
        
    }
    
    private static void printInfo(Organizacao org) {
        System.out.println("ID: " + org.getNumeroConta());
        System.out.println("Nome: " + org.getNome());
        System.out.println("CNPJ: " + org.getCnpj());
        System.out.println("E-mail: " + org.getEmail());
    }
    
    private static void printInfoAdm(Administrador adm) {
        System.out.println("E-mail: " + adm.getEmail());
        System.out.println("Senha: " + adm.getSenha());
    }
}
