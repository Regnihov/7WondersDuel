
public class Cost{
	private Integer[] cost;

	Cost(Integer[] cost){
		this.cost=cost;
	}
	public int coin(){return cost[0];}
	public int clay(){return cost[1];}
	public int stone(){return cost[2];}
	public int wood(){return cost[3];}
	public int glass(){return cost[4];}
	public int paper(){return cost[5];}

	public String toString(){
		return "["+cost[0]+","+
		cost[1]+","+
		cost[2]+","+
		cost[3]+","+
		cost[4]+","+
		cost[5]+"]";}
}
