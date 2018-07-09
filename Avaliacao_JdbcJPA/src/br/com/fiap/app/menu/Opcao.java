package br.com.fiap.app.menu;

public class Opcao {	
	private Integer codigo;
	private String descricao;
	
	public Opcao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}	
	
	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		if (this.getCodigo() == null) {
			return this.getDescricao();
		} else {
			return this.getCodigo() + " - " + this.getDescricao();
		}
	}
}
