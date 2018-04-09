package main;

import java.util.ArrayList;

import logica.Algoritmo;
import persistenciaDatos.OperacionesFicheros;

public class Main {

	public static void main(String[] args) {
		OperacionesFicheros of = new OperacionesFicheros();
		try {
			ArrayList<ArrayList<String>> ejemplos = of.leerEjemplos("Juego.txt");
			ArrayList<String> atributos = of.leerAtributos("AtributosJuego.txt");
			Algoritmo a = new Algoritmo(ejemplos, atributos);
			a.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
