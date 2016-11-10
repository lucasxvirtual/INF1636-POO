package Jogo;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import Interfaces.ObservadoIF;
import Interfaces.ObservadorIF;

public class Dado implements ObservadoIF {
	
	private static Dado inst = null;
	private int valor = 0;
	private Random geraValor = new Random();
	private List<ObservadorIF> lst = new ArrayList<ObservadorIF>();
	
	private Dado(){
		
	}
	
	public static Dado getDado(){
		if (inst == null)
			inst = new Dado();
		return inst;
	}
	
	public void geraValor(){
		ListIterator<ObservadorIF> li = lst.listIterator();
		valor = geraValor.nextInt(6) + 1;
		while(li.hasNext())
			li.next().notify(this);
	}

	@Override
	public void add(ObservadorIF o) {
		// TODO Auto-generated method stub
		lst.add(o);
		
	}

	@Override
	public int get() {
		// TODO Auto-generated method stub
		return valor;
	}

}
