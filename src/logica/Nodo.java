package logica;

import java.util.ArrayList;

public class Nodo {

	private ArrayList<Nodo> hijos; //Lista de hijos
	private Nodo padre; //Null si es la raiz del arbol
	private String nombre; // Nombre del atributo. Si es "Si" o "No" es el nodo final
	private String condicion; // Valor del atributo con el que se llega a este nodo. Null si es la raiz
	private float merito; //merito del atributo
	private int nivel; 
	private int numeroNodo;
	
	public Nodo(String nombre, float merito) {
		this.nombre = nombre;
		this.merito = merito;
		this.padre = null;
		this.hijos = new ArrayList<Nodo>();
		this.nombre = " ";
	}
	
	public Nodo(String nombre,String condicion) {
		this.nombre = nombre;
		this.merito = 0;
		this.padre = null;
		this.condicion = condicion;
		this.hijos = new ArrayList<Nodo>();
		this.nombre = " ";
	}
	
	public Nodo() {
		this.nombre = " ";
		this.padre = null;;
		this.hijos = new ArrayList<Nodo>();
	}

	public Nodo getPadre() {
		return this.padre;
	}
	
	public void setPadre(Nodo p) {
		this.padre = p;
	}
	
	public ArrayList<Nodo> getHijos() {
		return this.hijos;
	}
	
	public void setHijo(Nodo hijo, int index) {
		this.hijos.add(index, hijo);
	}
	
	public void setNombre(String s) {
		this.nombre = s;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setCondicion(String s) {
		this.condicion = s;
	}
	
	public String getCondicion() {
		return this.condicion;
	}
	
	public void setMerito(float merito) {
		this.merito = merito;
	}
	
	public float getMerito() {
		return this.merito;
	}
	
	public void setHijos(ArrayList<Nodo> lista) {
		this.hijos = lista;
	}
	
	public boolean esHoja() {
		return this.nombre.equals("si") || this.nombre.equals("no");
	}

	public int getNivel() {
		return this.nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getNumeroNodo() {
		return this.numeroNodo;
	}

	public void setNumeroNodo(int numeroNodo) {
		this.numeroNodo = numeroNodo;
	}
	
	
	public String toString() {
		String s;
		s = "Nombre: " + this.nombre +"\n";
		if(this.condicion == null) {
			s += "Condicion: []\n"; 
		} else {
			s += "Condicion: " + this.condicion + "\n";
		}
		if(this.padre == null) {
			s += "Padre: Ninguno\n";
		} else {
			s += "Padre: " + this.padre.nombre + "\n";
		}
		
		s+="\n";
		
		for(int i = 0; i < this.hijos.size(); i++) {
			s += "Hijo " + i + "\n" + this.hijos.get(i).toString() + "\n";
		}
		return s;
	}
}
