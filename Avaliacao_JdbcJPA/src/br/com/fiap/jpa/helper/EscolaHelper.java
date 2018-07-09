package br.com.fiap.jpa.helper;

import java.util.List;

import javax.persistence.Query;

import br.com.fiap.entity.Escola;

public class EscolaHelper extends Helper<Escola> {

	@SuppressWarnings("unchecked")
	public List<Escola> listar() {
		Query query = em.createQuery("select e from Escola e");
		return query.getResultList();
	}
	
	public Escola buscar(int id) {
		Query query = em.createQuery("select e from Escola e where id = :id");
		query.setParameter("id", id);
		return (Escola) query.getSingleResult();	
	}
}
