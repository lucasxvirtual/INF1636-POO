package Estrutura;

import Jogo.Peca;


public abstract class TabFin {
	
	protected Lista lista = new Lista();
	
	public void insere(Peca x, int casa){
		lista.posicionaCorr(casa);
		
		lista.insCorr(x);
		
		x.setPosicao(casa);
		
	}
	
	public Peca retira(int casa, int num){
		
		lista.posicionaCorr(casa);
		return lista.getElemCorr(num);
		
	}
	
	public int numPecasCasa(int casa){
		
		lista.posicionaCorr(casa);
		return lista.numPecaNaCasa();
	}
	
	public abstract void movimenta(int numpeca, int origem, int dest);

}
