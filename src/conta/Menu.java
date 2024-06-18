package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		ContaController contas = new ContaController();

		int opcao, numero = 0, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		System.out.println("\nCriar Contas\n");

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);

		contas.listarTodas();

		while (true) {

			System.out.println(Cores.TEXT_CYAN + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                  BANCO VESTIDOBANK	             ");
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

			try {
				opcao = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				scanner.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_BLACK_BACKGROUND
						+ "\nVESTIDOBANK   - O banco cianinho!");
				sobre();
				scanner.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");

				System.out.println("Digite o Numero da Agência: ");
				agencia = scanner.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				scanner.skip("\\R?");
				titular = scanner.nextLine();

				do {
					System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
					tipo = scanner.nextInt();
				} while (tipo < 1 || tipo > 2);

				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = scanner.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = scanner.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversario da Conta: ");
					aniversario = scanner.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				keyPress();
				break;

			case 2:
				System.out.println(Cores.TEXT_CYAN + Cores.ANSI_BLACK_BACKGROUND + "\n Listar todas as Contas");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(
						Cores.TEXT_CYAN + Cores.ANSI_BLACK_BACKGROUND + "\n Consultar dados da Conta - por número\n\n");

				System.out.println("Digite o número da Conta: ");

				boolean input = false;
				do {
					try {
						numero = scanner.nextInt();
						input = true;
					} catch (InputMismatchException e) {
						System.out.println("Digite valores inteiros.");
						scanner.nextLine();
					}

				} while (input == false);

				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
			    System.out.println("Atualizar dados da Conta\n\n");
			    System.out.println("Digite o número da conta: ");
			    numero = scanner.nextInt();
			    var buscaConta = contas.buscarNaCollection(numero);
			    if(buscaConta != null) {
			        tipo = buscaConta.getTipo();
			        System.out.println("Digite o Numero da Agência: ");
			        agencia = scanner.nextInt();
			        System.out.println("Digite o Nome do Titular: ");
			        scanner.skip("\\R?");
			        titular = scanner.nextLine();
			        System.out.println("Digite o Saldo da Conta (R$): ");
			        saldo = scanner.nextFloat();
			        switch (tipo) {
			            case 1 -> {
			                System.out.println("Digite o Limite de Crédito (R$): ");
			                limite = scanner.nextFloat();
			                contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
			            }
			            case 2 -> {
			                System.out.println("Digite o dia do Aniversário da Conta: ");
			                aniversario = scanner.nextInt();
			                contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
			            }
			            default -> {
			                System.out.println("Tipo de conta inválido!");
			            }
			        }
			    } else {
			        System.out.println("A Conta não foi encontrada!");
			    }
			    keyPress();
			    break;

			case 5:
			    System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
			    System.out.println("Digite o número da conta: ");
			    numero = scanner.nextInt();
			    contas.deletar(numero);
			    keyPress();
			    break;

			case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");

				System.out.println("Digite o Numero da conta: ");
				numero = scanner.nextInt();

				do {
					System.out.println("Digite o Valor do Saque (R$): ");
					valor = scanner.nextFloat();
				} while (valor <= 0);

				contas.sacar(numero, valor);

				keyPress();
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");

				System.out.println("Digite o Numero da conta: ");
				numero = scanner.nextInt();

				do {
					System.out.println("Digite o Valor do Depósito (R$): ");
					valor = scanner.nextFloat();
				} while (valor <= 0);

				contas.depositar(numero, valor);

				keyPress();
			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");

				System.out.println("Digite o Numero da Conta de Origem: ");
				numero = scanner.nextInt();
				System.out.println("Digite o Numero da Conta de Destino: ");
				numeroDestino = scanner.nextInt();

				do {
					System.out.println("Digite o Valor da Transferência (R$): ");
					valor = scanner.nextFloat();
				} while (valor <= 0);

				contas.transferir(numero, numeroDestino, valor);

				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida" + Cores.TEXT_RESET);

				keyPress();
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

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}
