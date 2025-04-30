package com.senac.pizzademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senac.pizzademo.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

}
