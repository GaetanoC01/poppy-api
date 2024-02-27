package com.trafalino.poppy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    private String id;
    private String mese;
    private int meseEncoded;
    private int anno;
    private String grossista;
    private double scontistica;
    private String citta;
    private String cap;
    private String regione;
    private String provincia;
    private String prodotto;
    private double prezzo;
    private int quantita;
    private double totaleVendite; // qty * prezzo

    // Getters
    public String getCitta() {
        return citta;
    }
    public String getGrossista() {
        return grossista;
    }
    public String getProdotto() {
        return prodotto;
    }
    public int getQuantita() {
        return quantita;
    }
    public double getScontistica() {
        return scontistica;
    }
    public double getPrezzo() {
        return prezzo;
    }
    public String getMese() {
        return mese;
    }

    public int getMeseEncoded() {
        return meseEncoded;
    }

    // Setters
    public void setCitta(String newCity) {
        citta = newCity;
    }
    public void setGrossista(String newWholesaler) {
        grossista = newWholesaler;
    }
    public void setProdotto(String newProduct) {
        prodotto = newProduct;
    }
    public void setCap(String newCap) {
        cap = newCap;
    }
    public void setProvincia(String newProvince) {
        provincia = newProvince;
    }
    public void setRegione(String newRegion) {
        regione = newRegion;
    }
    public void setScontistica(double newDiscount) {
        scontistica = newDiscount;
    }
    public void setPrezzo(double newPrice) {
        prezzo = newPrice;
    }
    public void setTotaleVendite(double newTotaleVendite) {
        totaleVendite = newTotaleVendite;
    }
    public void setMese(String newMese){
        mese = newMese;
    }
    public void setMeseEncoded(int newMeseEncoded){
        meseEncoded = newMeseEncoded;
    }
}
