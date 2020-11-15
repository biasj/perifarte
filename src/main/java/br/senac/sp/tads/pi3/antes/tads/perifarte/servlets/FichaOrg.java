/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Organizacao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Administrador;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.OrganizacaoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "FichaOrg", urlPatterns = {"/editar/org"})
public class FichaOrg extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Administrador adm = (Administrador) sessao.getAttribute("administrador");
        // pega o parametro passado pela url (ao clicar no item da lista)
        String id = request.getParameter("id");
        
        OrganizacaoDao orgDao = new OrganizacaoDao(); 
        Organizacao org = null;
        
        try {
            // caso esteja acessando pelo painel de administrador
            if(id != null && org == null) {
                org = orgDao.findById(id);
                
            } else {
                // caso tenha acessado o get pelo post (organizacao atualizada)
                org = (Organizacao) sessao.getAttribute("org");
                // mostra mensagem de sucesso
                request.setAttribute("atualizacaoSucesso", "Organização atualizada com sucesso");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FichaOrg.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sessao.setAttribute("org", org);
        sessao.setAttribute("administrador", adm);
  
        // envia para a tela de ficha de específica de organização
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ficha-org.jsp");
        dispatcher.forward(request, response);
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession sessao = request.getSession();
        
        // recupera dados enviados no form
        Organizacao org = (Organizacao) sessao.getAttribute("org");
        Administrador adm = (Administrador) sessao.getAttribute("administrador");
        String id = String.valueOf(org.getId());
        
        // pega os valores para saber qual botão foi clicado
        String botaoAprovar = request.getParameter("aprovar");
        String botaoSuspender = request.getParameter("suspender");
        
        OrganizacaoDao dao = new OrganizacaoDao();
        
        try {
            // verifica qual dos botões foram clicados
            if(botaoAprovar != null) {
                dao.aprovarOrganizacao(id);
            } else if(botaoSuspender != null) {
                dao.suspenderCadastro(id);
            } else {
                dao.excluirSolicitacao(id);
            }
            
            // atualiza a organizacao a partir do banco de dados
            org = dao.findById(id);
            
            // atualiza lista de organizacoes do administrador, com os valores atualizados
            // a partir do banco de dados
            adm.setOrganizacoes(dao.findAll());
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FichaOrg.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sessao.setAttribute("org", org);
        sessao.setAttribute("administrador", adm);
        
        response.sendRedirect("org");
        
    }
}
