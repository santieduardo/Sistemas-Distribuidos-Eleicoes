package br.com.eduardosanti;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
	
	List<PrintWriter> escritores = new ArrayList<>();
	ServerSocket server;
	Socket socket;
	
	public Servidor(){
		try {
			server = new ServerSocket(5000);
			socket = server.accept();
			new Thread(new EscutaUrna(socket)).start();
			PrintWriter printer = new PrintWriter(socket.getOutputStream());
			escritores.add(printer);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	private void encaminharParaTodos(String texto){
		for (PrintWriter print : escritores){
			try {
				print.println(texto);
				print.flush();
				System.out.println("to aqui");
				System.err.println(texto);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Servidor();
	}
	
	
	
	private class EscutaUrna implements Runnable{

		Scanner leitor;
		int candidatoA, candidatoB, candidatoC, candidatoD;
		
		public EscutaUrna(Socket socket) {
			try {
				leitor = new Scanner(socket.getInputStream());
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			String votoRecebido;
			while ((votoRecebido = leitor.nextLine()) != null) {
				switch (votoRecebido) {
				case "Eneias":
					candidatoA ++;
					encaminharParaTodos("d" + candidatoA);
					System.out.println("Eneias: " + candidatoA);
					break;
				case "Lula":
					candidatoB ++;
					encaminharParaTodos("d" + candidatoA);
					System.out.println("Lula: " + candidatoB);
					break;
				case "Collor":
					candidatoC ++;
					encaminharParaTodos("d" + candidatoA);
					System.out.println("Collor: " + candidatoC);
					break;
				case "FHC":
					candidatoD ++;
					encaminharParaTodos("d" + candidatoA);
					System.out.println("FHC: " + candidatoD);
					break;
				default:
					break;
				}
				System.out.println("Recebi: " + votoRecebido);
			}
		}
		
	}

}
