package br.com.fiap.jpa.helper;

import java.util.List;

import javax.persistence.Query;

import br.com.fiap.entity.Curso;

public class CursoHelper extends Helper<Curso> {

	@SuppressWarnings("unchecked")
	public List<Curso> listar() {
		Query query = em.createQuery("select c from Curso c");
		return query.getResultList();
	}
	
	public Curso buscar(int id) {
		Query query = em.createQuery("select c from Curso c where id = :id");
		query.setParameter("id", id);
		return (Curso) query.getSingleResult();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Curso> listarPorEscola(int idEscola) {
		Query query = em.createQuery("select c from Curso c where idescola = :id_escola");
		query.setParameter("id_escola", idEscola);
		return query.getResultList();
	}
}
