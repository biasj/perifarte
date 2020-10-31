/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Artista extends Usuario{

    private int id;
    private String portifolio;
    private ArrayList<Obra> obras;
 
    public Artista(String nome, String email, String senha, String portifolio) {
        super(nome, email, senha);
        this.portifolio = portifolio;
        this.obras = new ArrayList<>();
    }
    
    public void cadastrarObra(Obra obra){
        obras.add(obra);
    }

    public String getPortifolio() {
        return portifolio;
    }

    public ArrayList<Obra> getObras() {
        return obras;
    }
    
    public int contaObras() {
        return obras.size();
    }

    @Override
    public int getNumeroConta() {
        return numeroConta;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getSenha() {
        return senha;
    }
    
    public void excluirObra(Obra obra) {
        obras.remove(obra);
    }

    public void setPortifolio(String portifolio) {
        this.portifolio = portifolio;
    }

    public void setObras(ArrayList<Obra> obras) {
        this.obras = obras;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public void AtualizarObra(Obra obra){
        //tarefa referente ao marco 3 
    }
}
