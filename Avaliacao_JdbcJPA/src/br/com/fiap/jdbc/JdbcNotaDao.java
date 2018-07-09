package br.com.fiap.jdbc;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.fiap.entity.Nota;
import br.com.fiap.jdbc.mapper.NotaMapper;

public class JdbcNotaDao {
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void alterar(Nota nota) {
		try {
			String sql = "UPDATE NOTA SET NOTA = ? WHERE IDCURSO = ? AND IDALUNO = ?";
			this.jdbcTemplate.update(sql, 
					nota.getNota(), nota.getCurso().getId(), nota.getAluno().getId());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Nota buscar(int idCurso, int idAluno) {
		Nota nota = null;
		
		try {
			String query = "SELECT * FROM NOTA WHERE IDCURSO = ? AND IDALUNO = ?";
			nota = this.jdbcTemplate.queryForObject(
					query, 
					new Integer[] {idCurso, idAluno},
					new NotaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;			
		} catch (Exception e) {
			throw e;
		}
		
		return nota;
	}
}
