package com.catalogo.back.catalogo.controller;

import com.catalogo.back.catalogo.model.Category;
import com.catalogo.back.catalogo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.SimpleTimeZone;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/insert")
    public Category insert(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/list")
    public List<Category> list() {
        return categoryService.findAll();
    }

    @GetMapping("listById/{id}")
    public Category findById(@PathVariable String id) {
        return categoryService.findById(id);
    }

    @GetMapping("listByName/{name}")
    public Category findByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> update(@PathVariable String id, @RequestBody Category category) {
        Category existing = categoryService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        } else {
            if (category.getName() != null) {
                existing.setName(category.getName());
            }
            if (category.getDescription() != null) {
                existing.setDescription(category.getDescription());
            }
            if (category.isActive() != existing.isActive()) {
                existing.setActive(category.isActive());
            }
            Category updated = categoryService.save(existing);
            return ResponseEntity.ok(updated);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        Category category = categoryService.findById(id);
        try {
            categoryService.delete(category);
            return ResponseEntity.ok("Categoría eliminada exitosamente");
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
