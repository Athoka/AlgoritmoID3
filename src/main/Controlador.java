package main;

import java.util.ArrayList;

import logica.Algoritmo;
import persistenciaDatos.OperacionesFicheros;

public class Controlador {

	private Algoritmo a;
	
	public Controlador() {
		a = new Algoritmo();
	}
	
	
	private int menu() {
		System.out.println("1 - Ejecutar Algoritmo");
		System.out.println("2 - Generar Reglas");
		System.out.println("3 - Mostrar Nodos");
		return 0;
	}
	
	public void ejecutarControlador() {
		int opt;
		do {
			opt = menu();
			switch(opt) {
				case 1:
					ejecutarAlgoritmo();
					break;
				case 2:
					generarReglas();
					break;
				case 3:
					mostrarNodos();
					break;
			}
		} while (opt != 0);
	}
	
	private void generarReglas() {
		
	}
	
	private void mostrarNodos() {
		
	}
	
	private void ejecutarAlgoritmo() {
		try {
			OperacionesFicheros of = new OperacionesFicheros();
			ArrayList<ArrayList<String>> ejemplos = of.leerEjemplos("Juego.txt");
			ArrayList<String> atributos = of.leerAtributos("AtributosJuego.txt");
			a.setEjemplos(ejemplos);
			a.setAtributos(atributos);
			a.ejecutar();
		} catch (Exception e) {
		}
	}
}