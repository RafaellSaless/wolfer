package wolfer;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Game sistema = new Game();

	
		System.out.println("------------------------");
		System.out.println("Bem vindo ao Game wolfer");
		/*
		System.out.print("Quantas pessoas vão jogar? ");
		int quantplayers = sc.nextInt();
		
		
		sc.nextLine();
		for(int i = 0; i < quantplayers; i++) {
			System.out.print("Digite o nome do player: ");
			String nomeAdicionar = sc.nextLine();
			sistema.adicionarPlayer(nomeAdicionar);
			System.out.println();
		}*/
		
		
		sistema.escolherWolfer();
		System.out.println("------------------------");
		sistema.addLogs("------------------------");
		
		while(sistema.continuarPartida() != false) {
			sistema.noite();
			sistema.dia();
			sistema.continuarPartida();
		}
		sistema.mostrarLogs();
		sc.close();

	}

}
