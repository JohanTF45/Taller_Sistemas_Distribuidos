package Cliente;

public class Main {
	
	public static void main(String[] args){
		Cliente RealizarConexionServidor = new Cliente();
		while (RealizarConexionServidor != null) {
			Cliente cliente = new Cliente(); //Conecta la Clase Cliente
			cliente.Menu();	// Inicia la Clase Menu
		}
	}
}