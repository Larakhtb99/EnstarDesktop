package fr.ensta.servPattern;
import java.io.IOException;
import java.net.Socket;

/**
 * Processus de Echange (anciennement ServeurSpecifique)
 */
public class ProcessusEchange extends Thread {

	public ServeurTCP getMonServeurTCP() {
		return monServeurTCP;
	}

	private Socket clientSocket;
	private ServeurTCP monServeurTCP;
	private String nom;
	boolean isloggedin;

	public ProcessusEchange(Socket uneSocket, ServeurTCP unServeur, String name) {
		super("ServeurThread");
		clientSocket = uneSocket;
		System.out.println("[ProcessusEchange] CLIENT : " + clientSocket);
		monServeurTCP = unServeur;
		nom = name;
		isloggedin = true;
	}


	public void run() {
		while(isloggedin){
			try {
				monServeurTCP.getProtocole().execute( monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream(), this );
				System.out.println("[ProcessusEchange] Processus transaction fait");
			} catch (IOException e) {
				System.err.println("[ProcessusEchange] Exception : " + e );
				e.printStackTrace();
			}
		}
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public String getNom() {
		return nom;
	}
}
