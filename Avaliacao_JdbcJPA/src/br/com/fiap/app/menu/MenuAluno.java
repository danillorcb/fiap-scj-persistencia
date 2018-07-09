package br.com.fiap.app.menu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;
import br.com.fiap.jdbc.ApplicationContextFactory;
import br.com.fiap.jdbc.JdbcAlunoDao;
import br.com.fiap.jpa.helper.CursoHelper;
import br.com.fiap.jpa.helper.EscolaHelper;

public class MenuAluno extends Menu {	
	
	public static void submenu() throws Exception {
		List<Opcao> listaMenu = new ArrayList<>();
		
		listaMenu.add(new Opcao(1, "Inserir novo aluno"));
		listaMenu.add(new Opcao(2, "Alterar dados cadastrais"));		
		listaMenu.add(new Opcao(3, "Excluir aluno"));
		listaMenu.add(new Opcao(4, "Cadastrar aluno em um curso"));
		listaMenu.add(new Opcao(5, "Listar TODOS os alunos"));
		listaMenu.add(new Opcao(6, "Listar alunos POR CURSO"));
		
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
				case 6:				
					listarPorCurso();
					break;	
			}
		} catch (NullPointerException e) {	//Caso seja usada a opção Cancelar no preenchimento de algum campo	
			opcao = 0;						//Capturo a exception e lanço opção 0 para voltar ao menu inicial
		}
	}
	
	private static void inserir() throws Exception {
		salvar(new Aluno());
	}
	
	private static void alterar() throws Exception {		
		JdbcAlunoDao daoAluno = (JdbcAlunoDao) 
				ApplicationContextFactory.getContext().getBean(JdbcAlunoDao.class);
		Aluno aluno = MenuAluno.filtrar(daoAluno);
		
		salvar(aluno);
	}
	
	private static void excluir() throws Exception {
		JdbcAlunoDao daoAluno = (JdbcAlunoDao) 
				ApplicationContextFactory.getContext().getBean(JdbcAlunoDao.class);		
		Aluno aluno = MenuAluno.filtrar(daoAluno);
			
		if (aluno != null) {				
			daoAluno.excluir(aluno);			
			JOptionPane.showMessageDialog(null, 
					"Aluno excluído com sucesso", "Cadastro de aluno", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private static void salvar(Aluno aluno) throws Exception {
			
		if (aluno != null) {	
			JdbcAlunoDao daoAluno = (JdbcAlunoDao) 
					ApplicationContextFactory.getContext().getBean(JdbcAlunoDao.class);
			
			String cpf = "";
			boolean existeCpf;
			do {
				cpf = Menu.inputString("Digite o CPF do aluno:", aluno.getCpf(), true, 11);
				existeCpf = daoAluno.existeCpf(cpf);
				if (existeCpf) {
					JOptionPane.showMessageDialog(null, 
							"CPF já existe, digite novamente", "Cadastro de aluno", JOptionPane.WARNING_MESSAGE);
				}
			} while (existeCpf);
			
			aluno.setCpf(cpf);
			aluno.setNome(
					Menu.inputString("Digite o nome do aluno:", aluno.getNome(), true));		
			String data = "";
			if (aluno.getId() != 0) {								//Se Aluno já existir no banco, busco a data
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				data = df.format(aluno.getDataNascimento());
			}
			aluno.setDataNascimento(
					Menu.inputDate("Data de nascimento do aluno (dd/MM/aaaa):", data, true));			
			aluno.setCelular(
					Menu.inputString("Digite o celular do aluno (Max. 11 dígitos):", aluno.getCelular(), true, 11));
			aluno.setEmail(
					Menu.inputString("Digite o email do aluno:", aluno.getEmail(), true, 45));
			
			if (aluno.getId() == 0) {								
				daoAluno.inserir(aluno);
			} else {
				daoAluno.alterar(aluno);
			}			
			JOptionPane.showMessageDialog(null, 
					"Dados salvos com sucesso", "Cadastro de aluno", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private static void listarPorCurso() {
		EscolaHelper daoEscola = new EscolaHelper();
		Escola escola = MenuEscola.filtrar(daoEscola);

		CursoHelper daoCurso = new CursoHelper();
		Curso curso = MenuCurso.filtrar(daoCurso, escola);	

		if (curso != null) {
			JdbcAlunoDao daoAluno = (JdbcAlunoDao) 
					ApplicationContextFactory.getContext().getBean(JdbcAlunoDao.class);
			List<Aluno> listaAlunos = daoAluno.listarPorCurso(curso.getId());
			
			if (listaAlunos.size() == 0) {
				JOptionPane.showMessageDialog(null, 
						"Nenhum aluno cadastrado para este curso", "Cadastro de aluno", JOptionPane.WARNING_MESSAGE);
			} else {
				MenuAluno.imprimeListaAlunos(listaAlunos, curso);
			}
		}
	}
	
	private static void listar() {	
		JdbcAlunoDao daoAluno = (JdbcAlunoDao) 
				ApplicationContextFactory.getContext().getBean(JdbcAlunoDao.class);	
		List<Aluno> listaAlunos = daoAluno.listar();
		
		if (listaAlunos.size() == 0) {
			JOptionPane.showMessageDialog(null, 
					"Nenhum aluno cadastrado no banco de dados", "Cadastro de aluno", JOptionPane.WARNING_MESSAGE);
		} else {
			MenuAluno.imprimeListaAlunos(listaAlunos, null);
		}
	}
	
	private static void imprimeListaAlunos(List<Aluno> listaAlunos, Curso curso) {
		if (curso != null) {
			System.out.println("--------------------------------------------------------------"
					+ "--------------------------------------------------------------------");
			System.out.println("CURSO: " + curso.getDescricao());				
		}
	    System.out.println("--------------------------------------------------------------"
	    		+ "--------------------------------------------------------------------");
	    System.out.printf("%12s %50s %12s %12s %40s", 
	    		"CPF", "NOME COMPLETO", "DATA NASC", "CELULAR", "EMAIL");
	    System.out.println();
	    System.out.println("--------------------------------------------------------------"
	    		+ "--------------------------------------------------------------------");
	    for(Aluno aluno : listaAlunos){
	    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	    	String data = df.format(aluno.getDataNascimento());

	    	System.out.format("%12s %50s %12s %12s %40s", 
	    			aluno.getCpf(), aluno.getNome(), data, aluno.getCelular(), aluno.getEmail());
	        System.out.println();			        
	    }					
	    System.out.println("--------------------------------------------------------------"
	    		+ "--------------------------------------------------------------------");
		
		JOptionPane.showMessageDialog(null, 
				"Resultado impresso no console", "Cadastro de aluno", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	/*
	 * Método cria o input com todos alunos da tabela
	 */
	public static Aluno filtrar(JdbcAlunoDao daoAluno) {
		return filtrar(daoAluno, null);
	}
	
	/*
	 * Método cria o input somente com os alunos do curso passado como parâmetro, quando curso != null
	 */
	public static Aluno filtrar(JdbcAlunoDao daoAluno, Curso curso) {		
		Aluno aluno = null;
		List<Aluno> alunos = new ArrayList<>();
		
		if (curso == null) {
			alunos = daoAluno.listar();
		} else {
			alunos = daoAluno.listarPorCurso(curso.getId());
		}	
		
		if (alunos.size() == 0) {
			JOptionPane.showMessageDialog(null, "Não existem alunos cadastrados", 
					"Filtrar Aluno", JOptionPane.INFORMATION_MESSAGE);
		} else {
			aluno = (Aluno) JOptionPane.showInputDialog(
					null,						
					"Selecione o aluno para prosseguir",		
					"Filtrar Aluno",				
					JOptionPane.DEFAULT_OPTION,	
					null,						
					alunos.toArray(),		    
					null);	
		}			
		return aluno;	
	}
}
