
class RedCard extends Card{
	int damage;

	RedCard(int lvl, Color color, String string, Cost cost, Boolean hac, int points, int amount){
		super(lvl, color, string, cost, hac, points);
		damage = amount;
	}

	public String toString(){
		return super.toString()+" DMG:"+damage;
	}

}
