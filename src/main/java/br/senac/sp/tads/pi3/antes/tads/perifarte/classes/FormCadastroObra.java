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
 * @author Gabriel
 */
@WebServlet(name = "FormCadastroObra", urlPatterns = {"/processar-cadastro-obra"})
public class FormCadastroObra extends HttpServlet {

    

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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-cadastro-obra.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // pega os dados do formulario de login
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String ongEscolhida = request.getParameter("ongEscolhida");
        

        // Validacao do titulo
        boolean tituloValido = (titulo != null);
        
        // Validacao do e-mail
        boolean descricaoValido = (descricao != null);
        
        // Validacao do e-mail
        boolean precoValido = (preco > 0 && preco < 50);
        
        boolean ongEscolhidaValido = (ongEscolhida != null);
        
        boolean camposValidosGlobal = tituloValido && descricaoValido && precoValido && ongEscolhidaValido;
        
        
        
        if (!camposValidosGlobal) {
            
            if (!tituloValido) {
                request.setAttribute("tituloErro", "titulo deve ser preenchido");
            }
            
            if (!descricaoValido) {
                request.setAttribute("descricaoErro", "descricao deve ser preenchida");
            }
            
            if (!precoValido) {
                request.setAttribute("precoErro", "preco deve ser preenchido");
            }
            
            if (!ongEscolhidaValido) {
                request.setAttribute("ongEscolhidaErro", "ongEscolhida deve ser preenchida");
            }
            
            
            
            request.setAttribute("titulo", titulo);
            request.setAttribute("descricao", descricao);
            request.setAttribute("preco", preco);
            request.setAttribute("ongEscolhida", ongEscolhida);
            

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/form-cadastro-obra.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // cria o artista e bota no bd
        Obra lObra = new Obra(titulo, descricao, preco);
        HttpSession sessao = request.getSession();
        sessao.setAttribute("obra", lObra);
        // manda para a área do usuário ou carrinho (pra onde tava antes?)
        response.sendRedirect("processar-cadastro-obra");
        
    }
}
