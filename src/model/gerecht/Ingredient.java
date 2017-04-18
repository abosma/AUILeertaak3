package model.gerecht;

public class Ingredient {
	private String naam;
	
	public Ingredient(String nm) {
		this.naam = nm;
	}
	
	public String getNaam(){
		return this.naam;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Ingredient))
			return false;
		Ingredient temporaryIngredient = (Ingredient)o;
		return temporaryIngredient.getNaam().equals(this.getNaam());
	}
}
