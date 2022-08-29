import java.util.List;
import java.util.ArrayList;


public class Card{
	int level;
	Color color;
	String name;
	Cost cost;
	Boolean hasAlternateCost;
	Card alternateCost;
	int points;
	boolean available;
	List<Card> cardsOver;

	Card(int lvl, Color color, String string, Cost cost, Boolean hac, int points){
		cardsOver=new ArrayList<Card>();
		this.level=lvl;
		this.color=color;
		this.name=string;
		this.cost=cost;
		this.hasAlternateCost=hac;
		this.points=points;
		available=false;
	}

	/*Card(int lvl, String string){
		this.level=lvl;
		this.name=string;
	}*/
	public Color getColor(){return color;}
	public void setAvailable(boolean a){available=a;}
	public boolean getAvailable(){return available;}
	public void addOver(Card c){
		cardsOver.add(c);
		available=false;
	}
	public boolean containsOver(Card c){return cardsOver.contains(c);}
	public void removeOver(Card c){
		cardsOver.remove(c);
		if(cardsOver.isEmpty())available=true;
	}
	public void trigger(Player p){System.out.println(this.toString() + " got triggered");}

	public String getName(){return name;}
	public String toString(){
		//if(!cardsOver.isEmpty())System.out.print(cardsOver.get(0).getName());
		return color+name+Color.RESET+" "+cost;
	}
}
