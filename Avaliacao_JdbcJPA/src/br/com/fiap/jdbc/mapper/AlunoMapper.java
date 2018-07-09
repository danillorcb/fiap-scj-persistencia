package br.com.fiap.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fiap.entity.Aluno;

public class AlunoMapper implements RowMapper<Aluno> {

	@Override
	public Aluno mapRow(ResultSet rs, int arg1) throws SQLException {
		Aluno aluno = new Aluno();
		
		aluno.setId(rs.getInt("ID"));
		aluno.setCpf(rs.getString("CPF"));
		aluno.setNome(rs.getString("NOME"));
		aluno.setDataNascimento(rs.getDate("DATANASCIMENTO"));
		aluno.setCelular(rs.getString("CELULAR"));
		aluno.setEmail(rs.getString("EMAIL"));		
		
		return aluno;
	}
	
}
