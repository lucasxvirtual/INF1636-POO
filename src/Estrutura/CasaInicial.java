package Estrutura;
import Jogo.Peca;
public class CasaInicial {
	
	private Peca[] CasasIniciais = new Peca[4];
	private int numPecasCasa;
	
	private static CasaInicial[] inst = new CasaInicial[4];
	
	private CasaInicial(int jogador){
		numPecasCasa = 0;
	}
	
	public static CasaInicial getCasaInicial(int jogador){
		if(inst[jogador-1] == null){
			inst[jogador-1] = new CasaInicial(jogador);
		}
		return inst[jogador - 1];
	}
	
	public int getNumPecasJogador(){
		return numPecasCasa;
	}
	
	public void colocaPecaJogador(Peca x){
		CasasIniciais[x.getNumPeca() - 1] = x;
		numPecasCasa++;
		x.setPosicao(0);
	}
	
	public Peca retiraPecasJogador(int numPeca){
		Peca x = CasasIniciais[numPeca-1];
		CasasIniciais[numPeca-1] = null;
		numPecasCasa--;
		return x;
	}

}
