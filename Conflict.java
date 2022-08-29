
class Conflict{
	int pawnPosition;
	boolean[] militaryTokenUp;
	Player left;
	Player right;
	Conflict(Player p1, Player p2){
		pawnPosition = 0;
		militaryTokenUp = new boolean[]{true,true,true,true};
		left=p1;
		right=p2;
	}

	public void moveConflict(Player p, int amount){
		if(p==left){
			pawnPosition+=amount;
		}else{
			pawnPosition-=amount;
		}
	}

	public String toString(){
		System.out.print("\u001B[41m\u001B[30m"+"L");
		if(militaryTokenUp[0])for(int i = 0; i<3; i++)System.out.print("\u001B[33m"+"5");
		if(militaryTokenUp[1])for(int i = 0; i<3; i++)System.out.print("\u001B[33m"+"2");
		for(int i = 0; i<2; i++)System.out.print("\u001B[37m"+" ");
		System.out.print("S");
		for(int i = 0; i<2; i++)System.out.print("\u001B[37m"+" ");
		if(militaryTokenUp[2])for(int i = 0; i<3; i++)System.out.print("\u001B[33m"+"2");
		if(militaryTokenUp[3])for(int i = 0; i<3; i++)System.out.print("\u001B[33m"+"5");
		System.out.print("\u001B[30m"+"L" + "\u001B[0m\n");
	/*RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	GRAY("\u001B[38;5;239m"),
	PURPLE("\u001B[35m"),
	BROWN("\u001B[38;5;52m"),
	RESET("\u001B[0m");*/
		return "";

	}
}

