package Roulette;

//The LogicalPocket objects are made of a number and a color, corresponding to the pockets of the wheel
public class LogicalPocket 
{
	private String num;
	private String color;
	
	protected LogicalPocket(String num, String color)
	{
		this.num = num;
		this.color = color;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
