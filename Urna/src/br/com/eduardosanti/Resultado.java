package br.com.eduardosanti;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Window;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Resultado extends JFrame {

	Container envio;
	JTextArea textoRecebido;
	JScrollPane scroll;

	Socket socket;
	PrintWriter escritor;
	Scanner leitor;

	public Resultado() {
		initElementos();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setResizable(false);
		setVisible(true);
	}

	private void initElementos() {
		textoRecebido = new JTextArea();
		textoRecebido.setEditable(false);
		scroll = new JScrollPane(textoRecebido);

		envio = new JPanel();
		envio.setLayout(new BorderLayout());
		getContentPane().add(BorderLayout.CENTER, scroll);
		getContentPane().add(BorderLayout.SOUTH, envio);
		configRede();
	}

	private void configRede() {
		try {
			socket = new Socket("127.0.0.1", 5000);
			escritor = new PrintWriter(socket.getOutputStream());
			leitor = new Scanner(socket.getInputStream()); // captura as informações do server
			new Thread(new EscutaServidor()).start(); // cria thread para ouvir(private classEscutaServidor) o server
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Resultado();
	}

	private class EscutaServidor implements Runnable {
		
		@Override
		public void run() {
			try {
				String texto;
				while ((texto = leitor.nextLine()) != null) {
					textoRecebido.append(texto + "\n");
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

	}

}
