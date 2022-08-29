import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


class Main {
	//finds Color based on its name
	public static Color findColor(String name){
		for(Color c : Color.values()){
			if(c.name().equals(name))return c;
		}
		return Color.RESET;
	}
	public static Resource findResource(String name){
		for(Resource c : Resource.values()){
			if(c.name().equals(name))return c;
		}
		return null;
	}
	//reads line of file containing cards
	public static Card readLine(Scanner sc){
		int lvl=66;
		if(sc.hasNextInt()){
			lvl=sc.nextInt();
		}
		String name=sc.next();
		Color color = findColor(sc.next());
		Integer[] c = new Integer[]{6,6,6,6,6,6};
		for(int i = 0; i<6; i++)c[i] = sc.nextInt();
		Cost cost = new Cost(c);
		boolean b = sc.nextBoolean();
		int points = sc.nextInt();
		switch(color){
			case GRAY:
			case BROWN:
				Resource resource = findResource(sc.next());
				int amount = sc.nextInt();
				sc.nextLine();
				return new ResourceCard(lvl,color,name,cost,b,points, resource, amount);
			case RED:
				int dmg = sc.nextInt();
				sc.nextLine();
				return new RedCard(lvl,color,name,cost,b,points, dmg);
			default:
				sc.nextLine();
				return new Card(lvl,color,name,cost,b,points);
		}
	}
	public static final String inputFileName = "cards.csv";

	public static List<Card> readCards(){
		List<Card> list = new ArrayList<Card>();
		try{
			File file = new File(inputFileName);
			Scanner sc = new Scanner(file);
			sc.nextLine();
			sc.nextLine();
			sc.useDelimiter(";");
			while(sc.hasNextLine()){
				Card c = readLine(sc);
				list.add(c);
				//System.out.println(c);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}



	public static void main(String[] args) {
		List<Card> cards = readCards();
		Board b = new Board("round1.csv", cards);
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		boolean roundFinished=false;
		Conflict c = new Conflict(p1, p2);
		while(!roundFinished){
			roundFinished = p1.takeTurn(b,c);
			if(!roundFinished)p2.takeTurn(b,c);
		}
		System.out.println("Round finished!");
	}
}
