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
@WebServlet(name = "PerfilServlet", urlPatterns = {"/perfil"})
public class PerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
        // verifica qual tipo de usuário é para popular jsp com as informações certas
        if(usuario instanceof Artista) {
            request.setAttribute("usuarioArtista", usuario);
        } else if(usuario instanceof Organizacao) {
            request.setAttribute("usuarioOrganizacao", usuario);
        } else if(usuario instanceof Administrador) {
            request.setAttribute("usuarioAdministrador", usuario);
        } else {
            request.setAttribute("usuarioDoador", usuario);
        }
        
        request.setAttribute("usuario", usuario);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/meu-perfil.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession sessao = request.getSession();
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
        // pega elementos dos parametros a serem atualizados
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        
        AdministradorDao admDao = new AdministradorDao();
        ArtistaDao artistaDao = new ArtistaDao();
        DoadorDao doaDao = new DoadorDao();
        OrganizacaoDao orgDao = new OrganizacaoDao();
        
        try {
            if(usuario instanceof Administrador) {
                admDao.atualizaConta((Administrador) usuario);
                request.setAttribute("usuarioAdministrador", usuario);
            } else if (usuario instanceof Doador) {
                doaDao.atualizaDoador((Doador) usuario);
                request.setAttribute("usuarioDoador", usuario);
            } else if(usuario instanceof Artista) {
                String portfolio = request.getParameter("portfolio");
                ((Artista) usuario).setPortifolio(portfolio);
                
                artistaDao.atualizaConta((Artista) usuario);
                request.setAttribute("usuarioArtista", usuario);
            } else {
                String descricao = request.getParameter("descricaoOrganizacao");
                String justificativa = request.getParameter("justificativaOrganizacao");
                ((Organizacao) usuario).setDescricao(descricao);
                ((Organizacao) usuario).setJustificativa(justificativa);                
                
                orgDao.atualizaConta((Organizacao) usuario);
                
                request.setAttribute("usuarioOrganizacao", usuario);
            }
            
            sessao.setAttribute("usuario", usuario);
            request.setAttribute("atualizaPerfil", "Perfil atualizado!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/meu-perfil.jsp");
            dispatcher.forward(request, response);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PerfilServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
