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
import java.util.Collections;
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

/**
 *
 * @author beatrizsato
 */
@WebServlet(name = "DoadorServlet", urlPatterns = {"/painel/doador"})
public class DoadorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        Doador doador = (Doador) sessao.getAttribute("usuario");
        // limpa as obras do doador para não ter obra duplicada ao atualizar a página
        doador.getObras().clear();
        
        DoacoesDao doacaoDao = new DoacoesDao();
        ObraDao obraDao = new ObraDao();
        
        try {
            // pega o id da obra e o status de todas as doações feitas por esse usuário
            List<Doacao> obrasCompradas = doacaoDao.findAllDonationsByDonor(doador.getId());
           
            // para cada id de obra, acha a obra inteira no banco de dados 
            for(Doacao doacao : obrasCompradas) {
                Obra obra = obraDao.findById(String.valueOf(doacao.getIdObra()));
                doador.comprarObra(obra);
            }
            
            // mostra primeiro as ultimas obras compradas
            Collections.reverse(doador.getObras());
            request.setAttribute("doacoes", obrasCompradas);

            
        } catch (SQLException ex) {
            Logger.getLogger(DoadorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        request.setAttribute("doador", doador);
        
        // envia para a tela de continuação de solicitação de cadastro 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-usuario.jsp");
        dispatcher.forward(request, response);
    }

}
