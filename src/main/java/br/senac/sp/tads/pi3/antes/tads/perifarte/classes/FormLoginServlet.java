/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

import conexaobd.AdministradorDao;
import conexaobd.OrganizacaoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@WebServlet(name = "FormLoginServlet", urlPatterns = {"/processamento"})
public class FormLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        Organizacao org = (Organizacao) sessao.getAttribute("organizacao");
        Administrador adm = (Administrador) sessao.getAttribute("administrador");
        // TODO: doador
        // TODO: artista
        // TODO: adm
        
        // switch case?
        // direciona para a página certa
        if(org != null) {
            redirecionarOrg(request, response, org);
        } else if (adm != null) {
            redirecionarAdm(request, response, adm);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // pega os dados do formulario de login
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        
        // LOGIN ORGANIZACAO
        // busca o e-mail na lista de usuários/bd??
        OrganizacaoDao orgDao = new OrganizacaoDao();
        AdministradorDao admDao = new AdministradorDao();
        HttpSession sessao = request.getSession();
        
        try {
            // procura no banco de dados pelo e-mail e senha
             Organizacao org = orgDao.findAccount(email, senha);
             Administrador adm = admDao.findAccount(email, senha);
            // confere se é ong, adm, doador, ou artista. 
            
            // se for ong
            if(org != null) {
                // coloca o objeto org como atributo sessão para levar dados do usuário para a próxima página 
                sessao.setAttribute("organizacao", org);
            // se for adm
            } else if(adm != null) {
                sessao.setAttribute("administrador", adm);
            }
            
            // TODO: doador
            // TODO: artista
            
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
            Logger.getLogger(FormLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        // manda para a área do usuário ou carrinho (pra onde tava antes?)
        response.sendRedirect("processamento");
        
    }
    
    // chamado no doGet
    private void redirecionarOrg(HttpServletRequest request, HttpServletResponse response, Organizacao org) 
            throws ServletException, IOException {
        // recupera os dados do post guardados pela sessão
        request.setAttribute("organizacao", org);

        // envia para a tela de continuação de solicitação de cadastro 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-organizacao.jsp");
        dispatcher.forward(request, response);
    }
    
    // chamado no doGet
    private void redirecionarAdm(HttpServletRequest request, HttpServletResponse response, Administrador adm) 
            throws ServletException, IOException {
        // recupera os dados do post guardados pela sessão
        request.setAttribute("administrador", adm);

        // envia para a tela de continuação de solicitação de cadastro 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-administrador.jsp");
        dispatcher.forward(request, response);
    }
}
