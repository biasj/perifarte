package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import java.io.IOException;
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

import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.ArtistaDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.DoadorDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.ObraDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Artista;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.DetalheObra;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doador;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.MiniaturaObra;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Obra;

/**
 * Servlet implementation class CarrinhoServlet
 */

@WebServlet(name = "CarrinhoServlet", urlPatterns = {"/carrinho"})
public class CarrinhoServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
	           
	           // pega os dados do formulario de login
	           String email = request.getParameter("email");
	           String senha = request.getParameter("senha");
	           
	           // LOGIN 
	           // busca o e-mail na lista de usuários/bd
//	           OrganizacaoDao orgDao = new OrganizacaoDao();
//	           AdministradorDao admDao = new AdministradorDao();
//	           ArtistaDao artDao = new ArtistaDao();
	           DoadorDao doadorDao = new DoadorDao();
//	           ObraDao obraDao = new ObraDao();
	           
	           HttpSession sessao = request.getSession();
	           
	           try {
	               // procura no banco de dados pelo e-mail e senha
	             
	                Doador doador = doadorDao.findAccount(email, senha);
	                
	               // confere se é ong, adm, doador, ou artista. 
	               
	               // se for doador
	               if(doador != null) {
	            	   
	            	   //redireciona para o painel de carrinho
	                   sessao.setAttribute("usuario", doador);
	                   response.sendRedirect("painel/carrinho");
	               } else { 
	            	   //se não for doador, é redirecionado para a mesma página
	            	   response.sendRedirect("painel/obra");
	               }
	           } catch (SQLException ex) {
	               Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
	           }
	           
	    }

}
