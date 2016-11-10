package Jogo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Desenha.DesenhaTab;
import Desenha.Tela;
import Estrutura.*;
import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;
public class Main implements ObservadorIF{
	
	private static Peca[] pecas = Peca.getPeca();
	private static Tabuleiro tab = Tabuleiro.getTabuleiro();
	private static CasaInicial inicialjogador[] = new CasaInicial[4];
	private static PecasParaFinalizar[] fin = new PecasParaFinalizar[4];
	private Tela t = Fachada.getTela();
	private static boolean esperaClique = false;
	private static  boolean esperaBotao = true;
	private static int jogador = 1;
	private static boolean captura = false;
	private static boolean repete = false;
	
	private void jogo(){	
		
		Fachada.Registra(this, 3);
		
		int i;
		
		for (i = 0; i < 4; i++)
			inicialjogador[i] = CasaInicial.getCasaInicial(i+1);
		
		int j = -1;
		
		for (i=0; i < 16; i++){
			if (i%4 == 0)
				j++;
			inicialjogador[j].colocaPecaJogador(pecas[i]);
		
		}
		
		for (i=0; i < 4; i++)
			fin[i] = PecasParaFinalizar.getPecasParaFinalizar(i);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Main x = new Main();
		x.jogo();
		
	}

	@Override
	public void notify(ObservadoIF o) {
		// TODO Auto-generated method stub
		int casa = 0;
		int dado = Dado.getDado().get();
		if (captura == true){
			dado = 20;
			captura = false;
		}
		if(repete == true)
			repete = false;
		int numPeca = 0;
		Peca x, y;
		if(jogador == 1)
			casa = 1;
		else if(jogador == 2)
			casa = 14;
		else if(jogador == 3)
			casa = 27;
		else if(jogador == 4)
			casa = 40;
		
		if(esperaClique == true){
			int posicao = Fachada.get(0);
			for(int i = 3; i >= 0; i--)
				if(pecas[((jogador - 1)*4)+i].get() == posicao){
					numPeca = ((jogador - 1)*4) + (Fachada.getNumPeca() - 1);
				}
			
			switch (Regras.checaRegra(jogador, pecas[numPeca], dado)){
			
			case MovimentoInvalido:
				Fachada.mudaLabel("jogador "+jogador+": movimento invalido.");
				return;
				
			case RetiraCasaInicial:
				pecaFoiCap(casa);
				tab.insere(inicialjogador[jogador - 1].retiraPecasJogador(pecas[numPeca].getNumPeca()), casa);
				pecas[numPeca].setCasasAndadas(1);
				break;
				
			case RetornaUltPeca:
				x = Regras.getUltPecaMov();
				if(x.get() != -6){
					if(x.get() > 0){
						y = tab.retira(x.get(), 0);
						if (x.getNumPeca() != y.getNumPeca()){
							tab.insere(y, x.get());
							y = tab.retira(x.get(), 1);
						}
					}
					else{
						y = fin[x.getJogador()-1].retira(-x.get(), 0);
						if (x.getNumPeca() != y.getNumPeca()){
							fin[x.getJogador()-1].insere(y, -x.get());
							y = fin[x.getJogador()-1].retira(-x.get(), 1);
						}
					}
					inicialjogador[y.getJogador() - 1].colocaPecaJogador(y);
					y.setCasasAndadas(0);
				}	
				break;
				
			case MovimentaNormal:
				movimentaNormal(numPeca, dado, casa);
				break;
			
			case MovimentaERepete:
				movimentaNormal(numPeca, dado, casa);
				if(captura != true)
					repete = true;
				break;
				
			case Anda7CasasERepete:
				movimentaNormal(numPeca, dado+1, casa);
				if(captura != true)
					repete = true;
				break;
			default:
				break;
			}
			System.out.println(fin[jogador - 1].numPecasCasa(6));
			if(fin[jogador - 1].numPecasCasa(6) == 4){
				Fachada.mudaLabel("FIM DE JOGO: JOGADOR " + jogador + " VENCEU");
				calculaColocacao();
				esperaBotao = false;
				esperaClique = false;
			}
			
			if (repete == false && captura == false){
				jogador = (jogador % 4) + 1;
				esperaBotao = true;
				esperaClique = false;
				Fachada.mudaLabel("jogador "+jogador+": role o dado");
			}
			if(repete == true){
				esperaBotao = true;
				esperaClique = false;
				Fachada.mudaLabel("jogador "+jogador+": role o dado");
			}
			if (captura == true){
				esperaBotao = false;
				esperaClique = true;
				Fachada.mudaLabel("jogador " + jogador + " escolha uma peca para andar 20 casas.");
			}
			
			
		}	
		
	}
	
	private void pecaFoiCap(int casa){
		Peca x;
		if(casa == 10 || casa == 23 || casa == 36 || casa == 49)
			return;
		
		if(tab.numPecasCasa(casa) != 0){
			if(casa == 1){
				x = tab.retira(casa, 0);
				tab.insere(x, casa);
				if(x.getJogador() == 1){
					return;
				}
			}
			else if(casa == 14){
				x = tab.retira(casa, 0);
				tab.insere(x, casa);
				if(x.getJogador() == 2){
					return;
				}
			}
			else if(casa == 27){
				x = tab.retira(casa, 0);
				tab.insere(x, casa);
				if(x.getJogador() == 3){
					return;
				}
			}
			else if(casa == 40){
				x = tab.retira(casa, 0);
				tab.insere(x, casa);
				if(x.getJogador() == 4){
					return;
				}
			}
			x = tab.retira(casa, 0);
			if(x.getJogador() != jogador){
				inicialjogador[x.getJogador() - 1].colocaPecaJogador(x);
				x.setCasasAndadas(0);
				if(Regras.PossibilidadeMovimento(jogador, 20) == CondRetRegra.NenhumaJogada){
					Fachada.mudaLabel("Jogador " + jogador + ": nao houve jogada. Jogador " + ((jogador % 4) + 1) + ": role o dado.");
					jogador = (jogador % 4) + 1;
				}
				else 
					captura = true;
				
				
			}
			
			else 
				tab.insere(x, casa);
			
		}
		return;
	}
	
