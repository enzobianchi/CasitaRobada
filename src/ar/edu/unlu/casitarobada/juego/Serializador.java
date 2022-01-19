package ar.edu.unlu.casitarobada.juego;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Serializador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void guardarObjeto(String fileName,Object modelo) throws RemoteException {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream entradaDeDatos = new ObjectOutputStream(fileOut);
			entradaDeDatos.writeObject(modelo);
			entradaDeDatos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object cargarObjeto(String fileName) throws RemoteException {
		Object respuesta = null;
		try {
			ObjectInputStream salidaDeDatos = new ObjectInputStream(new FileInputStream(fileName));
			respuesta = salidaDeDatos.readObject();
			salidaDeDatos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return respuesta;
	}
}
