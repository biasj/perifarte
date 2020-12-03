/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.modelos;

import java.util.List;
import java.util.Random;

/**
 *
 * @author beatrizsato
 */
public class Administrador extends Usuario{
    private int id;
    private String status;
    private List<Organizacao> organizacoes;
    
    public Administrador(String nome, String email, String senha, String status) {
        super(nome, email, senha);
        organizacoes = null;
        this.status = status;
    }
    
    public Administrador(String email, String hashSenha) {
        super(email, hashSenha);
    }
    
    public void setInfo(String nome, String status, int id) {
        this.nome = nome;
        this.status = status;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public List<Organizacao> getOrganizacoes() {
        return organizacoes;
    }
    
    public void setId(int id) {
        Administrador.id = id;
    }

    public void setOrganizacoes(List<Organizacao> organizacoes) {
        this.organizacoes = organizacoes;
    }
    
}
