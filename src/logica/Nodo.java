package logica;

import java.util.ArrayList;

public class Nodo {

	private ArrayList<Nodo> hijos;
	private Nodo padre;
	private String nombre; // Nombre del atributo. Si es "Si" o "No" es el nodo final
	private String condicion; // Valor que tiene el atributo 
	private float merito;
	
	public Nodo(String nombre, float merito) {
		this.nombre = nombre;
		this.merito = merito;
		this.padre = null;
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
}
