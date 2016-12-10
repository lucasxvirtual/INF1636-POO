package Desenha;

import javax.swing.JFrame;

public class TelaNickname extends JFrame {
	
	private Nickname nickname;
	
	public TelaNickname(){
		super("SUPER LUDO");
		nickname = new Nickname();
		setVisible(true);
		setSize(600, 300);
		getContentPane().add(nickname).setBounds(0, 0, 590, 290);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void visible(){
		nickname.setLabelVisibility(true);
	}

}
