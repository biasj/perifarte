/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doador;
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
@WebServlet(name = "DoadorServlet", urlPatterns = {"/painel/doador"})
public class DoadorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Doador doador = (Doador) sessao.getAttribute("usuario");
        
        request.setAttribute("doador", doador);
        
        // envia para a tela de continuação de solicitação de cadastro 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-usuario.jsp");
        dispatcher.forward(request, response);
    }

}
