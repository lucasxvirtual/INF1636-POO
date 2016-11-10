package Estrutura;

import Jogo.Peca;

public class PecasParaFinalizar extends TabFin{
	
	private Peca[] finalizadas = new Peca[4];
	private int numFinalizadas = 0;
	private static PecasParaFinalizar inst[] = new PecasParaFinalizar[4];
	
	private PecasParaFinalizar(){
		
		for(int i = 0; i < 5; i++)
			lista.insNo();
		
	}
	
	public static PecasParaFinalizar getPecasParaFinalizar(int i){
		if(inst[i] == null)
			inst[i] = new PecasParaFinalizar();
		return inst[i];
	}

	@Override
	public void movimenta(int numpeca, int origem, int dest) {
		// TODO Auto-generated method stub
		Peca x = retira(origem, numpeca);
		if(dest > 5){
			finalizadas[x.getNumPeca() - 1] = x;
			x.setPosicao(-6);
			numFinalizadas++;
		}
		else{
			insere(x, dest);
			x.setPosicao(-dest);
		}
		
	}
	
	public void insere(Peca x, int casa){
		if (casa >= 6){
			finalizadas[x.getNumPeca() - 1] = x;
			x.setPosicao(-6);
			numFinalizadas++;
		}
		else{
			lista.posicionaCorr(casa);
			lista.insCorr(x);
			x.setPosicao(-casa);
		}
		
	}
	
	public int numPecasCasa(int casa){
		if(casa == 6){
			return numFinalizadas;
		}
		lista.posicionaCorr(casa);
		return lista.numPecaNaCasa();
	}

}
