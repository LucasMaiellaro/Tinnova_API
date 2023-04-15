package com.veiculos.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> listarVeiculos() {
        Iterable<Veiculo> veiculosIterable = repository.findAll();
        List<Veiculo> veiculosList = new ArrayList<>();
        veiculosIterable.forEach(veiculosList::add);
        return veiculosList;
    }

    public Optional<Veiculo> buscarVeiculoPorId(Long id) {
        return repository.findById(id);
    }

    public List<Veiculo> listarVeiculosPorMarca(String marca) {
        return repository.findByMarca(marca);
    }

    public List<Veiculo> listarVeiculosVendidos(boolean vendido) {
        List<Veiculo> veiculos = listarVeiculos();
        return veiculos.stream()
                .filter(veiculo -> veiculo.isVendido() == vendido)
                .collect(Collectors.toList());
    }

    public Veiculo cadastrarVeiculos(Veiculo veiculo) {
        Assert.isNull(veiculo.getId(), "Não foi possível cadastrar o registro");
        veiculo.setCreated(LocalDateTime.now());
        veiculo.setUpdated(LocalDateTime.now());
        return repository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
        Assert.isNull(veiculo.getId(), "Não foi possível atualizar o registro");
        Optional<Veiculo> veiculoOptional = buscarVeiculoPorId(id);

        if (veiculoOptional.isPresent()) {
            Veiculo db = veiculoOptional.get();
            db.setNome(veiculo.getNome());
            db.setMarca(veiculo.getMarca());
            db.setAno(veiculo.getAno());
            db.setDescricao(veiculo.getDescricao());
            db.setUpdated(LocalDateTime.now());
            repository.save(db);
            return db;
        } else {
            return null;
        }
    }

    public boolean deletarVeiculo(Long id) {
        Optional<Veiculo> veiculoOptional = buscarVeiculoPorId(id);
        if (veiculoOptional.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


    public Veiculo alterarAno(Long id, Integer ano) {
        Optional<Veiculo> veiculoOptional = buscarVeiculoPorId(id);
        if (veiculoOptional.isPresent()) {
            Veiculo db = veiculoOptional.get();
            db.setAno(ano);
            db.setUpdated(LocalDateTime.now());
            repository.save(db);
            return db;
        } else {
            return null;
        }
    }
}
