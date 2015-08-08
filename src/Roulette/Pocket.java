package Roulette;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Pocket
{
	int number;
	Color color;
	Path path;
	
	
	static int INNERCIRCLERADIUS = Roulette.INNERCIRCLERADIUS;
	static int OUTERCIRCLERADIUS = Roulette.OUTERCIRCLERADIUS;
	
	
	public Pocket(int num, Color col, Path p_path)
	{
		number = num;
		color = col;
		path = p_path;
	}
}
