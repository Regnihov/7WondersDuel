
enum Color{
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"),
	GRAY("\u001B[38;5;239m"),
	PURPLE("\u001B[35m"),
	BROWN("\u001B[38;5;52m"),
	RESET("\u001B[0m");


	private final String color;
	private Color(String str){
		this.color = str;
	}
	public String toString(){return color;}
}
