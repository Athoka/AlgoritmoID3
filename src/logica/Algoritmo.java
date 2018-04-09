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
	public Nodo ejecutar() {
		HashMap<String, Float> meritos = new HashMap<String, Float>();
		
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		 * 1. Si lista-ejemplos está vacía, "regresar"; en caso contrario, seguir.                                             *
		 * 2. Si todos los ejemplos en lista-ejemplos son +, devolver "+"; de otro modo seguir                                 *
		 * 3. Si todos los ejemplos en lista-ejemplos son , devolver ""; de otro modo seguir                                   *
		 * 4. Si lista-atributos está vacía, devolver "error"; en caso contrario:                                              *
		 * 		(1) llamar mejor al elemento a de lista-atributos que minimice mérito (a)                                      *
		 * 		(2) iniciar un árbol cuya raíz sea mejor: para cada valor vi de mejor                                          *
		 * 			* incluir en ejemplos-restantes los elementos de lista-ejemplos que tengan valor vi del atributo mejor.    *
		 * 			* dejar en atributos-restantes todos los elementos de lista-atributos excepto mejor.                       *
		 * 			* devolver el valor de: ID3 (ejemplos-restantes, atributos-restantes) (llamada recursiva al algoritmo)     *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		
		/**
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
		 * 
		 */
		
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
		
		
		
		this.solucion = new Nodo(menorAtributo, menorMerito);
		calcularRama(menorAtributo);
		
		
		return null;
	}
	
	private void calcularRama(String atributo) {
		boolean encontrado = false;
		int indexAtributo = 0, indexClase;
		HashMap<String, ArrayList<String>> valores;
		
		for(int i = 0; i < this.lista_atributos.size() && !encontrado; i++) {
			if(this.lista_atributos.get(i).equals(atributo)) {
				encontrado = true;
				indexAtributo = i;
			}
		}
		
		HashMap<String, ArrayList<String>> atributos = new HashMap<String, ArrayList<String>>();
		
		for(int i = 0; i < this.lista_ejemplos.size(); i++) {
			indexClase = this.lista_ejemplos.get(i).size();
			if(atributos.containsKey(this.lista_ejemplos.get(i).get(indexAtributo))) {
				atributos.get(this.lista_ejemplos.get(i).get(indexAtributo)).add(this.lista_ejemplos.get(i).get(indexAtributo));
			} else {
				ArrayList<String> list = new ArrayList<String>();
				list.add(this.lista_ejemplos.get(i).get(indexClase));
				atributos.put(this.lista_ejemplos.get(i).get(indexAtributo), list);
			}
		}
		
		
	}
}
