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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author beatrizsato
 */
@WebServlet(name = "AbrirCadastroObra", urlPatterns = {"/cadastroobra"})
public class AbrirCadastroObra extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // mostra todas as organizações que pode ser escolhida no input de seleção
        OrganizacaoDao orgDao = new OrganizacaoDao();
        try {
            List<Organizacao> orgs = orgDao.findOrganizacoesAprovadas();
            request.setAttribute("organizacoes", orgs);
        } catch (SQLException ex) {
            Logger.getLogger(AbrirCadastroObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-cadastro-obra.jsp");
        dispatcher.forward(request, response);
    }

}
