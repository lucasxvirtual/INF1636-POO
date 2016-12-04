package Jogo;

import Desenha.Botoes;
import Desenha.DesenhaTab;
import Desenha.Tela;
import Interfaces.ObservadorIF;

public class Fachada {
	
	
	//FUNCAO DE FACHADA PARA REGISTRATAR O OBSERVADOR (FAÇADE)
	public static void Registra(ObservadorIF o, int i){
		if (i==1)
			Dado.getDado().add(o);
		else if (i==2) {
			for (int j = 0; j < 16; j++){
				Peca.getPeca()[j].add(o);
			}
		}
		else {
			DesenhaTab.getTabuleiro().add(o);
		}
	}
	
	public static void btnClicado(){
		if(Main.getEsperaBotao() == true){
			Dado.getDado().geraValor();
			Main.esperaBotao();
		}
	}
	
	public static int get(int i){
		return DesenhaTab.getTabuleiro().get();
	}
	
	public static void mudaLabel(String txt){
		Botoes.getBotoes().setTextLabel(txt);
	}
	
	public static Tela getTela(){
		return new Tela();
	}
	
	public static int getNumPeca(){
		return DesenhaTab.getTabuleiro().getNumPeca();
	}
	
	public static void mudaLabelColoc(String txt, int lbl){
			
		Botoes.getBotoes().setTextLabelColoc(txt, lbl);
	}
	
	public static void salvarJogo(){
		Main.salvarJogo2();
	}
	
	public static void carregarJogo(){
		Main.carregarJogo2();
	}
	
}
