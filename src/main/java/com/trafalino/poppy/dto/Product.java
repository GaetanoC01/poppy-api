package com.trafalino.poppy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    private ObjectId id;
    private String nome;
    private double prezzo;

    //Getters
    public double getPrezzo() {
        return prezzo;
    }
    public String getNome() {
        return nome;
    }

    // Setters
    public void setNome(String newName) {
        nome = newName;
    }
}
