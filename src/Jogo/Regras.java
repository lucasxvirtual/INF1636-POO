package Jogo;

import Estrutura.PecasParaFinalizar;
import Estrutura.Tabuleiro;

public class Regras {

	private static int numDado6 = 0;
	private static Peca ultPecaMov;
	private static Tabuleiro tab = Tabuleiro.getTabuleiro(); 
	private static PecasParaFinalizar peFin[] = new PecasParaFinalizar[4];
	private static Peca Pecas[] = Peca.getPeca();
	
	public static CondRetRegra checaRegra(int jogador, Peca x, int valorMov){
		boolean barreira = false;
		int casa = x.get();
		int i;
		
		if (peFin[0] == null){
			for (i = 0; i < 4; i++)
				peFin[i] = PecasParaFinalizar.getPecasParaFinalizar(i);
		}
		
		//SOBRE O 6
		if(valorMov == 6){
			
			if(ChecaPecaCasaInicial(jogador) == false)
				valorMov++;
			
			if(MovimentoInvalido(x, valorMov) == true)
				return CondRetRegra.MovimentoInvalido;
			
			numDado6++;
			if(numDado6 == 2)
				ultPecaMov = x;
			
			if(numDado6 == 3)
				return CondRetRegra.RetornaUltPeca;
			
			for (i = 0; i < 4; i++){
				for (int j = i+1; j < 4; j++){
					if(Pecas[((jogador - 1)*4)+i].get() == Pecas[((jogador - 1)*4)+j].get() 
							&& Pecas[((jogador - 1)*4)+i].get() != 0 
							&& Pecas[((jogador - 1)*4)+i].get() != -6
							&& MovimentoInvalido(Pecas[((jogador - 1)*4)+i], valorMov) == false){
						barreira = true;
					}
				}
			}
			
			if(barreira == true){
				if (casa > 0){
					if(tab.numPecasCasa(casa) != 2)
						return CondRetRegra.MovimentoInvalido;
				}
				else if(casa < 0){
					if(peFin[jogador-1].numPecasCasa(-casa) !=2)
						return CondRetRegra.MovimentoInvalido;
				}
			}
			
			if(valorMov == 7)
				return CondRetRegra.Anda7CasasERepete;
			
			return CondRetRegra.MovimentaERepete;	
			
		}
		
		//SOBRE O 5
		if(valorMov == 5){
			numDado6 = 0;
			if(ChecaPecaCasaInicial(jogador) == true && casa != 0)
				for(i = 0; i<4; i++)
					if(Pecas[((jogador - 1)*4)+i].get() == 0 && MovimentoInvalido(Pecas[((jogador - 1)*4)+i], valorMov) == false)
						return CondRetRegra.MovimentoInvalido;
			if(casa == 0 && MovimentoInvalido(x, valorMov) == false)
				return CondRetRegra.RetiraCasaInicial;
			if(MovimentoInvalido(x, valorMov) == true)
				return CondRetRegra.MovimentoInvalido;
			return CondRetRegra.MovimentaNormal;
			
		}
		numDado6 = 0;
		if(MovimentoInvalido(x, valorMov) == true)
			return CondRetRegra.MovimentoInvalido;
			
		return CondRetRegra.MovimentaNormal;
		
	}
	
	public static CondRetRegra PossibilidadeMovimento(int jogador, int valorMov){
		
		if(MovimentoInvalido(Pecas[(jogador - 1)*4], valorMov) == true 
				&& MovimentoInvalido(Pecas[((jogador - 1)*4)+1], valorMov) == true 
				&& MovimentoInvalido(Pecas[((jogador - 1)*4)+2], valorMov) == true 
				&& MovimentoInvalido(Pecas[((jogador - 1)*4)+3], valorMov) == true){
			numDado6 = 0;
			return CondRetRegra.NenhumaJogada;
		}
		
		return CondRetRegra.MovimentaNormal;
		
	}
	
	private static boolean ChecaPecaCasaInicial(int jogador){
		
		for (int i = 0; i < 4; i++)
			if(Pecas[((jogador - 1)*4)+i].get() == 0)			
				return true;

		return false;
		
	}
	
	private static boolean MovimentoInvalido(Peca x, int valorMov){
		Peca y, z;
		int i, dest;
		int casa = x.get();
		int casasAndadas = x.getCasasAndadas();
		int primCasaJog;
		
		//SE A PECA QUE TENTAR MOVIMENTAR FOR UMA PECA FINALIZADA
		if(casa == -6)
			return true;
		
		//TESTAR SE O JOGADOR PODE TIRAR PECA DA CASA INICIAL
		if(casa == 0 && valorMov != 5)
			return true;
		if(casa == 0 && valorMov == 5){
			if(x.getJogador() == 1)
				primCasaJog = 1;
			else if(x.getJogador() == 2)
				primCasaJog = 14;
			else if(x.getJogador() == 3)
				primCasaJog = 27;
			else 
				primCasaJog = 40;
			
			if(tab.numPecasCasa(primCasaJog) == 2){
				y = tab.retira(primCasaJog, 0);
				tab.insere(y, primCasaJog);
				z = tab.retira(primCasaJog, 1);
				tab.insere(z, primCasaJog);
				if(y.getJogador() == x.getJogador() && z.getJogador() == x.getJogador())
					return true;
			}
		}
		//TESTAR SE O JOGADOR PODE ANDAR COM PECA EM CASAS NORMAIS
		if(casa > 0 && casa < 53 && x.getCasasAndadas() + valorMov < 54){
			dest = casa+valorMov;
			for (i = casa + 1; i <= dest;i++){
				if(i>52){
					i = i % 52;
					dest = dest % 52;
				}
				if(tab.numPecasCasa(i) == 2)
					return true;
			}
			
		}
		//TESTAR SE JOGADOR PODE ANDAR CASA NORMAL -> CASA FINAL
		if(casa > 0 && casa < 53 && x.getCasasAndadas() < 54 &&  x.getCasasAndadas() + valorMov >= 54){
			
			dest = casa+valorMov;
			for(i = casa; casasAndadas < 54; i++, casasAndadas++, valorMov--){
				if(i>52){
					i = i % 52;
					dest = dest % 52;
				}
				if(tab.numPecasCasa(i) == 2)
					return true;
			}

			valorMov = (-1) - valorMov;
			for(i = -1; i>=valorMov; i--){
				
				if(i == -6)
					return false;
				if(peFin[x.getJogador() - 1].numPecasCasa(-i) == 2)
					return true;
			
			}
			
		}
		//TESTAR SE JOGADOR PODE ANDAR CASA FINAL
		if(casa < 0){
			if(valorMov != 20 && valorMov != 10 && casa - valorMov < -6)
				return true;
		
			for (i = casa; i >= casa - valorMov; i--){
				if (i == -6)
					return false;
				if(peFin[x.getJogador() - 1].numPecasCasa(-i) == 2)
					return true;
			}
		}
			
		return false;
	}
	
	public static Peca getUltPecaMov(){
		return ultPecaMov;
	}
	
	
}
