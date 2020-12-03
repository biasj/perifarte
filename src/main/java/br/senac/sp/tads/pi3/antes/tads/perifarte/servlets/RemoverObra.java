/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;
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
@WebServlet(name = "RemoverObra", urlPatterns = {"/retira-carrinho"})
public class RemoverObra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        List<DetalheObra> obras = (List<DetalheObra>) sessao.getAttribute("obrasCarrinho");
        
        String id = request.getParameter("id");
        
        int index = this.getIndex(obras, Integer.parseInt(id));
        obras.remove(index);
        
        sessao.setAttribute("obrasCarrinho", obras);
        
        // envia para a tela de ficha de específica de carrinho
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/carrinho.jsp");
        dispatcher.forward(request, response);
        
    }

    public int getIndex(List<DetalheObra> obras, int id) {
        for(int i=0;i<obras.size();i++) {
            if(obras.get(i).getObra().getId() == id)
                return i;
        }
        return -1;
    }
}
