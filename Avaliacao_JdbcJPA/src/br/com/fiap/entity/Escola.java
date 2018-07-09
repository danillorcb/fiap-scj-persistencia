package br.com.fiap.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="escola", uniqueConstraints = {@UniqueConstraint(name="UK_CNPJ", columnNames = {"CNPJ"})})
public class Escola implements Serializable, Comparable<Escola> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="CNPJ", unique=true)
	private String cnpj;
	
	@Column(name="RAZAOSOCIAL")
	private String razaoSocial;
	
	@Column(name="ENDERECO")
	private String endereco;
	
	@Column(name="DATAFUNDACAO")
	private Date dataFundacao;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="escola")
	private Set<Curso> cursos = new HashSet<>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Date getDataFundacao() {
		return dataFundacao;
	}
	
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	public Set<Curso> getCursos() {
		return cursos;
	}
	
	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}
		
	@Override
	public String toString() {
		return this.getCnpj() + " - " + this.getRazaoSocial();
	}
	
	@Override
	public int compareTo(Escola escola) {
		if (this.getId() < escola.getId()) {
			return -1;
		}		
		if (this.getId() > escola.getId()) {
			return 1;
		}		
		return 0;
	}
}
