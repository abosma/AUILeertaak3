package model.gerecht;

import java.util.ArrayList;

public class Gerecht {
	private String status; // lekker simpel.
	private ArrayList<Ingredient> ingredienten; 
	private String naam;
	private double prijs;
	private int bereidingsduur;
	
	public Gerecht(String nm, double pr, int bd, ArrayList<Ingredient> in) {
		this.naam = nm;
		this.prijs = pr;
		this.bereidingsduur = bd;
		this.ingredienten = in;
		this.status = "niet actief";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public int getBereidingsduur() {
		return bereidingsduur;
	}

	public void setBereidingsduur(int bereidingsduur) {
		this.bereidingsduur = bereidingsduur;
	}

	public ArrayList<Ingredient> getIngredienten() {
		return ingredienten;
	}
	
	public void addIngredient(Ingredient i){
		if(!this.ingredienten.contains(i)){
			this.ingredienten.add(i);
		}
	}
	
	public boolean removeIngredient(Ingredient i){
		if(this.ingredienten.contains(i)){
			return this.ingredienten.remove(i);
		}
		return false;
	}
	
	@Override
	public boolean equals(Object o){
		if(!o.equals(this)){
			return false;
		}else{
			Gerecht temp = (Gerecht)o;
			if(this.naam.equals(temp.naam) 
				&& this.prijs == temp.prijs 
				&& this.ingredienten.containsAll(temp.ingredienten)){
				return true;
			}else{
				return false;
			}
		}
	}
}
