package br.com.alura.gerenciador.repository;

import java.util.List;

import br.com.alura.gerenciador.modelo.Empresa;
import jakarta.transaction.Transactional;

public interface EmpresaRepository {
	
	@Transactional
	public void persist(Empresa empresa);
	@Transactional
	public void update(Empresa empresa);

	public Empresa findEmpresaById(Long id);
	
	@Deprecated
	public List<Empresa> findEmpresas();
	public List<Empresa> findEmpresasPaged(Integer startIndex, Integer endIndex);

	@Deprecated
	public List<Empresa> findEmpresasByUsuarioId(Long id);
	public List<Empresa> findEmpresasPagedByUsuarioIdAndAtivo(Long id, Integer start, Integer end, Boolean ativo);

	@Deprecated
	public List<Empresa> searchEmpresasByNameLike(String nomeEmpresa);
	public List<Empresa> searchEmpresasPagedByNameLike(String nomeEmpresa, Integer start, Integer end);
	
	public Long countEmpresasByAtivoTrue();
	public Long countEmpresasUsuarioByAtivo(Long userId, Boolean empresaAtivo);
	public Long countEmpresasByParamSearch(String nomeEmpresa);
}