package Roulette;

import java.util.ArrayList;


import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

//This class provides utilities for the roulette program 
public class RouletteUtil 
{	
	static double INNERCIRCLERADIUS = Roulette.INNERCIRCLERADIUS;
	static double OUTERCIRCLERADIUS = Roulette.OUTERCIRCLERADIUS;
			
	static VisualPocket pocket00 = null;
	static VisualPocket pocket0 = null;
	static VisualPocket pocket1 = null;
	static VisualPocket pocket2 = null;
	static VisualPocket pocket3 = null;
	static VisualPocket pocket4 = null;
	static VisualPocket pocket5 = null;
	static VisualPocket pocket6 = null;	
	static VisualPocket pocket7 = null;
	static VisualPocket pocket8 = null;
	static VisualPocket pocket9 = null;
	static VisualPocket pocket10 = null;
	static VisualPocket pocket11 = null;
	static VisualPocket pocket12 = null;
	static VisualPocket pocket13 = null;
	static VisualPocket pocket14 = null;
	static VisualPocket pocket15 = null;
	static VisualPocket pocket16 = null;
	static VisualPocket pocket17 = null;
	static VisualPocket pocket18 = null;
	static VisualPocket pocket19 = null;
	static VisualPocket pocket20 = null;
	static VisualPocket pocket21 = null;
	static VisualPocket pocket22 = null;
	static VisualPocket pocket23 = null;
	static VisualPocket pocket24 = null;
	static VisualPocket pocket25 = null;
	static VisualPocket pocket26 = null;
	static VisualPocket pocket27 = null;
	static VisualPocket pocket28 = null;
	static VisualPocket pocket29 = null;
	static VisualPocket pocket30 = null;
	static VisualPocket pocket31 = null;
	static VisualPocket pocket32 = null;
	static VisualPocket pocket33 = null;
	static VisualPocket pocket34 = null;
	static VisualPocket pocket35 = null;
	static VisualPocket pocket36 = null;
	
	//ArrayList containing the pocketsPane. Used to loop through them.
	protected static ArrayList<VisualPocket> pocketsList = new ArrayList<VisualPocket>();
	
