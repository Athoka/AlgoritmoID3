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
		 */
		
		for(int i = 0; i < this.lista_atributos.size()-1; i++) {
			meritos.put(lista_atributos.get(i), Calculos.calcularMerito(lista_ejemplos, i)); //columna
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
		this.solucion.setHijos(calcularRama(menorAtributo));
		for(int i = 0; i < this.solucion.getHijos().size(); i++) {
			if(!this.solucion.getHijos().get(i).getNombre().equals("Si") && !this.solucion.getHijos().get(i).getNombre().equals("No")) {
				this.solucion.setHijo(ejecutar(), i);
			}
		}
			
		return this.solucion;
	}
	
	//TODO: Como calcular una rama
	private ArrayList<Nodo> calcularRama(String atributo) {
		boolean encontrado = false;
		int indexAtributo = 0, indexClase;
		
		//Busca el indice del atributo
		for(int i = 0; i < this.lista_atributos.size() && !encontrado; i++) {
			if(this.lista_atributos.get(i).equals(atributo)) {
				encontrado = true;
				indexAtributo = i;
			}
		}
		
		//Almacena en un hashmap los datos de la manera clave -> nombre atributo, valor -> arraylist con los valores de la clase (Si o No)
		HashMap<String, ArrayList<String>> atributos = new HashMap<String, ArrayList<String>>(); 
		
		//Para cada fila de lista ejemplos 
		for(int i = 0; i < this.lista_ejemplos.size(); i++) {
			indexClase = this.lista_ejemplos.get(i).size()-1; //Cogemos la ultima posicion de cada fila, que representa la columna de la clase 
			if(atributos.containsKey(this.lista_ejemplos.get(i).get(indexAtributo))) { //Si el atributo ya estaba en el hashmap
				ArrayList<String> list = atributos.get(this.lista_ejemplos.get(i).get(indexAtributo));
				list.add(this.lista_ejemplos.get(i).get(indexClase));
				atributos.put(this.lista_ejemplos.get(i).get(indexAtributo), list);
			} else { //Si no estaba
				ArrayList<String> list = new ArrayList<String>();
				list.add(this.lista_ejemplos.get(i).get(indexClase));
				atributos.put(this.lista_ejemplos.get(i).get(indexAtributo), list);
			}
		}
		
		//Una vez hemos rellenado el hashmap con los datos
		ArrayList<Nodo> rama = new ArrayList<Nodo>();
		for(String a : atributos.keySet()) { //Para cada clave a en el hashmap
			boolean positivos = true, negativos = true;
			for(int i = 0; i < atributos.get(a).size(); i++) { // Para cada posicion del arraylist de la clave a
				if(atributos.get(a).get(i).equals("Si")) {
					negativos = false;
				} else if (atributos.get(a).get(i).equals("Si")) {
					positivos = false;
				}
			}
			//Rellenamos un nodo por cada clave
			Nodo n = new Nodo();
			if(positivos) {
				n.setCondicion(a);
				n.setNombre("Si");
			} else if (negativos) {
				n.setCondicion(a);
				n.setNombre("No");
			} else {
				n.setCondicion(a);
			}
			rama.add(n);
		}
		
		return rama;
	}
	
	public void mostrarSolucion() {
		System.out.println(this.solucion.getNombre());
	}
}
