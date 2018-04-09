package persistenciaDatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class OperacionesFicheros {

	public OperacionesFicheros() {}

	public ArrayList<ArrayList<String>> leerEjemplos (String fichero) throws Exception  {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			ArrayList<ArrayList<String>> listaEjemplos = new ArrayList<ArrayList<String>>();
			
			String line = br.readLine();
			
			while (line != null) {
				ArrayList<String> fila = new ArrayList<String>();
				String[] partes = line.split(",");
				for(int j = 0; j < partes.length; j++) {
					fila.add(partes[j]);
				}
				listaEjemplos.add(fila);
				line = br.readLine();
			}
			
			br.close();
			return listaEjemplos;
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	public ArrayList<String> leerAtributos (String fichero) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			ArrayList<String> listaAtributos = new ArrayList<String>();
			
			String line = br.readLine();
			
			while (line != null) {
				String[] partes = line.split(",");
				for(int j = 0; j < partes.length; j++) {
					listaAtributos.add(partes[j]);
				}
				line = br.readLine();
			}
			
			br.close();
			return listaAtributos;
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
