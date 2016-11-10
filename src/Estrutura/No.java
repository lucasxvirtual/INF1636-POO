package Estrutura;
import Jogo.Peca;;
public class No {
	
	private  Peca[] elem = new Peca[2];
	private No prox;
	private int numPecaNo = 0;

	public No(No p){
		prox=p;
	}
	
	public Peca getElem(int i){
		Peca x;
		if(numPecaNo < i)
			return null;
		if(i == 0 && elem[i] == null){
			x = elem[1];
			elem[1]=null;
		}
		else if (i == 1 && elem[i] == null){
			x = elem[0];
			elem[0] = null;
		}
		else {
			x = elem[i];
			elem[i] = null;
		}
		numPecaNo--;
		return x;
	}
	
	public boolean setElem(Peca p){
		if (numPecaNo == 2)
			return false;
		
		if(elem[0] == null){
			elem[0] = p;
		}
		else{
			elem[1] = p;
		}
		numPecaNo++;
		return true;
	}
	
	public No getProx(){
		return prox;
	}
	
	public void setProx(No o){
		prox = o;
	} 
	
	public int getNumPecaNo(){
		return numPecaNo;
	} 
}
