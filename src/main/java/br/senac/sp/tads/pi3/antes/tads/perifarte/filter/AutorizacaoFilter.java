/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.filter;

import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author beatrizsato
 */
@WebFilter(filterName = "AutorizacaoFilter", urlPatterns = {"/painel/*", "/editar/*", "/cadastroobra", "/processar-cadastro-obra"})
public class AutorizacaoFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
               // implementa a lógica aqui
        // CAST para objetos do tipo HttpServlet (aspecto técnico)
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // proposta do fluxograma
        // verficar se usuário está logado (tipo homeServlet)
        HttpSession sessao = httpRequest.getSession();
        if(sessao.getAttribute("usuario") == null) {
            // usuário não logado, redireciona p login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }
        
        // recupera dados da sessao
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
        // verifica se usuário possui papel para acessar funcionalidade
        if(verificarAcesso(usuario, httpRequest)) {
            // usuario pode acessar
            chain.doFilter(request, response);
        } else {
            // mostra tela de erro pra usuário (nao autorizado)
            sessao.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/nao-autorizado.jsp");
            dispatcher.forward(request, response);
        }
    }


    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        

    }
    
    private boolean verificarAcesso(Usuario usuario, HttpServletRequest httpRequest) {
        // para recuperar a página acessada
        String paginaAcessada = httpRequest.getRequestURI();
        
        if(usuario instanceof Administrador) {
            return paginaAcessada.endsWith("/painel/adm") || paginaAcessada.endsWith("/editar/org") || paginaAcessada.endsWith("/editar/adm");
        } else if(usuario instanceof Artista) {
            return paginaAcessada.endsWith("/painel/artista") || paginaAcessada.endsWith("/editar/obra") || paginaAcessada.endsWith("/cadastroobra") || paginaAcessada.endsWith("/processar-cadastro-obra");
        } else if(usuario instanceof Doador) {
            return paginaAcessada.endsWith("/painel/doador");
        } else if(usuario instanceof Organizacao) {
            return paginaAcessada.endsWith("/painel/org");
        }

        return false;
    }

}
