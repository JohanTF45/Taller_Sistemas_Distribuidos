package Cliente;

public class Main {
	
	public static void main(String[] args){
		Cliente RealizarConexionServidor = new Cliente();
		while (RealizarConexionServidor != null) {
			Cliente cliente = new Cliente();
			cliente.Menu();	
		}
	}
}