package Desenha;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JPanel;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;
import Jogo.Fachada;
import Jogo.Main;
import Jogo.Peca;

public class DesenhaTab extends JPanel implements ObservadorIF, MouseListener, ObservadoIF{
	
	private Graphics2D g2d;
	private static DesenhaTab inst = null;
	private Peca[] pecas = Peca.getPeca();
	private int coordx[] = new int[16];
	private int coordy[] = new int[16];
	private int ultCasaClicada;
	private List<ObservadorIF> lst = new ArrayList<ObservadorIF>();
	private int numPeca = 0;
	
	//construtor que define o tamanho do panel que contem o tabuleiro com a moldura
	private DesenhaTab(){
		setSize(650, 650);
		Fachada.Registra(this, 2);
		addMouseListener(this);
	}
	
	public static DesenhaTab getTabuleiro(){
		if(inst == null)
			inst = new DesenhaTab();
		return inst;
	}
	
	//pinta o tabuleiro na tela chamando os metodos privados 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		
		//desenha molduras
		DesenhaMolduras();
		
		//desenha casas
		DesenhaCasas();	
		
		//desenha quadrados grandes
		DesenhaQuadradosGrandes();
		
		//desenha triangulos
		DesenhaTriangulos();
		
		//desenha circulos
		DesenhaCirculos();
		
		
		DesenhaPino(pecas[0].get(), pecas[0].getJogador(), pecas[0].getNumPeca());
		DesenhaPino(pecas[1].get(), pecas[1].getJogador(), pecas[1].getNumPeca());
		DesenhaPino(pecas[2].get(), pecas[2].getJogador(), pecas[2].getNumPeca());
		DesenhaPino(pecas[3].get(), pecas[3].getJogador(), pecas[3].getNumPeca());
		DesenhaPino(pecas[4].get(), pecas[4].getJogador(), pecas[4].getNumPeca());
		DesenhaPino(pecas[5].get(), pecas[5].getJogador(), pecas[5].getNumPeca());
		DesenhaPino(pecas[6].get(), pecas[6].getJogador(), pecas[6].getNumPeca());
		DesenhaPino(pecas[7].get(), pecas[7].getJogador(), pecas[7].getNumPeca());
		DesenhaPino(pecas[8].get(), pecas[8].getJogador(), pecas[8].getNumPeca());
		DesenhaPino(pecas[9].get(), pecas[9].getJogador(), pecas[9].getNumPeca());
		DesenhaPino(pecas[10].get(), pecas[10].getJogador(), pecas[10].getNumPeca());
		DesenhaPino(pecas[11].get(), pecas[11].getJogador(), pecas[11].getNumPeca());
		DesenhaPino(pecas[12].get(), pecas[12].getJogador(), pecas[12].getNumPeca());
		DesenhaPino(pecas[13].get(), pecas[13].getJogador(), pecas[13].getNumPeca());
		DesenhaPino(pecas[14].get(), pecas[14].getJogador(), pecas[14].getNumPeca());
		DesenhaPino(pecas[15].get(), pecas[15].getJogador(), pecas[15].getNumPeca());
	
	}
	
	private void DesenhaMolduras(){
		
		Rectangle2D MolduraExt = new Rectangle2D.Double(0,0,640,640);
		g2d.setPaint(Color.gray);
		g2d.fill(MolduraExt);
		Rectangle2D MolduraInt = new Rectangle2D.Double(20,20,600,600);
		g2d.setPaint(Color.white);
		g2d.fill(MolduraInt);
		g2d.setPaint(Color.black);
		g2d.draw(MolduraExt);
		
	}
	
	private void DesenhaQuadradosGrandes(){
		
		Rectangle2D RetanguloSupEsq = new Rectangle2D.Double(20,20,240,240);
		g2d.setColor(Color.red);
		g2d.fill(RetanguloSupEsq);
		Rectangle2D RetanguloSupDir = new Rectangle2D.Double(380,20,240,240);
		g2d.setColor(Color.green);
		g2d.fill(RetanguloSupDir);
		Rectangle2D RetanguloInfEsq = new Rectangle2D.Double(20,380,240,240);
		g2d.setColor(Color.blue);
		g2d.fill(RetanguloInfEsq);
		Rectangle2D RetanguloInfDir = new Rectangle2D.Double(380,380,240,240);
		g2d.setColor(Color.yellow);
		g2d.fill(RetanguloInfDir);
		
		g2d.setColor(Color.black);
		g2d.draw(RetanguloSupEsq);
		g2d.draw(RetanguloInfEsq);
		g2d.draw(RetanguloSupDir);
		g2d.draw(RetanguloInfDir);
		
	}
	
	private void DesenhaCasas(){
		int i;
		int j;
		int coordx = 260;
		int valorj = 3;
		
		
		
		for(i = 0; i < 15; i++){
			if (i > 5 && i < 9){
				coordx = 20;
				valorj = 12;
			}
			else{
				coordx = 260;
				valorj = 3;
			}
			for(j = 0; j < valorj; j++){
				if(j>5)
					coordx = 140;
				
				if(i == 1 && j== 0){
					g2d.setColor(Color.black);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
				}
				if(i == 1 && j== 2){
					g2d.setColor(Color.green);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
				}	
				if(i == 13 && j== 0){
					g2d.setColor(Color.blue);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
				}
				if(i == 13 && j== 2){
					g2d.setColor(Color.black);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
				}
				if (i>0 && i<6 && coordx + 40*j == 300){
					g2d.setColor(Color.green);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
				}
				if(i==6 && coordx + 40*j == 60){
					g2d.setColor(Color.red);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
					g2d.setColor(Color.black);
					g2d.fill(new Rectangle2D.Double(540,20+40*i, 40, 40));
				}
				if(i==8 && coordx + 40*j == 540){
					g2d.setColor(Color.yellow);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
					g2d.setColor(Color.black);
					g2d.fill(new Rectangle2D.Double(60,20+40*i, 40, 40));
				}
				if (i>8 && i<14 && coordx + 40*j == 300){
					g2d.setColor(Color.blue);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
				}
				if (i==7 && coordx + 40*j>=60 && coordx + 40*j<=320){
					g2d.setColor(Color.red);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
				}
				if (i==7 && coordx + 40*j>=350 && coordx + 40*j <= 550){
					g2d.setColor(Color.yellow);
					g2d.fill(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
				}
					
				g2d.setColor(Color.black);			
				g2d.draw(new Rectangle2D.Double(coordx + 40*j,20+40*i, 40, 40));
				
			}
		}
	}
	
	private void DesenhaTriangulos(){
		
		g2d.setColor(Color.red);
		g2d.fillPolygon(new int[] {260,260,320}, new int[] {260,380,320}, 3);
		g2d.setColor(Color.yellow);
		g2d.fillPolygon(new int[] {380,380,320}, new int[] {260,380,320}, 3);
		g2d.setColor(Color.green);
		g2d.fillPolygon(new int[] {260,380,320}, new int[] {260,260,320}, 3);
		g2d.setColor(Color.blue);
		g2d.fillPolygon(new int[] {260,380,320}, new int[] {380,380,320}, 3);
		g2d.setColor(Color.white);
		g2d.fillPolygon(new int[] {345,375,360}, new int[] {65,65,95}, 3);
		g2d.fillPolygon(new int[] {65,65,95}, new int[] {265,295,280}, 3);
		g2d.fillPolygon(new int[] {265,295,280}, new int[] {575,575,545}, 3);
		g2d.fillPolygon(new int[] {575,575,545}, new int[] {345,375,360}, 3);
		
		g2d.setColor(Color.black);
		g2d.drawPolygon(new int[] {260,260,320}, new int[] {260,380,320}, 3);
		g2d.drawPolygon(new int[] {380,380,320}, new int[] {260,380,320}, 3);
		g2d.drawPolygon(new int[] {260,380,320}, new int[] {260,260,320}, 3);
		g2d.drawPolygon(new int[] {260,380,320}, new int[] {380,380,320}, 3);
		g2d.drawPolygon(new int[] {345,375,360}, new int[] {65,65,95}, 3);
		g2d.drawPolygon(new int[] {65,65,95}, new int[] {265,295,280}, 3);
		g2d.drawPolygon(new int[] {265,295,280}, new int[] {575,575,545}, 3);
		g2d.drawPolygon(new int[] {575,575,545}, new int[] {345,375,360}, 3);
		
	}
	
	private void DesenhaCirculos(){
		
		g2d.setColor(Color.white);
		g2d.fill(new Ellipse2D.Double(65, 65, 30, 30));
		g2d.fill(new Ellipse2D.Double(185, 65, 30, 30));
		g2d.fill(new Ellipse2D.Double(65, 185, 30, 30));
		g2d.fill(new Ellipse2D.Double(185, 185, 30, 30));
		
		g2d.fill(new Ellipse2D.Double(425, 65, 30, 30));
		g2d.fill(new Ellipse2D.Double(545, 65, 30, 30));
		g2d.fill(new Ellipse2D.Double(425, 185, 30, 30));
		g2d.fill(new Ellipse2D.Double(545, 185, 30, 30));
		
		g2d.fill(new Ellipse2D.Double(65, 425, 30, 30));
		g2d.fill(new Ellipse2D.Double(185, 545, 30, 30));
		g2d.fill(new Ellipse2D.Double(185, 425, 30, 30));
		g2d.fill(new Ellipse2D.Double(65, 545, 30, 30));
		
		g2d.fill(new Ellipse2D.Double(425, 425, 30, 30));
		g2d.fill(new Ellipse2D.Double(425, 545, 30, 30));
		g2d.fill(new Ellipse2D.Double(545, 425, 30, 30));
		g2d.fill(new Ellipse2D.Double(545, 545, 30, 30));
		
		g2d.setColor(Color.black);
		g2d.draw(new Ellipse2D.Double(65, 65, 30, 30));
		g2d.draw(new Ellipse2D.Double(185, 65, 30, 30));
		g2d.draw(new Ellipse2D.Double(65, 185, 30, 30));
		g2d.draw(new Ellipse2D.Double(185, 185, 30, 30));
		
		g2d.draw(new Ellipse2D.Double(425, 65, 30, 30));
		g2d.draw(new Ellipse2D.Double(545, 65, 30, 30));
		g2d.draw(new Ellipse2D.Double(425, 185, 30, 30));
		g2d.draw(new Ellipse2D.Double(545, 185, 30, 30));
		
		g2d.draw(new Ellipse2D.Double(65, 425, 30, 30));
		g2d.draw(new Ellipse2D.Double(185, 545, 30, 30));
		g2d.draw(new Ellipse2D.Double(185, 425, 30, 30));
		g2d.draw(new Ellipse2D.Double(65, 545, 30, 30));
		
		g2d.draw(new Ellipse2D.Double(425, 425, 30, 30));
		g2d.draw(new Ellipse2D.Double(425, 545, 30, 30));
		g2d.draw(new Ellipse2D.Double(545, 425, 30, 30));
		g2d.draw(new Ellipse2D.Double(545, 545, 30, 30));
		
		
	}
	
	private void DesenhaPino(int posicao, int jogador, int peca){
		
		int x = 0, y = 0;
		Color cor;
		
		if (jogador == 1)
			cor = Color.red;
				
		else if(jogador == 2)
			cor = Color.green;
		
		else if (jogador == 3)
			cor = Color.yellow;
		
		else  
			cor = Color.blue;
		
		if(posicao == 0){
			if (jogador == 1){
				if(peca == 1){
					x = 65;
					y = 65;
				}
				else if(peca == 2){
					x = 185;
					y = 65;
				}
				else if(peca == 3){
					x = 65;
					y = 185;
				}
				else {
					x = 185;
					y = 185;
				}
			}
			else if(jogador == 2){
				if(peca == 1){
					x = 425;
					y = 65;
				}
				else if(peca == 2){
					x = 545;
					y = 65;
				}
				else if(peca == 3){
					x = 425;
					y = 185;
				}
				else {
					x = 545;
					y = 185;
				}
			}
			else if (jogador == 3){
				if(peca == 1){
					x = 425;
					y = 425;
				}
				else if(peca == 2){
					x = 545;
					y = 425;
				}
				else if(peca == 3){
					x = 425;
					y = 545;
				}
				else {
					x = 545;
					y = 545;
				}
			}
			else {
				if(peca == 1){
					x = 65;
					y = 425;
				}
				else if(peca == 2){
					x = 185;
					y = 425;
				}
				else if(peca == 3){
					x = 65;
					y = 545;
				}
				else {
					x = 185;
					y = 545;
				}
			}
		}
		
		if(posicao >= 1 && posicao <= 5){
			x = 25 + 40*posicao;
			y = 265;
		}
		else if(posicao >= 6 && posicao <= 11){
			x = 265;
			y = 25 + 40*Math.abs(11-posicao);
		}
		else if(posicao >= 12 && posicao <= 13){
			x = 305 + 40*(posicao-12);
			y = 25;
		}
		else if(posicao >= 14 && posicao <= 18){
			x = 345;
			y = 65 + 40*(posicao - 14);
		}
		else if(posicao >= 19 && posicao <= 24){
			x = 385 + 40*(posicao - 19);
			y = 265;
		}
		else if(posicao >= 25 && posicao <= 26){
			x = 585;
			y = 305 + 40*(posicao - 25);
		}
		else if(posicao >= 27 && posicao <= 31){
			x = 545 - 40*Math.abs(posicao - 27);
			y = 345;
		}
		else if(posicao >= 32 && posicao <= 37){
			x = 345;
			y = 385 + 40*(posicao - 32);
		}
		else if(posicao >= 38 && posicao <= 39){
			x= 305 - 40*(posicao - 38); 
			y = 585;
		}
		else if(posicao >= 40 && posicao <= 44){
			x = 265;
			y = 545 - 40*Math.abs(posicao - 40);
		}
		else if(posicao >= 45 && posicao <= 50){	
			x = 225 - 40*(posicao - 45);
			y = 345;
		}
		else if(posicao >= 51 && posicao <= 52){
			x = 25;
			y = 305 - 40*(posicao - 51); 
		}
		else if(posicao < 0){
			if(jogador == 1){
				x = 25 - 40*posicao;
				y = 305;
			}
			else if(jogador == 2){
				x = 305;
				y = 25 - 40*posicao;
			}
			else if(jogador == 3){
				x = 585 + 40*posicao;
				y = 305;
			}
			else {
				x = 305;
				y = 585 + 40*posicao;
			}
		}
		
		coordx[(jogador - 1) * 4 + (peca-1)] = x;
		coordy[(jogador - 1) * 4 + (peca-1)] = y;
		
		Ellipse2D pino1 = new Ellipse2D.Double(x, y, 30, 30);
		g2d.setColor(Color.darkGray);
		g2d.fill(pino1);
		g2d.setColor(cor);
		g2d.fill(new Ellipse2D.Double(x + 5, y + 5, 20, 20));
		g2d.setColor(Color.black);
		g2d.draw(pino1);
	
	}

	@Override
	public void notify(ObservadoIF o) {
		// TODO Auto-generated method stub
		repaint();
		//Botoes.getBotoes().repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 16; i++)
			if(arg0.getX() > coordx[i] && arg0.getX() < coordx[i] + 40 && arg0.getY() > coordy[i] && arg0.getY() < coordy[i] + 40){
				if(pecas[i].getJogador() == Main.getJogador()){
					ultCasaClicada = pecas[i].get();
					numPeca = pecas[i].getNumPeca();
					ListIterator<ObservadorIF> li = lst.listIterator();
					while(li.hasNext())
						li.next().notify(this);
				
				}
			}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(ObservadorIF o) {
		// TODO Auto-generated method stub
		lst.add(o);	
	}

	@Override
	public int get() {
		// TODO Auto-generated method stub
		return ultCasaClicada;
	}
	
	public int getNumPeca(){
		return numPeca;
	}

}
