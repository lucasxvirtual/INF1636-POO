package Estrutura;

public class Tabuleiro extends TabFin{
	
	private static Tabuleiro inst;
	
	private Tabuleiro(){
		
		for(int i = 0; i<52; i++)
			lista.insNo();
		
	}
	
	public static Tabuleiro getTabuleiro(){
		if (inst == null)
			inst = new Tabuleiro();
		return inst;
	}
	
	public void movimenta(int numpeca, int origem, int destino){
		insere(retira(origem, numpeca), destino);
	}

}
