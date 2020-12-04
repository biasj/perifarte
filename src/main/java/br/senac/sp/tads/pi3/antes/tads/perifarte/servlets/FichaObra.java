/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Organizacao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Obra;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Artista;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.ArtistaDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.ObraDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.OrganizacaoDao;
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

/**
 *
 * @author beatrizsato
 */
@WebServlet(name = "FichaObra", urlPatterns = {"/editar/obra"})
public class FichaObra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Artista artista = (Artista) sessao.getAttribute("artista");

        String id = request.getParameter("id");
        
        ObraDao obraDao = new ObraDao();
        OrganizacaoDao orgDao = new OrganizacaoDao();
        Obra obra = null;
        
        try {
            // carrega organizações que podem receber dinheiro
            List<Organizacao> orgs = orgDao.findOrganizacoesAprovadas();
            request.setAttribute("organizacoes", orgs);
            
            // caso esteja acessando pelo painel de administrador
            if(id != null && obra == null) {
                obra = obraDao.findById(id);
                
            } else {
                // caso tenha acessado o get pelo post (organizacao atualizada)
                obra = (Obra) sessao.getAttribute("obra");
                request.setAttribute("atualizacaoSucesso", "Obra atualizada com sucesso");

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FichaOrg.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sessao.setAttribute("obra", obra);
        sessao.setAttribute("artista", artista);
  
        // envia para a tela de ficha de específica de organização
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ficha-obra.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession sessao = request.getSession();
        
        // recupera dados enviados no form
        Obra obra = (Obra) sessao.getAttribute("obra");
        Artista artista = (Artista) sessao.getAttribute("usuario");
        
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String precoStr = request.getParameter("preco");
        String ongEscolhida = request.getParameter("ongEscolhida");
        BigDecimal preco = new BigDecimal(precoStr);
        
        obra.setTitulo(titulo);
        obra.setDescricao(descricao);
        obra.setPreco(preco);
        
        // pega os valores para saber qual botão foi clicado
        String botaoAtualizar = request.getParameter("botaoAtualizar");
        String botaoExcluir = request.getParameter("botaoExcluir");
        
        ObraDao obraDao = new ObraDao();
                
        try {
            // verifica qual dos botões foram clicados
            if(botaoAtualizar != null) {
                obraDao.atualizarObra(obra, ongEscolhida);
                // atualiza a obra a partir do banco de dados
                obra = obraDao.findById(String.valueOf(obra.getId()));                
                
            } else if(botaoExcluir != null) {
                obraDao.excluirObra(obra.getId());
                // envia para a tela de painel
                artista.setObras(obraDao.findObraByArtista(artista.getId()));
                // manda mensagem de sucesso
                request.setAttribute("exclusaoSucesso", "Obra excluída com sucesso");
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-artista.jsp");
                dispatcher.forward(request, response);
            }

            artista.setObras(obraDao.findObraByArtista(artista.getId()));
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FichaOrg.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sessao.setAttribute("obra", obra);
        sessao.setAttribute("usuario", artista);
        
        response.sendRedirect("obra");
    }


}
