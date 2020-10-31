/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

import conexaobd.ObraDao;
import conexaobd.OrganizacaoDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 *
 * @author Gabriel
 */
@WebServlet(name = "FormCadastroObra", urlPatterns = {"/processar-cadastro-obra"})
public class FormCadastroObra extends HttpServlet {
    
    // database connection settings
    private String dbURL = "jdbc:mysql://localhost:3306/AppDB";
    private String dbUser = "root";
    private String dbPass = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession sessao = request.getSession();
        // recupera os dados do post guardados pela sessão
        Obra obra = (Obra) sessao.getAttribute("obra");
        request.setAttribute("obra", obra);
        sessao.removeAttribute("obra");

        // envia para a tela de login
        // TODO: APARECER MENSAGEM DE CADASTRO COM SUCESSO
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/painel-artista.jsp");
        dispatcher.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        // pega os dados do formulario de login
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String precoStr = request.getParameter("preco");
        String ongEscolhida = request.getParameter("ongEscolhida");
        
        BigDecimal preco = null;
        if(precoStr != null && precoStr.trim().length() > 0) {
            preco = new BigDecimal(precoStr);
        }
        
        System.out.println("entrou no post");

        // Validacao do titulo
        boolean tituloValido = (titulo != null);
        
        // Validacao do e-mail
        boolean descricaoValido = (descricao != null);
        
         //Validacao do e-mail
        // BigDecimal compara = new BigDecimal(0);
        //boolean precoValido = (preco.compareTo(compara) >= 0 && preco.compareTo(compara) <= 50);
        
        boolean ongEscolhidaValido = (ongEscolhida != null);
        
        boolean camposValidosGlobal = tituloValido && descricaoValido && /*precoValido &&*/  ongEscolhidaValido;
        
        
        
        if (!camposValidosGlobal) {
            
            if (!tituloValido) {
                request.setAttribute("tituloErro", "titulo deve ser preenchido");
            }
            
            if (!descricaoValido) {
                request.setAttribute("descricaoErro", "descricao deve ser preenchida");
            }
            
            /*if (!precoValido) {
                request.setAttribute("precoErro", "preco deve ser preenchido");
            }*/
            
           if (!ongEscolhidaValido) {
               request.setAttribute("ongEscolhidaErro", "ongEscolhida deve ser preenchida");
           }
            
            
            
            request.setAttribute("titulo", titulo);
            request.setAttribute("descricao", descricao);
            request.setAttribute("preco", preco);
            request.setAttribute("ongEscolhida", ongEscolhida);
            

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/form-cadastro-obra.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        
        /*InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("file");
        
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }*/
        
        HttpSession sessao = request.getSession();
        Artista art = (Artista) sessao.getAttribute("artista");
        
        Obra obra = new Obra(titulo, descricao, preco);
        
        ObraDao obraDao = new ObraDao();
        // cria a obra e bota no bd
        OrganizacaoDao orgDao = new OrganizacaoDao();
        try {
            int idOrg = orgDao.findByName(ongEscolhida);
            obraDao.addObra(obra, idOrg, art.getId());
        } catch (SQLException ex) {
            Logger.getLogger(FormCadastroObra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        sessao.setAttribute("obra", obra);
        // manda para a área do usuário ou carrinho (pra onde tava antes?)
        response.sendRedirect("processar-cadastro-obra");
        
    }
}
