package Desenha;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Jogo.Main;

public class Nickname extends JPanel implements ActionListener {
	
	private JButton botao = new JButton("enviar");
	private JLabel label = new JLabel("escreva seu nickname: ");
	private JTextField textfield = new JTextField();
	private JLabel label2 = new JLabel("esperando pelos outros jogadores");
	private boolean alreadySent = false;
	
	public Nickname(){
		setSize(500, 500);
		botao.addActionListener(this);
		label2.setVisible(false);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		this.add(label).setBounds(0, 0, 200, 40);
		this.add(textfield).setBounds(150, 0, 300, 40);
		this.add(botao).setBounds(460, 0, 100, 40);
		this.add(label2).setBounds(0, 100, 200, 40);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(this.alreadySent == false){
			String s = textfield.getText();
			Main.inicializaConexao(s);
			alreadySent = true;
		}
		
	}
	
	public void setLabelVisibility(boolean visible){
		label2.setVisible(visible);
	}
}
