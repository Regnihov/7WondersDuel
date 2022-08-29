import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Player{
	String name;
	List<Card> cardsOwned;
	Integer[] resourcesOwned;
	int moneyOwned;


	Player(String name){
		this.name = name;
		cardsOwned = new ArrayList<Card>();
		resourcesOwned = new Integer[]{0,0,0,0,0};
		moneyOwned = 7;
	}

	private void showAvailable(Board b){
		List<Card> l = b.getCards();
		int count=0;
		for(int i = 0; i<l.size(); i++){
			if(l.get(i).getAvailable())System.out.println(count++ +" "+ l.get(i));
		}
	}

	public int countCardsOfType(Color color){
		int count = 0;
		for(Card c : cardsOwned){
			if(c.getColor() == color)count++;
		}
		return count;
	}

	public boolean takeTurn(Board b, Conflict conflict){
		System.out.println(b);
		System.out.println(conflict);
		if(b.availableCards()==0)return true;
		showAvailable(b);
		System.out.println(this + "\npick card [0-"+(b.availableCards()-1)+"]");
		Scanner action = new Scanner(System.in);
		int c = action.nextInt();
		Card taken = b.takeCard(c);
		System.out.println(taken);
		System.out.println("pick action [0-2]");
		int a = action.nextInt();
		switch(a){
			case 0:
				//TODO pay 
				System.out.println("You bought "+taken);
				taken.trigger(this);
				cardsOwned.add(taken);
				break;
			case 1:
				System.out.println("Sold! You got "+ (countCardsOfType(Color.YELLOW)+2) +"$");
				moneyOwned += countCardsOfType(Color.YELLOW)+2;
				break;
			case 2:
				System.out.println("Wonderful!");
				//card.buildWonderOn(wonder);
				break;
		}
		return false;
	}

	public void getResource(Resource r, int amount){
		resourcesOwned[r.ID()]+=amount;
	}

	public List<Card> getCardsOwned(){return cardsOwned;}

	public int getResourceAmountOwned(int resourceId){return resourcesOwned[resourceId];}
	public String toString(){
		return name+": "+moneyOwned+"$ "+
			resourcesOwned[0]+" clay "+
			resourcesOwned[1]+" wood "+
			resourcesOwned[2]+" stone "+
			resourcesOwned[3]+" glass "+
			resourcesOwned[4]+" papyrus ";
	}
}
