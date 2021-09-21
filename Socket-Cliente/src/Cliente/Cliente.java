package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
	final String HOST = "localhost"; //DEFINE HOST Y PUERTO
	final int PUERTO = 5000;
	Scanner scanner = new Scanner(System.in); //Lee Datos
	DataInputStream datainputstream; //Recibe Datos
	DataOutputStream dataoutputstream; //Recibe Datos
	
	public void RealizarConexionServidor(int opcion, String mensaje) {
		try {
			//
			Socket socket = new Socket(HOST, PUERTO); //Inicializamos la CLase Socket
			//Definimos los Data...put para que reciba los Datos y los envie a la Clase Servidor 
			datainputstream = new DataInputStream(socket.getInputStream());
			dataoutputstream = new DataOutputStream(socket.getOutputStream());
			
			dataoutputstream.writeUTF(opcion + "," + mensaje); //
			mensaje = datainputstream.readUTF();
			System.out.println(mensaje); //Imprime el mensaje del servidor
			socket.close(); // Cierra el proceso de la Clase Socket
			
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("\nOperación Finalizada\n");
		}
	}
	
	public void Menu() { //Creamos metodo para interacturar con el cliente
		System.out.println("Bienvenid@");
		System.out.println("¿Que desea hacer?\n");
		
		int opcion = CapturarOpcion(); //Inicializamos la clase CapturarOpcion()
		String mensaje;
		
		switch (opcion) { //se crea Swtich donde dependiendo la opcion del cliente realizar una accion
		
		case 1: //Si eliege la opcion 1 de CapturarOpcion 
			mensaje = CapturarDatosCuenta(); //Inicializara la captura de datos para enviarlos a la clase servidor,
			RealizarConexionServidor(opcion, mensaje); //establece la conexion con el servidor para almacenar la informacion obtenida
			break;

		case 2: //Si eliege la opcion 2 de CapturarOpcion 
			mensaje = CapturarCuenta(); // Inicializara la captura de datos para enviarlos a la clase servidor
			RealizarConexionServidor(opcion, mensaje); //una vez establecida la conexion con el servidor realizara una consulta apartir de la informacion dada
			break;
		}
	}
	
	public int CapturarOpcion() {
		System.out.println("Eliga una Opción: ");
		System.out.println("1) Registrar una Cuenta");
		System.out.println("2) Realizar una Consulta");
		return scanner.nextInt(); // Retorna a la consola para interactuar con el cliente
	}
	
	public String CapturarDatosCuenta() { 
		System.out.println("\nDigite Numero de Cuenta");
		String cuenta = scanner.next();
		
		System.out.println("Digite Valor a Ingresar a la Cuenta");
		String valor = scanner.next();
		
		return cuenta + "," + valor; //Captura los Datos de la cuenta y el valor separdos por un coma
	}
	
	public String CapturarCuenta() {
		System.out.println("\nDigite Numero de Cuenta");
		return scanner.next();  // Retorna a la consola para interactuar con el cliente
	}
	
}
