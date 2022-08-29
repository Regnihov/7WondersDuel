
public class ResourceCard extends Card{
	Resource resource;
	int amount;

	ResourceCard(int lvl, Color color, String string, Cost cost, Boolean hac, int points, Resource resource, int amount){
		super(lvl, color, string, cost, hac, points);
		this.resource = resource;
		this.amount = amount;
	}

	public void buy(Player p){
		p.getResource(resource, amount);
	}

	public void trigger(Player p){
		p.getResource(resource, amount);
	}

	public String toString(){
		return super.toString()+" "+amount+" "+resource.name();
	}
}