	private void movimentaNormal(int numPeca, int dado, int casa){
		
		int num;
		Peca x;
		if(pecas[numPeca].get() > 0){
			if(pecas[numPeca].getCasasAndadas() + dado == 53){
				pecaFoiCap(casa);
				tab.movimenta(0, pecas[numPeca].get(), casa);
			}
			else if(pecas[numPeca].getCasasAndadas() + dado < 53){
				num = pecas[numPeca].get() + dado;
				if(num > 52)
					num = ((pecas[numPeca].get() + dado) % 53) + 1;
				pecaFoiCap(num);
				x = tab.retira(pecas[numPeca].get(), 0);
				if(x != pecas[numPeca]){
					tab.insere(x, pecas[numPeca].get());
					x = tab.retira(pecas[numPeca].get(), 1);
				}
				tab.insere(x, num);
			}
			else if(pecas[numPeca].getCasasAndadas() + dado > 53){
				num = (pecas[numPeca].getCasasAndadas() + dado) %53;
				tab.retira(pecas[numPeca].get(), 0);
				fin[pecas[numPeca].getJogador() - 1].insere(pecas[numPeca], num);
			}
			pecas[numPeca].setCasasAndadas(pecas[numPeca].getCasasAndadas() + dado);
		}
		else
			fin[pecas[numPeca].getJogador() - 1].movimenta(0, -pecas[numPeca].get(), -pecas[numPeca].get() + dado);		
		
	}
	
	//TODO 	terminar implementaçao de calculaColocacao
	private static void calculaColocacao(){
		Peca x;
		int soma[] = new int[4];
		int jogador[] = new int[4];
		int j;
		for(j=0; j < 4; j++){
			soma[j] = 0;
			jogador[j] = j+1;
		}
		
		j=0;
		
		for (int i = 0; i < 16; i++){
			
			if(i!=0 && i%4 == 0)
				j++;
			
			x = pecas[i];
			if(x.getCasasAndadas() <= 53)
				soma[j] = soma[j] + (59 - x.getCasasAndadas());
			
			else
				soma[j] = soma[j] + (6 + x.get());
			
		}

		bolha(4, soma, jogador);
		for(j=0; j<4; j++)
			Fachada.mudaLabelColoc((j+1) + "º lugar: jogador " + jogador[j], (j+1));
		
	}
	
	private static void bolha (int n, int v[], int jogador[]){
		int fim,i;
		for (fim=n-1; fim>0; fim--)
				for (i=0; i<fim; i++)
					if (v[i]>v[i+1]) {
						int temp = v[i]; /* troca */
						v[i] = v[i+1];
						v[i+1] = temp;
						temp = jogador[i];
						jogador[i] = jogador[i+1];
						jogador[i+1] = temp;
					}
	}
	
	public static void esperaBotao(){
		if(Regras.PossibilidadeMovimento(jogador, Dado.getDado().get()) == CondRetRegra.NenhumaJogada){
			Fachada.mudaLabel("Jogador " + jogador + ": nao houve jogada. Jogador " + ((jogador % 4) + 1) + ": role o dado.");
			jogador = (jogador % 4) + 1;
		}
		else{
			Fachada.mudaLabel("jogador "+ jogador +": escolha uma peca");
			esperaBotao = false;
			esperaClique = true;
		}
	}
	
	public static boolean getEsperaBotao(){
		return esperaBotao;
	}
	
	public static int getJogador(){
		return jogador;
	}
	
	public static void salvarJogo(){
		FileWriter outputStream = null;
		try{
			outputStream = new FileWriter("arquivo.txt");
			outputStream.write(jogador);
			
			for(int i = 0; i < 16; i++){
				if(pecas[i].get() >= 0)
					outputStream.write(pecas[i].get());
				if(pecas[i].get() < 0)
					outputStream.write((52 - pecas[i].get()));
				
				outputStream.write(pecas[i].getCasasAndadas());
			}
		}catch(Exception e){
			System.exit(0);
		} finally {
			if(outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.exit(1);
				}
		}
	}
	
	public static void carregarJogo(){
		FileReader inputStream = null;
		
		try{
			inputStream = new FileReader("arquivo.txt");
			jogador = inputStream.read();
			for(int i = 0; i < 16; i++){
				int x = inputStream.read();
				System.out.println(x);
				if(x < 53)
					pecas[i].setPosicao(x);
				else 
					pecas[i].setPosicao(-(x - 52));
					
				pecas[i].setCasasAndadas(inputStream.read());
				
				if(pecas[i].get() > 0)
					tab.insere(pecas[i], pecas[i].get());
				
				else if(pecas[i].get() < 0)
					fin[i/4].insere(pecas[i], -(pecas[i].get()));
					
			}
		}catch(Exception e){
			System.exit(2);
		}finally{
			if(inputStream != null)
				try{
					inputStream.close();
				}catch(Exception e){
					System.exit(3);
				}
		}
		Fachada.mudaLabel("jogador "+jogador+": role o dado");
		
	}

}
