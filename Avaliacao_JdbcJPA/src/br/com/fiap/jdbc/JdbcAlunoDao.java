package br.com.fiap.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import br.com.fiap.entity.Aluno;
import br.com.fiap.jdbc.mapper.AlunoMapper;

public class JdbcAlunoDao {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}	
	
	public void inserir(Aluno aluno) { 
		try {
			String sql = "INSERT INTO ALUNO (CPF, NOME, DATANASCIMENTO, CELULAR, EMAIL) VALUES (?,?,?,?,?)";
			this.jdbcTemplate.update(sql, aluno.getCpf(), aluno.getNome(), 
					aluno.getDataNascimento(), aluno.getCelular(), aluno.getEmail());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void alterar(Aluno aluno) { 
		try {
			String sql = "UPDATE ALUNO SET"
					+ " CPF = ?"
					+ ",NOME = ?"
					+ ",DATANASCIMENTO = ?"
					+ ",CELULAR = ?"
					+ ",EMAIL = ?"
					+ " WHERE ID = ?"; 
			this.jdbcTemplate.update(sql, 
					aluno.getCpf(), 
					aluno.getNome(), 
					aluno.getDataNascimento(), 
					aluno.getCelular(), 
					aluno.getEmail(),
					aluno.getId());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void excluir(Aluno aluno) { 
		try {
			String sql = "DELETE FROM ALUNO WHERE ID = ?";
			this.jdbcTemplate.update(sql, aluno.getId());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Aluno> listar() {
		List<Aluno> alunos = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM ALUNO";
			alunos = this.jdbcTemplate.query(query, new AlunoMapper());
		} catch (Exception e) {
			throw e;
		}
		
		return alunos;
	}
	
	public List<Aluno> listarPorCurso(int idCurso) {
		List<Aluno> alunos = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM ALUNO A"
					+ " INNER JOIN NOTA N ON A.ID = N.IDALUNO"
					+ " WHERE N.IDCURSO = ?";
			alunos = this.jdbcTemplate.query(
					query, 
					new Integer[] {idCurso}, 
					new AlunoMapper());
		} catch (Exception e) {
			throw e;
		}
		
		return alunos;
	}
	
	public Aluno buscar(int id) {
		Aluno aluno = null;
		
		try {
			String query = "SELECT * FROM ALUNO WHERE ID = ?";
			aluno = this.jdbcTemplate.queryForObject(
					query, 
					new Integer[] {id}, 
					new AlunoMapper());
		} catch (Exception e) {
			throw e;
		}
		
		return aluno;
	}	
	
	public boolean existeCpf(String cpf) {
		int qtd = 0;
		
		try {
			String query = "SELECT COUNT(*) FROM ALUNO WHERE CPF = ?";
			qtd = this.jdbcTemplate.queryForObject(
					query, 
					new String[] {cpf}, 
					Integer.class);
		} catch (Exception e) {
			throw e;
		}
		
		return (qtd == 0) ? false : true;
	}
}
