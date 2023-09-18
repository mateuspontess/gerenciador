package main.java.br.com.alura.gerenciador.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.br.com.alura.gerenciador.modelo.Empresa;

public class EmpresaRepository {
	private EntityManager em;
	public EmpresaRepository(EntityManager em) {
		this.em  = em;
	}
	
	
	
	public void persist(Empresa empresa) {
		em.getTransaction().begin();
		em.persist(empresa);
		em.getTransaction().commit();
	}
	
	public List<Empresa> getEmpresas(){
		return em.createNativeQuery("SELECT * FROM empresas WHERE ativo = 1", Empresa.class).getResultList();
	}



	public Empresa getEmpresaById(Long id) {
		Query query = em.createNativeQuery("SELECT * FROM empresas WHERE id =:id", Empresa.class).setParameter("id", id);
		
		try {
			return (Empresa) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void updateEmpresaById(Empresa empresa) {
		em.getTransaction().begin();
		em.getTransaction().commit();
	}


	public EmpresaRepository criaInstancia(EntityManager em) {
		return new EmpresaRepository(em);
	}
	
}