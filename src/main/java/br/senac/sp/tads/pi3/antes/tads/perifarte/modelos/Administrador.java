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
    private final String credencial;
    private List<Organizacao> organizacoes;
    
    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
        organizacoes = null;
        credencial = gerarCredencial();
    }

    public String getCredencial() {
        return credencial;
    }

    public int getId() {
        return id;
    }

    public List<Organizacao> getOrganizacoes() {
        return organizacoes;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setOrganizacoes(List<Organizacao> organizacoes) {
        this.organizacoes = organizacoes;
    }
    
    // credencial é composta por 3 números e 1 letra, usada para conferir que usuário é administrador
    private String gerarCredencial() {
        StringBuilder credencialGerada = new StringBuilder();
        
        // valueOf() -> pega 3 números aleatórios de 0 a 9 e coloca na credencial
        for(int i=0;i<3;i++) {
            credencialGerada.append(randomDigit());
        }
        
        // gera 1 letra aleatorias e coloca na senha
        credencialGerada.append(randomLetter());
        
        // StringBuilder -> junta tudo e converte pra String
        return credencialGerada.toString();
    }
    
    private static String randomLetter() {
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        
        int posicaoAleatoriaAlfabeto = (int)(Math.random()*alfabeto.length());
        
        return String.valueOf(alfabeto.charAt(posicaoAleatoriaAlfabeto));
        
    }
    
    private static String randomDigit() {
        int num = (int)(Math.random()*10);
        
        return String.valueOf(num);
    }
    
}