package main.java.br.com.alura.gerenciador.repository;

import java.util.List;

import jakarta.transaction.Transactional;

import main.java.br.com.alura.gerenciador.modelo.Empresa;

public interface EmpresaRepository {
	
	@Transactional
	public void persist(Empresa empresa);
	
	@Transactional
	public void update(Empresa empresa);

	public Empresa findEmpresaById(Long id);
	
	public List<Empresa> findEmpresas();

	public List<Empresa> findEmpresasByUsuarioId(Long id);
	
	public List<Empresa> findEmpresasByUsuarioIdAndAtivoTrue(Long id);
	
}