package aplication;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entity.Celular;
import entity.Cidade;
import entity.Cliente;
import entity.ClienteFilme;
import entity.Endereco;
import entity.Estado;
import entity.Filme;
import model.DAO.CelularDAO;
import model.DAO.CidadeDAO;
import model.DAO.ClienteDAO;
import model.DAO.Cliente_FilmeDAO;
import model.DAO.EnderecoDAO;
import model.DAO.EstadoDAO;
import model.DAO.FabricaDAO;
import model.DAO.FilmeDAO;
import servico.AluguelFilme;
import servico.BancoCaixa;
import servico.BancoNuBank;
import servico.BancoPicPay;
import servico.ContratoDeVenda;
import servico.ParcelaFilme;
import servico.ProcessoDeVenda;

public class Program {

	private static final int CADASTRAR_CLIENTE = 1;
	private static final int CADASTRAR_FILME = 2;
	private static final int VENDA_ALUGUEL = 3;
	private static final int CONSULTAR_FILME = 4;
	private static final int CONSULTAR_CLIENTE = 5;
	private static final int CONSULTAR_UF_CID = 6;
	private static final int SAIR = 0;

	// fazer opções para consultar cidade, estado, celular, endereço e voltar ao
	// menu principal. Colocar 'delete e update' nos menus de cliente, filme, estado/cidade
	//e criar um menu de celular com inserir, pesquisar, listar todos, update e deletar

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter dmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Cidade cid = null;
		Endereco end = null;
		Estado est = null;
		Celular cel = null;

		System.out.println("--Sistema Locadora de Filmes IgaraTexas--");
		System.out.println("______________________________________________");
		int opcaoMenu = 99;
		System.out.println();

