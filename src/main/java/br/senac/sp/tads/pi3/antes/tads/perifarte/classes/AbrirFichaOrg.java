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

        String id = request.getParameter("id");
        OrganizacaoDao orgDao = new OrganizacaoDao();
        Organizacao org = null;
        try {
            org = orgDao.findById(id);
        } catch (SQLException ex) {
            Logger.getLogger(AbrirFichaOrg.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sessao.setAttribute("org", org);
        
        // envia para a tela de continuação de solicitação de cadastro 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ficha-org.jsp");
        dispatcher.forward(request, response);
    }

}
