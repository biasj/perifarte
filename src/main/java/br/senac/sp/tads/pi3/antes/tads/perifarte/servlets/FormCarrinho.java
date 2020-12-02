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
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;
import java.util.ArrayList;

@WebServlet(name = "FormCarrinho", urlPatterns = {"/carrinho"})
public class FormCarrinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        // PEGA INFO DA OBRA ESCOLHIDA
        List<DetalheObra> obras = (List<DetalheObra>) sessao.getAttribute("obrasCarrinho");
                
        request.setAttribute("obras", obras);
        
        
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
        List<DetalheObra> obras = (List<DetalheObra>) sessao.getAttribute("obrasCarrinho");
        Doador doador = (Doador) sessao.getAttribute("usuario");
        DoacoesDao doacaoDao = new DoacoesDao();
        
        // pega os valores para saber qual botão foi clicado
        if (doador != null) {
            for (DetalheObra detalhe: obras) {
                    Doacao doacao = new Doacao(doador.getId(), detalhe.getObra().getId(), detalhe.getObra().getOrganizacao().getId(), detalhe.getObra().getPreco());
                    try {
                        doacaoDao.addDoacao(doacao);
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
            }
            response.sendRedirect("painel/doador");
        }
        else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-login.jsp");
            dispatcher.forward(request, response);
        }
        
        
               
    }
}
