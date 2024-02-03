package com.trafalino.poppy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wholesalers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wholesaler {
    @Id
    private ObjectId id;
    private String nome;
    private double scontistica;

    public double getScontistica() {
        return scontistica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String newName) {
        nome = newName;
    }
}
