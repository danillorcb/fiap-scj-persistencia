package br.com.fiap.app.menu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.Escola;
import br.com.fiap.entity.Nota;
import br.com.fiap.jdbc.ApplicationContextFactory;
import br.com.fiap.jdbc.JdbcAlunoDao;
import br.com.fiap.jdbc.JdbcNotaDao;
import br.com.fiap.jpa.helper.CursoHelper;
import br.com.fiap.jpa.helper.EscolaHelper;

public class Menu {
	
	public static int show(List<Opcao> menu) {
		Opcao opcao = (Opcao) JOptionPane.showInputDialog(
				null, 
				"Selecione uma opção para avançar:",
				"Cadastro escolar [MENU]", 
				JOptionPane.DEFAULT_OPTION, 
				null, 
				menu.toArray(), 
				null);

		//Verificando se foi usado o botão Cancelar [ESC]
		if (opcao == null) {
			return 0;
		} else {
			return opcao.getCodigo();
		}
	}

	protected static String inputString(String msg, String inputDefault, boolean notNull) {
		return inputString(msg, inputDefault, notNull, 0);
	}
	
	protected static String inputString(String msg, String inputDefault, boolean notNull, int maxLength) {
		String value;			
		while (true) {
			value = JOptionPane.showInputDialog(null, msg, inputDefault);
			
			//Se o campo tiver tamanho máximo, faz a verificação
			if (maxLength != 0 && value.length() > maxLength) {
				JOptionPane.showMessageDialog(null, 
						"Tamanho máximo: " + maxLength + " caracteres", "Atenção", JOptionPane.WARNING_MESSAGE);
			} else			
			//Se o campo for NotNull, faz a verificação
			if (notNull) {
				if (!value.trim().isEmpty()) {
					break;
				}
				JOptionPane.showMessageDialog(null, 
						"Este campo não pode ser nulo", "Atenção", JOptionPane.WARNING_MESSAGE);
			}			
		}
		return value;
	}
	
	protected static Date inputDate(String msg, String inputDefault, boolean notNull) {
		String msgWarning = "Informe uma data válida no formato (dd/MM/aaaa)";		
		if (!notNull) {
			msgWarning = msgWarning + ", ou deixe em branco";
		}
		
		Date value = null;		
		while (true) {
			try {
				String input = JOptionPane.showInputDialog(null, msg, inputDefault);
				if (!notNull) {
					if (input.trim().isEmpty()) {
						break;
					}
				}				
				if (input.length() == 10) {
					value = new SimpleDateFormat("dd/MM/yyyy").parse(input);
					break;
				}				
				JOptionPane.showMessageDialog(null, msgWarning, "Atenção", JOptionPane.WARNING_MESSAGE);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, msgWarning, "Atenção", JOptionPane.WARNING_MESSAGE);
			}
		}				
		return value;
	}
	
	protected static Float inputFloat(String msg, String inputDefault, boolean notNull) {		
		String msgWarning = "Informe um número válido";
		if (!notNull) {
			msgWarning = msgWarning + ", ou deixe em branco";
		}
		
		Float value = null;		
		while (true) {
			try {
				value = Float.parseFloat(JOptionPane.showInputDialog(null, msg, inputDefault));
				break;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, msgWarning, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}		
		return value;
	}
	
	protected static Integer inputInt(String msg, String inputDefault, boolean notNull) {		
		String msgWarning = "Informe um número inteiro válido";
		if (!notNull) {
			msgWarning = msgWarning + ", ou deixe em branco";
		}
		
		Integer value = null;		
		while (true) {
			try {
				String input = JOptionPane.showInputDialog(null, msg, inputDefault);

				value = (input == null || input.equals("")) ? null : Integer.parseInt(input);
				break;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, msgWarning, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}				
		return value;
	}		
	
	protected static void vincularAlunoCurso() throws Exception {
		while (true) {
			int opcao = JOptionPane.showConfirmDialog(null, 
					"Cadastrar novo aluno em um curso?", "Cadastro aluno/curso", JOptionPane.YES_NO_OPTION);
			
			if (opcao != JOptionPane.YES_OPTION) {
				break;
			}
			
			EscolaHelper daoEscola = new EscolaHelper();
			Escola escola = MenuEscola.filtrar(daoEscola);
			
			CursoHelper daoCurso = new CursoHelper();
			Curso curso = MenuCurso.filtrar(daoCurso, escola);				
			
			JdbcAlunoDao daoAluno = (JdbcAlunoDao) 
					ApplicationContextFactory.getContext().getBean(JdbcAlunoDao.class);
			Aluno aluno = MenuAluno.filtrar(daoAluno); 
			
			//Verifico se o relacionamento N:N - Curso/Aluno já existe
			JdbcNotaDao daoNota = (JdbcNotaDao) 
					ApplicationContextFactory.getContext().getBean(JdbcNotaDao.class);			
			Nota nota = daoNota.buscar(curso.getId(), aluno.getId());
			
			//Se retornar algo não permito a nova inserção
			if (nota != null) {
				JOptionPane.showMessageDialog(null, 
						"O aluno selecionado já está cadastrado neste curso", 
						"Cadastro aluno/curso", JOptionPane.WARNING_MESSAGE);
				break;
			}
			
			if (aluno != null || curso != null) {
				aluno.getCursos().add(curso);						
				curso.getAlunos().add(aluno);
				daoCurso.alterar(curso);
				
				JOptionPane.showMessageDialog(null, 
						"Aluno cadastrado no curso com sucesso", "Cadastro aluno/curso", JOptionPane.INFORMATION_MESSAGE);
			}
		}	
	}
}
