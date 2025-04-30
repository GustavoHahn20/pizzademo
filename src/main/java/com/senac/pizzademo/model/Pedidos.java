package com.senac.pizzademo.model;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Usuario usuario;

    @Column
    private List<Pizza> listaPizzas;

    @Column
    private Double total;

    @Column
    private LocalDateTime data;

    @Column
    private PedidoStatus status;

}
