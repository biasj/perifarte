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

    	request.setCharacterEncoding("UTF-8");

    	AdministradorDao admDao = new AdministradorDao();
    	ArtistaDao artDao = new ArtistaDao();
    	DoadorDao doadorDao = new DoadorDao();
    	ObraDao obraDao = new ObraDao();

    	HttpSession sessao = request.getSession();
    	Doador doador = (Doador) sessao.getAttribute("usuario");
    	Obra obra = (Obra) sessao.getAttribute("obra_id");
    	
    	// pega os dados de Id da obra e Id de quem está logado
    	String id = request.getParameter("obra_id");
    	String email = request.getParameter("email");
    	String senha = request.getParameter("senha");

    	// pega os valores para saber qual botão foi clicado
    	String botaoComprar = request.getParameter("botaoComprar");
    	String botaoRetornar = request.getParameter("botaoRetornar");

    	try {
    		// verifica qual dos botões foram clicados
    		if(botaoComprar != null) {
    			//verificar se quem está logado é doador e remete para lista de compras
    			if(doador != null) {

    				obraDao.findById(id);
    				// atualiza a obra a partir do banco de dados
    				obra = obraDao.findById(String.valueOf(obra.getId()));
    				//redireciona para o painel de carrinho
    				sessao.setAttribute("usuario", doador);
    				response.sendRedirect("painel/carrinho");
    				
    			//se não for doador, é remetido para a tela inicial
    			} else {
    				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
    				dispatcher.forward(request, response);
    			}    

    		//se selecionar o botão retornar, é devolvido à tela inicial
    		} else if(botaoRetornar != null) {
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
    			dispatcher.forward(request, response);
    		}

    	} catch (SQLException ex) {

    		Logger.getLogger(FichaOrg.class.getName()).log(Level.SEVERE, null, ex);
    	}

    }

}
