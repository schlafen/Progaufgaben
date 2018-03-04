package pack;

public class Kunde implements Comparable<Kunde>{
	
	//attribute
	private int kundenNr;
	private String kundenName;
	private String land;
	private int alter;
	
	//Konstruktor
	public Kunde(int nr, String name, String country, int age) {
		this.kundenNr = nr;
		this.kundenName = name;
		this.land = country;
		this.alter = age;
	}
	
	@Override
	public int compareTo(Kunde k) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getKundenNr() {
		return kundenNr;
	}

	public void setKundenNr(int kundenNr) {
		this.kundenNr = kundenNr;
	}

	public String getKundenName() {
		return kundenName;
	}

	public void setKundenName(String kundenName) {
		this.kundenName = kundenName;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	
	
	
	

}
