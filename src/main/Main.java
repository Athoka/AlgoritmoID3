package main;

import java.util.ArrayList;

import logica.Algoritmo;
import persistenciaDatos.OperacionesFicheros;

public class Main {

	public static void main(String[] args) {
		try {
			OperacionesFicheros of = new OperacionesFicheros();
			ArrayList<ArrayList<String>> ejemplos = of.leerEjemplos("Juego.txt");
			ArrayList<String> atributos = of.leerAtributos("AtributosJuego.txt");
			Algoritmo a = new Algoritmo(ejemplos, atributos);
			a.ejecutar();
			a.mostrarSolucion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