		while (opcaoMenu != SAIR) {
			System.out.println("--------Menu--------\n");
			System.out.println(" 1 - Cadastro de cliente;\n " 
					+ "2 - Cadastro de filme;\n "
					+ "3 - Venda ou Aluguel de filme;\n " 
					+ "4 - Consultar filme;\n "
					+ "5 - Consultar cliente;\n "
					+ "6 - Consultar Cidade ou Estado;\n " 
					+ "0 - Sair\n");

			System.out.print("Digite a opção desejada: ");
			opcaoMenu = sc.nextInt();
			sc.nextLine();
			switch (opcaoMenu) {
			
//------------------------------------------------------------------------------------------

			case CADASTRAR_CLIENTE: {
				System.out.println("--Cadastro de Cliente--\n");

				LocalDate data = null;
				boolean dataValida = false;

				while (!dataValida) {
					try {
						System.out.print("Insira a data de nascimento(dd/MM/yyyy): ");
						data = LocalDate.parse(sc.next(), dmt);
						sc.nextLine();

						LocalDate dataAtual = LocalDate.now();
						Period periodo = Period.between(data, dataAtual);
						int idade = periodo.getYears();

						if (idade > 17) {
							dataValida = true;
						} else {
							System.out.println(
									"Idade inapropriada! Necessita de um responsável para efetuar o cadastro!");
						}
					} catch (DateTimeParseException e) {
						System.out.println("Formato de data inválido! - " + e.getMessage());
					}
				}

					boolean validarCPF = false;
					String cpf = null;
					while (!validarCPF) {
						System.out.print("Insira o CPF: ");
						cpf = sc.nextLine();
						if (Cliente.validarCPF(cpf) == true) {
							if (cpfExistente(cpf) == true) {
								validarCPF = true;
							} else {
								System.out.println("CPF existente!");
							}
						} else {
							System.out.println("CPF inválido!");
						}
					}
				

				boolean validarNome = false;
				String nome = null;
				while (!validarNome) {
					System.out.print("Insira o nome: ");
					nome = sc.nextLine();
					if (Cliente.validacaoNome(nome) == true) {
						validarNome = true;
					}
				}

				boolean validarCelular = false;
				String celular = null;
				while (!validarCelular) {
					System.out.print("Insira o número de celular para contato: ");
					celular = sc.nextLine();
					if (Celular.validarNumero(celular) == true) {
						if (cadastrarCelular(celular) != null) {
							validarCelular = true;
						} else {
							System.out.println("Celular existente!");
						}
					}
				}

				cel = cadastrarCelular(celular);

				String estado = null;
				boolean validarUf = false;
				while (!validarUf) {
					System.out.print("Insira o UF da cidade: ");
					estado = sc.nextLine();
					if (cadastrarEstado(estado) != null) {
						validarUf = true;
					} else {
						System.out.println("Estado existente!");
					}
				}

				est = cadastrarEstado(estado);

				String cidade = null;
				String cep = null;
				boolean validarCidade = false;
				while (!validarCidade) {
					System.out.print("Insira a cidade de residência: ");
					cidade = sc.nextLine();
					System.out.print("Insira o CEP da cidade: ");
					cep = sc.nextLine();
					if (cadastrarCidade(cidade, cep, est) != null) {
						validarCidade = true;
					} else {
						System.out.println("Cidade existente!");
					}
				}
				cid = cadastrarCidade(cidade, cep, est);

				String rua = null;
				String bairro = null;
				String numero = null;
				String complemento = null;
				boolean validarEndereco = false;
				while (!validarEndereco) {
					System.out.print("--Endereço--\nInsira a rua/avenida: ");
					rua = sc.nextLine();
					System.out.print("Insira o bairro: ");
					bairro = sc.nextLine();
					System.out.print("Insira o NÚMERO do imóvel: ");
					numero = sc.nextLine();
					System.out.print("Insira o complemento(casa/apartamento): ");
					complemento = sc.nextLine();
					if (cadastrarEndereco(rua, bairro, complemento, numero, cid) != null) {
						validarEndereco = true;
					} else {
						System.out.println("Endereço existente!");
					}
				}

				end = cadastrarEndereco(rua, bairro, complemento, numero, cid);

				cadastrarCliente(cpf, nome, data, cel, end);

				break;

			}
			
//------------------------------------------------------------------------------------------
			
			case CADASTRAR_FILME: {
				System.out.println("--Cadastro de Filme--\n");

				System.out.print("Insira o nome do filme: ");
				String nomeFilme = sc.nextLine();

				System.out.print("Insira a Classificação indicativa do filme (0/10/12/16/18): ");
				Integer classificacao = sc.nextInt();
				sc.nextLine();

				System.out.print("Insira o ano de lançamento do filme: ");
				Integer anoLancamento = sc.nextInt();
				sc.nextLine();

				System.out.print("Insira o preço(Total) do filme: ");
				Double preco = sc.nextDouble();
				sc.nextLine();

				cadastrarFilme(nomeFilme, classificacao, anoLancamento, preco);

				break;
			}
			
//------------------------------------------------------------------------------------------
			
			case CONSULTAR_FILME: {
				final int CONSULTAR_FILME_POR_ID = 1;
				final int COSULTAR_FILME_POR_NOME = 2;
				final int LISTAR_FILMES = 3;
				final int VOLTAR = 4;

				int opFilme = 5;

				while (opFilme != VOLTAR) {
					System.out.println("\n--MENU--\n"
							+ "1 - Consultar filme por id;\n2 - Consultar filme por nome;\n"
							+ "3 - Listar todos os filmes;\n4 - Voltar");
					System.out.print("\nDigite a opção desejada('4' para voltar): ");
					opFilme = sc.nextInt();
					sc.nextLine();
					switch (opFilme) {
					case CONSULTAR_FILME_POR_ID: {
						System.out.print("Escreva o ID do filme: ");
						Integer id = sc.nextInt();
						sc.nextLine();
						buscarFilmePorId(id);
						break;
					}
					case COSULTAR_FILME_POR_NOME: {
						System.out.print("Escreva o nome do filme: ");
						String nome = sc.nextLine();
						buscarFilmePorNome(nome);
						break;
					}
					case LISTAR_FILMES: {
						listarTodosFilmes();
						break;
					}
					}
				}
				break;
			}
			
//------------------------------------------------------------------------------------------
			
			case CONSULTAR_CLIENTE: {
				final int CONSULTAR_CLIENTE_POR_CPF = 1;
				final int COSULTAR_CLIENTE_POR_NOME = 2;
				final int LISTAR_CLIENTES = 3;
				final int VOLTAR = 4;

				int opCliente = 5;
				
				while (opCliente != VOLTAR) {
					System.out.println("\n--MENU--\n" 
							+ "1 - Consultar cliente por CPF;\n2 - Consultar cliente por nome;\n"
							+ "3 - Listar todos os clientes;\n4 - Voltar");
					System.out.print("\nDigite a opção desejada('4' para voltar): ");
					opCliente = sc.nextInt();
					sc.nextLine();
					switch (opCliente) {
					case CONSULTAR_CLIENTE_POR_CPF : {
						System.out.print("Escreva o CPF do cliente: ");
						String cpf = sc.nextLine();
						Cliente existeCPF = buscarClientePorCPF(cpf);
						if(existeCPF != null) {
							buscarClientePorCPF(cpf);
						}else {
							System.out.println("CPF inexistente no banco de dados!");
						}
						break;
					}
					case COSULTAR_CLIENTE_POR_NOME : {
						System.out.print("Escreva o nome do cliente: ");
						String nome = sc.nextLine();
						System.out.println();
						buscarClientePorNome(nome);
						break;
					}
					case LISTAR_CLIENTES : {
						listarTodosClientes();
						break;
					}
					}	
				}
				break;
			}
			
//------------------------------------------------------------------------------------------			
			
			case VENDA_ALUGUEL : {
				
				ContratoDeVenda cdv = null;
				ProcessoDeVenda pdv  = null;
				LocalDate agora = null;
				Filme filme = null;
				
				final int COMPRAR = 1;
				final int ALUGAR = 2;
				final int VOLTAR = 3;
				
				int opCompra = 4;
				
				while(VOLTAR != opCompra) {
					System.out.println("\n--MENU--\n" 
							+ "1 - Comprar filme;\n2 - Alugar filme;\n"
							+ "3 - Voltar");
					System.out.print("\nDigite a opção desejada('3' para voltar): ");
					opCompra = sc.nextInt();
					sc.nextLine();
					
					switch(opCompra) {
					case COMPRAR : {
						System.out.print("\n---Comprar filme---\n"
								+ "Deseja parcelar?(s/n): ");
						char op = sc.next().charAt(0);
						sc.nextLine();
						String desejaParcelar = "NÃO";
						
						System.out.print("Digite o CPF do cliente: ");
						String idCliente = sc.nextLine();
						boolean validar = false;
						while(!validar) {
							if(clienteDao.clienteExistente(idCliente)) {
								validar = true;
							}else {
								System.out.print("CPF inexistente! Digite o CPF novamente: ");
								idCliente = sc.nextLine();
							}
						}
						System.out.print("Digite o ID do filme: ");
						int idFilme = sc.nextInt();
						sc.nextLine();
						
						validar = false;
						while(!validar) {
							if(filmeDao.filmeExistente(idFilme)) {
								validar = true;
							}else {
								System.out.print("Filme inexistente! Digite o filme novamente: ");
								idFilme = sc.nextInt();
							}
						}
						
						if(op == 'n' || op == 'N') {
							System.out.println("\n---Compra à vista!---\n");
							cadastrarClienteFilme(idFilme, idCliente, desejaParcelar);
						}
						
						filme = buscarFilmePorId(idFilme);
						agora = LocalDate.now();
						
						cdv = new ContratoDeVenda(filme.getPreco(), agora);
						
						if(op == 's' || op == 'S') {
							System.out.println("Escolha o cartão: \n 1 - Nubank "
									+ "\n 2 - PicPay \n 3 - Caixa");
							int op2 = sc.nextInt();
							sc.nextLine();
							System.out.print("De quantos meses deseja parcelar? ");
							int parcelas = sc.nextInt();
							sc.nextLine();
							
							switch (op2) {
							case 1 : {
								pdv = new ProcessoDeVenda(new BancoNuBank());
								pdv.processarContrato(cdv, parcelas);
								cadastrarClienteFilmeComParcela(idFilme, idCliente, cdv.getParcelas());
								
								for (ParcelaFilme p : cdv.getParcelas()) {
									System.out.println(p);
								}
								
								break;
							}
							
							case 2 : {
								pdv = new ProcessoDeVenda(new BancoPicPay());
								pdv.processarContrato(cdv, parcelas);
								cadastrarClienteFilmeComParcela(idFilme, idCliente, cdv.getParcelas());
								
								for (ParcelaFilme p : cdv.getParcelas()) {
									System.out.println(p);
								}
								
								break;
							}
							
							case 3 : {
								pdv = new ProcessoDeVenda(new BancoCaixa());
								pdv.processarContrato(cdv, parcelas);
								cadastrarClienteFilmeComParcela(idFilme, idCliente, cdv.getParcelas());
								
								for (ParcelaFilme p : cdv.getParcelas()) {
									System.out.println(p);
								}
								
								break;
							}
							
							default:
								System.out.println("Opção inválida!");
							}	
						}
						break;
					}
					case ALUGAR : {
						System.out.print("\n--Alugar filme--\n"
								+ "Insira o CPF do cliente: ");
						String idCliente = sc.nextLine();
						boolean validar = false;
						while(!validar) {
							if(clienteDao.clienteExistente(idCliente)) {
								validar = true;
							}else {
								System.out.print("CPF inexistente! Digite o CPF novamente: ");
								idCliente = sc.nextLine();
							}
						}
						
						System.out.print("Digite o ID do filme para alugar: ");
						Integer idFilme = sc.nextInt();
						sc.nextLine();
						
						validar = false;
						while(!validar) {
							if(filmeDao.filmeExistente(idFilme)) {
								validar = true;
							}else {
								System.out.print("Filme inexistente! Digite o filme novamente: ");
								idFilme = sc.nextInt();
							}
						}
						
						filme = buscarFilmePorId(idFilme);
						
						System.out.print("Por quantos dias deseja alugar?(Máximo 7 dias"
								+ ",mínimo 2 dias): ");
						int dias = sc.nextInt();
						sc.nextLine();
						
						while(dias < 2 || dias > 7) {
							System.out.println("\nEntrada de dias inválida! O aluguel deve ser "
									+ "entre dois dias e sete dias!\n");
							System.out.print("Digite novamente os dias que deseja alugar: ");
							dias = sc.nextInt();
						}
						
						agora = LocalDate.now();
						
						cdv = new ContratoDeVenda(filme.getPreco(), agora);
						pdv = new ProcessoDeVenda();
						pdv.processarAluguel(cdv, dias);
						
						double totalAluguel = 0.0;
						for(AluguelFilme a : cdv.getAluguel()) {
							totalAluguel += a.getQuantia();
							System.out.println(a);
						}
						
						cadastrarClienteFilmeAluguel(idFilme, idCliente, cdv.getAluguel());
						
						System.out.println("Total do Aluguel: " + String.format("%.2f", totalAluguel));
						
						break;
					}
					}
				}
				
//------------------------------------------------------------------------------------------			
				
			} case CONSULTAR_UF_CID : {
				
			}

			default:
				// throw new IllegalArgumentException("Entrada inesperada: " + op);
			}
		}

