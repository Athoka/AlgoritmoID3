package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class Algoritmo {

	private Nodo solucion;
	private ArrayList<String> lista_atributos;
	private ArrayList<ArrayList<String>> lista_ejemplos;
	
	public Algoritmo() {
		
	}
	
	public Algoritmo(ArrayList<ArrayList<String>> ejemplos, ArrayList<String> atributos) {
		this.lista_atributos = atributos;
		this.lista_ejemplos = ejemplos;
		this.solucion = null;
	}

	/**
	 * Ejecuta el algoritmo id3
	 * @return Nodo raíz del arbol
	 */
	
	public Nodo ejecutar() {
		int k = 0;
		this.solucion = new Nodo();
		this.solucion.setNumeroNodo(0);
		this.solucion.setNivel(0);
		return ejecutar(this.solucion, k+1, this.lista_atributos, this.lista_ejemplos);
	}
	
	private Nodo ejecutar (Nodo nodo, int k, ArrayList<String> lAtributos, ArrayList<ArrayList<String>> lEjemplos) {
		
		if(lAtributos.size() == 1 || nodo.esHoja()) { //Si no quedan más atributos
			return nodo;
		} else {
			HashMap<String, Float> meritos = new HashMap<String, Float>();	
			for(int i = 0; i < lAtributos.size()-1; i++) {
				meritos.put(lAtributos.get(i), Calculos.calcularMerito(lEjemplos, i));
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
					ArrayList<String> aux = new ArrayList<String>((ArrayList<String>)lAtributos.clone());
					aux.remove(indice);
					ArrayList<ArrayList<String>> auxEjemplos = new ArrayList<ArrayList<String>> (modificarEjemplos(lEjemplos, indice, nodo.getHijos().get(i).getCondicion()));
							
					ejecutar(nodo.getHijos().get(i), k+1, aux, auxEjemplos);
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
			} else { //Si no estaba
				
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
			
			//Rellenamos un nodo por cada clave
			Nodo n = new Nodo();
			n.setCondicion(a);
			n.setPadre(nodo);
			//n.setNivel(nodo.getPadre().getNivel()+1);
			if(positivos) {
				n.setNombre("si");
			} else if (negativos) {
				n.setNombre("no");
			}
			nodo.getHijos().add(n);
		}
	}
	
	public void setEjemplos(ArrayList<ArrayList<String>> ej) {
		this.lista_ejemplos = ej;
	}
	
	public void setAtributos(ArrayList<String> at) {
		this.lista_atributos = at;
	}
	
	public void mostrarSolucion() {
		mostrarSolucion(this.solucion);
	}
	
	private void mostrarSolucion(Nodo n) {
		System.out.println(n.toString());
			for(int i = 0; i < n.getHijos().size(); i++) {
				mostrarSolucion(n.getHijos().get(i));
		}
	}
}
