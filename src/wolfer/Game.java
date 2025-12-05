package wolfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import wolfer.enums_profissoes.Classes;

public class Game {
	private List<Players> jogadores = new ArrayList<>();
	private List<Players> mortos = new ArrayList<>();
	private List<String> log = new ArrayList<>();

	Scanner sc = new Scanner(System.in);
	
	public void addLogs(String logs) {
		log.add(logs);
	}
	
	public void mostrarLogs() {
		for(String x: log) {
			System.out.println(x);
		}
	}
	
	public void adicionarPlayer(String nome) {
		Players p = new Players(nome, Classes.CIDADAO, 1);
		jogadores.add(p);
	}
	
	public void ListarPlayers() {
		for(Players p: jogadores) {
			System.out.println(p.getName());
		}
	}
	
	public void escolherWolfer() {
	    int sorteado = (int)(Math.random() * jogadores.size());
	    Players wolfer = jogadores.get(sorteado);
	    wolfer.setRole(Classes.WOLFER);
	    System.out.println("Wolfer selecionado secretamente!");
	}

	
	public Players encontrarPlayer() {
		boolean playerencontrado = false;
		while(playerencontrado != true) {
			System.out.print("Digite o nome do jogador: ");
			String nomeAchar = sc.nextLine().trim();
			
			for(Players p: jogadores) {
				if(p.getName().equalsIgnoreCase(nomeAchar)) {
					
					if(mortos.stream().anyMatch(x -> x.getName().equalsIgnoreCase(nomeAchar))) {
						System.out.println("Jogador morto, tente novamente!");
						continue;
						}
					
					playerencontrado = true;
					return p;
				}
			}
		System.out.println("Nome não encontado, tente novamente!");
		}
		return null;
	}
	
	public void linchar(Players p) {
		p.setStatus(0);
		mortos.add(p);
		jogadores.remove(p);
		log.add("A cidade linchou: " + p.getName());
	}
	
	public void acaoWolfer(Players p) {
		p.setStatus(0);
		mortos.add(p);
		jogadores.remove(p);
		log.add("Wolfer matou: " + p.getName());
	}
	
	public void iniciarVotacao() {
		System.out.println("----- Chegamos ao fim da noite -----");
		System.out.print("Digite o nome do jogador ser linchado nesta partida: ");
		Players playerLinchar = encontrarPlayer();
		System.out.println(playerLinchar.getName() + " foi linchado!");
		
		playerLinchar.setStatus(0);
		mortos.add(playerLinchar);
		jogadores.remove(playerLinchar);
		log.add("A cidade linchou: " + playerLinchar.getName());
	
	}
	
	public void resumoPartida() {
	}
	
	public void espacos() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}
	
	public Boolean continuarPartida() {
		boolean temWolfer = true;
		for(Players p: jogadores) {
			if(p.getRole() == Classes.WOLFER) {
				if(jogadores.size() >= 3) {
					return true;
				} else if(jogadores.size() <=2 ) {
					if(p.getRole() == Classes.CIDADAO) {
						acaoWolfer(p);
						log.add("O wolfer ficou sozinho com " + p.getName() + ".... infelizmente já sabemos o que aconteceu" );
					}
					log.add("O wolfer matou todo mundo...");
					return false;
					}
				}
			}
		if(temWolfer == false) {
			log.add("O wolfer morreu, a cidade venceu!!");
			return false;
		}
		return true;
	}
	
	public void noite() {
		Players matar = null;
		for(Players p: jogadores) {
			if(p.getStatus() == 1) {
				if(p.getRole() == Classes.WOLFER) {
					System.out.println("Jogador: "+ p.getName() + " Deve realizar a ação, realize a ação abaixo;!");
					System.out.println("");
					System.out.print("Digite qualquer palavra: ");
					sc.nextLine();
					System.out.println("Você é o wolfer, agora você vai digitar o nome da vitima: ");
					matar = encontrarPlayer();
					espacos();
					
				} else if(p.getRole() == Classes.CIDADAO) {
					System.out.println("Jogador: "+ p.getName() + " Deve realizar a ação, realiza a ação abaixo;!");
					System.out.println("");
					System.out.print("Digite qualquer palavra: ");
					sc.nextLine();
					System.out.print("Digite outra  palavra: ");
					sc.nextLine();
					espacos();
					}
			}	
		}
		espacos();
		if(matar !=null) {
			acaoWolfer(matar);
		}
		
	}
	public void dia() {
		System.out.println("Jogadores mortos: ");
		for(Players p: mortos) {
				System.out.println("- "+p.getName());
		}
		System.out.println();
		iniciarVotacao();
		}
	}
