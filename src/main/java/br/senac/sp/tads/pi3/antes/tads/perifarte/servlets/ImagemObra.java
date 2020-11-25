/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.ObraDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Obra;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author beatrizsato
 */

// CARREGA AS IMAGENS todas as imagens no src mandam pra esse servlet
@WebServlet(name = "ImagemObra", urlPatterns = {"/imagem-obra"})
public class ImagemObra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");            
        
        ObraDao obraDao = new ObraDao();
        Obra obra = null;
        try {
            obra = obraDao.findById(id);
            response.setContentType("image/jpg");
            byte[] imagemObra = obra.getImageBytes();
            response.getOutputStream().write(imagemObra);
            
        } catch (SQLException ex) {
            Logger.getLogger(ImagemObra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
