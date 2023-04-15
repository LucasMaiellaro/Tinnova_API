package com.veiculos.api;

import com.veiculos.domain.Veiculo;
import com.veiculos.domain.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculoService service;

    @GetMapping()
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        return ResponseEntity.ok(service.listarVeiculos());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarVeiculoPorId(@PathVariable("id") Long id) {
        Optional<Veiculo> veiculoOptional = service.buscarVeiculoPorId(id);
        return veiculoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity listarVeiculosPorMarca(@PathVariable("marca") String marca) {
        List<Veiculo> veiculos = service.listarVeiculosPorMarca(marca);
        return veiculos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(veiculos);
    }

    @GetMapping("/vendidos/{vendido}")
    public ResponseEntity buscarVeiculoPorVendido(@PathVariable("vendido") boolean vendido) {
        List<Veiculo> veiculos = service.listarVeiculosVendidos(vendido);
        return veiculos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(veiculos);
    }

    @PostMapping
    public ResponseEntity cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        try {
            Veiculo veiculoCadastrado = service.cadastrarVeiculos(veiculo);
            URI location = getUri(veiculoCadastrado.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarVeiculo(@PathVariable("id") Long id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoAtualizado = service.atualizarVeiculo(id, veiculo);
        return veiculoAtualizado != null ?
                ResponseEntity.ok(veiculoAtualizado) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarVeiculo(@PathVariable("id") Long id) {
            boolean deletado = service.deletarVeiculo(id);
            return deletado ?
                    ResponseEntity.ok().build() :
                    ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/ano/{ano}")
    public ResponseEntity alterarAno(@PathVariable("id") Long id, @PathVariable("ano") Integer ano) {
        Veiculo veiculoAtualizado = service.alterarAno(id, ano);
        return veiculoAtualizado != null ?
                ResponseEntity.ok(veiculoAtualizado) :
                ResponseEntity.notFound().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
