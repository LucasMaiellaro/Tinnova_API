package com.veiculos;

import com.veiculos.domain.Veiculo;
import com.veiculos.domain.VeiculoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class VeiculosApplicationTests {

	@Autowired
	private VeiculoService service;

	@Test
	void cadastrarVeiculoTest() {
		Veiculo veiculo = new Veiculo();
		veiculo.setNome("HB20");
		veiculo.setMarca("Hyundai");
		veiculo.setAno(2023);
		veiculo.setDescricao("Hyundai HB20 Branco");
		veiculo.setVendido(false);
		Assertions.assertNotNull(service.cadastrarVeiculos(veiculo));

		Long id = veiculo.getId();
		Assertions.assertNotNull(id);

		Optional<Veiculo> veiculoOptional = service.buscarVeiculoPorId(veiculo.getId());
		Assertions.assertTrue(veiculoOptional.isPresent());

		veiculo = veiculoOptional.get();
		Assertions.assertEquals("HB20", veiculo.getNome());
		Assertions.assertEquals("Hyundai", veiculo.getMarca());
		Assertions.assertEquals((Integer) 2023, veiculo.getAno());
		Assertions.assertEquals("Hyundai HB20 Branco", veiculo.getDescricao());
		Assertions.assertFalse(veiculo.isVendido());

		service.deletarVeiculo(id);
		Assertions.assertFalse(service.buscarVeiculoPorId(id).isPresent());
	}

}
