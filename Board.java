import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


class Board{
	int[][] places;
	int width;
	int height;
	List<Card> cards;
	List<List<Integer>> cardPositions;
	Board(String settingFileName, List<Card> cardsThisRound){
		File file = new File(settingFileName);
		cards = new ArrayList<Card>();
		cardPositions = new ArrayList<List<Integer>>();
		try{
			Scanner sc = new Scanner(file);
			sc.useDelimiter(";");
			width = 0;
			height = 0;
			int current = 0;
			while(current!=9){
				current = sc.nextInt();
				width++;
			}
			width--;
			while(sc.hasNextLine()){
				height++;
				sc.nextLine();
			}
			places = new int[height][width];
			sc = new Scanner(file);
			sc.useDelimiter(";");
			Random r = new Random();
			for(int i = 0; i<height; i++){
				int previous=0;
				for(int j = 0; j<width; j++){
					places[i][j]=sc.nextInt();
					if(previous==0 && places[i][j]!=0){
						int t = r.nextInt(cardsThisRound.size());
						Card c = cardsThisRound.remove(t);
						if(i==height-1)c.setAvailable(true);
						cards.add(c);
						List<Integer> l = new ArrayList<Integer>();
						l.add(i);
						l.add(j);
						cardPositions.add(l);
						sc.nextInt();sc.nextInt();
						j+=2;
					}
				}
				sc.nextLine();
			}
		}catch(Exception e){
			System.out.println(e);
		}
		linkCards();
	}

	private void linkCards(){
		for(int i=0; i<cards.size(); i++){
			int h = cardPositions.get(i).get(0);	
			int w = cardPositions.get(i).get(1);
			Card current = cards.get(i);
			//System.out.println(current + " h:"+h+" w:"+w);
			if(h<height-1){
				if(w>1){
					if(places[h+1][w-2]!=0){
						for(int j = i+1; j<cards.size(); j++){
							int h2 = cardPositions.get(j).get(0);
							int w2 = cardPositions.get(j).get(1);
							//System.out.println(j+" h2:"+h2+" w2:"+w2);
							if(h+1==h2 && w-2==w2){
								current.addOver(cards.get(j));
								//System.out.println("add left");
								j++;
							}
						}
					}
				}
				if(w<width-3){
					//only right
					if(places[h+1][w+2]!=0){
						for(int j = i+1; j<cards.size(); j++){
							int h2 = cardPositions.get(j).get(0);
							int w2 = cardPositions.get(j).get(1);
							//System.out.println(j+" h2:"+h2+" w2:"+w2);
							if(h+1==h2 && w+2==w2){
								current.addOver(cards.get(j));
								//System.out.println("add right");
								j++;
							}
						}
					}
				}
			}
		}
	}

	public int availableCards(){
		int count = 0;
		for(Card c : cards)if(c.getAvailable())count++;
		return count;
	}

	private void fixPlaces(int h, int w, int newNumber){
		places[h][w]=newNumber;
		places[h][w+1]=newNumber;
		places[h][w+2]=newNumber;
	}

	public Card takeCard(int cardId){
		for(int i = 0; i<cards.size(); i++){
			if(cards.get(i).getAvailable()&&cardId!=0){
				cardId--;
			}else if(cardId==0 && cards.get(i).getAvailable()){
				int h = cardPositions.get(i).get(0);
				int w = cardPositions.get(i).get(1);
				fixPlaces(h,w,0);
				//make cards available
				//for(Card c : cards)System.out.println(c);
				for(int j = 0; j<i; j++){
					if(cards.get(j).containsOver(cards.get(i))){
						cards.get(j).removeOver(cards.get(i));
						//System.out.println("removing card over");
						if(cards.get(j).getAvailable()){
							System.out.println("card "+cards.get(j)+" got available!");
							int h2 = cardPositions.get(j).get(0);
							int w2 = cardPositions.get(j).get(1);
							fixPlaces(h2,w2,2);
						}
					}
				}
				cardPositions.remove(i);
				return cards.remove(i);
			}
		}
		return null;
	}

	public List<Card> getCards(){return cards;}

	public String toString(){
		System.out.println();
		int index = 0;
		for(int i = 0; i<height; i++){
			int prev=0;
			for(int j = 0; j<width; j++){
				if(prev==0&&places[i][j]!=0){
					if(places[i][j]==2){
						Color c = cards.get(index).getColor();
						System.out.print(c+"222"+Color.RESET);
						j+=2;
					}else{
						System.out.print("111");
						j+=2;
					}
					index++;
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		return "";
	}
}