		sc.close();
	}

//------------------------------------------------------------------------------------------
	
	static ClienteDAO clienteDao = FabricaDAO.criarClienteDAO();
	static CelularDAO celularDao = FabricaDAO.criarCelularDAO();
	static EstadoDAO estadoDao = FabricaDAO.criarEstadoDAO();
	static CidadeDAO cidadeDao = FabricaDAO.criarCidadeDAO();
	static EnderecoDAO enderecoDao = FabricaDAO.criarEnderecoDAO();
	static FilmeDAO filmeDao = FabricaDAO.criarFilmeDAO();
	static Cliente_FilmeDAO clienteFilmeDao = FabricaDAO.criarClienteFilmeDAO();
	
	private static void cadastrarClienteFilmeAluguel(Integer Filme, String cpf, List<AluguelFilme> aluguel) {
		ClienteFilme novoClienteFilme = null;
		
		for(AluguelFilme a : aluguel) {
			novoClienteFilme = new ClienteFilme(null, cpf, Filme, a);
			clienteFilmeDao.inserirClienteComFilmeAlugado(novoClienteFilme);
		}
		System.out.println("\nAluguel adicionado! ID final = " + novoClienteFilme.getId());
	}
	
	private static void cadastrarClienteFilmeComParcela(Integer Filme, String cpf, List<ParcelaFilme> parcelas) {		
		ClienteFilme novoClienteFilme = null;
		
		 for (ParcelaFilme parcela : parcelas) {
		       novoClienteFilme = new ClienteFilme(null, cpf, Filme, parcela);
		       clienteFilmeDao.inserirClienteComFilmeComParcela(novoClienteFilme);
		    }
		
		System.out.println("\nCliente com filme adicionado! ID final = " + novoClienteFilme.getId());
	}
	
	
	private static void cadastrarClienteFilme(Integer Filme, String cpf, String desejaParcelar) {		
		ClienteFilme novoClienteFilme = new ClienteFilme(null, cpf, Filme, desejaParcelar);
		clienteFilmeDao.inserirClienteComFilme(novoClienteFilme);
		System.out.println("\nCliente com filme adicionado! Novo ID = " + novoClienteFilme.getId());
	}

	private static void cadastrarFilme(String nome, Integer classificacao, Integer ano, Double preco) {
		Filme novoFilme = new Filme(null, nome, classificacao, ano, preco);
		filmeDao.inserir(novoFilme);
		System.out.println("\nFilme inserido! Novo ID = " + novoFilme.getId());
		System.out.println(novoFilme);
	}

	private static void cadastrarCliente(String cpf, String nome, LocalDate dataNascimento, Celular celular,
			Endereco endereco) {

		Celular cel = new Celular(celular.getId(), null);
		Endereco end = new Endereco(endereco.getId(), null, null, null, null, null);

		Cliente novoCliente = new Cliente(cpf, nome, dataNascimento, end, cel);
		clienteDao.inserir(novoCliente);
		System.out.println("\nCliente inserido! Novo ID = " + novoCliente.getCPF());
		System.out.println(novoCliente);

	}

	private static boolean cpfExistente(String cpf) {
		if (clienteDao.clienteExistente(cpf) != true) { //lê-se: "se o cliente não existir (false)"
			return true; //se o cpf não existir, retorna true para cadastro de novo cpf.
		} else {
			return false; //se o cpf existir, retorna false.
		}
	}

	private static Celular cadastrarCelular(String numero) {
		Celular novoCelular = new Celular(null, numero);
		if (!celularDao.existe(novoCelular)) {
			celularDao.inserir(novoCelular);
			System.out.println("\nCelular inserido! Novo ID = " + novoCelular.getId());
			return novoCelular;
		} else {
			return celularDao.buscarCelularExistente(numero);
		}

	}

	private static Estado cadastrarEstado(String estado) {
		Estado novoEstado = new Estado(null, estado);
		if (!estadoDao.existe(novoEstado)) {
			estadoDao.inserir(novoEstado);
			System.out.println("\nEstado inserido! Novo ID = " + novoEstado.getId());
			return novoEstado;
		} else {
			return estadoDao.buscarEstadoExistente(estado);
		}
	}

	private static Cidade cadastrarCidade(String cidade, String cep, Estado uf) {
		Cidade novaCidade = new Cidade(null, cidade, cep, uf);
		if (!cidadeDao.existe(novaCidade)) {
			cidadeDao.inserir(novaCidade);
			System.out.println("\nCidade inserida! Novo ID = " + novaCidade.getId());
			return novaCidade;
		} else {
			return cidadeDao.buscarCidadeExistente(cidade, cep, uf);
		}
	}

	private static Endereco cadastrarEndereco(String rua, String bairro, String complemento, String numero,
			Cidade cidade) {
		Endereco novoEndereco = new Endereco(null, cidade, rua, bairro, numero, complemento);
		if (!enderecoDao.existe(novoEndereco)) {
			enderecoDao.inserir(novoEndereco);
			System.out.println("\nEndereço inserido! Novo ID = " + novoEndereco.getId());
			return novoEndereco;
		} else {
			return enderecoDao.buscarEnderecoExistente(rua, bairro, numero, complemento, cidade);
		}
	}

	private static Filme buscarFilmePorId(Integer id) {
		Filme filme = filmeDao.encontrarPorId(id);
		System.out.println(filme);
		return filme;
	}

	private static List<Filme> buscarFilmePorNome(String nome) {
		List<Filme> filmes = filmeDao.buscarPorNome(nome);
		if(filmes.isEmpty()) {
			System.out.println("Nenhum filme encontrado com o nome: " + nome);
		}else {
			for (Filme filme : filmes) {
				System.out.println(filme);
			}
		}
		
		return filmes;
	}

	private static List<Filme> listarTodosFilmes() {
		List<Filme> filmes = filmeDao.acharTodos();
		for (Filme filme : filmes) {
			System.out.println(filme.getNome() + " - " + "Classificação Indicativa: " + filme.getClassificacao() + " anos");
		}
		return filmes;
	}

	private static List<Cliente> buscarClientePorNome(String nome){
		List<Cliente> clientes = clienteDao.buscarPorNome(nome);
		
		if(clientes.isEmpty()) {
			System.out.println("Nenhum cliente encontrado com o nome: " + nome);
		}else {
			for (Cliente cliente : clientes) {
				System.out.println(cliente);
			}
		}
		return clientes;
	}
	
	private static Cliente buscarClientePorCPF(String cpf) {
		Cliente cliente = clienteDao.encontrarPorCPF(cpf);
		System.out.println(cliente);
		return cliente;
	}
	
	private static List<Cliente> listarTodosClientes(){
		List<Cliente> clientes = clienteDao.acharTodos();
		for (Cliente cliente : clientes) {
			System.out.println(cliente.getCPF() + " - " + cliente.getNome());
		}
		return clientes;
	}
}
