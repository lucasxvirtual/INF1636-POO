package Estrutura;
import Jogo.Peca;
public class Lista {

	private int tam=0;
	private No ini=null;
	private No fin=null;
	private No corr=null;

	public boolean vazio(){
		if (tam == 0)
			return true;
		return false;
	}

	public boolean insNo(){
		No novo = new No(ini);
		ini = novo;
		corr = ini;
		if(tam == 0){
			fin = ini;
		}
		tam++;
		return true;
	}
	
	public void insCorr(Peca x){
		corr.setElem(x);
	}
	
	public int getTam(){
		return tam;
	}

	public void posicionaCorr(int no){
		if(no > tam)
			corr = fin;
		else{
			corr = ini;
			for (int i = 1; i < no; i++)
				corr = corr.getProx();
		}
		
	}
	
	public Peca getElemCorr(int i){
		return corr.getElem(i);
	}
	
	public boolean checaBarreira(){
		if(corr.getNumPecaNo()==2)
			return true;
		return false;
	}
	
	public int numPecaNaCasa(){
		return corr.getNumPecaNo();
	}
	
}
