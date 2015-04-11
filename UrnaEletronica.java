package br.com.eduardosanti;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class UrnaEletronica extends JFrame {
	
	private JPanel candidatos;
	private ButtonGroup grupoCandidatos;
	private JRadioButton candidatoA, candidatoB, candidatoC, candidatoD;
	private JButton botaoEnviar;
	
	
	public UrnaEletronica(String nome) {
		setTitle(nome);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 80);
		iniciaComponentes();
	}
	
	private void iniciaComponentes() {
		candidatos = new JPanel();
		grupoCandidatos = new ButtonGroup();
		candidatoA = new JRadioButton("Eneias");
		candidatoB = new JRadioButton("Eduardo Campos");
		candidatoC = new JRadioButton("Collor");
		candidatoD = new JRadioButton("Roberto Jeffeson");
		botaoEnviar = new JButton("VOTAR");
		botaoEnviar.setName("votar");

		grupoCandidatos.add(candidatoA);
		grupoCandidatos.add(candidatoB);
		grupoCandidatos.add(candidatoC);
		grupoCandidatos.add(candidatoD);
		candidatos.add(candidatoA);
		candidatos.add(candidatoB);
		candidatos.add(candidatoC);
		candidatos.add(candidatoD);
		candidatos.add(botaoEnviar, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JButton) {
					String nomeBotao = ((JButton) e.getSource()).getName();
					JOptionPane.showMessageDialog(null, nomeBotao);
				}
				 
			}
		});
		getContentPane().add(BorderLayout.NORTH, candidatos);
		setVisible(true);
	}

	public static void main(String[] args) {
		new UrnaEletronica("Sessão Eleitoral");
	}
	
}
