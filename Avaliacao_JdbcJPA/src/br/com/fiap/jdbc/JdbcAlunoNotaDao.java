package br.com.fiap.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import br.com.fiap.jdbc.mapper.AlunoNotaMapper;
import br.com.fiap.jdbc.viewmodel.AlunoNotaViewModel;

public class JdbcAlunoNotaDao {
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<AlunoNotaViewModel> listarAlunosNotasPorCurso(int idCurso) {
		List<AlunoNotaViewModel> alunos = new ArrayList<>();
		
		String query = 
				"SELECT A.NOME, N.NOTA FROM NOTA N\r\n" + 
				"INNER JOIN ALUNO A ON A.ID = N.IDALUNO\r\n" + 
				"WHERE N.IDCURSO = ?";		
		try {
			alunos = this.jdbcTemplate.query(
					query,
					new Integer[] {idCurso},
					new AlunoNotaMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return alunos;
	}
}
