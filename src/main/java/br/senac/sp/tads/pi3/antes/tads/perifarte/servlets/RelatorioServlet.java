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
@WebServlet(name = "RelatorioServlet", urlPatterns = {"/relatorio"})
public class RelatorioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        OrganizacaoDao orgDao = new OrganizacaoDao();   
        DoacoesDao doacoesDao = new DoacoesDao();
        
        List<Organizacao> organizacoes = null;
        
        try {
            organizacoes = orgDao.findAll();
            double totalDoado = doacoesDao.totalDonation();
            request.setAttribute("totalDoado", totalDoado);
            
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(organizacoes != null)
            request.setAttribute("organizacoes", organizacoes);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/relatorio.jsp");
        dispatcher.forward(request, response);
    }

}
