package br.com.fiap.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fiap.entity.Nota;
import br.com.fiap.jdbc.ApplicationContextFactory;
import br.com.fiap.jdbc.JdbcAlunoDao;
import br.com.fiap.jpa.helper.CursoHelper;

public class NotaMapper implements RowMapper<Nota> {

	@Override
	public Nota mapRow(ResultSet rs, int arg1) throws SQLException {
		Nota nota = new Nota();
		
		nota.setId(rs.getInt("ID"));
		nota.setNota(rs.getFloat("NOTA"));
		
		//Buscando o curso através do ID que está no banco e setando no objeto nota
		CursoHelper daoCurso = new CursoHelper();
		nota.setCurso(daoCurso.buscar(rs.getInt("IDCURSO")));
		
		//Buscando o aluno através do ID que está no banco e setando no objeto nota
		JdbcAlunoDao daoAluno = (JdbcAlunoDao) 
				ApplicationContextFactory.getContext().getBean(JdbcAlunoDao.class);
		nota.setAluno(daoAluno.buscar(rs.getInt("IDALUNO")));
		
		return nota;
	}	
}
