/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaobd;

import br.senac.sp.tads.pi3.antes.tads.perifarte.classes.Administrador;
import br.senac.sp.tads.pi3.antes.tads.perifarte.classes.Artista;
import br.senac.sp.tads.pi3.antes.tads.perifarte.classes.Doador;
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
        ArtistaDao artDao = new ArtistaDao();
//        Artista art = new Artista("Marcelo", "marcelo@gmail.com", "marcelo123", "insta");
//        artDao.addArtista(art);

//        System.out.println(dao.findById("1"));
//        printInfo(dao.findById("1"));
//        printInfoArt(artDao.findAccount("marcelo@gmail.com", "marcelo123"));
        
//        dao.excluirSolicitacao("1");
//        dao.aprovarOrganizacao("1");
//        dao.suspenderCadastro("1");
//        List<Organizacao> resultados = dao.findAll();
//        System.out.println("VALORES NO BANCO DE DADOS");
//        for (Organizacao org : resultados) {
//            printInfo(org);
//        }

        DoadorDao doaDao = new DoadorDao();
        Doador doador = new Doador("Juan", "juan@gmail.com", "juan123");
        doaDao.addDoador(doador);
        
        
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
    
    private static void printInfoArt(Artista art) {
        System.out.println("Nome: " + art.getNome());
        System.out.println("E-mail: " + art.getEmail());
    }
}
