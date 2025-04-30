package com.senac.pizzademo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.pizzademo.model.Cardapio;
import com.senac.pizzademo.repository.CardapioRepository;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {

    @Autowired
    private CardapioRepository cardapioRepository;

    @GetMapping
    public List<Cardapio> getAllCardapios() {
        return cardapioRepository.findAll();
    }

    @PostMapping
    public Cardapio createCardapio(@RequestBody Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cardapio> updateCardapio(@PathVariable Long id, @RequestBody Cardapio updatedCardapio) {
        System.out.println("Put recebido para cardapio id: " + id);

        return cardapioRepository.findById(id).map(cardapio -> {

            if(updatedCardapio.getValor() != null){
                cardapio.setValor(updatedCardapio.getValor());
            }
            if(updatedCardapio.getTamanho() != null){
                cardapio.setTamanho(updatedCardapio.getTamanho());
            }
            if(updatedCardapio.getPizza() != null){
                cardapio.setPizza(updatedCardapio.getPizza());
            }

            Cardapio saved = cardapioRepository.save(cardapio);
            return ResponseEntity.ok(saved);

        }).orElse(ResponseEntity.notFound().build());
        
    }

    // Adicionar métodos para atualização e exclusão conforme necessário
}
