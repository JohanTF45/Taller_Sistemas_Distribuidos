package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
	final String HOST = "localhost";
	final int PUERTO = 5000;
	Scanner scanner = new Scanner(System.in);
	DataInputStream datainputstream;
	DataOutputStream dataoutputstream;
	
	public void RealizarConexionServidor(int opcion, String mensaje) {
		try {
			Socket socket = new Socket(HOST, PUERTO);
			datainputstream = new DataInputStream(socket.getInputStream());
			dataoutputstream = new DataOutputStream(socket.getOutputStream());
			
			dataoutputstream.writeUTF(opcion + "," + mensaje);
			mensaje = datainputstream.readUTF();
			System.out.println(mensaje);
			socket.close();
			
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("\nOperación Finalizada\n");
		}
	}
	
	public void Menu() {
		System.out.println("Bienvenido");
		System.out.println("¿Que desea hacer?\n");
		
		int opcion = CapturarOpcion();
		String mensaje;
		
		switch (opcion) {
		
		case 1:
			mensaje = CapturarDatosCuenta();
			RealizarConexionServidor(opcion, mensaje);
			break;

		case 2:
			mensaje = CapturarCuenta();
			RealizarConexionServidor(opcion, mensaje);
			break;
		}
	}	
	
	public String CapturarDatosCuenta() {
		System.out.println("\nDigite Numero de Cuenta");
		String cuenta = scanner.next();
		
		System.out.println("Digite Valor a Ingresar a la Cuenta");
		String valor = scanner.next();
		
		return cuenta + "," + valor;	
	}
	
	public String CapturarCuenta() {
		System.out.println("\nDigite Numero de Cuenta");
		return scanner.next();
	}
	
	public int CapturarOpcion() {
		System.out.println("Eliga una Opción: ");
		System.out.println("1) Registrar una Cuenta");
		System.out.println("2) Realizar una Consulta");
		return scanner.nextInt();
	}
	
}
