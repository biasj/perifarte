/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

/**
 *
 * @author beatrizsato
 */
public class Usuario {
    private static int contas = 10000;
    protected int numeroConta;
    protected String nome;
    protected String email;
    protected String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        
        numeroConta = contas++;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    
    // redefine a senha
    public void redefinirSenha(String novaSenha) {
        senha = novaSenha;
    }
}
