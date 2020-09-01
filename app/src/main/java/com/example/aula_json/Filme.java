package com.example.aula_json;

import java.util.List;

public class Filme {

    private String titulo;
    private String genero;
    private int ano;
    private List<Cast> elenco;

    public Filme(String titulo, String genero, int ano, List<Cast> elenco) {
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
        this.elenco = elenco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<Cast> getElenco() {
        return elenco;
    }

    public void setElenco(List<Cast> elenco) {
        this.elenco = elenco;
    }
}
