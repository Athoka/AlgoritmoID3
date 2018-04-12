package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class Algoritmo {

	private Nodo solucion;
	private ArrayList<String> lista_atributos;
	private ArrayList<ArrayList<String>> lista_ejemplos;
	
	public Algoritmo(ArrayList<ArrayList<String>> ejemplos, ArrayList<String> atributos) {
		this.lista_atributos = atributos;
		this.lista_ejemplos = ejemplos;
		this.solucion = null;
	}

	/**
	 * Ejecuta el algoritmo id3
	 * @return Nodo raíz del arbol
	 */
	/*public Nodo ejecutar() {
		if(this.lista_atributos.isEmpty()) {
			return null; 
		}
		/*
		 * Recorrer lista_atributos
		 * Para cada atributo en lista_atributos, calculo el merito de cada columna de atributo
		 * El resultado es el merito
		 * 
		 * Luego, con los meritos calculados, elijo el que menor merito tenga
		 * Es el Nodo raiz. Con eso tengo que calcular una funcion que me calcule la rama
		 * Para buscar las soluciones, comparar la columna que estoy mirando con la ultima y ver valores.
		 * 
		 * La recursion es un movidote
		 * Hay que recalcular los meritos para cada llamada recursiva
		 
		
		HashMap<String, Float> meritos = new HashMap<String, Float>();	
		for(int i = 0; i < this.lista_atributos.size()-1; i++) {
			meritos.put(lista_atributos.get(i), Calculos.calcularMerito(lista_ejemplos, i));
		}
		
		String menorAtributo = " ";
		float menorMerito = Float.MAX_VALUE;
		for(String atributo : meritos.keySet()) {
			if(menorMerito > meritos.get(atributo)) {
				menorAtributo = atributo; 
				menorMerito = meritos.get(atributo);
			}
		}
		
		//Falla porque en la ultima vuelta, cuando no quedan mas atributos, no sabe ver que no tiene mas atributos y sigue
		this.solucion = new Nodo(menorAtributo, menorMerito);
		calcularRama(menorAtributo);
		for(int i = 0; i < this.solucion.getHijos().size(); i++) {
			if(this.solucion.esHoja()) {
				System.out.println("Es hoja");
				return this.solucion;
			} else {
				int index=0;
				for(int j = 0; j < this.lista_atributos.size(); j++) {
					if(this.lista_atributos.get(j).equals(menorAtributo))
						index = j;
				}
				this.lista_atributos.remove(menorAtributo);
				
				for(int j = 0; j < this.lista_ejemplos.size(); j++) {
					this.lista_ejemplos.get(j).remove(index);
				}
				
				System.out.println("No es hoja");
				return ejecutar();
			}
		}
			
		return this.solucion;
	}*/
	
	public Nodo ejecutar() {
		int k = 0;
		this.solucion = new Nodo();
		return ejecutar(solucion, k, this.lista_atributos, this.lista_ejemplos);
	}
	
	private Nodo ejecutar (Nodo nodo, int k, ArrayList<String> lAtributos, ArrayList<ArrayList<String>> lEjemplos) {
		
		if(lAtributos.size() == 1) { //Si no quedan más atributos
			return nodo;
		} else {
			HashMap<String, Float> meritos = new HashMap<String, Float>();	
			for(int i = 0; i < lAtributos.size()-1; i++) {
				meritos.put(lista_atributos.get(i), Calculos.calcularMerito(lista_ejemplos, i));
			}
			
			String menorAtributo = " ";
			float menorMerito = Float.MAX_VALUE;
			for(String atributo : meritos.keySet()) {
				if(menorMerito > meritos.get(atributo)) {
					menorAtributo = atributo; 
					menorMerito = meritos.get(atributo);
				}
			}
			
			nodo.setNombre(menorAtributo);
			nodo.setMerito(menorMerito);
			//nodo = new Nodo(menorAtributo, menorMerito);
			calcularRama(nodo, menorAtributo, lAtributos, lEjemplos);
	
			//quitar el atributo de lAtributo
			//quedarme sólo con los ejemplos que cumplan que el ejemplo es la condicion, hay que hacerlo para cada hijo, asi que tiene que ir en el for
			//antes de la llamada recursiva
			
			if (nodo.getHijos() != null) {
				for(int i = 0; i < nodo.getHijos().size(); i++) {
					int indice = 0;
					for(int j = 0; j < lAtributos.size(); j++) {
						if(lAtributos.get(j).equals(menorAtributo))
							indice = j;
					}
					lAtributos.remove(indice);
					lEjemplos = modificarEjemplos(lEjemplos, indice, nodo.getHijos().get(i).getCondicion());
					nodo = ejecutar(nodo.getHijos().get(i), k+1, lAtributos, lEjemplos);
				}
			}
			
			return nodo;
		}
	}
	
	
	private ArrayList<ArrayList<String>> modificarEjemplos(ArrayList<ArrayList<String>> lista, int indice, String condicion) {
		ArrayList<ArrayList<String>> l = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).get(indice).equals(condicion)) {
				l.add(lista.get(i));
				l.get(l.size()-1).remove(indice);
			}
		}
		
		return l;
	}
	
	
	//TODO: Como calcular una rama
	private void calcularRama(Nodo nodo, String atributo, ArrayList<String> lAtributos, ArrayList<ArrayList<String>> lEjemplos) {
		boolean encontrado = false;
		int indexAtributo = 0, indexClase;
		
		//Busca el indice del atributo
		for(int i = 0; i < lAtributos.size() && !encontrado; i++) {
			if(lAtributos.get(i).equals(atributo)) {
				encontrado = true;
				indexAtributo = i;
			}
		}
		
		//Almacena en un hashmap los datos de la manera clave -> valor atributo, valor -> arraylist con los valores de la clase (si o No)
		HashMap<String, ArrayList<String>> atributos = new HashMap<String, ArrayList<String>>(); 
		
		//Para cada fila de lista ejemplos 
		for(int i = 0; i < lEjemplos.size(); i++) {
			indexClase = lEjemplos.get(i).size()-1; //Cogemos la ultima posicion de cada fila, que representa la columna de la clase 
			if(!atributos.isEmpty() && atributos.containsKey(lEjemplos.get(i).get(indexAtributo))) { //Si el atributo ya estaba en el hashmap
				ArrayList<String> list = atributos.get(lEjemplos.get(i).get(indexAtributo));
				list.add(lEjemplos.get(i).get(indexClase));
				atributos.put(lEjemplos.get(i).get(indexAtributo), list);
			} else/* if(/*!atributos.isEmpty())*/{ //Si no estaba
				
				ArrayList<String> list = new ArrayList<String>();
				list.add(lEjemplos.get(i).get(indexClase));
				atributos.put(lEjemplos.get(i).get(indexAtributo), list);
			}
		}
		
		//Una vez hemos rellenado el hashmap con los datos
		for(String a : atributos.keySet()) { //Para cada clave a en el hashmap
			boolean positivos = true, negativos = true;
			for(int i = 0; i < atributos.get(a).size(); i++) { // Para cada posicion del arraylist de la clave a
				if(atributos.get(a).get(i).equals("si")) {
					negativos = false;
				} else if (atributos.get(a).get(i).equals("no")) {
					positivos = false;
				}
			}
			
			//TODO esto puede que sea lo problematico
			//Rellenamos un nodo por cada clave
			Nodo n = new Nodo();
			n.setCondicion(a);
			n.setPadre(this.solucion);
			if(positivos) {
				n.setNombre("si");
			} else if (negativos) {
				n.setNombre("no");
			} else {
				//n.setNombre(null);
			}
			nodo.getHijos().add(n);
		}
	}
	
	public void mostrarSolucion() {
		System.out.println(this.solucion.toString());
	}
}
