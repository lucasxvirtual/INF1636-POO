package Desenha;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Nickname extends JPanel {
	
	private JButton botao = new JButton("enviar");
	private JLabel label = new JLabel("escreva seu nickname: ");
	private JTextField textfield = new JTextField();
	
	public Nickname(){
		setSize(500, 500);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		this.add(label).setBounds(0, 0, 200, 40);
		this.add(textfield).setBounds(150, 0, 300, 40);
		this.add(botao).setBounds(460, 0, 100, 40);
	}

}
