package com.workintech.fswebs17d1.controller;


import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    private Map<Integer, Animal> animals = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return ResponseEntity.ok(new ArrayList<>(animals.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Integer id) {
        Animal animal = animals.get(id);
        if (animal != null) {
            return ResponseEntity.ok(animal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addAnimal(@PathVariable Integer id, @PathVariable String name) {
        if (animals.containsKey(id)) {
            return ResponseEntity.badRequest().body("Animal with this ID already exists!");
        }
        animals.put(id, new Animal(id, name));
        return ResponseEntity.ok("Animal added successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAnimal(@PathVariable Integer id, @RequestBody Animal updatedAnimal) {
        if (animals.containsKey(id)) {
            animals.put(id, updatedAnimal);
            return ResponseEntity.ok("Animal updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Integer id) {
        if (animals.containsKey(id)) {
            animals.remove(id);
            return ResponseEntity.ok("Animal deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
