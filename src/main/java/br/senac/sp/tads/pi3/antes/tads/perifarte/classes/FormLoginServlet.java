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
@WebServlet(name = "FormLoginServlet", urlPatterns = {"/processamento"})
public class FormLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        // recupera os dados do post guardados pela sessão
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        request.setAttribute("usuario", usuario);

        // envia para a tela de continuação de solicitação de cadastro 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-usuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // pega os dados do formulario de login
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        // valida os dados
        boolean emailValido = (email != null && email.trim().length() > 0);
        if (emailValido) {
            Pattern emailPattern = Pattern.compile("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$");
            Matcher emailMatcher = emailPattern.matcher(email);
            emailValido = emailValido && emailMatcher.matches();
        }
        
        if(!emailValido) {
            request.setAttribute("email", email);
            
            // volta para o formulário com os campos preenchidos
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-login.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // busca o e-mail na lista de usuários/bd?
        // confere se a senha é compatível
        // se for compatível
        
        
        // cria sessão para levar para a próxima página 
        Usuario usuario = new Usuario("Bia", email, senha);
        HttpSession sessao = request.getSession();
        sessao.setAttribute("usuario", usuario);
        // manda para a área do usuário ou carrinho (pra onde tava antes?)
        response.sendRedirect("processamento");
    }

}
