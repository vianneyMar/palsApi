package com.example.demo.controller;

import com.example.demo.beans.Pals;
import com.example.demo.beans.Skill;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/pals")
public class PalsController {

    private final MongoTemplate mongoTemplate;

    public PalsController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping()
    public List<Pals> getPals() {
        return mongoTemplate.findAll(Pals.class);
    }

    @PostMapping
    public void savePals(@RequestBody Pals product) {
        mongoTemplate.save(product);
    }

    @GetMapping("getByID/{id}")
    public Pals getPlasById(@PathVariable int id) {
        return mongoTemplate.findById(id, Pals.class);
    }

    @GetMapping("getByName/{name}")
    public List<Pals> getPlasByName(@PathVariable String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(name));
        return mongoTemplate.find(query, Pals.class);
    }

    @GetMapping("getByType/{type}")
    public List<Pals> getPlasByType(@PathVariable String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("types").is(type));
        return mongoTemplate.find(query, Pals.class);
    }

    @PostMapping("saveNewPal")
    public void saveNewPals(@RequestBody Pals pal) {
        mongoTemplate.save(pal);
    }

    @GetMapping("getSkills/{id}")
    public List<Skill> getSkills(@PathVariable int id) {
        Pals pal = mongoTemplate.findById(id, Pals.class);
        return pal.getSkills();
    }

    @PostMapping("addSkill/{id}")
    public void addSkill(@PathVariable int id, @RequestBody Skill skill) {
        Pals pal = mongoTemplate.findById(id, Pals.class);
        pal.addSkill(skill);
        mongoTemplate.save(pal);
    }

    @PutMapping("modifySkill/{id}/{skillName}")
    public void addSkill(@PathVariable int id, @PathVariable String skillName, @RequestBody Skill updatedSkill) {
        Pals pal = mongoTemplate.findById(id, Pals.class);
        if (pal == null) {
            // Gérer le cas où le pal n'est pas trouvé
            return;
        }

        Skill skillToUpdate = null;
        for (Skill skill : pal.getSkills()) {
            if (skill.getName().equals(skillName)) {
                skillToUpdate = skill;
                break;
            }
        }

        // Modifier le skill si trouvé
        if (skillToUpdate != null) {
            // Mettre à jour les propriétés du skill avec les nouvelles valeurs
            skillToUpdate.setName(updatedSkill.getName());
            skillToUpdate.setType(updatedSkill.getType());
            skillToUpdate.setCooldown(updatedSkill.getCooldown());
            skillToUpdate.setPower(updatedSkill.getPower());
            skillToUpdate.setDescription(updatedSkill.getDescription());
            // Autres propriétés à mettre à jour si nécessaire

            // Enregistrer les modifications dans la base de données
            mongoTemplate.save(pal);
        }
    }

    @GetMapping("getTypes/{id}")
    public List<String> getTypes(@PathVariable int id) {
        Pals pal = mongoTemplate.findById(id, Pals.class);
        return pal.getTypes();
    }

    @PostMapping("addType/{id}/{type}")
    public void addType(@PathVariable int id,@PathVariable String type) {
        Pals pal = mongoTemplate.findById(id, Pals.class);
        pal.addType(type);
        mongoTemplate.save(pal);
    }

    @PostMapping("addTypes/{id}")
    public void addType(@PathVariable int id, @RequestBody List<String> types) {
        Pals pal = mongoTemplate.findById(id, Pals.class);
        for (String type : types) {
            pal.addType(type);
        }
        mongoTemplate.save(pal);
    }

    @DeleteMapping("removeType/{id}/{type}")
    public void removeType(@PathVariable int id, @PathVariable String type) {
        Pals pal = mongoTemplate.findById(id, Pals.class);
        if (pal == null) {
            return;
        }
        pal.getTypes().remove(type);
        mongoTemplate.save(pal);
    }


}
