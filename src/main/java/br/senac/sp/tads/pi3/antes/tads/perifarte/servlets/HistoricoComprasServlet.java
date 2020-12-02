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

import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.DoacoesDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.ObraDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doacoes;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doador;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Obra;

/**
 * Servlet implementation class HistoricoComprasServlet
 */

@WebServlet(name = "HistoricoComprasServlet", urlPatterns = {"/historico-compras"})
public class HistoricoComprasServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sessao = request.getSession();
		Doador doador = (Doador) sessao.getAttribute("usuario");

		String id = request.getParameter("id");

		ObraDao obraDao = new ObraDao();
		DoacoesDao doacaoDao = new DoacoesDao();
		Doacoes doacao = null;
		
		//precisa alterar o metodo DoacaoDao para puxar corretamente as doacoes
//		try {
//			doacao = doacaoDao.findByDonatorId(id);
//			obra = obraDao.findById(doacao.)
//			response.setContentType("image/jpg");
//			byte[] imagemObra = obra.getImageBytes();
//			response.getOutputStream().write(imagemObra);

//		} catch (SQLException ex) {
//			Logger.getLogger(ImagemObra.class.getName()).log(Level.SEVERE, null, ex);
//		}

	}

}
