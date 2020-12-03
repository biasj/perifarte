/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.*;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import sun.misc.IOUtils;

/**
 *
 * @author beatrizsato
 */
@WebServlet(name = "ObraServlet", urlPatterns = {"/obra"})
public class ObraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        String id = request.getParameter("id");
        
        ObraDao obraDao = new ObraDao();
        ArtistaDao artistaDao = new ArtistaDao();
        
        try {
            // pega informações sobre determinado artista e sua obra
            MiniaturaObra mini = obraDao.findMiniaturaByObra(Integer.parseInt(id));
            
            // carregar informações da obra, artista e ong
            Obra obra = obraDao.findById(id);
            Artista artista = artistaDao.findById(mini.getIdArtista());
            
            DetalheObra detalheObra = new DetalheObra(obra, artista);
            
            sessao.setAttribute("detalhe", detalheObra);
            request.setAttribute("detalhe", detalheObra);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ObraServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/obra.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    	   request.setCharacterEncoding("UTF-8");
           HttpSession sessao = request.getSession();
           
            DetalheObra obra = (DetalheObra) sessao.getAttribute("detalhe");

             // pega todas as obras detalhadas        
            List<DetalheObra> obrasCarrinho = (List<DetalheObra>) sessao.getAttribute("obrasCarrinho");

             // adiciona na lista de obras do carrinho
             if(obrasCarrinho != null) {
                 obrasCarrinho.add(obra);
             } else {
                 obrasCarrinho = new ArrayList<>();
                 obrasCarrinho.add(obra);
             }

             sessao.setAttribute("obrasCarrinho", obrasCarrinho);

             response.sendRedirect("carrinho");
           
    }

}
