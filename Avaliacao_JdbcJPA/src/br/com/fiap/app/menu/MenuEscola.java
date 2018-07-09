package br.com.fiap.app.menu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.entity.Escola;
import br.com.fiap.jpa.helper.EscolaHelper;

public class MenuEscola extends Menu {
	
	public static void submenu() throws Exception {
		List<Opcao> listaMenu = new ArrayList<>();
		
		listaMenu.add(new Opcao(1, "Cadastrar dados da escola"));
		listaMenu.add(new Opcao(2, "Alterar dados da escola"));
		listaMenu.add(new Opcao(3, "Visualizar dados cadastrais"));
		
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
					listar();
					break;
			}
		} catch (NullPointerException e) {	//Caso seja usada a opção Cancelar no preenchimento de algum campo	
			opcao = 0;						//Capturo a exception e lanço opção 0 para voltar ao menu inicial
		}
	}
	
	private static void inserir() throws Exception {
		EscolaHelper daoEscola = new EscolaHelper();
		
		if (daoEscola.listar().size() == 0) {
			salvar(new Escola());
		} else {
			int opcao = JOptionPane.showConfirmDialog(null, "A escola já está cadastrada, deseja alterar os dados?", 
					"Cadastro Escola", JOptionPane.YES_NO_OPTION);

			if (opcao == JOptionPane.YES_OPTION) {
				alterar();
			}
		}		
	}
	
	private static void alterar() throws Exception {
		EscolaHelper daoEscola = new EscolaHelper();
		Escola escola = MenuEscola.filtrar(daoEscola);
		
		salvar(escola);
	}
	
	private static void salvar(Escola escola) throws Exception {		
		EscolaHelper daoEscola = new EscolaHelper();

		String dataFundacao = "";
		if (escola.getDataFundacao() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			dataFundacao = df.format(escola.getDataFundacao());
		}

		escola.setCnpj(inputString("Digite o CNPJ da escola:", escola.getCnpj(), true, 14));
		escola.setRazaoSocial(inputString("Digite o nome/razão social da escola:", escola.getRazaoSocial(), true));
		escola.setEndereco(inputString("Digite o endereço da escola:", escola.getEndereco(), true));
		escola.setDataFundacao(inputDate("Data do fundação da escola (dd/MM/aaaa):", dataFundacao, false));
		
		if (escola.getId() == 0) {
			daoEscola.inserir(escola);
		} else {
			daoEscola.alterar(escola);
		}
		
		JOptionPane.showMessageDialog(null, "Dados salvos com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static void listar() {		
		EscolaHelper daoEscola = new EscolaHelper();
		Escola escola = MenuEscola.filtrar(daoEscola);
		
		String dataFundacao = "";
		if (escola.getDataFundacao() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			dataFundacao = df.format(escola.getDataFundacao());
		}

		StringBuffer msg = new StringBuffer();	
		msg.append("CNPJ: " + escola.getCnpj() + "\n");
		msg.append("Razão Social: " + escola.getRazaoSocial() + "\n");
		msg.append("Endereço: " + escola.getEndereco() + "\n");		
		msg.append("Data de Fundação: " + dataFundacao + "\n");
		
		System.out.println(msg);
		JOptionPane.showMessageDialog(null, msg, "Dados da escola", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static Escola filtrar(EscolaHelper daoEscola) {
		Escola escola = null;	
		List<Escola> escolas = daoEscola.listar();
		
		if (escolas.size() == 0) {
			JOptionPane.showMessageDialog(null, "É necessário cadastrar a escola", 
					"Filtrar Escola", JOptionPane.INFORMATION_MESSAGE);
		} else {
			escola = escolas.get(0);
		}
		return escola;
	}
}
