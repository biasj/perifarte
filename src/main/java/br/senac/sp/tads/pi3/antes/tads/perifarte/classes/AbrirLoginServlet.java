/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

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
@WebServlet(name = "AbrirLoginServlet", urlPatterns = {"/login"})
public class AbrirLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        Organizacao org = (Organizacao) sessao.getAttribute("organizacao");
        Administrador adm = (Administrador) sessao.getAttribute("administrador");
        
        if(org!=null) {
            sessao.removeAttribute("organizacao");
        } else if(adm!=null) {
            sessao.removeAttribute("administrador");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-login.jsp");
        dispatcher.forward(request, response);
    }
    
}
