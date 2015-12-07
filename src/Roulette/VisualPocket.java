package Roulette;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class VisualPocket
{
	private String number;
	private Color color;
	private Path path;
	
	//Pocket constructor
	//param num as a string, to be able to use the 00 num
	//path as a parameter, to be able to link a path to a num after graphical Ball&Pocket colision test
	protected VisualPocket(String num, Color col, Path p_path)
	{
		number = num;
		color = col;
		setPath(p_path);
	}
	
	protected void setPath(Path p_path)
	{
		this.path = p_path;
	}
	
	protected Path getPath()
	{
		return path;
	}
	
	protected String getNumber()
	{
		return number;
	}
	
	protected void setNumber(String numb)
	{
		number = numb;
	}
	
	protected Color getColor()
	{
		return color;
	}
}
