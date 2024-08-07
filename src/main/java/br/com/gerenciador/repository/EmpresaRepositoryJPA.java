package br.com.gerenciador.repository;

import java.util.List;

import br.com.gerenciador.exception.DatabaseAccessException;
import br.com.gerenciador.modelo.Empresa;
import br.com.gerenciador.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

public class EmpresaRepositoryJPA implements EmpresaRepository {
	
	private EntityManager em;

	public EmpresaRepositoryJPA() {
		this.em = JPAUtil.getEntityManager();
	}
	public EmpresaRepositoryJPA(EntityManager entityManger) {
		this.em = entityManger;
	}

	
	@Transactional
	public void persist(Empresa empresa) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(empresa);
			transaction.commit();
			
		} catch (PersistenceException e) {
			transaction.rollback();
			throw new DatabaseAccessException(
				"ocorreu um erro ao cadastrar empresa", e);
		}
	}
	
	@Transactional
	public void update(Empresa empresa) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(empresa);
			transaction.commit();
			
		} catch (PersistenceException e) {
			transaction.rollback();
			throw new DatabaseAccessException(
				"ocorreu um erro ao atualizar empresa", e);
		}
	}
	
    public Empresa findByIdAndUserId(Long empresaId, Long userId) {
        try {
            return em.createQuery("""
					SELECT e FROM Empresa e 
					WHERE e.id = :empresaId 
					AND e.usuario.id = :userId
				""", 
				Empresa.class
			)
			.setParameter("empresaId", empresaId)
			.setParameter("userId", userId)
			.getSingleResult();
			
		} catch (NoResultException e) {
			throw new NoResultException("registro de empresa não encontrado");
		}
	}
	
	public List<Empresa> findAllPaged(Integer start, Integer max) {
		try {
			return em.createQuery("""
					SELECT e FROM Empresa e 
					WHERE e.ativo = 1
				""", 
				Empresa.class
			)
			.setFirstResult(start)
			.setMaxResults(max)
			.getResultList();
			
		} catch (PersistenceException e) {
			throw new DatabaseAccessException(
				"ocorreu um erro ao consultar todas as empresas", e);
		}
	}
	
	public List<Empresa> findByUsuarioIdAndAtivoPaged(
		Long id, 
		Integer start, 
		Integer max, 
		Boolean ativo
	) {
		try {
			return em.createQuery("""
					SELECT e FROM Empresa e 
					WHERE e.ativo = :ativo 
					AND e.usuario.id =:id
				""", 
				Empresa.class
			)
			.setFirstResult(start)
			.setMaxResults(max)
			.setParameter("id", id)
			.setParameter("ativo", ativo)
			.getResultList();
			
		} catch (PersistenceException e) {
			throw new DatabaseAccessException(
				"ocorreu um erro ao consultar todas as empresas do usuário", e);
		}
	}
	
	public List<Empresa> findByNameLikePaged(
		String nomeEmpresa, 
		Integer start, 
		Integer max
	){
		try {
			return em.createQuery("""
					SELECT e FROM Empresa e 
					WHERE e.ativo = 1 
					AND e.nome LIKE :nomeEmpresa
				""", 
				Empresa.class
			)
			.setParameter("nomeEmpresa", "%" + nomeEmpresa + "%")
			.setFirstResult(start)
			.setMaxResults(max)
			.getResultList();
			
		} catch (PersistenceException e) {
			throw new DatabaseAccessException(
				"ocorreu um erro ao consultar empresas pelo nome", e);
		}
	}
	
	
	public Long countByAtivoTrue() {
		try {
			Query query = em.createQuery("""
					SELECT COUNT(e) FROM Empresa e 
					WHERE e.ativo = 1
				"""
			);
			return (Long) query.getSingleResult();
			
		} catch (PersistenceException e) {
			throw new DatabaseAccessException(
				"ocorreu um erro ao contar empresas ativas", e);
		}
	}
	
	public Long countByUsuarioAndAtivo(Long id, Boolean ativo) {
		try {
			Query query = em.createQuery("""
					SELECT COUNT(e) FROM Empresa e 
					WHERE e.ativo = :ativo 
					AND e.usuario.id = :id
				"""
			)
			.setParameter("id", id)
			.setParameter("ativo", ativo);

			return (Long) query.getSingleResult();
			
		} catch (PersistenceException e) {
			throw new DatabaseAccessException(
				"ocorreu um erro ao contar empresas ativas do usuário", e);
		}
	}
	
	public Long countByParamSearch(String nomeEmpresa) {
		try {
			Query query = em.createQuery("""
				SELECT COUNT(e) FROM Empresa e 
				WHERE e.ativo = 1 
				AND e.nome LIKE :nomeEmpresa"""
			)
			.setParameter("nomeEmpresa", "%" + nomeEmpresa + "%");
			
			return (Long) query.getSingleResult();
			
		} catch (PersistenceException e) {
			throw new DatabaseAccessException(
				"ocorreu um erro ao contar empresas pelo parâmetro consultado" ,e);
		}
	}
}