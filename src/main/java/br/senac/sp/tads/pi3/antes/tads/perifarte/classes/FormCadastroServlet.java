/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "FormCadastroServlet", urlPatterns = {"/processar-cadastro"})
public class FormCadastroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        // recupera os dados do post guardados pela sessão
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        request.setAttribute("usuario", usuario);
        sessao.removeAttribute("usuario");

        // envia para a tela de login
        // TODO: APARECER MENSAGEM DE CADASTRO COM SUCESSO
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-login.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // pega os dados do formulario de login
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String artista = request.getParameter("artista");
        String portfolio = request.getParameter("portfolio");
        
        // Validação do nome
        boolean nomeValido = (nome != null && nome.trim().length() > 0);

        // Validacao do e-mail
        boolean emailValido = (email != null && email.trim().length() > 0);
        if (emailValido) {
            Pattern emailPattern = Pattern.compile("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");
            Matcher emailMatcher = emailPattern.matcher(email);
            emailValido = emailValido && emailMatcher.matches();
        }
        
        
        boolean camposValidosGlobal = nomeValido && emailValido;
        if(!camposValidosGlobal) {
            if(!nomeValido) {
                request.setAttribute("nomeErro", "Nome deve ser preenchido");
            }
            if(!emailValido) {
                request.setAttribute("emailErro", "Nome deve ser preenchido");
            }
            
            request.setAttribute("nome", nome);
            request.setAttribute("email", email);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/form-cadastro.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // se for artista
        if(artista.equals("checked")) {
            // cria o artista e bota no bd
            Usuario usuario = new Usuario(nome, email, senha);
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuario", usuario);
        
        // se for doador
        } else {
            Usuario usuario = new Usuario(nome, email, senha);
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuario", usuario);
        }
        
        // manda para a área do usuário ou carrinho (pra onde tava antes?)
        response.sendRedirect("processar-cadastro");
    }

}
