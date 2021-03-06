/*
 * Servidor.java Servidor echo sencillo.
 */
package ejemplo.echosencillo;

import java.net.*;
import java.io.*;

public class Servidor {

	public static final int PUERTO = 2000;
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket servidor = new ServerSocket(PUERTO); /*Creacion del socket*/
		
		for(;;) {
			try {
				System.out.println("----Servidor aceptando conexiones----");
				try(Socket sock = servidor.accept()) {
					///////////*///////////
					 /* zona de servicio.
					 */
					InputStream in = sock.getInputStream(); /* entrada socket */
					
					Reader r1 = new InputStreamReader(in);
					PrintStream outred;
					try(BufferedReader inred = new BufferedReader(r1)) {
						OutputStream out = sock.getOutputStream(); /* salida socket */
						outred = new PrintStream(out);
						String linea;
						/* bucle ECHO */
						while((linea = inred.readLine()) != null) { /* lee de la red */
							System.out.println("Echoing: "+linea); /* echo por la pantalla */
							outred.println(linea); /* echo al cliente */
							if(linea.equals("Adios.")) { break; }
						}
					} /* salida socket */
					outred.close();
					outred.close();
					///////////*//////////
					/* Fin zona de servicio
					*/
				}
			} catch(IOException e) {
				e.printStackTrace(System.err);
			}
		}
	}
}
