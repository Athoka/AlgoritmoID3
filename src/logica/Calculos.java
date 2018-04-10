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
		HashMap<String, HashMap<String, Integer>> valores = new HashMap<String, HashMap<String, Integer>>();  //Clave -> Valor del atributo, Valor -> (Clave -> Si o No, Valor -> Numero veces)
		int indexClase; 
		int N = lista_ejemplos.size();
		for(int i = 0; i < lista_ejemplos.size(); i++) {
			indexClase = lista_ejemplos.get(i).size()-1;
			if(!valores.containsKey(lista_ejemplos.get(i).get(index))) {
				HashMap<String, Integer> aux = new HashMap<String, Integer>();
				if(lista_ejemplos.get(i).get(indexClase).equals("Si")) {
					aux.put(lista_ejemplos.get(i).get(indexClase), 1);
					aux.put("No", 0);
				} else {
					aux.put(lista_ejemplos.get(i).get(indexClase), 1);
					aux.put("Si", 0);
				}
				valores.put(lista_ejemplos.get(i).get(index), aux);
			} else {
				HashMap<String, Integer> aux = valores.get(lista_ejemplos.get(i).get(index));
				aux.put(lista_ejemplos.get(i).get(indexClase), aux.get(lista_ejemplos.get(i).get(indexClase)));
			}
		}
		
		//Una vez tengo el hashmap con todo, tengo que calcular las p y n de cada atributo
		ArrayList<Float> p = new ArrayList<Float>();
		ArrayList<Float> n = new ArrayList<Float>();
		ArrayList<Integer>a = new ArrayList<Integer>();
		
		//Necesito un arraylist con las r (ai/ N)
		//Necesito un arraylist con las a (Veces que se repite cada clave)
		//Necesito un arraylist con las p (veces positivas / ai)
		//Necesito un arraylist con las n (veces negativas / ai) 
		
		//Necesito una funcion para calcular los info ((-p1*log(pi) en base 2 - ni*log(ni) en base 2))
		
		//Calcular el merito (sumatorio de ri*mi)
		return merito;
	}

}
