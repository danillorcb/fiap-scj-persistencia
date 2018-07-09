package br.com.fiap.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import br.com.fiap.app.menu.Menu;
import br.com.fiap.app.menu.MenuAluno;
import br.com.fiap.app.menu.MenuCurso;
import br.com.fiap.app.menu.MenuEscola;
import br.com.fiap.app.menu.MenuNota;
import br.com.fiap.app.menu.Opcao;

public class App {

	public static void main(String[] args) {
		boolean run = true;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			//Opções de menu
			List<Opcao> listaMenu = new ArrayList<>();
			listaMenu.add(new Opcao(1, "Escola"));
			listaMenu.add(new Opcao(2, "Curso"));
			listaMenu.add(new Opcao(3, "Aluno"));
			listaMenu.add(new Opcao(4, "Nota"));

			while (run) {
				int opcao = Menu.show(listaMenu);

				switch (opcao) {
					case 0:					
						//Opção finaliza a aplicação
						run = false;
						break;
					case 1:
						MenuEscola.submenu();
						break;
					case 2:
						MenuCurso.submenu();
						break;
					case 3:
						MenuAluno.submenu();
						break;
					case 4:
						MenuNota.submenu();
						break;
				}
			}			
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(null,
					"Erro: " + e.getMessage() + "\nCausa: " + e.getCause().getCause().getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			System.exit(0);
		}
	}
}
