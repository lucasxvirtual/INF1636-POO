package Jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;

public class Peca implements ObservadoIF{
	
	private int jogador;
	private int posicao;
	private int numPeca;
	private int numCasasAndadas;
	protected List<ObservadorIF> lst = new ArrayList<ObservadorIF>();
	private static Peca inst[] = null;
	
	private Peca(int jogador, int posicao, int numPeca, int numCasasAndadas){
		this.jogador = jogador;
		this.posicao = posicao;
		this.numPeca = numPeca;
		this.numCasasAndadas = numCasasAndadas;
	}
	
	public static Peca[] getPeca(){
		if (inst == null){
			inst = new Peca[16];
			inst[0] = new Peca(1, 0, 1, 0);
			inst[1] = new Peca(1, 0, 2, 0);
			inst[2] = new Peca(1, 0, 3, 0);
			inst[3] = new Peca(1, 0, 4, 0);
			inst[4] = new Peca(2, 0, 1, 0);
			inst[5] = new Peca(2, 0, 2, 0);
			inst[6] = new Peca(2, 0, 3, 0);
			inst[7] = new Peca(2, 0, 4, 0);
			inst[8] = new Peca(3, 0, 1, 0);
			inst[9] = new Peca(3, 0, 2, 0);
			inst[10] = new Peca(3, 0, 3, 0);
			inst[11] = new Peca(3, 0, 4, 0);
			inst[12] = new Peca(4, 0, 1, 0);
			inst[13] = new Peca(4, 0, 2, 0);
			inst[14] = new Peca(4, 0, 3, 0);
			inst[15] = new Peca(4, 0, 4, 0);
		}
		return inst;
	}

	public int getJogador(){
		return jogador;
	}
	
	public void setPosicao(int posicao){

		this.posicao = posicao;
		ListIterator<ObservadorIF> li = lst.listIterator();
		while(li.hasNext())
			li.next().notify(this);
	}
	
	public int get(){
		return posicao;
	}
	
	public void setNumPeca(int numPeca){
		this.numPeca = numPeca;
	}
	
	public int getNumPeca(){
		return numPeca;
	}
	
	public void setCasasAndadas(int numCasasAndadas){
		this.numCasasAndadas = numCasasAndadas;
	}
	
	public int getCasasAndadas(){
		return numCasasAndadas;
	}

	@Override
	public void add(ObservadorIF o) {
		// TODO Auto-generated method stub
		lst.add(o);
	}

}
