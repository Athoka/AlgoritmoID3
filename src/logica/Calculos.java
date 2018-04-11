package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class Calculos {

	
	/*
	 * Para calcular el merito tengo que ir, columna por columna del  array lista_ejemplos
	 * agrupando por los valores de dicho atributo y si la clase es si o no.
	 * 
	 * 
	 * 
	 * */

	public static Float calcularMerito(ArrayList<ArrayList<String>> lista_ejemplos, int index) {
		float merito = 0;
		HashMap<String, HashMap<String, Integer>> valores = new HashMap<String, HashMap<String, Integer>>();  //Clave -> Valor del atributo, Valor -> (Clave -> si o no, Valor -> Numero veces)
		int indexClase; 
		int N = lista_ejemplos.size();
		for(int i = 0; i < lista_ejemplos.size(); i++) {
			indexClase = lista_ejemplos.get(i).size()-1;
			if(!valores.containsKey(lista_ejemplos.get(i).get(index))) {
				HashMap<String, Integer> aux = new HashMap<String, Integer>();
				if(lista_ejemplos.get(i).get(indexClase).equals("si")) {
					aux.put(lista_ejemplos.get(i).get(indexClase), 1);
					aux.put("no", 0);
				} else {
					aux.put(lista_ejemplos.get(i).get(indexClase), 1);
					aux.put("si", 0);
				}
				valores.put(lista_ejemplos.get(i).get(index), aux);
			} else {
				HashMap<String, Integer> aux = valores.get(lista_ejemplos.get(i).get(index)); //Cojo el hashmap interno
				int valor = aux.get(lista_ejemplos.get(i).get(indexClase));
				String clave = lista_ejemplos.get(i).get(indexClase);
				valor++;
				aux.put(clave,(Integer)valor);
				
				valores.put(lista_ejemplos.get(i).get(index), aux);
				
			}
		}
		
		//Una vez tengo el hashmap con todo, tengo que calcular las p y n de cada atributo
		ArrayList<Float> p = new ArrayList<Float>(); //Veces positivas / ai
		ArrayList<Float> n = new ArrayList<Float>(); //Veces negativas / ai
		ArrayList<Integer>a = new ArrayList<Integer>(); //Veces que se repite cada clave
		ArrayList<Float> r = new ArrayList<Float>(); // ai / N
		
		for(String valorAtrib : valores.keySet()) { //Para cada valor del atributo tiene que haber una entrada en cada uno de los arrays
			a.add(valores.get(valorAtrib).get("si") + valores.get(valorAtrib).get("no")); //ai
			n.add((float)(valores.get(valorAtrib).get("no"))/a.get(a.size()-1));
			p.add((float)(valores.get(valorAtrib).get("si"))/a.get(a.size()-1));
			r.add((float)(a.get(a.size()-1))/N);
		}
		
		//Calcula los info(p, n)
		ArrayList<Float> info = new ArrayList<Float>();
		for(int i = 0; i < n.size(); i++) {
			info.add(Calculos.info(p.get(i), n.get(i)));
		}
		
		//Calcula el merito = sumatorio info*r
		for(int i = 0; i < info.size(); i++) {
			merito += r.get(i)*info.get(i);
		}
		
		return merito;
	}
	
	/**
	 * Calcula info(p, n) = -p*log2(p) -n*log2(n)
	 */
	private static float info(float p, float n) {
		float auxp, auxn;
		if(p == 0) {
			auxp = 0;
		} else {
			auxp = (float) (-p*(Math.log10(p)/Math.log10(2)));
		}
		if(n == 0) { 
			auxn = 0;
		} else {
			auxn = (float) (-n*(Math.log10(n)/Math.log10(2)));
		}
		
		return auxp+auxn;
	}

}
