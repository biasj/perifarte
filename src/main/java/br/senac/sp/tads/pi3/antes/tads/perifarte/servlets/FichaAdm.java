/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.servlets;

import br.senac.sp.tads.pi3.antes.tads.perifarte.daos.AdministradorDao;
import br.senac.sp.tads.pi3.antes.tads.perifarte.modelos.Administrador;
import java.io.IOException;
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
@WebServlet(name = "FichaAdm", urlPatterns = {"/editar/adm"})
public class FichaAdm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();

        String id = request.getParameter("id");
        
        AdministradorDao admDao = new AdministradorDao();
        Administrador admEdit = null;
        
        try {
            // caso esteja acessando pelo painel de administrador
            if(id != null && admEdit == null) {
                admEdit = admDao.findById(id);
            } else {
                admEdit = (Administrador) sessao.getAttribute("admEdit");
                request.setAttribute("atualizacaoSucesso", "Administrador aprovado!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FichaOrg.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        sessao.setAttribute("administrador", adm);
        sessao.setAttribute("admEdit", admEdit);
  
        // envia para a tela de ficha de específica de organização
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ficha-adm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession sessao = request.getSession();
        
        Administrador admEdit = (Administrador) sessao.getAttribute("admEdit");
        String botaoExcluir = request.getParameter("botaoExcluir");
        
        AdministradorDao admDao = new AdministradorDao();
        
        try {
            if(botaoExcluir != null) {
                admDao.excluirConta(String.valueOf(admEdit.getId()));

                // atualiza a lista de administradores no painels
                List adms = admDao.findAll();
                sessao.setAttribute("todosAdms", adms);
                
                sessao.setAttribute("exclusaoSucesso", "Administrador excluído com sucesso");
                response.sendRedirect(request.getContextPath() + "/painel/adm");
                
            } else {
                admDao.aprovaConta(admEdit);
                
                // atualiza adms da sessão
                List<Administrador> adms = admDao.findAll();
                sessao.setAttribute("todosAdms", adms);
                sessao.setAttribute("admEdit", admEdit);
                
                response.sendRedirect("adm");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FichaAdm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
