package Desenha;

import javax.swing.JFrame;

public class Tela extends JFrame{
	
	public Tela(){
		super("SUPER LUDO");
		setVisible(true);
		setSize(1024, 768);
		getContentPane().add(Botoes.getBotoes()).setBounds(660, 0, 350, 650);
		getContentPane().add(DesenhaTab.getTabuleiro()).setBounds(0, 0, 650, 650);
		
	}

}
