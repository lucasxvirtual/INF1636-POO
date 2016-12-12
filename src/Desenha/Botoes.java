package Desenha;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;
import Jogo.Fachada;
import Jogo.Main;

public class Botoes extends JPanel implements ActionListener, ObservadorIF {
	
	private JButton RolarDados = new JButton("Rolar dados");
	private JButton SalvarJogo = new JButton("Salvar jogo");
	private JButton CarregarJogo = new JButton("Carregar jogo");
	private JLabel label = new JLabel("Jogador 1: role o dado.");
	private JLabel pri = new JLabel();
	private JLabel seg = new JLabel();
	private JLabel ter = new JLabel();
	private JLabel qua = new JLabel();
	private BufferedImage image[] = new BufferedImage[6];
	private static int valor = 0;
	private static Botoes inst = null;
	
	private Botoes(){
		RolarDados.addActionListener(this);
		SalvarJogo.addActionListener(this);
		CarregarJogo.addActionListener(this);
		
		try{
			image[0] = ImageIO.read(getClass().getResource("/Imagens/dado1.png"));
			image[1] = ImageIO.read(getClass().getResource("/Imagens/dado2.png"));
			image[2] = ImageIO.read(getClass().getResource("/Imagens/dado3.png"));
			image[3] = ImageIO.read(getClass().getResource("/Imagens/dado4.png"));
			image[4] = ImageIO.read(getClass().getResource("/Imagens/dado5.png"));
			image[5] = ImageIO.read(getClass().getResource("/Imagens/dado6.png"));
		}
		catch(Exception ex){
			System.out.println("erro ao abrir imagem, abortado");
			System.exit(1);
		}
		
		// REGISTRA O OBSERVADOR
		Fachada.Registra(this, 1);
		
	}
	
	public static Botoes getBotoes(){
		if (inst == null)
			inst = new Botoes();
		return inst;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		add(label).setBounds(0, 150, 350, 20);
		add(RolarDados).setBounds(65, 0, 200, 50);
		add(pri).setBounds(50, 200, 200, 20);
		add(seg).setBounds(50, 250, 200, 20);
		add(ter).setBounds(50, 300, 200, 20);
		add(qua).setBounds(50, 350, 200, 20);
		add(SalvarJogo).setBounds(65, 500, 200, 50);
		add(CarregarJogo).setBounds(65, 560, 200, 50);
		g.drawImage(image[valor], 140, 60, null);
			
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(Fachada.comparePlayerTime()){
			if(e.getActionCommand().equals("Rolar dados")){
				Fachada.btnClicado();
			}
			else if (e.getActionCommand().equals("Salvar jogo")){
				Fachada.salvarJogo();
			}
			else {
				Fachada.carregarJogo();
			}
		}
		repaint();
	}

	@Override
	public void notify(ObservadoIF o) {
		valor = o.get() - 1;
		repaint();
	}
	
	public void setTextLabel(String txt){
		label.setText(txt);
		//repaint();
	}
	
	public void setTextLabelColoc(String txt, int lbl){
		if(lbl == 1)
			pri.setText(txt);
		else if(lbl == 2)
			seg.setText(txt);
		else if(lbl == 3)
			ter.setText(txt);
		else 
			qua.setText(txt);
	}
	
	public void atualizaBotoes(){
		if(!Fachada.comparePlayerTime()){
			RolarDados.setEnabled(false);
			Main.carregarJogo2();
		} else {
			RolarDados.setEnabled(true);
		}
	}

}
