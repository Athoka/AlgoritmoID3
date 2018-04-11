package logica;

import java.util.ArrayList;

public class Nodo {

	private ArrayList<Nodo> hijos; //Lista de hijos
	private Nodo padre; //Null si es la raiz del arbol
	private String nombre; // Nombre del atributo. Si es "Si" o "No" es el nodo final
	private String condicion; // Valor del atributo con el que se llega a este nodo. Null si es la raiz
	private float merito; //merito del atributo
	
	public Nodo(String nombre, float merito) {
		this.nombre = nombre;
		this.merito = merito;
		this.padre = null;
		this.condicion = null;
		this.hijos = new ArrayList<Nodo>();
	}
	
	public Nodo(String nombre,String condicion) {
		this.nombre = nombre;
		this.merito = 0;
		this.padre = null;
		this.condicion = condicion;
		this.hijos = new ArrayList<Nodo>();
	}
	
	public Nodo() {
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
	
	public String toString() {
		String s;
		s = this.nombre + "\n" + this.condicion + "\n" + this.getMerito();
		return s;
	}
}
