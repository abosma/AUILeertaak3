package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import model.gerecht.Gerecht;
import model.gerecht.Ingredient;
import model.informatiesysteem.InformatieSysteem;
import server.Conversation;
import server.Handler;

public class GerechtController implements Handler {

	InformatieSysteem infoSys;
	
	public GerechtController(InformatieSysteem is) {
		this.infoSys = is;
	}
	
	@Override
	public void handle(Conversation conversation) {
		System.out.println(conversation.getRequestedURI());
		String uri = conversation.getRequestedURI();
		String prefix = "/gerecht/";
		if(uri.startsWith(prefix+"allegerechten")){
			alleGerechten(conversation);
		} 
		else if (uri.startsWith(prefix+"voeggerechttoe")){
			voegGerechtToe(conversation);
		}
		else if (uri.startsWith(prefix+"verwijdergerecht")){
			verwijderGerecht(conversation);
		}
	}

	/* De methode kan ��n gerecht toeveogen op basis van een Json Input met de volgende structuur:
	 * {
	 * 	"naam": "<naam>",
	 *  "prijs": <prijs>,
	 *  "tijd":  <bereidingsduur>,
	 *  "ingredienten": <JsonArray {"naam": "<naam>", ...} >
	 * }
	 * */
	
	private void verwijderGerecht(Conversation conversation) {
		JsonObject jsonGerecht = (JsonObject) conversation.getRequestBodyAsJSON();
		String naam = jsonGerecht.getString("naam");
		infoSys.removeGerecht(naam);
		
		
	}
	
	private void voegGerechtToe(Conversation conversation) {
		JsonObject jsonGerecht = (JsonObject) conversation.getRequestBodyAsJSON();
		

		String naam = jsonGerecht.getString("naam");
		String strPrijs = jsonGerecht.getString("Prijs"); 
		String strTijd = jsonGerecht.getString("tijd");
		
		JsonArray ja = (JsonArray) jsonGerecht.get("ingredienten");
		
		ArrayList<Ingredient> ingredienten = new ArrayList<Ingredient>();
		
		for (JsonValue jsonValue : ja) {
			
			String iNaam = jsonValue.toString(); 
			
			Ingredient tmp = infoSys.getIngredient(iNaam);
			if(tmp == null){
				tmp = new Ingredient(iNaam);
				infoSys.addIngredient(tmp);
			}
			ingredienten.add(tmp);
		}
		
		int tijd = Integer.parseInt(strTijd);
		double prijs = Double.parseDouble(strPrijs);
		
		Gerecht nieuwGerecht = new Gerecht(naam, prijs, tijd, ingredienten);
		
		infoSys.addGerecht(nieuwGerecht);
	}

	private void alleGerechten(Conversation conversation) {
		//ArrayBuilder waarin we alle gerechten gaan plaatsen
		JsonArrayBuilder jaGerechten = Json.createArrayBuilder();
		
		// loop over alle gerechten heen, die bekend zijn in het informatiesysteem
		for(Gerecht g : infoSys.getGerechten()){
//			System.out.println(g.getNaam());
			//maak van alle ingredienten van het grecht een JsonArray
			JsonArrayBuilder jabIngredienten = Json.createArrayBuilder();
			for(Ingredient i : g.getIngredienten()){
				JsonObjectBuilder jobIngredient = Json.createObjectBuilder();
				jobIngredient.add("naam", i.getNaam());
				jabIngredienten.add(jobIngredient.build());
			}

			//maak van het gerecht-object een JsonObject
			JsonObjectBuilder jobSingleDish = Json.createObjectBuilder();
			jobSingleDish
				.add("naam", g.getNaam())
				.add("prijs", g.getPrijs())
				.add("tijd", g.getBereidingsduur())
				.add("status", g.getStatus())
				.add("ingredienten", jabIngredienten.build());
			
			//Voeg het JsonObject toe aan de array met gerechten
			jaGerechten.add(jobSingleDish);
		}
		// verstuur de gerchten naar de clientside
		conversation.sendJSONMessage(jaGerechten.build().toString());
	}
}
