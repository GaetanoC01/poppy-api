package com.trafalino.poppy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    private ObjectId id;
    private String mese;
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

    // Setters
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
}
