package br.com.gerenciador.dto.empresa.request;

import br.com.gerenciador.dto.empresa.EmpresaBaseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AlteraEmpresaDTO(
		@NotNull(message = "ID não pode ser nulo")
		Long id,
		
		@Valid
		EmpresaBaseDTO base
		) {
}