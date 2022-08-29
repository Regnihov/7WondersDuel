enum Resource{
	COIN(-1), CLAY(0), WOOD(1), STONE(2), GLASS(3), PAPYRUS(4);

	private int id;
	Resource(int id){this.id=id;}
	public int ID(){return id;}
}
