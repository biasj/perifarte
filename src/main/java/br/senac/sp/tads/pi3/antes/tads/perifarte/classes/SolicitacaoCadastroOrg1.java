/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

@WebServlet(name = "SolicitacaoCadastroOrg1", urlPatterns = {"/solicitacao-cadastro-org-1"})
public class SolicitacaoCadastroOrg1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
            HttpSession sessao = request.getSession();
            // recupera os dados do post guardados pela sessão
            Organizacao novaOrganizacao = (Organizacao) sessao.getAttribute("organizacao");
            request.setAttribute("organizacao", novaOrganizacao);
            
            // envia para a tela de continuação de solicitação de cadastro 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/formulario-cadastro-org-2.jsp");
            dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // pega valores do formulario
        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        // Validação do nome
        boolean nomeValido = (nome != null && nome.trim().length() > 0);
        
        boolean cnpjValido = (cnpj.matches("[0-9]+"));
        
        // Validacao do e-mail
        boolean emailValido = (email != null && email.trim().length() > 0);
        if (emailValido) {
            Pattern emailPattern = Pattern.compile("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");
            Matcher emailMatcher = emailPattern.matcher(email);
            emailValido = emailValido && emailMatcher.matches();
        }
        
        boolean camposValidosGlobal = nomeValido && emailValido && cnpjValido;
        
        // caso todos os campos não estejam ok
        if(!camposValidosGlobal) {
            // Reapresentar form com mensagens de erro pelo span
            if (!nomeValido) {
                request.setAttribute("nomeErro", "Nome deve ser preenchido");
            }
            if (!emailValido) {
                request.setAttribute("emailErro", "E-mail deve ser preenchido no formato abc@def.ghi");
            }
            if(!cnpjValido) {
                request.setAttribute("cnpjErro", "CNPJ deve conter apenas numeros");
            }
            
           // preenche o formulario de novo para usuário não ter que digitar tudo de novo
            request.setAttribute("nome", nome);
            request.setAttribute("email", email);
            request.setAttribute("cnpj", cnpj);
            
            // volta para o formulário com os campos preenchidos
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/formulario-cadastro-org-1.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // caso esteja tudo certo
        // cria novo objeto Organizacao e atribui os valores do formulario aos atributos
        // id, nome, email, senha, cnpj, telefone
        Organizacao novaOrganizacao = new Organizacao(nome, email, senha, cnpj, telefone);
        
        // cria sessão para levar dados do usuário para a próxima página 
        HttpSession sessao = request.getSession();
        sessao.setAttribute("organizacao", novaOrganizacao);
        response.sendRedirect("solicitacao-cadastro-org-1");
    }
    

}
