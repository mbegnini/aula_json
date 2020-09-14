package com.example.aula_json;

import java.io.Serializable;

public class Cast implements Serializable {
    private String nome;
    private String personagem;

    public Cast(String nome, String personagem) {
        this.nome = nome;
        this.personagem = personagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }
}
