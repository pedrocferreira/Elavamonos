package com.example.pedri.elavamonos;

public class Receita {
    public Integer id;
    public String nome;
    public String tempo; // tempo de preapro
    public String rendimento; // rendimento por pessoa
    public String ingredientes; //ingredientes com medida
    public String preparo; // Como é feito
    public byte[] foto;


    public Receita(int id, String nome, String tempo,String rendimento, String ingredientes,String preparo, byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.tempo = tempo;
        this.rendimento = rendimento;
        this.ingredientes = ingredientes;
        this.preparo = preparo;
        this.foto = foto;

    }




    public Integer getId() { return id;}

    public void setId(Integer id) {    this.id = id;   }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getRendimento() {
        return rendimento;
    }

    public void setRendimento(String rendimento) {
        this.rendimento = rendimento;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparo() {
        return preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }

    public byte[] getFoto(){return foto;}

    public void setFoto(byte[] foto) {this.foto = foto;}


    @Override
    public String toString(){
        return nome ;

    }




}

