/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

import conexaobd.DoadorDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Doador doador = (Doador) sessao.getAttribute("doador");
        request.setAttribute("doador", doador);
        sessao.removeAttribute("doador");

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
                request.setAttribute("emailErro", "E-mail deve ser preenchido no formato abc@def.ghi");
            }
            
            request.setAttribute("nome", nome);
            request.setAttribute("email", email);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-cadastro.jsp");
            dispatcher.forward(request, response);
            return;
        }
        Doador doador = new Doador(nome, email, senha);
        
        
        //confere se o nome do cadastro já existe no banco de dados.

        if (!this.checkIfNameExist(nome)) {
    		// preciso colocar uma mensagem de erro aqui.

    	} 
    	else {
	    
    		// insere no banco de dados
    		DoadorDao doadorDao = new DoadorDao();
	        try {
	            doadorDao.addDoador(doador);
	        } catch (SQLException ex) {
	            Logger.getLogger(SolicitacaoCadastroOrg2.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        HttpSession sessao = request.getSession();
	        sessao.setAttribute("doador", doador);
	        
	        // manda para a área do usuário ou carrinho (pra onde tava antes?)
	        response.sendRedirect("processar-cadastro");
    	}    
    }

}
