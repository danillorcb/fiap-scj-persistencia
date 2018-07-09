package br.com.fiap.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="curso")
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDESCOLA")
	private Escola escola;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="CARGAHORARIA")
	private Integer cargaHoraria;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="curso")
	private Set<Nota> notas = new HashSet<>();
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="nota", joinColumns=
			{
					@JoinColumn(name="IDCURSO", nullable=false, updatable=false)
			}, inverseJoinColumns= 
			{
					@JoinColumn(name="IDALUNO", nullable=false, updatable=false)
			})
	private Set<Aluno> alunos = new HashSet<>();
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Set<Nota> getNotas() {
		return notas;
	}

	public void setNotas(Set<Nota> notas) {
		this.notas = notas;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public String toString() {
		return this.getDescricao();
	}
	
}
