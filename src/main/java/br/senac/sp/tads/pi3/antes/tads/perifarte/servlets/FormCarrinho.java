package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.*;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "FormCarrinho", urlPatterns = {"/carrinho"})
public class FormCarrinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        // PEGA INFO DA OBRA ESCOLHIDA
        List<DetalheObra> obras = (List<DetalheObra>) sessao.getAttribute("obrasCarrinho");
        if(obras != null) {
            int quantidadeObra = obras.size();
            request.setAttribute("numObra", quantidadeObra);
            
            double total = 0.0;
            
            for(DetalheObra detalhe : obras) {
                total += detalhe.getObra().getPreco().doubleValue();
            }
            
            request.setAttribute("total", total);
        }
        
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
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        DoacoesDao doacaoDao = new DoacoesDao();
        
        // se for um doador
        if (usuario instanceof Doador) {
            for (DetalheObra detalhe: obras) {
                    Doacao doacao = new Doacao(((Doador)usuario).getId(), detalhe.getObra().getId(), detalhe.getObra().getOrganizacao().getId(), detalhe.getObra().getPreco());
                    try {
                        doacaoDao.addDoacao(doacao);
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
            }
            
            // retira as obras do carrinho
            sessao.removeAttribute("obrasCarrinho");
            response.sendRedirect("painel/doador");
        } else if(usuario != null){
            // se não estiver logado ou não for um doador
            request.setAttribute("erro", "Só é possível comprar com perfil de Doador");
            sessao.removeAttribute("obrasCarrinho");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/carrinho.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login");
        }

    }
}
