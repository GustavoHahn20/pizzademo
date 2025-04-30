package com.senac.pizzademo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.pizzademo.model.Ingredientes;
import com.senac.pizzademo.repository.IngredientesRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/ingredientes")
public class IngredientesController {

    @Autowired
    private IngredientesRepository ingredientesRepository;

    @GetMapping
    public List<Ingredientes> getAllIngredientes() {
        return ingredientesRepository.findAll();
    }

    @PostMapping
    public Ingredientes createPizza(@RequestBody Ingredientes ingredientes) {
        return ingredientesRepository.save(ingredientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredientes> updateIngredientes(@PathVariable Long id, @RequestBody Ingredientes updatedIngredientes) {
        
        System.out.println("Put recebido para ingrediente id: " + id);
        System.out.println("");
        
        return ingredientesRepository.findById(id).map(ingrediente ->{
            
            if(updatedIngredientes.getIngrediente() != null){
                ingrediente.setIngrediente(updatedIngredientes.getIngrediente());
            }
            if(updatedIngredientes.getQuantidade() != null){
                ingrediente.setQuantidade(updatedIngredientes.getQuantidade());
            }
            if(updatedIngredientes.getPizza() != null){
            ingrediente.setPizza(updatedIngredientes.getPizza());
            }

            Ingredientes saved = ingredientesRepository.save(ingrediente);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());

    }

    // Adicionar métodos para atualização e exclusão conforme necessário
}
