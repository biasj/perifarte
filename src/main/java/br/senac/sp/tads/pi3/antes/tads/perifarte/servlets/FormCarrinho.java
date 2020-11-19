package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import java.io.IOException;
import java.math.BigDecimal;
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

import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.DoacoesDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.ObraDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.OrganizacaoDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Artista;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doacoes;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Doador;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Obra;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Organizacao;

@WebServlet(name = "FormCarrinho", urlPatterns = {"/carrinho"})
public class FormCarrinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Doador doador = (Doador) sessao.getAttribute("usuario");

        String id = request.getParameter("id");
        
        DoacoesDao doacaoDao = new DoacoesDao();
        Doacoes doacao = null;
        
        // envia para a tela de ficha de específica de carrinho
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/carrinho.jsp");
        dispatcher.forward(request, response);
    }

    
    //aqui precisa passar os dados que serão importantes para quando for passar para a janela de compras
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession sessao = request.getSession();
        
        // recupera dados enviados no form
        Obra obra = (Obra) sessao.getAttribute("obra");
   //     Artista artista = (Artista) sessao.getAttribute("usuario");
        Doador doador = (Doador) sessao.getAttribute("usuario");
        Doacoes valor = (Doacoes) sessao.getAttribute("valor");
   //     Double valor = new Double(valor);
        
        // pega os valores para saber qual botão foi clicado
        String botaoPagamento = request.getParameter("botaoPagamento");
        
        DoacoesDao doacaoDao = new DoacoesDao();
                
        sessao.setAttribute("obra", obra);
        sessao.setAttribute("usuario", doador);
        sessao.setAttribute("valor", valor);
        
        response.sendRedirect("valor");
    }
}
