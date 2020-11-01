package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Organizacao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Usuario;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Artista;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Obra;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FormListaDoacoes
 */

// NÃO ESTÁ SENDO USADO (relacionado à ficha-org)
@WebServlet(name = "/FormListaDoacoes", urlPatterns = {"/painel-org-servlet"})
public class FormListaDoacoesServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession sessao = request.getSession();
        
        Usuario organizacao = (Organizacao) sessao.getAttribute("organizacao");
        Obra obra = (Obra) sessao.getAttribute("titulo");
        Obra preco = (Obra) sessao.getAttribute("preco");
        Usuario artista = (Artista) sessao.getAttribute("artista");
        
        request.setAttribute("organizacao", organizacao);
        request.setAttribute("obra", obra);
        request.setAttribute("artistaNome", artista);
        request.setAttribute("preco", preco);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-login.jsp");
        dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
