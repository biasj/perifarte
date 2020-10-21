/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

import java.io.IOException;
import java.io.PrintWriter;
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
        
        String descricao = request.getParameter("descricao");
        String justificativa = request.getParameter("justificativa");
        
        // validação dos campos
        if(descricao.equals("") || justificativa.equals("")) {
            if(descricao.equals("")) {
                request.setAttribute("descricaoErro", "A descricao deve ser preenchida");
            }
            if(justificativa.equals("")) {
                request.setAttribute("justificativaErro", "O campo deve ser preenchido");
            }
            
            // coloca os atributos
            request.setAttribute("descricao", descricao);
            request.setAttribute("justificativa", justificativa);
            
            // volta para o formulário com os campos preenchidos
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/formulario-cadastro-org-2.jsp");
            dispatcher.forward(request, response);
            return;

        }
        
        // caso esteja tudo preenchido
        // cria uma sessao e pega os dados da sessao
        HttpSession sessao = request.getSession();
        // atribui ao objeto organizacao para "setar" os atributos descricao e justificativa
        Organizacao novaOrganizacao = (Organizacao) sessao.getAttribute("organizacao");
        novaOrganizacao.setDescricao(descricao);
        novaOrganizacao.setJustificativa(justificativa);
        
        // atualiza a sessao para mandar para a próxima página
        sessao.setAttribute("organizacao", novaOrganizacao);
        response.sendRedirect("solicitacao-cadastro-org-2");
    }

}
