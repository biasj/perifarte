/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Obra;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Organizacao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "OrgServlet", urlPatterns = {"/painel/org"})
public class OrgServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        Organizacao org = (Organizacao) sessao.getAttribute("usuario");
        List<Obra> obrasDoadas = (List<Obra>) sessao.getAttribute("obrasDoadas");
        request.setAttribute("obrasDoadas", obrasDoadas);
        // recupera os dados do post guardados pela sessão
        request.setAttribute("organizacao", org);

        // envia para a tela de continuação de solicitação de cadastro 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-organizacao.jsp");
        dispatcher.forward(request, response);
    }

}
