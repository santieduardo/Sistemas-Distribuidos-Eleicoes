package br.com.eduardosanti;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Urna extends JFrame {

	private JPanel candidatos;
	private ButtonGroup grupoCandidatos;
	private JRadioButton candidatoA, candidatoB, candidatoC, candidatoD;
	private JButton botaoEnviar;
	
	Socket socket;
	PrintWriter escritor;

	public Urna(String nome) {
		setTitle(nome);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 70);
		iniciaComponentes();
		setResizable(false);
		setVisible(true);
	}
	
	private void configRede(){
		try {
			socket = new Socket("127.0.0.1", 5000);
			escritor = new PrintWriter(socket.getOutputStream());
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	private void iniciaComponentes() {
		candidatos = new JPanel();
		grupoCandidatos = new ButtonGroup();
		candidatoA = new JRadioButton("Eneias");
		candidatoB = new JRadioButton("Lula");
		candidatoC = new JRadioButton("Collor");
		candidatoD = new JRadioButton("FHC");
		botaoEnviar = new JButton("VOTAR");
		botaoEnviar.setName("votar");
		botaoEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JButton) {
					String nomeBotao = ((JButton) e.getSource()).getName();

					if (nomeBotao.equals("votar")) {
						if (candidatoA.isSelected()) {
							escritor.println(candidatoA.getText());
							escritor.flush();
							candidatoA.setSelected(false);
						}
						else if (candidatoB.isSelected()) {
							escritor.println(candidatoB.getText());
							escritor.flush();
							candidatoB.setSelected(false);
						}
						else if (candidatoC.isSelected()) {
							escritor.println(candidatoC.getText());
							escritor.flush();
							candidatoC.setSelected(false);
						}
						else if(candidatoD.isSelected()){
							escritor.println(candidatoD.getText());
							escritor.flush();
							candidatoD.setSelected(false);
						}else {
							JOptionPane.showMessageDialog(null, "Você deve selecionar um ladrão");
						}
					}
				}

			}
		});

		grupoCandidatos.add(candidatoA);
		grupoCandidatos.add(candidatoB);
		grupoCandidatos.add(candidatoC);
		grupoCandidatos.add(candidatoD);
		candidatos.add(candidatoA);
		candidatos.add(candidatoB);
		candidatos.add(candidatoC);
		candidatos.add(candidatoD);
		candidatos.add(botaoEnviar);
		getContentPane().add(BorderLayout.NORTH, candidatos);
		
		configRede();
	}

	public static void main(String[] args) {
		new Urna("Sessão Eleitoral");
	}

}
