package Servidor;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	final int PUERTO = 5000;
	String archivo = "C:/Users/JT/Documents/datos.txt";
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;
	ServerSocket servidor = null;
	Socket socket = null;
	
	public void EscucharCliente() {
		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("Iniciando Servidor");
			
			while (true) {
				socket = servidor.accept();
				System.out.println("Conectado");
				
				dataInputStream = new DataInputStream(socket.getInputStream());
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
				
				String mensaje = dataInputStream.readUTF();
				System.out.println("Datos enviados");
				System.out.println(mensaje);
				
				String DatosCuenta[] = mensaje.split(",");
				String resultado;
				
				switch (DatosCuenta[0]) {
				case "1":
					resultado = RegistrarCuenta(DatosCuenta[1], DatosCuenta[2]);
					dataOutputStream.writeUTF(resultado);					
					break;

				case "2":
					String valor = BuscarCuenta(DatosCuenta[1]);
					resultado = "\nEl Numero de  Cuenta:" + DatosCuenta[1] +"\nEl Valor Actual de su Cuenta es: $" + valor;
					dataOutputStream.writeUTF(resultado);
					break;
				}
				socket.close();
				
				System.out.println("Desconectado");
			}
		} catch (Exception e) {
			// TODO: handle exception}
			e.printStackTrace();
		}
	}
	
	public String RegistrarCuenta(String NumCuenta, String valor) {
		FileWriter fichero = null;
		PrintWriter PrintWriter = null;
		try {
			fichero = new FileWriter(archivo, true);
			PrintWriter = new PrintWriter(fichero);
			System.out.println(NumCuenta + "," + valor);
			PrintWriter.println(NumCuenta + "," + valor);
			return "Registro Grabado OK";			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero) {
					fichero.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}		
		return "NO-OK";
	}
	
	public String BuscarCuenta(String NumCuenta) throws IOException{
		String cadena;
		FileReader FileReader = new FileReader(archivo);
		BufferedReader BufferedReader = new BufferedReader(FileReader);
		while ((cadena = BufferedReader.readLine()) != null) {
			String DatosCuenta[] = cadena.split(",");
			if (DatosCuenta[0].equals(NumCuenta)) {
				return DatosCuenta[1];
			}
		}
		BufferedReader.close();
		return "La cuenta ingresada no se encuentra registrada,\n por favor vuelva a intentarlo o registre una nueva cuenta";
	}
}
