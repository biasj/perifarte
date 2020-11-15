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

/**
 *
 * @author beatrizsato
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObraDao obraDao = new ObraDao();
        
        List<MiniaturaObra> miniaturas = null;
        List<DetalheObra> obras = null;
        
        try {
            // carrega todas as obras do banco de dados
            miniaturas = obraDao.findAllMiniaturas();
            obras = this.getObras(miniaturas);
            
        } catch (SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("obras", obras);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }
    
    public List<DetalheObra> getObras(List<MiniaturaObra> miniaturas) throws SQLException {
        List<DetalheObra> obras = new ArrayList<>();
        ObraDao obraDao = new ObraDao();
        ArtistaDao artDao = new ArtistaDao();
        
        // para cada miniatura, pegar a obra e o artsita pelo id
        for(MiniaturaObra miniatura: miniaturas) {
            Obra obra = obraDao.findById(String.valueOf(miniatura.getIdObra()));
            Artista artista = artDao.findById(miniatura.getIdArtista());
            
            DetalheObra obraDetalhada = new DetalheObra(obra, artista);
            
            obras.add(obraDetalhada);
        }
        
        return obras;
    } 
}
