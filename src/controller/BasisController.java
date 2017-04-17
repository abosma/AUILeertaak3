package controller;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import server.Conversation;
import server.Handler;

class BasisController implements Handler {
	public void LoginController() {
		
	}
	
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/basis")) {
			basis(conversation);
		}
	}
	
	private void basis(Conversation conversation){
		@SuppressWarnings("unused")
		JsonObject JsonObjIn = (JsonObject) conversation.getRequestBodyAsJSON();
		
		JsonObjectBuilder JsonBuilder = Json.createObjectBuilder();
		JsonBuilder.add("Test", "Hallo");
		
		String JsonOutString = JsonBuilder.build().toString();	
		conversation.sendJSONMessage(JsonOutString);
	}
}