	static protected void createPocketObjects()
	{
		Path path00 = createPath();
		Path path0 = createPath();
		Path path1 = createPath();
		Path path2 = createPath();
		Path path3 = createPath();
		Path path4 = createPath();
		Path path5 = createPath();
		Path path6 = createPath();
		Path path7 = createPath();
		Path path8 = createPath();
		Path path9 = createPath();
		Path path10 = createPath();
		Path path11 = createPath();
		Path path12 = createPath();
		Path path13 = createPath();
		Path path14 = createPath();
		Path path15 = createPath();
		Path path16 = createPath();
		Path path17 = createPath();
		Path path18 = createPath();
		Path path19 = createPath();
		Path path20 = createPath();
		Path path21 = createPath();
		Path path22 = createPath();
		Path path23 = createPath();
		Path path24 = createPath();
		Path path25 = createPath();
		Path path26 = createPath();
		Path path27 = createPath();
		Path path28 = createPath();
		Path path29 = createPath();
		Path path30 = createPath();
		Path path31 = createPath();
		Path path32= createPath();
		Path path33 = createPath();
		Path path34 = createPath();
		Path path35 = createPath();
		Path path36 = createPath();
		
		pocket00 = new VisualPocket("00", Color.GREEN, path00);
		pocket0 = new VisualPocket("0", Color.GREEN, path0);
		pocket1 = new VisualPocket("1", Color.RED, path1);
		pocket2 = new VisualPocket("2", Color.BLACK, path2);
		pocket3 = new VisualPocket("3", Color.RED, path3);
		pocket4 = new VisualPocket("4", Color.BLACK, path4);
		pocket5 = new VisualPocket("5", Color.RED, path5);
		pocket6 = new VisualPocket("6", Color.BLACK, path6);	
		pocket7 = new VisualPocket("7", Color.RED, path7);
		pocket8 = new VisualPocket("8", Color.BLACK, path8);
		pocket9 = new VisualPocket("9", Color.RED, path9);
		pocket10 = new VisualPocket("10", Color.BLACK, path10);
		pocket11 = new VisualPocket("11", Color.BLACK, path11);
		pocket12 = new VisualPocket("12", Color.RED, path12);
		pocket13 = new VisualPocket("13", Color.BLACK, path13);
		pocket14 = new VisualPocket("14", Color.RED, path14);
		pocket15 = new VisualPocket("15", Color.BLACK, path15);
		pocket16 = new VisualPocket("16", Color.RED, path16);
		pocket17 = new VisualPocket("17", Color.BLACK, path17);
		pocket18 = new VisualPocket("18", Color.RED, path18);
		pocket19 = new VisualPocket("19", Color.RED, path19);
		pocket20 = new VisualPocket("20", Color.BLACK, path20);
		pocket21 = new VisualPocket("21", Color.RED, path21);
		pocket22 = new VisualPocket("22", Color.BLACK, path22);
		pocket23 = new VisualPocket("23", Color.RED, path23);
		pocket24 = new VisualPocket("24", Color.BLACK, path24);
		pocket25 = new VisualPocket("25", Color.RED, path25);
		pocket26 = new VisualPocket("26", Color.BLACK, path26);
		pocket27 = new VisualPocket("27", Color.RED, path27);
		pocket28 = new VisualPocket("28", Color.BLACK, path28);
		pocket29 = new VisualPocket("29", Color.BLACK, path29);
		pocket30 = new VisualPocket("30", Color.RED, path30);
		pocket31 = new VisualPocket("31", Color.BLACK, path31);
		pocket32 = new VisualPocket("32", Color.RED, path32);
		pocket33 = new VisualPocket("33", Color.BLACK, path33);
		pocket34 = new VisualPocket("34", Color.RED, path34);
		pocket35 = new VisualPocket("35", Color.BLACK, path35);
		pocket36 = new VisualPocket("36", Color.RED, path36);
		
		pocketsList.add(pocket00);
		pocketsList.add(pocket1);
		pocketsList.add(pocket13);
		pocketsList.add(pocket36);
		pocketsList.add(pocket24);
		pocketsList.add(pocket3);
		pocketsList.add(pocket15);
		pocketsList.add(pocket34);
		pocketsList.add(pocket22);
		pocketsList.add(pocket5);
		pocketsList.add(pocket17);
		pocketsList.add(pocket32);
		pocketsList.add(pocket20);
		pocketsList.add(pocket7);
		pocketsList.add(pocket11);
		pocketsList.add(pocket30);
		pocketsList.add(pocket26);
		pocketsList.add(pocket9);
		pocketsList.add(pocket28);
		pocketsList.add(pocket0);
		pocketsList.add(pocket2);
		pocketsList.add(pocket14);
		pocketsList.add(pocket35);
		pocketsList.add(pocket23);
		pocketsList.add(pocket4);
		pocketsList.add(pocket16);
		pocketsList.add(pocket33);
		pocketsList.add(pocket21);
		pocketsList.add(pocket6);
		pocketsList.add(pocket18);
		pocketsList.add(pocket31);
		pocketsList.add(pocket19);
		pocketsList.add(pocket8);
		pocketsList.add(pocket12);
		pocketsList.add(pocket29);
		pocketsList.add(pocket25);
		pocketsList.add(pocket10);
		pocketsList.add(pocket27);			
	}
	
	//Creates a custom path used to build the visual roulette' pockets
	static private Path createPath()
	{
		 	Path path = new Path();		
			MoveTo moveTo = new MoveTo();
			moveTo.setX(0.0);
			moveTo.setY(0.0);
			
			double CircleAngle_Rad = 2*Math.PI; //The angle of a circle, in radians
			double angle = CircleAngle_Rad/38.0/2.0; //Full circle angle (2*PI) divised by the number of roulette pockets(38), divised by 2 (to make trigonometric calculations by using right triangles)
			
			ArcTo arcTo = new ArcTo();
			arcTo.setX(2*(Math.sin(angle)*INNERCIRCLERADIUS)); //x calculation from moveTo
			arcTo.setY(0);
			arcTo.setRadiusX(INNERCIRCLERADIUS);
			arcTo.setRadiusY(INNERCIRCLERADIUS);
			arcTo.setSweepFlag(true);
			
			LineTo lineTo = new LineTo();
			lineTo.setX(arcTo.getX() + (OUTERCIRCLERADIUS - INNERCIRCLERADIUS)*(Math.sin(angle)));
			lineTo.setY(arcTo.getY() - (OUTERCIRCLERADIUS - INNERCIRCLERADIUS)*(Math.cos(angle)));
			
			ArcTo arcTo2 = new ArcTo();
			arcTo2.setX(lineTo.getX() - 2*(Math.sin(angle)*OUTERCIRCLERADIUS)); //x calculation from moveTo
			arcTo2.setY(lineTo.getY());
			arcTo2.setRadiusX(OUTERCIRCLERADIUS);
			arcTo2.setRadiusY(OUTERCIRCLERADIUS);
			
			LineTo lineTo2 = new LineTo();
			lineTo2.setX(0);
			lineTo2.setY(0);
			
			path.getElements().addAll(moveTo, arcTo, lineTo, arcTo2, lineTo2);
			path.setStroke(Color.WHITE);
			path.setStrokeWidth(2);
			path.setStrokeLineCap(StrokeLineCap.BUTT);
			
			return path;
	}

}
