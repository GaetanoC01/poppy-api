package com.trafalino.poppy.dto;

public class Region {
    String codice;
    String nome;

    public String getNome() {
        return this.nome;
    }
    public void setNome(String newName) {
        this.nome = newName;
    }
    public String getCodoce() {
        return this.codice;
    }
    public void setCodice(String newCode) {
        this.codice = newCode;
    }

}
