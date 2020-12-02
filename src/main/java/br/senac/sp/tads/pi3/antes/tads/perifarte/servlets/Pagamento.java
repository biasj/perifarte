/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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


@WebServlet(name = "Pagamento", urlPatterns = {"/pagamento"})
public class Pagamento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Artista artista = (Artista) sessao.getAttribute("usuario");

        String id = request.getParameter("id");
        
        ObraDao obraDao = new ObraDao();
        OrganizacaoDao orgDao = new OrganizacaoDao();
        Obra obra = null;
        
        try {
            List<Organizacao> orgs = orgDao.findOrganizacoesAprovadas();
            request.setAttribute("organizacoes", orgs);
            // caso esteja acessando pelo painel de administrador
            if(id != null && obra == null) {
                obra = obraDao.findById(id);
                
            } else {
                // caso tenha acessado o get pelo post (organizacao atualizada)
                obra = (Obra) sessao.getAttribute("obra");
                request.setAttribute("atualizacaoSucesso", "Obra atualizada com sucesso");

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FichaOrg.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sessao.setAttribute("obra", obra);
        sessao.setAttribute("usuario", artista);
        request.setAttribute("artista", artista);
        
        // envia para a tela de ficha de específica de organização
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
       
    }


}
