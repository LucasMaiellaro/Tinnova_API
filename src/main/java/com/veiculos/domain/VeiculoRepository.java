package com.veiculos.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {
    List<Veiculo> findByMarca(String marca);
}
