package com.example.demo.beans;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Getter
@Document
public class Pals {

    @Id
    private int id;
    private String key;
    private String image;
    private String name;
    private String wiki;
    private List<String> types;
    private String imageWiki;
    private List<Suitability> suitabilities;
    private List<String> drops;
    private Aura auras;
    private String description;
    private List<Skill> skills;
    private Stats stats;
    private String asset;
    private String genus;
    private int rarity;
    private int price;
    private String size;
    private Map<String, String> maps;

    public Pals(int id, String key, String name, String wiki, List<String> types, String imageWiki, List<Suitability> suitabilities, List<String> drops, Aura auras, String description, List<Skill> skills, Stats stats, String asset, String genus, int rarity, int price, String size, Map<String,String> maps) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.wiki = wiki;
        this.types = types;
        this.imageWiki = imageWiki;
        this.suitabilities = suitabilities;
        this.drops = drops;
        this.auras = auras;
        this.description = description;
        this.skills = skills;
        this.stats = stats;
        this.asset = asset;
        this.genus = genus;
        this.rarity = rarity;
        this.price = price;
        this.size = size;
        this.maps = maps;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void addType(String type) {
        types.add(type);
    }
}
