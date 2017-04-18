package controller;

import java.io.File;

import model.informatiesysteem.InformatieSysteem;
import server.JSONFileServer;
 
public class Application {
	public static void main(String[] args) {
		JSONFileServer server = new JSONFileServer(new File("src/my-app"), 8888);
		
		InformatieSysteem informatieSysteem = new InformatieSysteem();
		
		BasisController basisController = new BasisController();
		GerechtController gerechtController = new GerechtController(informatieSysteem);
		
		server.registerHandler("/student/presentie", basisController);
		server.registerHandler("/gerecht/allegerechten", gerechtController);
		server.start();
	}
}