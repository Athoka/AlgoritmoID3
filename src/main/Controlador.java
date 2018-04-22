package main;

import java.util.ArrayList;
import java.util.Scanner;

import logica.Algoritmo;
import persistenciaDatos.OperacionesFicheros;

public class Controlador {

	private Algoritmo a;
	
	public Controlador() {
		a = new Algoritmo();
	}
	
	
	private int menu() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("1 - Ejecutar Algoritmo");
		System.out.println("0 - Salir");
		System.out.print("Introduce opción: ");
		return entrada.nextInt();
	}
	
	public void ejecutarControlador() {
		int opt;
		do {
			opt = menu();
			switch(opt) {
				case 1:
					ejecutarAlgoritmo();
					a.mostrarReglas();
					break;
				case 0: 
					break;
			}
		} while (opt != 0);
	}	
	
	private void ejecutarAlgoritmo() {
		try {
			Scanner in = new Scanner(System.in);
			System.out.print("Introduce el fichero de atributos (acabado en .txt): ");
			String atributosTxt = in.nextLine();
			System.out.print("Introduce el fichero de ejemplos (acabado en .txt): ");
			String ejemplosTxt = in.nextLine();
			OperacionesFicheros of = new OperacionesFicheros();
			ArrayList<ArrayList<String>> ejemplos = of.leerEjemplos(ejemplosTxt);
			ArrayList<String> atributos = of.leerAtributos(atributosTxt);
			a.setEjemplos(ejemplos);
			a.setAtributos(atributos);
			System.out.println("------ Ejecutando algoritmo ------");
			System.out.println();
			a.ejecutar();
			System.out.println();
			System.out.println();
		} catch (Exception e) {
		}
	}
}