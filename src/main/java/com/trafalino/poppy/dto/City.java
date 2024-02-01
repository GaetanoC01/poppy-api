package com.trafalino.poppy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private List<String> cap;
    private Integer popolazione;

    public String getRegion() {
        return regione.getNome();
    }

    public String getProvince() {
        return provincia.getNome();
    }

    public String getCap() {
        return cap.get(0);
    }
}
