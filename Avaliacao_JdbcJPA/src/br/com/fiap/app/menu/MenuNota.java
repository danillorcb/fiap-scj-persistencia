package br.com.fiap.app.menu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;
import br.com.fiap.entity.Nota;
import br.com.fiap.jdbc.ApplicationContextFactory;
import br.com.fiap.jdbc.JdbcAlunoDao;
import br.com.fiap.jdbc.JdbcAlunoNotaDao;
import br.com.fiap.jdbc.JdbcNotaDao;
import br.com.fiap.jdbc.viewmodel.AlunoNotaViewModel;
import br.com.fiap.jpa.helper.CursoHelper;
import br.com.fiap.jpa.helper.EscolaHelper;

public class MenuNota extends Menu {
	
	public static void submenu() throws Exception {
		List<Opcao> listaMenu = new ArrayList<>();
		
		listaMenu.add(new Opcao(1, "Lançar Nota"));
		listaMenu.add(new Opcao(2, "Consultar notas dos alunos por curso"));
		
		int opcao = Menu.show(listaMenu);
		
		try {
			switch (opcao) {
				case 1:
					lancar();
					break;
				case 2:
					notasAlunosPorCurso();
					break;
			}
		} catch (NullPointerException e) {	//Caso seja usada a opção Cancelar no preenchimento de algum campo	
			opcao = 0;						//Capturo a exception e lanço opção 0 para voltar ao menu inicial
		}
	}

	private static void lancar() throws Exception {
		
		while (true) {
			int opcao = JOptionPane.showConfirmDialog(null, "Deseja lançar/alterar uma nota?", "Confirmação", JOptionPane.YES_NO_OPTION);
			
			if (opcao == JOptionPane.NO_OPTION || opcao == JOptionPane.CLOSED_OPTION) {
				break;
			}

			EscolaHelper daoEscola = new EscolaHelper();	
			Escola escola = MenuEscola.filtrar(daoEscola);

			CursoHelper daoCurso = new CursoHelper();	
			Curso curso = MenuCurso.filtrar(daoCurso, escola);

			JdbcAlunoDao daoAluno = (JdbcAlunoDao) 
					ApplicationContextFactory.getContext().getBean(JdbcAlunoDao.class);
			Aluno aluno = MenuAluno.filtrar(daoAluno, curso);

			if (aluno == null) {
				break;
			}
					
			//Busco na tabela nota o registro que já existe devido ao relacionamento N:N - Curso/Aluno
			JdbcNotaDao daoNota = (JdbcNotaDao) 
					ApplicationContextFactory.getContext().getBean(JdbcNotaDao.class);
			Nota nota = daoNota.buscar(curso.getId(), aluno.getId());

			float notaInput = -1;
			while (notaInput < 0 || notaInput > 10) {
				notaInput = inputFloat("Digite a nota entre 0 a 10.0", 
						(nota.getNota() == null) ? "" : nota.getNota().toString(), true);
			}
			nota.setNota(notaInput);		

			daoNota.alterar(nota);			
			JOptionPane.showMessageDialog(null, 
					"Nota lançada com sucesso", "Lançar Nota", JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	
	public static void notasAlunosPorCurso() throws Exception {	
		EscolaHelper daoEscola = new EscolaHelper();
		Escola escola = MenuEscola.filtrar(daoEscola);

		CursoHelper daoCurso = new CursoHelper();
		Curso curso = MenuCurso.filtrar(daoCurso, escola);
		
		if (curso != null) {
			try {
				List<AlunoNotaViewModel> alunos = ((JdbcAlunoNotaDao) 
						ApplicationContextFactory.getContext().getBean(JdbcAlunoNotaDao.class))
						.listarAlunosNotasPorCurso(curso.getId());
				
				if (alunos.size() == 0) {
					JOptionPane.showMessageDialog(null, 
							"Nenhum aluno cadastrado para este curso", "Cadastro de aluno", JOptionPane.WARNING_MESSAGE);
				} else {
					
					//Imprimindo o resultado no console
					System.out.println("-------------------------------------------------------------");
					System.out.println("CURSO: " + curso.getDescricao());				
					System.out.println("-------------------------------------------------------------");
					System.out.printf("%50s %10s", "NOME DO ALUNO", "NOTA");
					System.out.println();
					System.out.println("-------------------------------------------------------------");
					for(AlunoNotaViewModel vm : alunos){
						System.out.format("%50s %10.1f", vm.getNome(), vm.getNota());
						System.out.println();			        
					}
					System.out.println("-------------------------------------------------------------");
					
					JOptionPane.showMessageDialog(null, "Resultado impresso no console", 
							"Notas dos alunos por curso", JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
