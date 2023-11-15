package main.java.br.com.alura.gerenciador.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "empresas")
public class Empresa {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Setter
	private String nome;
	@Setter
	private LocalDate dataAbertura;
	private Boolean ativo = true;
	@ManyToOne @JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Empresa(String nome, LocalDate dataAbertura, Usuario usuario) {
		this.nome = nome;
		this.dataAbertura = dataAbertura;
		this.usuario = usuario;
	}
	
	public Empresa removeOrRestoreEmpresa() {
		if (this.ativo == true) {
			this.ativo = false;
			} else {
				this.ativo = true;				
			}
		return this;
	}
	
	public Empresa alteraDados(String nome, LocalDate dataAbertura) {
		this.nome = nome;
		this.dataAbertura = dataAbertura;
		return this;
	}
}
