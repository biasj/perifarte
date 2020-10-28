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
public class Doador extends Usuario{
    
    private ArrayList<Obra> obras;
    private double totalDoado;
    private int id;

    public Doador(String nome, String email, String senha) {
        super(nome, email, senha);
        this.obras = new ArrayList<>();
        this.totalDoado = 0;
    }
    
    public void comprarObra(Obra obra){
        this.obras.add(obra);
        totalDoado = totalDoado + obra.getPreco();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ArrayList<Obra> getObras() {
        return obras;
    }

    public double getTotalDoado() {
        return totalDoado;
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

    public void setObras(ArrayList<Obra> obras) {
        this.obras = obras;
    }

    public void setTotalDoado(double totalDoado) {
        this.totalDoado = totalDoado;
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
    
    
}
