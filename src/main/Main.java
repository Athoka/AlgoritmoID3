package main;

public class Main {

	public static void main(String[] args) {
		try {
			
			Controlador c = new Controlador();
			c.ejecutarControlador();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
