package model.informatiesysteem;

import java.util.ArrayList;

import model.gerecht.Gerecht;
import model.gerecht.Ingredient;

public class InformatieSysteem {
	private ArrayList<Ingredient> ingredienten;
	private ArrayList<Gerecht> gerechten;
	
	public InformatieSysteem(){
		ingredienten = new ArrayList<Ingredient>();
		gerechten = new ArrayList<Gerecht>();
	}
	
	public void addGerecht(Gerecht g){
		if(!this.gerechten.contains(g)){
			this.gerechten.add(g);
		}
	}
	
	public void removeGerecht(String na){
		for(Gerecht g: getGerechten()){
			if(na.equals(g.getNaam())){
				gerechten.remove(g);
				return;
			}
		}
		return;
	}
	
	public void addIngredient(Ingredient i){
		if(!this.ingredienten.contains(i)){
			this.ingredienten.add(i);
		}
	}
	
	public boolean removeIngredient(Ingredient i){
		return this.ingredienten.remove(i);
	}

	public ArrayList<Ingredient> getIngredienten() {
		return ingredienten;
	}

	public ArrayList<Gerecht> getGerechten() {
		return gerechten;
	}
	
	public Ingredient getIngredient(String naam){
		for(Ingredient i : ingredienten){
			if(i.getNaam().equals(naam))
				return i;
		}
		return null;
	}
}
