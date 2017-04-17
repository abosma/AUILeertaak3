package controller;

import java.io.File;

import server.JSONFileServer;
 
public class Application {
	public static void main(String[] args) {
		JSONFileServer server = new JSONFileServer(new File("src/my-app"), 8888);
		
		BasisController basisController = new BasisController();
		
		server.registerHandler("/student/presentie", basisController);
		server.start();
	}
}