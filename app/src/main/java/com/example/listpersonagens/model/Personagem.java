package com.example.listpersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable { //faz importação das dependências da classe

    /*Traz a variável de fora*/
    private String nome;
    private String altura;
    private String nascimento;
    private String telefone;
    private String endereco;
    private String cep;
    private String rg;
    private String genero;
    private int id = 0;

    //determina as variáveis que receberá as informações de fora
    public Personagem(String nome, String altura, String nascimento, String telefone, String endereco, String cep, String rg, String genero) {

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cep = cep;
        this.rg = rg;
        this.genero = genero;
    }

    public Personagem(){
        //possibilita enviar formulário vazio
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }
    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone(){return  telefone;}
    public void setTelefone(String telefone){this.telefone = telefone;}

    public String getEndereco(){return endereco;}
    public void setEndereco(String endereco){this.endereco = endereco;}

    public String getCep(){return cep;}
    public void setCep(String cep){this.cep = cep;}

    public String getRg(){return rg;}
    public void setRg(String rg){this.rg = rg;}

    public String getGenero(){return genero;}
    public void setGenero(String genero){this.genero = genero;}

    //transaciona as informações
    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public boolean IdValido(){        return id > 0 ;    }
}
