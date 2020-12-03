/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.modelos;

import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Artista extends Usuario{
    private int id;
    private String portifolio;
    private List<Obra> obras;
 
    public Artista(String nome, String email, String senha, String portifolio) {
        super(nome, email, senha);
        this.portifolio = portifolio;
        this.obras = null;
    }
    
    public Artista(String email, String hashSenha) {
        super(email, hashSenha);
    }
    
    public void setInfo(String nome, int id, String portfolio) {
        this.nome = nome;
        this.id = id;
        this.portifolio = portfolio;
        this.obras = null;
    }
        
    public void cadastrarObra(Obra obra){
        obras.add(obra);
    }

    public String getPortifolio() {
        return portifolio;
    }

    public List<Obra> getObras() {
        return obras;
    }
    
    public int contaObras() {
        return obras.size();
    }

    public void excluirObra(Obra obra) {
        obras.remove(obra);
    }

    public void setPortifolio(String portifolio) {
        this.portifolio = portifolio;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
