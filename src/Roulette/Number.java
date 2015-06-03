package Roulette;

public class Number 
{
	int number = 0;
	String color = "";
	
	public static final String COLOR_BLACK = "black";
	public static final String COLOR_RED = "red";
	public static final String COLOR_GREEN = "green";
	public static final int MAX_NUMBER  = 36;
	public static final int MIN_NUMBER = 0;
	
	
	public Number (int p_number, String p_color)
	{
		if(!setNumber(p_number))
		{
			System.out.println("Invalid number");
		}
		
		if(!setColor(p_color))
		{
			System.out.println("Invalid color");
		}
		
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public boolean setNumber(int p_number)
	{
		boolean validationOK = validateNumber(p_number);
		
		if (validationOK)
		{
			number = p_number;
		}
		
		return validationOK;
	}
	
	private boolean setColor(String p_color) 
	{
		boolean validationOK = validateColor(p_color);
		
		
		if(validationOK)
		{
			color = p_color;
		}
		
		return validationOK;
	}
	
	public boolean validateNumber(int p_number)
	{
		return (p_number >=MIN_NUMBER && p_number <= MAX_NUMBER);
	}
	
	public boolean validateColor(String p_color)
	{
		return p_color.toLowerCase().equals(COLOR_BLACK) || p_color.toLowerCase().equals(COLOR_RED) || p_color.toLowerCase().equals(COLOR_GREEN);	
	}
}
