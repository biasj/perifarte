/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

import conexaobd.OrganizacaoDao;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author beatrizsato
 */

@WebServlet(name = "SolicitacaoCadastroOrg2", urlPatterns = {"/solicitacao-cadastro-org-2"})
public class SolicitacaoCadastroOrg2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        // recupera os dados do post guardados pela sessão
        Organizacao novaOrganizacao = (Organizacao) sessao.getAttribute("organizacao");
        request.setAttribute("organizacao", novaOrganizacao);
        // remove os atributos da sessao agora que a solicitação de cadastro está finalizada
        sessao.removeAttribute("organizacao");
        
        // envia para a tela de confirmação de solicitação -> deveria ser fazer link com paypal
        // TODO: TELA PARA LINKAR COM PAYPAL
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/formulario-cadastro-org-finalizacao.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // pega valor do formulário
        String descricao = request.getParameter("descricao");
        String justificativa = request.getParameter("justificativa");
        
        // tudo já vai estar preenchido por conta do required no form
        
        // cria uma sessao
        HttpSession sessao = request.getSession();
        // pega os dados da sessao sobre a organizacao e atribui os dados do formulario
        Organizacao novaOrganizacao = (Organizacao) sessao.getAttribute("organizacao");
        novaOrganizacao.setDescricao(descricao);
        novaOrganizacao.setJustificativa(justificativa);
        
        // insere no banco de dados
        OrganizacaoDao orgDao = new OrganizacaoDao();
        try {
            orgDao.addOrganizacao(novaOrganizacao);
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoCadastroOrg2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // atualiza a sessao para mandar para a próxima página
        sessao.setAttribute("organizacao", novaOrganizacao);
        response.sendRedirect("solicitacao-cadastro-org-2");
    }

}
