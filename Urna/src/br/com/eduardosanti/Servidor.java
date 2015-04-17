package br.com.eduardosanti;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {

	List<Socket> sockets = new ArrayList<>();
	ServerSocket server;

	public Servidor() {

	}

	public void connect() throws IOException {
		server = new ServerSocket(5000);

		while (true) {
			try {

				Socket socket = server.accept();
				sockets.add(socket);

				new Thread(new EscutaUrna(socket)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void encaminharParaTodos(String texto) {
		for (Socket socket : sockets) {
			try {
				PrintWriter print = new PrintWriter(socket.getOutputStream());
				print.println(texto);
				print.flush();
				System.err.println(texto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Servidor server = new Servidor();
		server.connect();
	}

	private class EscutaUrna implements Runnable {

		Scanner leitor;
		int candidatoA, candidatoB, candidatoC, candidatoD;

		public EscutaUrna(Socket socket) {
			try {
				leitor = new Scanner(socket.getInputStream());
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String votoRecebido;
			while ((votoRecebido = leitor.nextLine()) != null) {
				switch (votoRecebido) {
				case "Eneias":
					candidatoA++;
					encaminharParaTodos("Eneias: " + candidatoA + " voto(s)");
					System.out.println("Eneias: " + candidatoA);
					break;
				case "Lula":
					candidatoB++;
					encaminharParaTodos("Lula: " + candidatoB + " voto(s)");
					System.out.println("Lula: " + candidatoB);
					break;
				case "Collor":
					candidatoC++;
					encaminharParaTodos("Collor: " + candidatoC + " voto(s)");
					System.out.println("Collor: " + candidatoC);
					break;
				case "FHC":
					candidatoD++;
					encaminharParaTodos("FHC: " + candidatoD + " voto(s)");
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
