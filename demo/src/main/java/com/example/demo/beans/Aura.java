package com.example.demo.beans;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class Aura {
    @Id
    private String name;
    private String description;
    private String tech;
}
