/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Administrador;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Artista;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doador;
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
@WebServlet(name = "AdmServlet", urlPatterns = {"/painel/adm"})
public class AdmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Administrador administrador = (Administrador) sessao.getAttribute("usuario");
        List<Artista> artistas = (List<Artista>) sessao.getAttribute("todosArtistas");
        List<Doador> doadores = (List<Doador>) sessao.getAttribute("todosDoadores");
        List<Administrador> adms = (List<Administrador>) sessao.getAttribute("todosAdms");

        // recupera os dados do post guardados pela sessão
        request.setAttribute("administrador", administrador);
        request.setAttribute("todosDoadores", doadores);
        request.setAttribute("todosArtistas", artistas);
        request.setAttribute("todosAdms", adms);
        
        // envia para a tela de continuação de solicitação de cadastro 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-administrador.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
