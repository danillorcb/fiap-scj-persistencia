package br.com.fiap.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="nota", uniqueConstraints = {@UniqueConstraint(name="UK_IDCURSO_IDALUNO", columnNames = {"IDCURSO", "IDALUNO"})})
public class Nota implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NOTA")
	private Float nota;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCURSO")
	private Curso curso;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDALUNO")
	private Aluno aluno;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	@Override
	public boolean equals(Object o) {			
		if (o instanceof Nota) {
			Nota nota = (Nota) o;
			
			if (this.getAluno().getId() != nota.getAluno().getId()) {
				return false;
			}
			if (this.getCurso().getId() != nota.getCurso().getId()) {
				return false;
			}
		}
		return true;
	}
}
