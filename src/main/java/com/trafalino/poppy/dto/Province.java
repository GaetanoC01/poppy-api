package com.trafalino.poppy.dto;

public class Province {
    String codice;
    String nome;

    public String getNome() {
        return this.nome;
    }
    public void setNome(String newName) {
        this.nome = newName;
    }
    public String getCodice() {
        return this.codice;
    }
    public void setCodice(String newCode) {
        this.codice =  newCode;
    }
}
