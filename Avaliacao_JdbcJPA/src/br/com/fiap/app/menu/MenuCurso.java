package br.com.fiap.app.menu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;
import br.com.fiap.jpa.helper.CursoHelper;
import br.com.fiap.jpa.helper.EscolaHelper;

public class MenuCurso extends Menu {
	
	public static void submenu() throws Exception {
		List<Opcao> listaMenu = new ArrayList<>();
		
		listaMenu.add(new Opcao(1, "Inserir novo curso"));
		listaMenu.add(new Opcao(2, "Alterar informações do curso"));
		listaMenu.add(new Opcao(3, "Excluir curso"));
		listaMenu.add(new Opcao(4, "Cadastrar aluno em um curso"));
		listaMenu.add(new Opcao(5, "Listar cursos"));
		
		int opcao = Menu.show(listaMenu);
		
		try {
			switch (opcao) {
				case 1:
					inserir();
					break;
				case 2:
					alterar();
					break;	
				case 3:
					excluir();
					break;
				case 4:
					vincularAlunoCurso();
					break;
				case 5:
					listar();
					break;
			}
		} catch (NullPointerException e) {	//Caso seja usada a opção Cancelar no preenchimento de algum campo	
			opcao = 0;						//Capturo a exception e lanço opção 0 para voltar ao menu inicial
		}
	}
	
	private static void inserir() throws Exception {
		EscolaHelper daoEscola = new EscolaHelper();
		Escola escola = MenuEscola.filtrar(daoEscola);
		
		salvar(new Curso(), escola);
	}
	
	private static void alterar() throws Exception {
		EscolaHelper daoEscola = new EscolaHelper();
		Escola escola = MenuEscola.filtrar(daoEscola);

		CursoHelper daoCurso = new CursoHelper();
		Curso curso = MenuCurso.filtrar(daoCurso, escola);	
		
		salvar(curso, escola);
	}
	
	private static void excluir() throws Exception {
		EscolaHelper daoEscola = new EscolaHelper();
		Escola escola = MenuEscola.filtrar(daoEscola);

		CursoHelper daoCurso = new CursoHelper();
		Curso curso = MenuCurso.filtrar(daoCurso, escola);	
		
		if (curso != null) {
			daoCurso.excluir(curso);		
			JOptionPane.showMessageDialog(null, "Curso excluído com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private static void salvar(Curso curso, Escola escola) throws Exception {		
		if (curso != null) {			
			String cargaHoraria = "";
			if (curso.getCargaHoraria() != null) {
				cargaHoraria = String.valueOf(curso.getCargaHoraria());
			}			
			curso.setDescricao(inputString("Digite a descrição do curso:", curso.getDescricao(), true, 45));
			curso.setCargaHoraria(inputInt("Digite a carga horária do curso:", cargaHoraria, false));
			
			CursoHelper daoCurso = new CursoHelper();
			if (curso.getId() == 0) {
				curso.setEscola(escola);				
				daoCurso.inserir(curso);
			} else {
				daoCurso.alterar(curso);
			}			
			JOptionPane.showMessageDialog(null, "Dados salvos com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private static void listar() {		
		EscolaHelper daoEscola = new EscolaHelper();
		Escola escola = MenuEscola.filtrar(daoEscola);

		if (escola != null) {
			CursoHelper daoCurso = new CursoHelper();
			List<Curso> listaCursos = daoCurso.listarPorEscola(escola.getId());
		
			//Preparando mensagem de saída
			StringBuffer msg = new StringBuffer();	
			msg.append("-------------------------------------------\n");
			msg.append("ESCOLA: " + escola.getRazaoSocial() + "\n");
			msg.append("-------------------------------------------\n");
			for (Curso curso : listaCursos) {
				msg.append("Curso: " + curso.getDescricao() + "\n");
				msg.append("Carga Horária: " + curso.getCargaHoraria() + "\n");
				msg.append("-------------------------------------------\n");
			}
			
			System.out.println(msg);
			JOptionPane.showMessageDialog(null, msg, "Lista de Cursos", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public static Curso filtrar(CursoHelper daoCurso, Escola escola) {
		Curso curso = null;
		
		if (escola != null) {
			List<Curso> cursos = daoCurso.listarPorEscola(escola.getId());
			
			if (cursos.size() == 0) {
				JOptionPane.showMessageDialog(null, "Não existem cursos cadastrados para esta escola", 
						"Filtrar Curso", JOptionPane.INFORMATION_MESSAGE);
			} else {
				curso = (Curso) JOptionPane.showInputDialog(
						null,						
						"Selecione o curso para prosseguir",		
						"Filtrar Curso",				
						JOptionPane.DEFAULT_OPTION,	
						null,						
						cursos.toArray(),		    
						null);	
			}
		}			
		return curso;
	}
}