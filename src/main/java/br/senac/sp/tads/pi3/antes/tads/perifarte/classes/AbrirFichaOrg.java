/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

import conexaobd.OrganizacaoDao;
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
@WebServlet(name = "AbrirFichaOrg", urlPatterns = {"/editar/org"})
public class AbrirFichaOrg extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Administrador adm = (Administrador) sessao.getAttribute("administrador");
        Organizacao org = (Organizacao) sessao.getAttribute("org");
        
        String id = request.getParameter("id");
        
        OrganizacaoDao orgDao = new OrganizacaoDao();        
        
        try {
            // caso esteja acessando pelo painel
            if(id != null && org == null) {
                org = orgDao.findById(id);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AbrirFichaOrg.class.getName()).log(Level.SEVERE, null, ex);
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
        String id = String.valueOf(org.getId());
        
        OrganizacaoDao dao = new OrganizacaoDao();
        
        try {
            if(org.getStatus().equals("pendente") || org.getStatus().equals("suspenso")) {
                dao.aprovarOrganizacao(id);
            } else if(org.getStatus().equals("aprovado")) {
                dao.suspenderCadastro(id);
            }
            
            org = dao.findById(id);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(AbrirFichaOrg.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sessao.setAttribute("org", org);
        
        response.sendRedirect("org");
        
    }
}