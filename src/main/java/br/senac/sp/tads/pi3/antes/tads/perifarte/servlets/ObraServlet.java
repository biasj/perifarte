/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.*;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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
        String id = request.getParameter("id");
        
        String userid = request.getParameter("id"); //ver se vai funcionar
        
        ObraDao obraDao = new ObraDao();
        ArtistaDao artistaDao = new ArtistaDao();
        
        DoadorDao doadorDao = new DoadorDao(); //ver se vai funcionar
        
        try {
            MiniaturaObra mini = obraDao.findMiniaturaByObra(Integer.parseInt(id));
            
            
            Doador doador = doadorDao.findById(userid); //ver se vai funcionar
            
            
            // carregar informações da obra, artista e ong
            Obra obra = obraDao.findById(id);
            Artista artista = artistaDao.findById(mini.getIdArtista());
            
            DetalheObra detalheObra = new DetalheObra(obra, artista);
            
            request.setAttribute("detalhe", detalheObra);
            
            request.setAttribute("userid", doador);  //ver se vai funcionar
            
        } catch (SQLException ex) {
            Logger.getLogger(ObraServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/obra.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
