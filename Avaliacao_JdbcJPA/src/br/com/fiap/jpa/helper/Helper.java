package br.com.fiap.jpa.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Helper<T> {

	private EntityManagerFactory emf;
	protected EntityManager em;
	
	public Helper() {
		this.emf = Persistence.createEntityManagerFactory("jpaPU");
		this.em = emf.createEntityManager();
	}
	
	public void inserir(T elemento) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(elemento);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}
	
	public void alterar(T elemento) throws Exception {
		try {
			em.getTransaction().begin();
			em.merge(elemento);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}
	
	public void excluir(T elemento) throws Exception {
		try {
			em.getTransaction().begin();
			em.remove(elemento);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}
}