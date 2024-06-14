package conta;

import java.util.ArrayList;
import java.util.Scanner;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

	
		// Teste da Classe Conta Corrente
		ContaCorrente cc1 = new ContaCorrente(2, 123, 1, "Luquinhas", 10000.0f, 100.0f);
		cc1.visualizar();
		cc1.sacar(12000.0f);
		cc1.visualizar();
		cc1.depositar(5000.0f);
		cc1.visualizar();
		// Teste da Classe Conta Corrente
		ContaPoupanca cp1 = new ContaPoupanca(3, 123, 1, "Fernando", 10000.0f, 15);
		cp1.visualizar();
		cp1.sacar(12000.0f);
		cp1.visualizar();
		cp1.depositar(5000.0f);
		cp1.visualizar();

		ArrayList<Conta> listaContas = new ArrayList<>();

		listaContas.add(cc1);
		listaContas.add(cp1);

		int opcao;

		while (true) {

			System.out.println(Cores.TEXT_CYAN + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			opcao = scanner.nextInt();

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_BLACK_BACKGROUND
						+ "\nBanco do Brazil com Z    - O seu Futuro começa aqui!");
				sobre();
				scanner.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
				System.out.println("Digite o número da conta:");
				int numeroConta = scanner.nextInt();
				System.out.println("Digite o número da agência:");
				int numeroAgencia = scanner.nextInt();
				System.out.println("Digite o tipo de conta:");
				int tipoConta = scanner.nextInt();
				System.out.println("Digite o nome do titular:");
				scanner.nextLine();
				String nomeTitular = scanner.nextLine();
				System.out.println("Digite o saldo de inicialização:");
				float saldoInicial = scanner.nextFloat();
				if (tipoConta == 1) {
					System.out.println("Digite o limite da conta corrente");
					float limite = scanner.nextFloat();
					Conta contaNova = new ContaCorrente(numeroConta, numeroAgencia, tipoConta, nomeTitular,
							saldoInicial, limite);
					listaContas.add(contaNova);

				} else {

					Conta contaNova = new ContaPoupanca(numeroConta, numeroAgencia, tipoConta, nomeTitular,
							saldoInicial, (int) System.currentTimeMillis());
					listaContas.add(contaNova);


				}

				System.out.println("CONTA CRIADA!!");

				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
				for (Conta conta : listaContas) {
					System.out.println(conta);
				}
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");

				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");

				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");

				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");

				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");

				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");

				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				break;
			}
		}

	}

	public static void sobre() {

		System.out.println(Cores.TEXT_YELLOW_BOLD + "****************************************************");
		System.out.println("* Projeto Desenvolvido por:                        *");
		System.out.println("* Lucas Souza Barbosa                              *");
		System.out.println("* lucasbarbosa0217@gmail.com                       *");
		System.out.println("* github.com/lucasbarbosa0217                      *");
		System.out.println("****************************************************");

	}
}
