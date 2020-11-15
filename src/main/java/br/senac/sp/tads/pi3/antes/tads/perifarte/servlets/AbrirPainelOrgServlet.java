/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Organizacao;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AbrirPainelOrgServlet", urlPatterns = {"/painel/organizacao"})
public class AbrirPainelOrgServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        // recupera os dados do post guardados pela sessão
        Organizacao org = (Organizacao) sessao.getAttribute("usuario");
        request.setAttribute("org", org);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-organizacao.jsp");
        dispatcher.forward(request, response);
    }

}
