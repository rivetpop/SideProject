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

public class LogicalRoulette 
{	
	static double INNERCIRCLERADIUS = Roulette.INNERCIRCLERADIUS;
	static double OUTERCIRCLERADIUS = Roulette.OUTERCIRCLERADIUS;
			
	static Pocket pocket00 = null;
	static Pocket pocket0 = null;
	static Pocket pocket1 = null;
	static Pocket pocket2 = null;
	static Pocket pocket3 = null;
	static Pocket pocket4 = null;
	static Pocket pocket5 = null;
	static Pocket pocket6 = null;	
	static Pocket pocket7 = null;
	static Pocket pocket8 = null;
	static Pocket pocket9 = null;
	static Pocket pocket10 = null;
	static Pocket pocket11 = null;
	static Pocket pocket12 = null;
	static Pocket pocket13 = null;
	static Pocket pocket14 = null;
	static Pocket pocket15 = null;
	static Pocket pocket16 = null;
	static Pocket pocket17 = null;
	static Pocket pocket18 = null;
	static Pocket pocket19 = null;
	static Pocket pocket20 = null;
	static Pocket pocket21 = null;
	static Pocket pocket22 = null;
	static Pocket pocket23 = null;
	static Pocket pocket24 = null;
	static Pocket pocket25 = null;
	static Pocket pocket26 = null;
	static Pocket pocket27 = null;
	static Pocket pocket28 = null;
	static Pocket pocket29 = null;
	static Pocket pocket30 = null;
	static Pocket pocket31 = null;
	static Pocket pocket32 = null;
	static Pocket pocket33 = null;
	static Pocket pocket34 = null;
	static Pocket pocket35 = null;
	static Pocket pocket36 = null;
	
	//ArrayList containing the pocketsPane. Used to loop through them.
	protected static ArrayList<Pocket> pocketsList = new ArrayList<Pocket>();
	
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
		
		pocket00 = new Pocket("00", Color.GREEN, path00);
		pocket0 = new Pocket("0", Color.GREEN, path0);
		pocket1 = new Pocket("1", Color.RED, path1);
		pocket2 = new Pocket("2", Color.BLACK, path2);
		pocket3 = new Pocket("3", Color.RED, path3);
		pocket4 = new Pocket("4", Color.BLACK, path4);
		pocket5 = new Pocket("5", Color.RED, path5);
		pocket6 = new Pocket("6", Color.BLACK, path6);	
		pocket7 = new Pocket("7", Color.RED, path7);
		pocket8 = new Pocket("8", Color.BLACK, path8);
		pocket9 = new Pocket("9", Color.RED, path9);
		pocket10 = new Pocket("10", Color.BLACK, path10);
		pocket11 = new Pocket("11", Color.BLACK, path11);
		pocket12 = new Pocket("12", Color.RED, path12);
		pocket13 = new Pocket("13", Color.BLACK, path13);
		pocket14 = new Pocket("14", Color.RED, path14);
		pocket15 = new Pocket("15", Color.BLACK, path15);
		pocket16 = new Pocket("16", Color.RED, path16);
		pocket17 = new Pocket("17", Color.BLACK, path17);
		pocket18 = new Pocket("18", Color.RED, path18);
		pocket19 = new Pocket("19", Color.RED, path19);
		pocket20 = new Pocket("20", Color.BLACK, path20);
		pocket21 = new Pocket("21", Color.RED, path21);
		pocket22 = new Pocket("22", Color.BLACK, path22);
		pocket23 = new Pocket("23", Color.RED, path23);
		pocket24 = new Pocket("24", Color.BLACK, path24);
		pocket25 = new Pocket("25", Color.RED, path25);
		pocket26 = new Pocket("26", Color.BLACK, path26);
		pocket27 = new Pocket("27", Color.RED, path27);
		pocket28 = new Pocket("28", Color.BLACK, path28);
		pocket29 = new Pocket("29", Color.BLACK, path29);
		pocket30 = new Pocket("30", Color.RED, path30);
		pocket31 = new Pocket("31", Color.BLACK, path31);
		pocket32 = new Pocket("32", Color.RED, path32);
		pocket33 = new Pocket("33", Color.BLACK, path33);
		pocket34 = new Pocket("34", Color.RED, path34);
		pocket35 = new Pocket("35", Color.BLACK, path35);
		pocket36 = new Pocket("36", Color.RED, path36);
		
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
