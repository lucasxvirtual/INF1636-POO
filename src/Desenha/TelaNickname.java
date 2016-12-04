package Desenha;

import javax.swing.JFrame;

public class TelaNickname extends JFrame {
	
	public TelaNickname(){
		super("SUPER LUDO");
		setVisible(true);
		setSize(600, 300);
		getContentPane().add(new Nickname()).setBounds(0, 0, 590, 290);
	}

}
