package br.com.fiap.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fiap.jdbc.viewmodel.AlunoNotaViewModel;

public class AlunoNotaMapper implements RowMapper<AlunoNotaViewModel> {

	@Override
	public AlunoNotaViewModel mapRow(ResultSet rs, int arg1) throws SQLException {
		AlunoNotaViewModel vm = new AlunoNotaViewModel();
		
		vm.setNome(rs.getString("NOME"));
		vm.setNota(rs.getFloat("NOTA"));
		
		return vm;
	}	
}
