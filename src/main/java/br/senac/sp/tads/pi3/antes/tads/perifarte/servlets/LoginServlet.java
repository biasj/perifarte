/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.*;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author beatrizsato
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-login.jsp");
        dispatcher.forward(request, response);
    }
    
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // pega os dados do formulario de login
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        // LOGIN 
        // busca o e-mail na lista de usuários/bd
        OrganizacaoDao orgDao = new OrganizacaoDao();
        AdministradorDao admDao = new AdministradorDao();
        ArtistaDao artDao = new ArtistaDao();
        DoadorDao doadorDao = new DoadorDao();
        ObraDao obraDao = new ObraDao();
        
        HttpSession sessao = request.getSession();
        
        try {
            // procura no banco de dados pelo e-mail e senha
             Organizacao org = orgDao.findAccount(email, senha);
             Administrador adm = admDao.findAccount(email, senha);
             Artista art = artDao.findAccount(email, senha);
             Doador doador = doadorDao.findAccount(email, senha);
             
            // confere se é ong, adm, doador, ou artista. 
            
            // se for ong
            if(org != null) {
                // carrega todas as obras que já foram doadas para a organização
                List<Obra> obrasDoadas = obraDao.findObraByOrganizacao(org.getId());
                sessao.setAttribute("obrasDoadas", obrasDoadas);
                
                // coloca o objeto org como atributo sessão para levar dados do usuário para a próxima página 
                sessao.setAttribute("usuario", org);
                
                response.sendRedirect("painel/org");                
            // se for adm
            } else if(adm != null) {
                // pega a lista de doador e artista para apresentar no painel adm
                List<Artista> artistas = artDao.findAll();
                List<Doador> doadores = doadorDao.findAll();
                // carregar todas as organizações
                List organizacoes = orgDao.findAll();
                // carrega todos os adms
                List adms = admDao.findAll();
                
                // setar o atributo organizacao c a lista das organizacoes
                adm.setOrganizacoes(organizacoes);
                sessao.setAttribute("usuario", adm);
                // passa a lista de artistas e doadores para serem apresentadas no painel
                sessao.setAttribute("artistas", artistas);
                sessao.setAttribute("doadores", doadores);
                sessao.setAttribute("adms", adms);
                
                // enviar para servlet de adm
                response.sendRedirect("painel/adm");
                
            //se for art
            } else if(art != null){
                 // pega a lista de obras para apresentar no painel de adm
                List<Obra> obras = obraDao.findObraByArtista(art.getId());
                art.setObras(obras);

                sessao.setAttribute("usuario", art);
                // enviar para servlet de adm
                response.sendRedirect("painel/artista");
                
            } else if(doador != null) {
                sessao.setAttribute("usuario", doador);
                response.sendRedirect("painel/doador");
            }
            
            // se não for nenhum dos 4: email/senha errados
            else {
                request.setAttribute("email", email);
                request.setAttribute("loginErro", "E-mail e/ou senha inválidos");
                // volta para o formulário com os campos preenchidos
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-login.jsp");
                dispatcher.forward(request, response);
                return;
            }


        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
