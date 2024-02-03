package com.trafalino.poppy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    private ObjectId id;
    private String nome;
    private String codice;
    private Object zona;
    private Region regione;
    private Province provincia;
    private String sigla;
    private String codiceCatastale;
    private String[] cap;
    private Integer popolazione;

    public String getNome() {
        return nome;
    }

    public Region getRegione() {
        return this.regione;
    }

    public Province getProvincia() {
        return this.provincia;
    }

    public String[] getCap() {
        return cap;
    }

    public void setNome(String newName) {
        nome = newName;
    }

    public void setRegione(Region newRegion) {
        this.regione = newRegion;
    }

    public void setProvincia(Province newProvince) {
        this.provincia = newProvince;
    }

    public void setCap(String[] newCap) {
        this.cap = newCap;
    }
}
