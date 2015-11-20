package Roulette;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sun.javafx.collections.ObservableListWrapper;

import Casino.GameInterface;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.effect.Light;

public class Roulette extends GameInterface
{
	public Scene scene = null;
	private Pane root  = null;
	private Group buttonsGroup = null;
	private StackPane msgZone = null;
	
	public Button spinTheWheelButton = null;
	public Button removeLastBetButton = null;
	public Button removeAllBetsButton = null;
	
	//A stackPane made of a sphere (the ball) and a center point to check collision with the wheel's zones
	private StackPane rouletteBallStack = null;
	
	//Center point of the ball (see setBall() function)
	private Rectangle ballCenter = null;
	
	//Roulette ball
	private Circle rouletteBall = null;
	
	//Roulette wheel
	private StackPane rouletteWheel = null;
	Circle pocketsCircle = null;
	static final double INNERCIRCLERADIUS = 120.0;//Used to calculate the shape of the pockets, among others
	public static final double OUTERCIRCLERADIUS = 180.0;//Used to calculate the shape of the pockets, among others
	final double OUTERCIRCLESTROKE = 50.0;//Used to calculate the ball path, among others
	final double OUTERMOSTCIRCLERADIUS = OUTERCIRCLERADIUS+OUTERCIRCLESTROKE; //Used to calculate the position of the ball
	final double ballStackInnerWheelYTranslation = 15;//Distance between the outer edge of the wheel and the starting position of the ball
	int rouletteWheelXTranslation = 10;
	int rouletteWheelYTranslation = 10;
	
	//Rotation angle of the last spin of the ball around the wheel (in radians).
	//Used to do calculations for the ball animations.
	double finalSpinAngle;
	
		//Pane containing all the pockets pane
		private Pane pocketsPane = null;
			
		//Graphic wheel pockets (each made of a path and a label in a pane (see function createPocketPath())
		//The paths are made global variable because they are needed for collision(intersect) test with the ball
		Path pocket00Path = null;
		Path pocket0Path = null;
		Path pocket1Path = null;
		Path pocket2Path = null;
		Path pocket3Path = null;
		Path pocket4Path = null;
		Path pocket5Path = null;
		Path pocket6Path = null;
		Path pocket7Path = null;
		Path pocket8Path = null;
		Path pocket9Path = null;
		Path pocket10Path = null;
		Path pocket11Path = null;
		Path pocket12Path = null;
		Path pocket13Path = null;
		Path pocket14Path = null;
		Path pocket15Path = null;
		Path pocket16Path = null;
		Path pocket17Path = null;
		Path pocket18Path = null;
		Path pocket19Path = null;
		Path pocket20Path = null;
		Path pocket21Path = null;
		Path pocket22Path = null;
		Path pocket23Path = null;
		Path pocket24Path = null;
		Path pocket25Path = null;
		Path pocket26Path = null;
		Path pocket27Path = null;
		Path pocket28Path = null;
		Path pocket29Path = null;
		Path pocket30Path = null;
		Path pocket31Path = null;
		Path pocket32Path = null;
		Path pocket33Path = null;
		Path pocket34Path = null;
		Path pocket35Path = null;
		Path pocket36Path = null;
		
		StackPane pocket00Pane = null;
		StackPane pocket0Pane = null;
		StackPane pocket1Pane = null;
		StackPane pocket2Pane = null;
		StackPane pocket3Pane = null;
		StackPane pocket4Pane = null;
		StackPane pocket5Pane = null;
		StackPane pocket6Pane = null;
		StackPane pocket7Pane = null;
		StackPane pocket8Pane = null;
		StackPane pocket9Pane = null;
		StackPane pocket10Pane = null;
		StackPane pocket11Pane = null;
		StackPane pocket12Pane = null;
		StackPane pocket13Pane = null;
		StackPane pocket14Pane = null;
		StackPane pocket15Pane = null;
		StackPane pocket16Pane = null;
		StackPane pocket17Pane = null;
		StackPane pocket18Pane = null;
		StackPane pocket19Pane = null;
		StackPane pocket20Pane = null;
		StackPane pocket21Pane = null;
		StackPane pocket22Pane = null;
		StackPane pocket23Pane = null;
		StackPane pocket24Pane = null;
		StackPane pocket25Pane = null;
		StackPane pocket26Pane = null;
		StackPane pocket27Pane = null;
		StackPane pocket28Pane = null;
		StackPane pocket29Pane = null;
		StackPane pocket30Pane = null;
		StackPane pocket31Pane = null;
		StackPane pocket32Pane = null;
		StackPane pocket33Pane = null;
		StackPane pocket34Pane = null;
		StackPane pocket35Pane = null;
		StackPane pocket36Pane = null;		
		
	//Constants for the layouts
	static final int TABLE_MAIN_CELL_WIDTH = 40;
	static final int TABLE_MAIN_CELL_HEIGHT = 55;
	static final int TABLE_MAIN_CELL_GAP = 2;
			
	
	//ArrayList grouping the numbers by color
		static final ArrayList blackNumbersList = new ArrayList(Arrays.asList(2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35));
		static final ArrayList redNumbersList = new ArrayList(Arrays.asList(1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36));
	
	//Table Layout
		//Map of the 1 to 36 layout zones
		Map<String, Rectangle> rectangles1to36LayoutMap = new HashMap<String, Rectangle>();	
		//Pane containing the different table zones
		private Pane tableLayout = null;
		
			//Left Zone containing the zeros
			private VBox tableLeftZone = null;
			
			//Center Zone containing the numbers (1 to 36) and the bottom special bets
				private GridPane tableCenterZone = null;
				
			//Right zone containing the 2 to 1's bets
			private GridPane tableRightZone = null;
	
	//Table betting zones
		//Map of the bet zones
			Map<String, Shape> straightBetZoneMap = new HashMap<String, Shape>();
			Map<String, Shape> splitBetZoneMap = new HashMap<String, Shape>();
			Map<String, Shape> streetBetZoneMap = new HashMap<String, Shape>();
			Map<String, Shape> cornerBetZoneMap = new HashMap<String, Shape>();
			Map<String, Shape> basketBetZoneMap = new HashMap<String, Shape>();
			Map<String, Shape> topLineBetZoneMap = new HashMap<String, Shape>();
			
		//Pane containing the different table betting zones
		private Pane tableBetLayout = null;
			//Zone containing the numbers (1 to 36) and the bottom special bets
				private GridPane tableStraightBetZone1;
			//Zone containing the 2 to 1's bets 
				private GridPane tableStraightBetZone2;
			//Zone containing the zeros bets	
				private VBox tableStraightBetZone3;
				
	//Roulette chip image
		Image rouletteChipImg = null;
		ImageView rouletteChipImgView = null;
			
	public Roulette()
	{
		root = new Pane();
		scene = new Scene(root, 800,800);
				
		rouletteChipImg = new Image("roulette_chip.jpg");
		rouletteChipImgView = new ImageView(rouletteChipImg);
		
		LogicalRoulette.createPocketObjects();//create the pockets objects
		createMenu();
		createPlayerInfo();
		setBall();
		setWheel();
		setTable();
		setStraightBetZones();
		setSplitBetZones();
		setStreetBetZones();
		setCornerBetZones();
		setBasketBetZone();
		topLineBetZone();
		setButtons();
		setMessageZone("Click on the table to place a bet!");
		
		/*Line test = new Line (0.0, 0.0, 0.0, 125.0);
		test.setStrokeWidth(5);
		test.setStroke(Color.web("#00FF00",0.5));
		test.setTranslateX(250);
		test.setTranslateY(100);*/
		
		root.getChildren().addAll(rouletteWheel, msgZone, super.playerInfo, super.upperZone, tableLayout, buttonsGroup, tableBetLayout);
		
		super.playerInfo.setTranslateX(500);
		super.playerInfo.setTranslateY(15);
		
		buttonsGroup.setTranslateX(430);
		buttonsGroup.setTranslateY(370);
		
		msgZone.setTranslateX(500);
		msgZone.setTranslateY(130);
		
		rouletteWheel.setTranslateX(rouletteWheelXTranslation);
		rouletteWheel.setTranslateY(rouletteWheelYTranslation);
		//rouletteWheel.setOpacity(0.5);
		
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	private void setWheel()
	{		
		//Wheel (made of Inner Circle, numbered and colored pockets(see Pocket class), and an outer circle 
			//Inner circle
				Circle innerCircle = new Circle(INNERCIRCLERADIUS);
				innerCircle.setFill(Color.GOLDENROD);
				Light.Point light = new Light.Point();
				light.setX(120);
				light.setY(120);
				light.setZ(150);
				Lighting innerWheelLighting = new Lighting();
				innerWheelLighting.setLight(light);
				
				innerWheelLighting.setSurfaceScale(5.0);
				innerCircle.setEffect(innerWheelLighting);
				
			//Outer circle
				Circle outerCircle = new Circle(OUTERCIRCLERADIUS);
				outerCircle.setFill(Color.DARKRED);
				outerCircle.setStrokeWidth(OUTERCIRCLESTROKE);
				outerCircle.setStrokeType(StrokeType.OUTSIDE);
				outerCircle.setStroke(Color.DARKRED);
				
				Light.Point light2 = new Light.Point();
				light2.setX(230);
				light2.setY(230);
				light2.setZ(300);
				Lighting outerWheelLighting = new Lighting();
				outerWheelLighting.setLight(light2);
				outerCircle.setEffect(outerWheelLighting);
				
			//Outermost circle
				Circle outerMostCircle = new Circle(OUTERMOSTCIRCLERADIUS);
				outerMostCircle.setFill(Color.TRANSPARENT);
				outerMostCircle.setStroke(Color.BLACK);
				outerMostCircle.setStrokeWidth(10);
				outerMostCircle.setStrokeType(StrokeType.OUTSIDE);
				outerMostCircle.setEffect(innerWheelLighting);
				
			//Mid pocket circle - The circle going through the pocket, separating the sockets from the number's zone
				pocketsCircle = new Circle(INNERCIRCLERADIUS+(OUTERCIRCLERADIUS-INNERCIRCLERADIUS)/2);
				pocketsCircle.setFill(Color.TRANSPARENT);
				pocketsCircle.setStroke(Color.WHITE);
				pocketsCircle.setStrokeWidth(2);							
				
				Light.Point light4 = new Light.Point();
				light4.setX(150);
				light4.setY(150);
				light4.setZ(200);
				Lighting pocketsCircleLighting = new Lighting();
				
				pocketsCircleLighting.setLight(light4);
				pocketsCircle.setEffect(pocketsCircleLighting);
				
				
			//Graphic Pockets
				//Paths
					pocket00Path = LogicalRoulette.pocket00.getPath();
					pocket00Path.setFill(Color.GREEN);
					
					pocket27Path = LogicalRoulette.pocket27.getPath();
					pocket27Path.setFill(Color.RED);
					
					pocket10Path = LogicalRoulette.pocket10.getPath();
					pocket10Path.setFill(Color.BLACK);
					
					pocket25Path = LogicalRoulette.pocket25.getPath();
					pocket25Path.setFill(Color.RED);
					
					pocket29Path = LogicalRoulette.pocket29.getPath();
					pocket29Path.setFill(Color.BLACK);
	
					pocket12Path = LogicalRoulette.pocket12.getPath();
					pocket12Path.setFill(Color.RED);
					
					pocket8Path = LogicalRoulette.pocket8.getPath();
					pocket8Path.setFill(Color.BLACK);
					
					pocket19Path = LogicalRoulette.pocket19.getPath();
					pocket19Path.setFill(Color.RED);
					
					pocket31Path = LogicalRoulette.pocket31.getPath();
					pocket31Path.setFill(Color.BLACK);
					
					pocket18Path = LogicalRoulette.pocket18.getPath();
					pocket18Path.setFill(Color.RED);
					
					pocket6Path = LogicalRoulette.pocket6.getPath();
					pocket6Path.setFill(Color.BLACK);
					
					pocket21Path = LogicalRoulette.pocket21.getPath();
					pocket21Path.setFill(Color.RED);
					
					pocket33Path = LogicalRoulette.pocket33.getPath();
					pocket33Path.setFill(Color.BLACK);
					
					pocket16Path = LogicalRoulette.pocket16.getPath();
					pocket16Path.setFill(Color.RED);
					
					pocket4Path = LogicalRoulette.pocket4.getPath();
					pocket4Path.setFill(Color.BLACK);
					
					pocket23Path = LogicalRoulette.pocket23.getPath();
					pocket23Path.setFill(Color.RED);
					
					pocket35Path = LogicalRoulette.pocket35.getPath();
					pocket35Path.setFill(Color.BLACK);
					
					pocket14Path = LogicalRoulette.pocket14.getPath();
					pocket14Path.setFill(Color.RED);
					
					pocket2Path = LogicalRoulette.pocket2.getPath();
					pocket2Path.setFill(Color.BLACK);
					
					pocket0Path = LogicalRoulette.pocket0.getPath();
					pocket0Path.setFill(Color.GREEN);
					
					pocket28Path = LogicalRoulette.pocket28.getPath();
					pocket28Path.setFill(Color.BLACK);
					
					pocket9Path = LogicalRoulette.pocket9.getPath();
					pocket9Path.setFill(Color.RED);
					
					pocket26Path = LogicalRoulette.pocket26.getPath();
					pocket26Path.setFill(Color.BLACK);
					
					pocket30Path = LogicalRoulette.pocket30.getPath();
					pocket30Path.setFill(Color.RED);
					
					pocket11Path = LogicalRoulette.pocket11.getPath();
					pocket11Path.setFill(Color.BLACK);
					
					pocket7Path = LogicalRoulette.pocket7.getPath();
					pocket7Path.setFill(Color.RED);
					
					pocket20Path = LogicalRoulette.pocket20.getPath();
					pocket20Path.setFill(Color.BLACK);
					
					pocket32Path = LogicalRoulette.pocket32.getPath();
					pocket32Path.setFill(Color.RED);
					
					pocket17Path = LogicalRoulette.pocket17.getPath();
					pocket17Path.setFill(Color.BLACK);
					
					pocket5Path = LogicalRoulette.pocket5.getPath();
					pocket5Path.setFill(Color.RED);
					
					pocket22Path = LogicalRoulette.pocket22.getPath();
					pocket22Path.setFill(Color.BLACK);
					
					pocket34Path = LogicalRoulette.pocket34.getPath();
					pocket34Path.setFill(Color.RED);
					
					pocket15Path = LogicalRoulette.pocket15.getPath();
					pocket15Path.setFill(Color.BLACK);
					
					pocket3Path = LogicalRoulette.pocket3.getPath();
					pocket3Path.setFill(Color.RED);
					
					pocket24Path = LogicalRoulette.pocket24.getPath();
					pocket24Path.setFill(Color.BLACK);
					
					pocket36Path = LogicalRoulette.pocket36.getPath();
					pocket36Path.setFill(Color.RED);
					
					pocket13Path = LogicalRoulette.pocket13.getPath();
					pocket13Path.setFill(Color.BLACK);
					
					pocket1Path = LogicalRoulette.pocket1.getPath();
					pocket1Path.setFill(Color.RED);
				
				//Labels
					Label pocket00Label = new Label();
					pocket00Label.setText("00");
					pocket00Label.setTextFill(Color.WHITE);
					pocket00Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
						
					Label pocket27Label = new Label();
					pocket27Label.setText("27");
					pocket27Label.setTextFill(Color.WHITE);
					pocket27Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
					
					Label pocket10Label = new Label();
					pocket10Label.setText("10");
					pocket10Label.setTextFill(Color.WHITE);
					pocket10Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
					
					Label pocket25Label = new Label();
					pocket25Label.setText("25");
					pocket25Label.setTextFill(Color.WHITE);
					pocket25Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
					
					Label pocket29Label = new Label();
					pocket29Label.setText("29");
					pocket29Label.setTextFill(Color.WHITE);
					pocket29Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
					
					Label pocket12Label = new Label();
					pocket12Label.setText("12");
					pocket12Label.setTextFill(Color.WHITE);
					pocket12Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket8Label = new Label();
					pocket8Label.setText("8");
					pocket8Label.setTextFill(Color.WHITE);
					pocket8Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket19Label = new Label();
					pocket19Label.setText("19");
					pocket19Label.setTextFill(Color.WHITE);
					pocket19Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket31Label = new Label();
					pocket31Label.setText("31");
					pocket31Label.setTextFill(Color.WHITE);
					pocket31Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket18Label = new Label();
					pocket18Label.setText("18");
					pocket18Label.setTextFill(Color.WHITE);
					pocket18Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket6Label = new Label();
					pocket6Label.setText("6");
					pocket6Label.setTextFill(Color.WHITE);
					pocket6Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket21Label = new Label();
					pocket21Label.setText("21");
					pocket21Label.setTextFill(Color.WHITE);
					pocket21Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket33Label = new Label();
					pocket33Label.setText("33");
					pocket33Label.setTextFill(Color.WHITE);
					pocket33Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket16Label = new Label();
					pocket16Label.setText("16");
					pocket16Label.setTextFill(Color.WHITE);
					pocket16Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket4Label = new Label();
					pocket4Label.setText("4");
					pocket4Label.setTextFill(Color.WHITE);
					pocket4Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket23Label = new Label();
					pocket23Label.setText("23");
					pocket23Label.setTextFill(Color.WHITE);
					pocket23Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket35Label = new Label();
					pocket35Label.setText("35");
					pocket35Label.setTextFill(Color.WHITE);
					pocket35Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket14Label = new Label();
					pocket14Label.setText("14");
					pocket14Label.setTextFill(Color.WHITE);
					pocket14Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket2Label = new Label();
					pocket2Label.setText("2");
					pocket2Label.setTextFill(Color.WHITE);
					pocket2Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket0Label = new Label();
					pocket0Label.setText("0");
					pocket0Label.setTextFill(Color.WHITE);
					pocket0Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket28Label = new Label();
					pocket28Label.setText("28");
					pocket28Label.setTextFill(Color.WHITE);
					pocket28Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket9Label = new Label();
					pocket9Label.setText("9");
					pocket9Label.setTextFill(Color.WHITE);
					pocket9Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket26Label = new Label();
					pocket26Label.setText("26");
					pocket26Label.setTextFill(Color.WHITE);
					pocket26Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket30Label = new Label();
					pocket30Label.setText("30");
					pocket30Label.setTextFill(Color.WHITE);
					pocket30Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket11Label = new Label();
					pocket11Label.setText("11");
					pocket11Label.setTextFill(Color.WHITE);
					pocket11Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket7Label = new Label();
					pocket7Label.setText("7");
					pocket7Label.setTextFill(Color.WHITE);
					pocket7Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket20Label = new Label();
					pocket20Label.setText("20");
					pocket20Label.setTextFill(Color.WHITE);
					pocket20Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket32Label = new Label();
					pocket32Label.setText("32");
					pocket32Label.setTextFill(Color.WHITE);
					pocket32Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket17Label = new Label();
					pocket17Label.setText("17");
					pocket17Label.setTextFill(Color.WHITE);
					pocket17Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket5Label = new Label();
					pocket5Label.setText("5");
					pocket5Label.setTextFill(Color.WHITE);
					pocket5Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket22Label = new Label();
					pocket22Label.setText("22");
					pocket22Label.setTextFill(Color.WHITE);
					pocket22Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket34Label = new Label();
					pocket34Label.setText("34");
					pocket34Label.setTextFill(Color.WHITE);
					pocket34Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket15Label = new Label();
					pocket15Label.setText("15");
					pocket15Label.setTextFill(Color.WHITE);
					pocket15Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket3Label = new Label();
					pocket3Label.setText("3");
					pocket3Label.setTextFill(Color.WHITE);
					pocket3Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket24Label = new Label();
					pocket24Label.setText("24");
					pocket24Label.setTextFill(Color.WHITE);
					pocket24Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket36Label = new Label();
					pocket36Label.setText("36");
					pocket36Label.setTextFill(Color.WHITE);
					pocket36Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket13Label = new Label();
					pocket13Label.setText("13");
					pocket13Label.setTextFill(Color.WHITE);
					pocket13Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
	
					Label pocket1Label = new Label();
					pocket1Label.setText("1");
					pocket1Label.setTextFill(Color.WHITE);
					pocket1Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
				
	
				//Individual pockets Panes	
					pocket00Pane = new StackPane();
					pocket00Pane.getChildren().addAll(pocket00Path, pocket00Label);
					
					pocket27Pane = new StackPane();
					pocket27Pane.getChildren().addAll(pocket27Path, pocket27Label);
					double rotationPointYTranslate = OUTERCIRCLERADIUS;//Rotation point Y coordinate
					double rotationPointXTranslate = (pocket27Pane.getBoundsInLocal().getWidth())/2;//Rotation point X coordinate
					Rotate rotate27 = new Rotate(360.0/38.0, rotationPointXTranslate, rotationPointYTranslate);
					pocket27Pane.getTransforms().add(rotate27);
	
					pocket10Pane = new StackPane();
					pocket10Pane.getChildren().addAll(pocket10Path, pocket10Label);
					pocket10Pane.getTransforms().add(new Rotate(360.0/38.0*2, rotationPointXTranslate, rotationPointYTranslate));

					pocket25Pane = new StackPane();
					pocket25Pane.getChildren().addAll(pocket25Path, pocket25Label);
					pocket25Pane.getTransforms().add(new Rotate(360.0/38.0*3, rotationPointXTranslate, rotationPointYTranslate));

					pocket29Pane = new StackPane();
					pocket29Pane.getChildren().addAll(pocket29Path, pocket29Label);
					pocket29Pane.getTransforms().add(new Rotate(360.0/38.0*4, rotationPointXTranslate, rotationPointYTranslate));

					pocket12Pane = new StackPane();
					pocket12Pane.getChildren().addAll(pocket12Path, pocket12Label);
					pocket12Pane.getTransforms().add(new Rotate(360.0/38.0*5, rotationPointXTranslate, rotationPointYTranslate));

					pocket8Pane = new StackPane();
					pocket8Pane.getChildren().addAll(pocket8Path, pocket8Label);
					pocket8Pane.getTransforms().add(new Rotate(360.0/38.0*6, rotationPointXTranslate, rotationPointYTranslate));

					pocket19Pane = new StackPane();
					pocket19Pane.getChildren().addAll(pocket19Path, pocket19Label);
					pocket19Pane.getTransforms().add(new Rotate(360.0/38.0*7, rotationPointXTranslate, rotationPointYTranslate));

					pocket31Pane = new StackPane();
					pocket31Pane.getChildren().addAll(pocket31Path, pocket31Label);
					pocket31Pane.getTransforms().add(new Rotate(360.0/38.0*8, rotationPointXTranslate, rotationPointYTranslate));

					pocket18Pane = new StackPane();
					pocket18Pane.getChildren().addAll(pocket18Path, pocket18Label);
					pocket18Pane.getTransforms().add(new Rotate(360.0/38.0*9, rotationPointXTranslate, rotationPointYTranslate));

					pocket6Pane = new StackPane();
					pocket6Pane.getChildren().addAll(pocket6Path, pocket6Label);
					pocket6Pane.getTransforms().add(new Rotate(360.0/38.0*10, rotationPointXTranslate, rotationPointYTranslate));

					pocket21Pane = new StackPane();
					pocket21Pane.getChildren().addAll(pocket21Path, pocket21Label);
					pocket21Pane.getTransforms().add(new Rotate(360.0/38.0*11, rotationPointXTranslate, rotationPointYTranslate));

					pocket33Pane = new StackPane();
					pocket33Pane.getChildren().addAll(pocket33Path, pocket33Label);
					pocket33Pane.getTransforms().add(new Rotate(360.0/38.0*12, rotationPointXTranslate, rotationPointYTranslate));

					pocket16Pane = new StackPane();
					pocket16Pane.getChildren().addAll(pocket16Path, pocket16Label);
					pocket16Pane.getTransforms().add(new Rotate(360.0/38.0*13, rotationPointXTranslate, rotationPointYTranslate));

					pocket4Pane = new StackPane();
					pocket4Pane.getChildren().addAll(pocket4Path, pocket4Label);
					pocket4Pane.getTransforms().add(new Rotate(360.0/38.0*14, rotationPointXTranslate, rotationPointYTranslate));

					pocket23Pane = new StackPane();
					pocket23Pane.getChildren().addAll(pocket23Path, pocket23Label);
					pocket23Pane.getTransforms().add(new Rotate(360.0/38.0*15, rotationPointXTranslate, rotationPointYTranslate));

					pocket35Pane = new StackPane();
					pocket35Pane.getChildren().addAll(pocket35Path, pocket35Label);
					pocket35Pane.getTransforms().add(new Rotate(360.0/38.0*16, rotationPointXTranslate, rotationPointYTranslate));

					pocket14Pane = new StackPane();
					pocket14Pane.getChildren().addAll(pocket14Path, pocket14Label);
					pocket14Pane.getTransforms().add(new Rotate(360.0/38.0*17, rotationPointXTranslate, rotationPointYTranslate));

					pocket2Pane = new StackPane();
					pocket2Pane.getChildren().addAll(pocket2Path, pocket2Label);
					pocket2Pane.getTransforms().add(new Rotate(360.0/38.0*18, rotationPointXTranslate, rotationPointYTranslate));

					pocket0Pane = new StackPane();
					pocket0Pane.getChildren().addAll(pocket0Path, pocket0Label);
					pocket0Pane.getTransforms().add(new Rotate(360.0/38.0*19, rotationPointXTranslate, rotationPointYTranslate));

					pocket28Pane = new StackPane();
					pocket28Pane.getChildren().addAll(pocket28Path, pocket28Label);
					pocket28Pane.getTransforms().add(new Rotate(360.0/38.0*20, rotationPointXTranslate, rotationPointYTranslate));

					pocket9Pane = new StackPane();
					pocket9Pane.getChildren().addAll(pocket9Path, pocket9Label);
					pocket9Pane.getTransforms().add(new Rotate(360.0/38.0*21, rotationPointXTranslate, rotationPointYTranslate));

					pocket26Pane = new StackPane();
					pocket26Pane.getChildren().addAll(pocket26Path, pocket26Label);
					pocket26Pane.getTransforms().add(new Rotate(360.0/38.0*22, rotationPointXTranslate, rotationPointYTranslate));

					pocket30Pane = new StackPane();
					pocket30Pane.getChildren().addAll(pocket30Path, pocket30Label);
					pocket30Pane.getTransforms().add(new Rotate(360.0/38.0*23, rotationPointXTranslate, rotationPointYTranslate));

					pocket11Pane = new StackPane();
					pocket11Pane.getChildren().addAll(pocket11Path, pocket11Label);
					pocket11Pane.getTransforms().add(new Rotate(360.0/38.0*24, rotationPointXTranslate, rotationPointYTranslate));

					pocket7Pane = new StackPane();
					pocket7Pane.getChildren().addAll(pocket7Path, pocket7Label);
					pocket7Pane.getTransforms().add(new Rotate(360.0/38.0*25, rotationPointXTranslate, rotationPointYTranslate));

					pocket20Pane = new StackPane();
					pocket20Pane.getChildren().addAll(pocket20Path, pocket20Label);
					pocket20Pane.getTransforms().add(new Rotate(360.0/38.0*26, rotationPointXTranslate, rotationPointYTranslate));

					pocket32Pane = new StackPane();
					pocket32Pane.getChildren().addAll(pocket32Path, pocket32Label);
					pocket32Pane.getTransforms().add(new Rotate(360.0/38.0*27, rotationPointXTranslate, rotationPointYTranslate));

					pocket17Pane = new StackPane();
					pocket17Pane.getChildren().addAll(pocket17Path, pocket17Label);
					pocket17Pane.getTransforms().add(new Rotate(360.0/38.0*28, rotationPointXTranslate, rotationPointYTranslate));

					pocket5Pane = new StackPane();
					pocket5Pane.getChildren().addAll(pocket5Path, pocket5Label);
					pocket5Pane.getTransforms().add(new Rotate(360.0/38.0*29, rotationPointXTranslate, rotationPointYTranslate));

					pocket22Pane = new StackPane();
					pocket22Pane.getChildren().addAll(pocket22Path, pocket22Label);
					pocket22Pane.getTransforms().add(new Rotate(360.0/38.0*30, rotationPointXTranslate, rotationPointYTranslate));

					pocket34Pane = new StackPane();
					pocket34Pane.getChildren().addAll(pocket34Path, pocket34Label);
					pocket34Pane.getTransforms().add(new Rotate(360.0/38.0*31, rotationPointXTranslate, rotationPointYTranslate));

					pocket15Pane = new StackPane();
					pocket15Pane.getChildren().addAll(pocket15Path, pocket15Label);
					pocket15Pane.getTransforms().add(new Rotate(360.0/38.0*32, rotationPointXTranslate, rotationPointYTranslate));

					pocket3Pane = new StackPane();
					pocket3Pane.getChildren().addAll(pocket3Path, pocket3Label);
					pocket3Pane.getTransforms().add(new Rotate(360.0/38.0*33, rotationPointXTranslate, rotationPointYTranslate));

					pocket24Pane = new StackPane();
					pocket24Pane.getChildren().addAll(pocket24Path, pocket24Label);
					pocket24Pane.getTransforms().add(new Rotate(360.0/38.0*34, rotationPointXTranslate, rotationPointYTranslate));

					pocket36Pane = new StackPane();
					pocket36Pane.getChildren().addAll(pocket36Path, pocket36Label);
					pocket36Pane.getTransforms().add(new Rotate(360.0/38.0*35, rotationPointXTranslate, rotationPointYTranslate));

					pocket13Pane = new StackPane();
					pocket13Pane.getChildren().addAll(pocket13Path, pocket13Label);
					pocket13Pane.getTransforms().add(new Rotate(360.0/38.0*36, rotationPointXTranslate, rotationPointYTranslate));

					pocket1Pane = new StackPane();
					pocket1Pane.getChildren().addAll(pocket1Path, pocket1Label);
					Rotate rotate1 = new Rotate(360.0/38.0*37, rotationPointXTranslate, rotationPointYTranslate);
					pocket1Pane.getTransforms().add(rotate1);
				
				//Pockets main pane
				pocketsPane = new Pane();
				pocketsPane.getChildren().addAll(pocket00Pane, pocket0Pane, pocket1Pane, pocket2Pane, pocket3Pane, pocket4Pane, pocket5Pane, pocket6Pane, pocket7Pane, pocket8Pane, pocket9Pane, pocket10Pane, pocket11Pane, pocket12Pane, pocket13Pane, pocket14Pane, pocket15Pane, pocket16Pane, pocket17Pane, pocket18Pane, pocket19Pane, pocket20Pane, pocket21Pane, pocket22Pane, pocket23Pane, pocket24Pane, pocket25Pane, pocket26Pane, pocket27Pane, pocket28Pane, pocket29Pane, pocket30Pane, pocket31Pane, pocket32Pane, pocket33Pane, pocket34Pane, pocket35Pane, pocket36Pane);
				pocketsPane.setTranslateX(223.5);
				pocketsPane.setTranslateY(60);
				
				Light.Point light3 = new Light.Point();
				light3.setX(180);
				light3.setY(180);
				light3.setZ(400);
				Lighting pocketsLighting = new Lighting();
				
				pocketsLighting.setLight(light3);
				pocketsPane.setEffect(pocketsLighting);
				
				
		//RouletteWheel
		rouletteWheel = new StackPane();
		rouletteWheel.getChildren().addAll(outerMostCircle, outerCircle, innerCircle, pocketsPane, pocketsCircle);
		bringBallToInitialPosition();
	}
	
	private void setBall()
	{
		//Ball (Circle)
		rouletteBall = new Circle(10, Color.WHITESMOKE);
	
		//Center point, made of a 1 pixel rectangle
		ballCenter = new Rectangle(1, 1, Color.RED);
		ballCenter.setVisible(false);
				
		//A stackpane to automatically center the ballCenter in the middle of the ball
		rouletteBallStack = new StackPane();
		rouletteBallStack.getChildren().addAll( rouletteBall, ballCenter);
	}
	
	//Take the ball out of the pocket's pane (necessary after an wheel animation) and back to it's initial position
	private void bringBallToInitialPosition()
	{
		Pocket lastAnimationFinalPocket = getCollidingPocket();
		//if the ball is colliding a pocket, it means that an animation has run
		//and we need to take the ball out of the pocket
		if (lastAnimationFinalPocket != null)
		{
			StackPane ballParent = (StackPane)rouletteBallStack.getParent();
			ballParent.getChildren().remove(rouletteBallStack);	
		}
		//If the ball isn't already at it's initial position in the rouletteWheel StackPane, place it there.
		//The "if" is necessary, otherwise a fatal error will happen on the first animation because we try to put the ball twice in the same parent
		if (rouletteBallStack.getParent() != rouletteWheel)
		{
			rouletteWheel.getChildren().add(rouletteBallStack);
			rouletteBallStack.setTranslateY(-OUTERMOSTCIRCLERADIUS+ballStackInnerWheelYTranslation);//Move the ball from the center of the wheel to it's starting position
		}
	}
	
	private void setTable()
	{		
		//Create left betting zone. Made of text inside polygons.
		//A bigger white polygon is used to make the white border around the smaller green polygon
		
			double zerosZonesInnerHeight = (int)(1.5*TABLE_MAIN_CELL_HEIGHT+TABLE_MAIN_CELL_GAP);
			double zerosZonesInnerTriangleWidth = (int)TABLE_MAIN_CELL_WIDTH*3/8;
			
			double zerosZonesOuterHeight = (int)(zerosZonesInnerHeight + 2*TABLE_MAIN_CELL_GAP);
			double zerosZonesOuterTriangleWidth = (int)(zerosZonesInnerTriangleWidth + (Math.sqrt((Math.pow(TABLE_MAIN_CELL_GAP,2))*2)));//Pythagorean theorem used to calculate the difference between the inner and outer triangle's width
			
			
			Polygon bet_00outerZone = new Polygon();
			bet_00outerZone.getPoints().addAll(new Double[]{0.0, -zerosZonesOuterHeight/2, zerosZonesOuterTriangleWidth-TABLE_MAIN_CELL_GAP, 0.0, zerosZonesOuterTriangleWidth+TABLE_MAIN_CELL_WIDTH+TABLE_MAIN_CELL_GAP, 0.0, zerosZonesOuterTriangleWidth+TABLE_MAIN_CELL_WIDTH+TABLE_MAIN_CELL_GAP, -zerosZonesOuterHeight, zerosZonesOuterTriangleWidth-TABLE_MAIN_CELL_GAP, -zerosZonesOuterHeight});
			bet_00outerZone.setFill(Color.WHITE);
			
			Polygon bet_00innerZone = new Polygon();
			bet_00innerZone.getPoints().addAll(new Double[]{0.0, -zerosZonesInnerHeight/2, zerosZonesInnerTriangleWidth, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, -zerosZonesInnerHeight, zerosZonesInnerTriangleWidth, -zerosZonesInnerHeight});
			bet_00innerZone.setFill(Color.GREEN);
			
			Text text_00 = new Text("00");
			text_00.setFill(Color.WHITE);
			text_00.setFont(Font.font (null, FontWeight.BOLD, 20.0));
			text_00.setRotate(270);
			
			StackPane stack_00zone = new StackPane();
			stack_00zone.getChildren().addAll(bet_00outerZone, bet_00innerZone, text_00);
			
			
			Polygon bet_0outerZone = new Polygon();
			bet_0outerZone.getPoints().addAll(new Double[]{0.0, -zerosZonesOuterHeight/2, zerosZonesOuterTriangleWidth-TABLE_MAIN_CELL_GAP, 0.0, zerosZonesOuterTriangleWidth+TABLE_MAIN_CELL_WIDTH+TABLE_MAIN_CELL_GAP, 0.0, zerosZonesOuterTriangleWidth+TABLE_MAIN_CELL_WIDTH+TABLE_MAIN_CELL_GAP, -zerosZonesOuterHeight, zerosZonesOuterTriangleWidth-TABLE_MAIN_CELL_GAP, -zerosZonesOuterHeight});
			bet_0outerZone.setFill(Color.WHITE);
			
			Polygon bet_0innerZone = new Polygon();
			bet_0innerZone.getPoints().addAll(new Double[]{0.0, -zerosZonesInnerHeight/2, zerosZonesInnerTriangleWidth, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, -zerosZonesInnerHeight, zerosZonesInnerTriangleWidth, -zerosZonesInnerHeight});
			bet_0innerZone.setFill(Color.GREEN);	
		
			Text text_0 = new Text("0");
			text_0.setFill(Color.WHITE);
			text_0.setFont(Font.font(null, FontWeight.BOLD, 20.0));
			text_0.setRotate(270);
			
			StackPane stack_0zone = new StackPane();
			stack_0zone.getChildren().addAll(bet_0outerZone, bet_0innerZone, text_0);
	
	
			tableLeftZone = new VBox();
			
			tableLeftZone.getChildren().addAll(stack_00zone,stack_0zone);
			stack_0zone.setTranslateY(-(int)1.5*TABLE_MAIN_CELL_GAP);//O bet zone translation to fill a gap between it and the 00 zone
		
			
		//Create center betting zones. 
			
			/*TableCenterZone - Top (individual number betting zone) :The items contained in 
			 * this zone are made of a Text, inside an Ellipse(red or black),inside a Rectangle. 
			 * The rectangle's purpose is to fill the grid cell as a colored 
				background.*/
			
				tableCenterZone = new GridPane();
				
				//Zone 1 to 36
					double ellipseWidth = 2.0/5.0*TABLE_MAIN_CELL_WIDTH;
					double ellipseHeigth = 10.0/25.0*TABLE_MAIN_CELL_HEIGHT;
					int i=0;
					int j=2;
					int number = 1;
					
					for (; number<=36 ; number++)
					{
						
						Ellipse ellipse = new Ellipse(ellipseWidth, ellipseHeigth);
						
						if (blackNumbersList.contains(number))
						{
							ellipse.setFill(Color.BLACK);
						}
						
						else if (redNumbersList.contains(number))
						{
							ellipse.setFill(Color.RED);
						}
						
						Rectangle rect = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.GREEN);
						rectangles1to36LayoutMap.put(String.valueOf(number), rect);
						Text text = new Text(Integer.toString(number));
						text.setRotate(270);
						text.setFill(Color.WHITE);
						text.setFont(Font.font(null, FontWeight.BOLD, 15.0));
						text.setTextAlignment(TextAlignment.CENTER);
						
						//A stackpane to place the text in front of the ellipse in front of the rectangle
						StackPane stack = new StackPane();
		
						stack.getChildren().addAll(rect, ellipse, text);
						tableCenterZone.add(stack, i, j);
						
						j--;
						
						if (j==-1)
						{
							i++;
							j=2;
						}
					}		
				
		
				/*TableCenterZone - Bottom (special bets zone)
					
					//First line - Zones made of text inside rectangles. Rectangles spans on many columns.*/				
						int table_12sZoneWidth = TABLE_MAIN_CELL_WIDTH*4 + 3*TABLE_MAIN_CELL_GAP;//Column size, spanning on 4 normal columns
						
						//First 12
						Rectangle rect4 = new Rectangle(table_12sZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.GREEN);//height is casted to int to avoid getting floating point number for a pixel size 
						Text text_1st12 = new Text("1st 12");
						text_1st12.setFont(Font.font(null, FontWeight.BOLD, 20.0));
						text_1st12.setFill(Color.WHITE);
						
						StackPane stack_1st12 = new StackPane();
						stack_1st12.getChildren().addAll(rect4, text_1st12);
						
						//12nd 12
						Rectangle rect5 = new Rectangle(table_12sZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.GREEN);//height is casted to int to avoid getting floating point number for a pixel size 
						Text text_2nd12 = new Text("2nd 12");
						text_2nd12.setFont(Font.font(null, FontWeight.BOLD, 20.0));
						text_2nd12.setFill(Color.WHITE);
						
						StackPane stack_2nd12 = new StackPane();
						stack_2nd12.getChildren().addAll(rect5, text_2nd12);
						
						//3rd 12
						Rectangle rect6 = new Rectangle(table_12sZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.GREEN);//height is casted to int to avoid getting floating point number for a pixel size 
						Text text_3rd12 = new Text("3rd 12");
						text_3rd12.setFont(Font.font(null, FontWeight.BOLD, 20.0));
						text_3rd12.setFill(Color.WHITE);
						
						StackPane stack_3rd12 = new StackPane();
						stack_3rd12.getChildren().addAll(rect6, text_3rd12);
						
						//Add the bets zone to the center zone
						tableCenterZone.add(stack_1st12, 0, 3, 4, 1);
						tableCenterZone.add(stack_2nd12, 4, 3, 4, 1);
						tableCenterZone.add(stack_3rd12, 8, 3, 4, 1);
					
					//Second line - Zones made of text and polygons inside rectangles. Rectangles spans on many columns.*/
						int table_BottomZoneWidth = TABLE_MAIN_CELL_WIDTH*2 + TABLE_MAIN_CELL_GAP;//Column size, spanning on 2 normal columns
						
						//1 to 18 bet
						Rectangle rect7 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.GREEN);//height is casted to int to avoid getting floating point number for a pixel size 
						Text text_1to18 = new Text("1 to 18");
						text_1to18.setFont(Font.font(null, FontWeight.BOLD, 20.0));
						text_1to18.setFill(Color.WHITE);
						
						StackPane stack_1to18 = new StackPane();
						stack_1to18.getChildren().addAll(rect7, text_1to18);
						
						//Even bet
						Rectangle rect8 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.GREEN);//height is casted to int to avoid getting floating point number for a pixel size 
						Text text_Even = new Text("Even");
						text_Even.setFont(Font.font(null, FontWeight.BOLD, 20.0));
						text_Even.setFill(Color.WHITE);
						
						StackPane stack_Even = new StackPane();
						stack_Even.getChildren().addAll(rect8, text_Even);
						
						//Color bets
						double diamondWidth = TABLE_MAIN_CELL_WIDTH*2*0.8;//80% of the cell width
						double diamondHeigth = TABLE_MAIN_CELL_HEIGHT*0.75*0.8;//80% of the cell height
						
							//Red
							Rectangle rect9 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.GREEN);//height is casted to int to avoid getting floating point number for a pixel size 
							Polygon redBetDiamond = new Polygon();
							redBetDiamond.getPoints().addAll(new Double[]{0.0, 0.0, diamondWidth/2, diamondHeigth/2, diamondWidth, 0.0,  diamondWidth/2, -diamondHeigth/2});
							redBetDiamond.setFill(Color.RED);
							
							StackPane stack_RedBet = new StackPane();
							stack_RedBet.getChildren().addAll(rect9, redBetDiamond);
							
							//Black
							Rectangle rect10 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.GREEN);//height is casted to int to avoid getting floating point number for a pixel size 
							Polygon blackBetDiamond = new Polygon();
							blackBetDiamond.getPoints().addAll(new Double[]{0.0, 0.0, diamondWidth/2, diamondHeigth/2, diamondWidth, 0.0,  diamondWidth/2, -diamondHeigth/2});
							blackBetDiamond.setFill(Color.BLACK);
							
							StackPane stack_BlackBet = new StackPane();
							stack_BlackBet.getChildren().addAll(rect10, blackBetDiamond);
						
						//Odd bet
						Rectangle rect11 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.GREEN);//height is casted to int to avoid getting floating point number for a pixel size 
						Text text_Odd = new Text("Odd");
						text_Odd.setFont(Font.font(null, FontWeight.BOLD, 20.0));
						text_Odd.setFill(Color.WHITE);
						
						StackPane stack_Odd = new StackPane();
						stack_Odd.getChildren().addAll(rect11, text_Odd);
						
						//19 to 36 bet
						Rectangle rect12 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.GREEN);//height is casted to int to avoid getting floating point number for a pixel size 
						Text text_19to36 = new Text("19 to 36");
						text_19to36.setFont(Font.font(null, FontWeight.BOLD, 20.0));
						text_19to36.setFill(Color.WHITE);
						
						StackPane stack_19to36 = new StackPane();
						stack_19to36.getChildren().addAll(rect12, text_19to36);
						
						//Add the bets zone to the center zone
						tableCenterZone.add(stack_1to18, 0, 4, 2, 1);
						tableCenterZone.add(stack_Even, 2, 4, 2, 1);
						tableCenterZone.add(stack_RedBet, 4, 4, 2, 1);
						tableCenterZone.add(stack_BlackBet, 6, 4, 2, 1);
						tableCenterZone.add(stack_Odd, 8, 4, 2, 1);
						tableCenterZone.add(stack_19to36, 10, 4, 2, 1);
															
						
				/*2 to 1 zones
				*Made of a Text inside a Rectangle. The rectangle's purpose is to fill the grid cell as a colored 
				 background.
				*/		
					Rectangle rect1 = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.GREEN);
					Text text2to1_1 = new Text("2 to 1");
					text2to1_1.setRotate(270);
					text2to1_1.setFont(Font.font(null, FontWeight.BOLD, 15.0));
					text2to1_1.setFill(Color.WHITE);
					Group text2to1_Group = new Group();
					text2to1_Group.getChildren().add(text2to1_1);//We must place the text in a group, otherwise the text node keeps the non-rotated text's bounds (even after rotation) and it causes display problems 
					
					StackPane stack_2to1_1 = new StackPane();
					stack_2to1_1.getChildren().addAll(rect1, text2to1_Group);
					
							
					Rectangle rect2 = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.GREEN);
					Text text2to1_2 = new Text("2 to 1");
					text2to1_2.setRotate(270);
					text2to1_2.setFont(Font.font(null, FontWeight.BOLD, 15.0));
					text2to1_2.setFill(Color.WHITE);
					Group text2to1_2Group = new Group();
					text2to1_2Group.getChildren().add(text2to1_2);//We must place the text in a group, otherwise the text node keeps the non-rotated text's bounds (even after rotation) and it causes display problems 
					
					StackPane stack_2to1_2 = new StackPane();
					stack_2to1_2.getChildren().addAll(rect2, text2to1_2Group);
					
					
					Rectangle rect3 = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.GREEN);
					Text text2to1_3 = new Text("2 to 1");
					text2to1_3.setRotate(270);
					text2to1_3.setFont(Font.font(null, FontWeight.BOLD, 15.0));
					text2to1_3.setFill(Color.WHITE);
					Group text2to1_3Group = new Group();
					text2to1_3Group.getChildren().add(text2to1_3);//We must place the text in a group, otherwise the text node keeps the non-rotated text's bounds (even after rotation) and it causes display problems. 
					
					StackPane stack_2to1_3 = new StackPane();
					stack_2to1_3.getChildren().addAll(rect3, text2to1_3Group);
					
					tableRightZone = new GridPane();
												
					tableRightZone.add(stack_2to1_1, 0,0);
					tableRightZone.add(stack_2to1_2, 0,1);
					tableRightZone.add(stack_2to1_3, 0,2);
					
		
		//Add the zones to the tableLayout Pane
			tableLayout = new Pane();
			tableLayout.getChildren().addAll(tableCenterZone, tableLeftZone, tableRightZone);
			
			//Translate the left and rigth zones to their proper position
			tableRightZone.setTranslateX(12*TABLE_MAIN_CELL_WIDTH+13*TABLE_MAIN_CELL_GAP);
			
			double leftZoneTranslateValue = -(int)((TABLE_MAIN_CELL_WIDTH + (TABLE_MAIN_CELL_WIDTH*3/8) + (Math.sqrt((Math.pow(TABLE_MAIN_CELL_GAP,2))*2))));//Calculation based on the size of inner and outer polygons used to make the leftZone (see the code used to make the leftZone for details)
			tableLeftZone.setTranslateX(leftZoneTranslateValue);
		
		//Set the position of the tableLayout Pane
		tableLayout.setTranslateX(150);
		tableLayout.setTranslateY(520);
		
		//Set the lighting of the table
		setTableLight();	
			
		//Set the border of the table. Made of a white background with gaps between the cells.
		tableCenterZone.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		tableCenterZone.setVgap(TABLE_MAIN_CELL_GAP);
		tableCenterZone.setHgap(TABLE_MAIN_CELL_GAP);
		tableCenterZone.setPadding(new Insets(TABLE_MAIN_CELL_GAP));
		
		tableRightZone.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		tableRightZone.setVgap(TABLE_MAIN_CELL_GAP);
		tableRightZone.setHgap(TABLE_MAIN_CELL_GAP);
		tableRightZone.setPadding(new Insets(TABLE_MAIN_CELL_GAP, TABLE_MAIN_CELL_GAP, TABLE_MAIN_CELL_GAP, 0));
	}
	
	//This function is called at the launch of the game and after each mouseExited event on the table bet zones.
	//Otherwise, the events were causing lighting issues.
	private void setTableLight()
	{
		//Set the lighting of the table
				Light.Point tableLight = new Light.Point();
				tableLight.setX(300);
				tableLight.setY(150);
				tableLight.setZ(600);
				Lighting tableLighting = new Lighting();		
				tableLighting.setLight(tableLight);
				tableLayout.setEffect(tableLighting);
	}
	
	private void setStraightBetZones()
	{
		//Straight bets
		//Create left betting zone. Made of text inside polygons.
				//A bigger white polygon is used to make the white border around the smaller green polygon
				
					double zerosZonesInnerHeight = (int)(1.5*TABLE_MAIN_CELL_HEIGHT+TABLE_MAIN_CELL_GAP);
					double zerosZonesInnerTriangleWidth = (int)TABLE_MAIN_CELL_WIDTH*3/8;
					
					Polygon bet_00innerZone = new Polygon();
					bet_00innerZone.getPoints().addAll(new Double[]{0.0, -zerosZonesInnerHeight/2, zerosZonesInnerTriangleWidth, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, -zerosZonesInnerHeight, zerosZonesInnerTriangleWidth, -zerosZonesInnerHeight});
					bet_00innerZone.setFill(Color.TRANSPARENT);
					straightBetZoneMap.put("00", bet_00innerZone);//Add this betting zone to the straightBetZonemap
					bet_00innerZone.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									bet_00innerZone.setFill(Color.web("#00FF00",0.5));
									changeMessage("Straight bet");
								}
							});
							
					bet_00innerZone.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									bet_00innerZone.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
					
					
					Polygon bet_0innerZone = new Polygon();
					bet_0innerZone.getPoints().addAll(new Double[]{0.0, -zerosZonesInnerHeight/2, zerosZonesInnerTriangleWidth, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, -zerosZonesInnerHeight, zerosZonesInnerTriangleWidth, -zerosZonesInnerHeight});
					bet_0innerZone.setFill(Color.TRANSPARENT);
					bet_0innerZone.getPoints().addAll(new Double[]{0.0, -zerosZonesInnerHeight/2, zerosZonesInnerTriangleWidth, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, 0.0, zerosZonesInnerTriangleWidth+TABLE_MAIN_CELL_WIDTH, -zerosZonesInnerHeight, zerosZonesInnerTriangleWidth, -zerosZonesInnerHeight});
					bet_0innerZone.setFill(Color.TRANSPARENT);
					straightBetZoneMap.put("0", bet_0innerZone);//Add this betting zone to the straightBetZonemap
					bet_0innerZone.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									bet_0innerZone.setFill(Color.web("#00FF00",0.5));
									changeMessage("Straight bet");
								}
							});
							
					bet_0innerZone.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									bet_0innerZone.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
				
					tableStraightBetZone3 = new VBox();
					
					tableStraightBetZone3.getChildren().addAll(bet_00innerZone, bet_0innerZone);
					bet_0innerZone.setTranslateY(-(int)1.5*TABLE_MAIN_CELL_GAP);//O bet zone translation to fill a gap between it and the 00 zone
			
			//Zone 1 to 36
				tableStraightBetZone1 = new GridPane();
				int i=0;
				int j=2;
				int number = 1;
				
				for (; number<=36 ; number++)
				{
					Rectangle rect = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.TRANSPARENT);
					straightBetZoneMap.put(String.valueOf(number), rect);//Add this betting zone to the straightBetZonemap
					rect.setOnMouseEntered(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent mouseEvent)
						{
							rect.setFill(Color.web("#00FF00",0.5));
							changeMessage("Straight bet");
						}
					});
					
					rect.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
					
					tableStraightBetZone1.add(rect, i, j);
		
					j--;
					
					if (j==-1)
					{
						i++;
						j=2;
					}
				}
				
				//First 12
					int table_12sZoneWidth = TABLE_MAIN_CELL_WIDTH*4 + 3*TABLE_MAIN_CELL_GAP;//Column size, spanning on 4 normal columns
					Rectangle rect1 = new Rectangle(table_12sZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.TRANSPARENT);//height is casted to int to avoid getting floating point number for a pixel size 
					straightBetZoneMap.put("first12", rect1);//Add this betting zone to the straightBetZonemap
					rect1.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect1.setFill(Color.web("#00FF00",0.5));
									changeMessage("First 12");
								}
							});
							
					rect1.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect1.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
			
			
				//12nd 12
					Rectangle rect2 = new Rectangle(table_12sZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.TRANSPARENT);//height is casted to int to avoid getting floating point number for a pixel size 
					straightBetZoneMap.put("second12", rect2);//Add this betting zone to the straightBetZonemap
					rect2.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect2.setFill(Color.web("#00FF00",0.5));
									changeMessage("Second 12");
								}
							});
							
					rect2.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect2.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
			
			
				//3rd 12
					Rectangle rect3 = new Rectangle(table_12sZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.TRANSPARENT);//height is casted to int to avoid getting floating point number for a pixel size 
					straightBetZoneMap.put("third12", rect3);//Add this betting zone to the straightBetZonemap
					rect3.setOnMouseEntered(new EventHandler<MouseEvent>()		
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect3.setFill(Color.web("#00FF00",0.5));
									changeMessage("Third 12");
								}
							});
							
					rect3.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect3.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
			
			
				//Add the 12s bets zone to their gridpane
					tableStraightBetZone1.add(rect1, 0, 3, 4, 1);
					tableStraightBetZone1.add(rect2, 4, 3, 4, 1);
					tableStraightBetZone1.add(rect3, 8, 3, 4, 1);
				
			int table_BottomZoneWidth = TABLE_MAIN_CELL_WIDTH*2 + TABLE_MAIN_CELL_GAP;//Column size, spanning on 2 normal columns
			//1 to 18 bet
				Rectangle rect4 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.TRANSPARENT);//height is casted to int to avoid getting floating point number for a pixel size 
				straightBetZoneMap.put("1to18", rect4);//Add this betting zone to the straightBetZonemap
				rect4.setOnMouseEntered(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								rect4.setFill(Color.web("#00FF00",0.5));
								changeMessage("1 to 18");
							}
						});
						
				rect4.setOnMouseExited(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								rect4.setFill(Color.TRANSPARENT);
								setTableLight();
								changeMessage("Click on the table to place a bet");
							}
						});
				
			//Even bet
				Rectangle rect5 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.TRANSPARENT);//height is casted to int to avoid getting floating point number for a pixel size 
				straightBetZoneMap.put("even", rect5);//Add this betting zone to the straightBetZonemap
				rect5.setOnMouseEntered(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								rect5.setFill(Color.web("#00FF00",0.5));
								changeMessage("Even numbers");
							}
						});
						
				rect5.setOnMouseExited(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								rect5.setFill(Color.TRANSPARENT);
								setTableLight();
								changeMessage("Click on the table to place a bet");
							}
						});
				
			//Color bets
				//Red
					Rectangle rect6 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.TRANSPARENT);//height is casted to int to avoid getting floating point number for a pixel size 
					straightBetZoneMap.put("red", rect6);//Add this betting zone to the straightBetZonemap
					rect6.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect6.setFill(Color.web("#00FF00",0.5));
									changeMessage("Red");
								}
							});
							
					rect6.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect6.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
					
				//Black
					Rectangle rect7 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.TRANSPARENT);//height is casted to int to avoid getting floating point number for a pixel size 
					straightBetZoneMap.put("black", rect7);//Add this betting zone to the straightBetZonemap
					rect7.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect7.setFill(Color.web("#00FF00",0.5));
									changeMessage("Black");
								}
							});
							
					rect7.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect7.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
					
			//Odd bet
					Rectangle rect8 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.TRANSPARENT);//height is casted to int to avoid getting floating point number for a pixel size 
					straightBetZoneMap.put("odd", rect8);//Add this betting zone to the straightBetZonemap
					rect8.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect8.setFill(Color.web("#00FF00",0.5));
									changeMessage("Odd numbers");
								}
							});
							
					rect8.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect8.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
					
			//19 to 36 bet
					Rectangle rect9 = new Rectangle(table_BottomZoneWidth, (int)(TABLE_MAIN_CELL_HEIGHT*0.75), Color.TRANSPARENT);//height is casted to int to avoid getting floating point number for a pixel size 
					straightBetZoneMap.put("19to36", rect9);//Add this betting zone to the straightBetZonemap
					rect9.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect9.setFill(Color.web("#00FF00",0.5));
									changeMessage("19 to 36");
								}
							});
							
					rect9.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									rect9.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
					
			//Add the bets zone to the center zone
			tableStraightBetZone1.add(rect4, 0, 4, 2, 1);
			tableStraightBetZone1.add(rect5, 2, 4, 2, 1);
			tableStraightBetZone1.add(rect6, 4, 4, 2, 1);
			tableStraightBetZone1.add(rect7, 6, 4, 2, 1);
			tableStraightBetZone1.add(rect8, 8, 4, 2, 1);
			tableStraightBetZone1.add(rect9, 10, 4, 2, 1);
												
			
	/*2 to 1 zones*/
		Rectangle rect10 = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.TRANSPARENT);
		straightBetZoneMap.put("columnTop", rect10);//Add this betting zone to the straightBetZonemap
		rect10.setOnMouseEntered(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent mouseEvent)
					{
						rect10.setFill(Color.web("#00FF00",0.5));
						changeMessage("Column bet");
					}
				});
				
		rect10.setOnMouseExited(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent mouseEvent)
					{
						rect10.setFill(Color.TRANSPARENT);
						setTableLight();
						changeMessage("Click on the table to place a bet");
					}
				});
		
		Rectangle rect11 = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.TRANSPARENT);
		straightBetZoneMap.put("columnMid", rect11);//Add this betting zone to the straightBetZonemap
		rect11.setOnMouseEntered(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent mouseEvent)
					{
						rect11.setFill(Color.web("#00FF00",0.5));
						changeMessage("Column bet");
					}
				});
				
		rect11.setOnMouseExited(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent mouseEvent)
					{
						rect11.setFill(Color.TRANSPARENT);
						setTableLight();
						changeMessage("Click on the table to place a bet");
					}
				});
				
		
		Rectangle rect12 = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.TRANSPARENT);
		straightBetZoneMap.put("columnBottom", rect12);//Add this betting zone to the straightBetZonemap
		rect12.setOnMouseEntered(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent mouseEvent)
					{
						rect12.setFill(Color.web("#00FF00",0.5));
						changeMessage("Column bet");
					}
				});
				
		rect12.setOnMouseExited(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent mouseEvent)
					{
						rect12.setFill(Color.TRANSPARENT);
						setTableLight();
						changeMessage("Click on the table to place a bet");
					}
				});
		
		tableStraightBetZone2 = new GridPane();							
		tableStraightBetZone2.add(rect10, 0,0);
		tableStraightBetZone2.add(rect11, 0,1);
		tableStraightBetZone2.add(rect12, 0,2);
		

		//Add the zones to the tableBetLayout Pane
		tableBetLayout = new Pane();
		tableBetLayout.getChildren().addAll(tableStraightBetZone1, tableStraightBetZone2, tableStraightBetZone3);
		
		//Translate the zones to their proper position
		tableStraightBetZone2.setTranslateX(12*TABLE_MAIN_CELL_WIDTH+13*TABLE_MAIN_CELL_GAP);
		
		double leftZoneTranslateValue = -(int)((TABLE_MAIN_CELL_WIDTH + (TABLE_MAIN_CELL_WIDTH*3/8) + (Math.sqrt((Math.pow(TABLE_MAIN_CELL_GAP,2))*2))));//Calculation based on the size of inner and outer polygons used to make the leftZone (see the code used to make the leftZone for details)
		tableStraightBetZone3.setTranslateX(leftZoneTranslateValue);
		
		bet_0innerZone.setTranslateY(TABLE_MAIN_CELL_GAP*2);
		bet_00innerZone.setTranslateY(TABLE_MAIN_CELL_GAP);
		double zeroZonesXTranslation = (int)(Math.sqrt((Math.pow(TABLE_MAIN_CELL_GAP,2))*2));//Pythagorean theorem used to calculate the difference between the inner and outer triangle's width
		bet_0innerZone.setTranslateX(zeroZonesXTranslation);
		bet_00innerZone.setTranslateX(zeroZonesXTranslation);
		
		
		//Set the position of the tableLayout Pane
		tableBetLayout.setTranslateX(150);
		tableBetLayout.setTranslateY(520);
		
		//Set the gaps between the betting zones.
		tableStraightBetZone1.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		tableStraightBetZone1.setVgap(TABLE_MAIN_CELL_GAP);
		tableStraightBetZone1.setHgap(TABLE_MAIN_CELL_GAP);
		tableStraightBetZone1.setPadding(new Insets(TABLE_MAIN_CELL_GAP));
		
		tableStraightBetZone2.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		tableStraightBetZone2.setVgap(TABLE_MAIN_CELL_GAP);
		tableStraightBetZone2.setHgap(TABLE_MAIN_CELL_GAP);
		tableStraightBetZone2.setPadding(new Insets(TABLE_MAIN_CELL_GAP, TABLE_MAIN_CELL_GAP, TABLE_MAIN_CELL_GAP, 0));
	}
	
	private void setSplitBetZones()
	{
		//Side (vertical) split bet zones
			//The side split bet zones are created at the left of each 4 to 36 rectangles of the table layout
			int mapKeyNumber = 1;//Incrementing number used to make the splitBetZoneMap keys
			for (int i=1; i<=11 ; i++)
			{
				for (int j=1; j<=3; j++)
				{
					Rectangle sideBetZone = new Rectangle((int)(TABLE_MAIN_CELL_WIDTH*0.40), (int)(TABLE_MAIN_CELL_HEIGHT*2/3), Color.TRANSPARENT);
					splitBetZoneMap.put(String.valueOf(3*i-(j-1))+"-"+(String.valueOf(3*(i+1)-(j-1))), sideBetZone); //The key is a string made of both numbers included in the split bet. Ex: 3-6 for the 3 and 6 split bet
					sideBetZone.setTranslateX(i * (TABLE_MAIN_CELL_WIDTH + TABLE_MAIN_CELL_GAP) - sideBetZone.getWidth()/2 + TABLE_MAIN_CELL_GAP/2);
					sideBetZone.setTranslateY(((j-1)*TABLE_MAIN_CELL_HEIGHT) + (TABLE_MAIN_CELL_HEIGHT*1/6) + (j*TABLE_MAIN_CELL_GAP));
					
					//Add the side split bet zone to the tableBetLayout
					tableBetLayout.getChildren().add(sideBetZone);
					mapKeyNumber++;
					
					//Add a MouseEntered and a MouseExited Listener
					sideBetZone.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									sideBetZone.setFill(Color.web("#00FF00",0.5));
									changeMessage("Split bet");
								}
							});
							
					sideBetZone.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									sideBetZone.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
				}
			}
		//Bottom (horizontal) split bet zones
			//The bottom split bet zones are created at the bottom of the rectangles of the first two lines of the table layout
			mapKeyNumber = 1;//Incrementing number used to make the splitBetZoneMap keys
			for (int i=1; i<=12 ; i++)
			{
				for (int j=1; j<=2; j++)
				{
					Rectangle bottomBetZone = new Rectangle((int)(TABLE_MAIN_CELL_WIDTH*2/3), (int)(TABLE_MAIN_CELL_HEIGHT*0.4), Color.TRANSPARENT);
					splitBetZoneMap.put((String.valueOf(3*i-(j-1)-1) +"-"+ String.valueOf(3*i-(j-1))), bottomBetZone); //The key is a string made of both numbers included in the split bet. Ex: 2-3 for the 2 and 3 split bet
					bottomBetZone.setTranslateX(((i-1)*TABLE_MAIN_CELL_WIDTH) + (TABLE_MAIN_CELL_WIDTH*1/6) + (i*TABLE_MAIN_CELL_GAP) + TABLE_MAIN_CELL_GAP/2);
					bottomBetZone.setTranslateY(j*(TABLE_MAIN_CELL_HEIGHT + TABLE_MAIN_CELL_GAP) - bottomBetZone.getHeight()/2 + TABLE_MAIN_CELL_GAP/2);
					
					//Add the bottom split bet zone to the tableBetLayout
					tableBetLayout.getChildren().add(bottomBetZone);
					mapKeyNumber++;
					
					//Add a MouseEntered and a MouseExited Listener
					bottomBetZone.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									bottomBetZone.setFill(Color.web("#00FF00",0.5));
									changeMessage("Split bet");
								}
							});
							
					bottomBetZone.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									bottomBetZone.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
				}
			}
	}
	
	private void setStreetBetZones()
	{
		//Street bet zones
			//The street bet zones are created at the bottom of each row of the table layout
			int mapKeyNumber = 1;//Incrementing number used to make the streetBetZoneMap keys
			for (int i=1; i<=12 ; i++)
			{
				Rectangle streetBetZone = new Rectangle((int)(TABLE_MAIN_CELL_WIDTH*2/3), (int)(TABLE_MAIN_CELL_HEIGHT*0.4), Color.TRANSPARENT);
				streetBetZoneMap.put("street"+mapKeyNumber, streetBetZone); //The key is a string made of "street" + the first number of the column (1, 4, 7, 10,...)
				streetBetZone.setTranslateX(((i-1)*TABLE_MAIN_CELL_WIDTH) + (TABLE_MAIN_CELL_WIDTH*1/6) + (i*TABLE_MAIN_CELL_GAP) + TABLE_MAIN_CELL_GAP/2);
				streetBetZone.setTranslateY(3*(TABLE_MAIN_CELL_HEIGHT + TABLE_MAIN_CELL_GAP) - streetBetZone.getHeight()/2 + TABLE_MAIN_CELL_GAP/2);
				
				//Add the street bet zone to the tableBetLayout
				tableBetLayout.getChildren().add(streetBetZone);
				mapKeyNumber += 3;
				
				//Add a MouseEntered and a MouseExited Listener
				streetBetZone.setOnMouseEntered(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								streetBetZone.setFill(Color.web("#00FF00",0.5));
								changeMessage("Street bet");
							}
						});
						
				streetBetZone.setOnMouseExited(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								streetBetZone.setFill(Color.TRANSPARENT);
								setTableLight();
								changeMessage("Click on the table to place a bet");
							}
						});
			}
		//Double street bet zones
			//The street between each street bet zone
			for (int i=1; i<=11; i++)
			{
				Rectangle doubleStreetBetZone = new Rectangle((int)(TABLE_MAIN_CELL_WIDTH*1/2), (int)(TABLE_MAIN_CELL_HEIGHT*0.4), Color.TRANSPARENT);
				streetBetZoneMap.put("doublestreet"+mapKeyNumber, doubleStreetBetZone); //The key is a string made of "doublestreet" + the first numbers of the 2 columns (dobuelstreet14, doublestreet47,...)
				doubleStreetBetZone.setTranslateX(i * (TABLE_MAIN_CELL_WIDTH + TABLE_MAIN_CELL_GAP) - doubleStreetBetZone.getWidth()/2 + TABLE_MAIN_CELL_GAP/2);
				doubleStreetBetZone.setTranslateY(3*(TABLE_MAIN_CELL_HEIGHT + TABLE_MAIN_CELL_GAP) - doubleStreetBetZone.getHeight()/2 + TABLE_MAIN_CELL_GAP/2);
				
				//Add the double street bet zone to the tableBetLayout
				tableBetLayout.getChildren().add(doubleStreetBetZone);
				mapKeyNumber += 3;
				
				//Add a MouseEntered and a MouseExited Listener
				doubleStreetBetZone.setOnMouseEntered(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								doubleStreetBetZone.setFill(Color.web("#00FF00",0.5));
								changeMessage("Double Street bet");
							}
						});
						
				doubleStreetBetZone.setOnMouseExited(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								doubleStreetBetZone.setFill(Color.TRANSPARENT);
								setTableLight();
								changeMessage("Click on the table to place a bet");
							}
						});
			}
	}
	
	private void setCornerBetZones()
	{
		//The corner bet zones are created at the bottom-left of each rectangle of the first two lines of the table layout
		for (int i=1; i<=11 ; i++)
		{
			for (int j=1; j<=2; j++)
			{
				Rectangle cornerBetZone = new Rectangle((int)(TABLE_MAIN_CELL_WIDTH*1/2), (int)(TABLE_MAIN_CELL_HEIGHT*0.4), Color.TRANSPARENT);
				cornerBetZoneMap.put(String.valueOf(3*i-(j-1)-1) +"-"+ String.valueOf(3*i-(j-1)) +"-"+ String.valueOf(3*(i+1)-(j-1)-1) +"-"+ String.valueOf(3*(i+1)-(j-1)), cornerBetZone); //The key is a string made of all the number included in the corner bet Ex: 2-3-4-5 or 1-2-3-4
				cornerBetZone.setTranslateX(i * (TABLE_MAIN_CELL_WIDTH + TABLE_MAIN_CELL_GAP) - cornerBetZone.getWidth()/2 + TABLE_MAIN_CELL_GAP/2);
				cornerBetZone.setTranslateY(j*(TABLE_MAIN_CELL_HEIGHT + TABLE_MAIN_CELL_GAP) - cornerBetZone.getHeight()/2 + TABLE_MAIN_CELL_GAP/2);
				
				//Add the corner bet zone to the tableBetLayout
				tableBetLayout.getChildren().add(cornerBetZone);
				
				//Add a MouseEntered and a MouseExited Listener
				cornerBetZone.setOnMouseEntered(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								cornerBetZone.setFill(Color.web("#00FF00",0.5));
								changeMessage("Corner bet");
							}
						});
						
				cornerBetZone.setOnMouseExited(new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent mouseEvent)
							{
								cornerBetZone.setFill(Color.TRANSPARENT);
								setTableLight();
								changeMessage("Click on the table to place a bet");
							}
						});
			}
		}
	}
	
	private void setBasketBetZone()
	{
		//Basket bet zone
				String mapKeyValue = "";
				
				for (int i=1; i<=3 ; i++)
				{	
					switch(i)
					{
						case 1: mapKeyValue = "2-00-3";
								break;
						case 2: mapKeyValue = "0-2-00";
								break;
						case 3: mapKeyValue = "1-0-2";
								break;
					}					
					
					Rectangle basketBetZone = new Rectangle((int)(TABLE_MAIN_CELL_WIDTH*1/2), (int)(TABLE_MAIN_CELL_HEIGHT*0.4), Color.TRANSPARENT);
					basketBetZoneMap.put(mapKeyValue,basketBetZone); //The key is a string made of all the number included in the basket bet: 2-00-3 , 0-2-00, 1-0-2
					basketBetZone.setTranslateX(-basketBetZone.getWidth()/2 + TABLE_MAIN_CELL_GAP/2);
					if (i==2)
						basketBetZone.setTranslateY(TABLE_MAIN_CELL_HEIGHT*1.5 + 2*TABLE_MAIN_CELL_GAP - basketBetZone.getHeight()/2);	
					else if (i==1)
						basketBetZone.setTranslateY(TABLE_MAIN_CELL_HEIGHT + TABLE_MAIN_CELL_GAP - basketBetZone.getHeight()/2);
					else
						basketBetZone.setTranslateY(2*(TABLE_MAIN_CELL_HEIGHT + TABLE_MAIN_CELL_GAP - basketBetZone.getHeight()/2) + basketBetZone.getHeight()/2);
					
					//Add the basket bet zone to the tableBetLayout
					tableBetLayout.getChildren().add(basketBetZone);
					
					
					//Add a MouseEntered and a MouseExited Listener
					basketBetZone.setOnMouseEntered(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									basketBetZone.setFill(Color.web("#00FF00",0.5));
									changeMessage("Basket bet");
								}
							});
							
					basketBetZone.setOnMouseExited(new EventHandler<MouseEvent>()
							{
								@Override
								public void handle(MouseEvent mouseEvent)
								{
									basketBetZone.setFill(Color.TRANSPARENT);
									setTableLight();
									changeMessage("Click on the table to place a bet");
								}
							});
				}
	}
	
	private void topLineBetZone()
	{
		//TopLine bet zone1		
			Rectangle topLineBetZone1 = new Rectangle((int)(TABLE_MAIN_CELL_WIDTH*1/2), (int)(TABLE_MAIN_CELL_HEIGHT*0.4), Color.BLUE);
			topLineBetZoneMap.put("topline1",topLineBetZone1); 
			topLineBetZone1.setTranslateX(-topLineBetZone1.getWidth()/2 + TABLE_MAIN_CELL_GAP/2);
			topLineBetZone1.setTranslateY(3*(TABLE_MAIN_CELL_HEIGHT + TABLE_MAIN_CELL_GAP) - topLineBetZone1.getHeight()/2 + TABLE_MAIN_CELL_GAP/2);
			
			//Add a MouseEntered and a MouseExited Listener
			topLineBetZone1.setOnMouseEntered(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent mouseEvent)
						{
							topLineBetZone1.setFill(Color.web("#00FF00",0.5));
							changeMessage("Top line bet");
						}
					});
					
			topLineBetZone1.setOnMouseExited(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent mouseEvent)
						{
							topLineBetZone1.setFill(Color.TRANSPARENT);
							setTableLight();
							changeMessage("Click on the table to place a bet");
						}
					});
			
		//TopLine bet zone2	
		Rectangle topLineBetZone2 = new Rectangle((int)(TABLE_MAIN_CELL_WIDTH*1/2), (int)(TABLE_MAIN_CELL_HEIGHT*0.4), Color.BLUE);
		topLineBetZoneMap.put("topline1",topLineBetZone2); 
		topLineBetZone2.setTranslateX(-topLineBetZone2.getWidth()/2 + TABLE_MAIN_CELL_GAP/2);
		topLineBetZone2.setTranslateY(- topLineBetZone2.getHeight()/2 + TABLE_MAIN_CELL_GAP/2);
		
		//Add a MouseEntered and a MouseExited Listener
		topLineBetZone2.setOnMouseEntered(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent mouseEvent)
					{
						topLineBetZone2.setFill(Color.web("#00FF00",0.5));
						changeMessage("Top line bet");
					}
				});
				
		topLineBetZone2.setOnMouseExited(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent mouseEvent)
					{
						topLineBetZone2.setFill(Color.TRANSPARENT);
						setTableLight();
						changeMessage("Click on the table to place a bet");
					}
				});
		
		//Add the basket bet zone to the tableBetLayout
		tableBetLayout.getChildren().addAll(topLineBetZone1, topLineBetZone2);
	}
	
	private void setButtons()
	{
		spinTheWheelButton = new Button("Spin the wheel!");
		spinTheWheelButton.setPrefSize(200, 75);
		//spinTheWheelButton.setDisable(true);
		spinTheWheelButton.setTranslateX(60);
		spinTheWheelButton.setStyle("-fx-font-size:18pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		
		removeLastBetButton = new Button("Remove last bet");
		removeLastBetButton.setPrefSize(150, 50);
		removeLastBetButton.setDisable(true);
		removeLastBetButton.setTranslateY(85);
		removeLastBetButton.setStyle("-fx-font-size:12pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		
		removeAllBetsButton = new Button("Remove all bets");
		removeAllBetsButton.setPrefSize(150, 50);
		removeAllBetsButton.setDisable(true);
		removeAllBetsButton.setTranslateX(160);
		removeAllBetsButton.setTranslateY(85);
		removeAllBetsButton.setStyle("-fx-font-size:12pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		
		
		buttonsGroup = new Group(spinTheWheelButton, removeLastBetButton, removeAllBetsButton);
	}
	
	private void setMessageZone(String message)
	{
		Label msg = new Label(message);
		msg.setStyle("-fx-font-size: 20pt;");
		msg.setMaxWidth(280);
		msg.setWrapText(true);
		msg.setMaxWidth(Integer.MAX_VALUE);
		msg.setTextAlignment(TextAlignment.CENTER);
		msg.setAlignment(Pos.CENTER);
		
		msgZone = new StackPane();//StackPane necessary to allow center alignment of children
		
		msgZone.getChildren().add(msg);
		msgZone.setAlignment(Pos.CENTER);
		
		int msgZoneSizeX = 280;
		int msgZoneSizeY = 225;
		msgZone.setMinHeight(msgZoneSizeY);
		msgZone.setMaxHeight(msgZoneSizeY);
		msgZone.setMinWidth(msgZoneSizeX);
		msgZone.setMaxWidth(msgZoneSizeX);
		msgZone.setPrefSize(msgZoneSizeX, msgZoneSizeY);
		//msgZone.setBorder(new Border(new BorderStroke(Paint.valueOf("blue"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY,  BorderWidths.DEFAULT)));
	}
	
	private void changeMessage(String message)
	{
		Label msg = ((Label)msgZone.getChildren().get(0));
		msg.setText(message);
	}
	
	public void playWheelAnimation()
	{	
		//Take the ball at it's initial position
		bringBallToInitialPosition();
		
		//Wheel Rotation Animation				
			Rotate wheelRotation = new Rotate();
			
			//Move the rotation pivot to the center of the wheel
			//The default rotation pivot is at the top left corner of the 00 pocket bounds.
			double pivotXTranslation = (pocket00Pane.getWidth())/2; //The x translation of the pivot is equal to half the width of a pocket.
			wheelRotation.setPivotX(pivotXTranslation);
			double pivotYTranslation = INNERCIRCLERADIUS + (OUTERCIRCLERADIUS-INNERCIRCLERADIUS);
			wheelRotation.setPivotY(pivotYTranslation);
			
			pocketsPane.getTransforms().add(wheelRotation);
			
			//Timeline for wheel rotation animation			
			Timeline timelineWheel = new Timeline();
			
			Double lastRotationAngle = Math.random()*360.0;//The angle of the last rotation of the wheel is random
			
			timelineWheel.getKeyFrames().add(new KeyFrame(Duration.millis(12000), new KeyValue(wheelRotation.angleProperty(),720+lastRotationAngle, Interpolator.SPLINE(0.25, 0.4, 0.6, 0.99))));//SPLINE interpolator creates a curve going between 0,0 and 1,1 (x=time, y = animation progress, 1 being 100%). Playing with the coordinates parameters allows to adjust the deceleration vs time.
			
			SequentialTransition wheelAnimationSequence = new SequentialTransition(timelineWheel);
			
			wheelAnimationSequence.play();
			
			
		//Ball Animation
			//Ball rotations
				Path ballPath = new Path();
				
				MoveTo moveToBallStart = new MoveTo();
				moveToBallStart.setX(rouletteWheelXTranslation + OUTERMOSTCIRCLERADIUS);
				moveToBallStart.setY(rouletteWheelYTranslation + ballStackInnerWheelYTranslation); 
				
				ArcTo arcToHalf = new ArcTo();
				arcToHalf.setX(moveToBallStart.getX());
				arcToHalf.setY(moveToBallStart.getY() + (OUTERCIRCLERADIUS+OUTERCIRCLESTROKE-ballStackInnerWheelYTranslation)*2);
				arcToHalf.setRadiusX(OUTERCIRCLERADIUS+OUTERCIRCLESTROKE-ballStackInnerWheelYTranslation);
				arcToHalf.setRadiusY(OUTERCIRCLERADIUS+OUTERCIRCLESTROKE-ballStackInnerWheelYTranslation);		
				
				ArcTo arcToStart = new ArcTo();
				arcToStart.setX(moveToBallStart.getX());
				arcToStart.setY(moveToBallStart.getY());
				arcToStart.setRadiusX(OUTERCIRCLERADIUS+OUTERCIRCLESTROKE-ballStackInnerWheelYTranslation);
				arcToStart.setRadiusY(OUTERCIRCLERADIUS+OUTERCIRCLESTROKE-ballStackInnerWheelYTranslation);
				
				double[] finishPointCoordinnates = calculateBallLastRotationPosition();//Calculate a random final position around the wheel
				ArcTo arcToFinish = new ArcTo();
				arcToFinish.setX(moveToBallStart.getX() + finishPointCoordinnates[0]);
				arcToFinish.setY(moveToBallStart.getY() + finishPointCoordinnates[1]);
				arcToFinish.setRadiusX(OUTERCIRCLERADIUS+OUTERCIRCLESTROKE-ballStackInnerWheelYTranslation);
				arcToFinish.setRadiusY(OUTERCIRCLERADIUS+OUTERCIRCLESTROKE-ballStackInnerWheelYTranslation);
				
				//If the x coordinnate is higher than Pi(half the wheel), go to half the path (arcToHalf) before to go to the final position
				//Whithout that check, the path of the ball is wrong when the final position is in the right half of the wheel
				if (finishPointCoordinnates[0] > 0)
				{
							
					ballPath.getElements().addAll(moveToBallStart, arcToHalf, arcToStart,arcToHalf, arcToStart, arcToHalf, arcToStart, arcToHalf, arcToFinish);
				}
				//else, go right to the final position
				else
				{
					ballPath.getElements().addAll(moveToBallStart, arcToHalf, arcToStart,arcToHalf, arcToStart, arcToHalf, arcToStart, arcToFinish);
				}
						
				PathTransition pathTransition = new PathTransition(Duration.millis(8000), ballPath, rouletteBallStack);
				pathTransition.setInterpolator(Interpolator.SPLINE(0.25, 0.4, 0.6, 0.8));//Spline creates a curve going between 0,0 and 1,1 (x=time, y = animation progress, 1 being 100%). Playing with the coordinates parameters allows to adjust the deceleration vs time.
			
							
			SequentialTransition ballAnimationSequence = new SequentialTransition(pathTransition);
			ballAnimationSequence.play();
			
			ballAnimationSequence.setOnFinished(new EventHandler<ActionEvent>()
					{
						@Override
						public void handle(ActionEvent event)
						{
							playGetBallInPocketZoneAnimation(arcToFinish);
						}
					});
	}
	
	//This method creates and plays the animation that gets the ball from it's final rotating point to a pocket.
	//ArcToFinish parameter is received to access the x,y coordinates of the ball after it's rotation.
	private void playGetBallInPocketZoneAnimation(ArcTo arcToFinish)
	{		
		//Find the ArrayList's index of the pocket that is closest to the ball at the end of it's rotation
			int closestPocketArrayIndex = -1;
			Double closestDistance = 9999.9;
			
			int arrayIndex = 0;
			
			for(Pocket pocket: LogicalRoulette.pocketsList)
			{
				Path pathTemp = pocket.getPath();
				Point2D pathCoordinnates = pathTemp.localToScene(Point2D.ZERO);
				Point2D ballCenterCoordinates = ballCenter.localToScene(Point2D.ZERO);
				
				Double distanceX = pathCoordinnates.getX() - ballCenterCoordinates.getX();
				Double distanceY = pathCoordinnates.getY() - ballCenterCoordinates.getY();
				
				Double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));//Pythagorean theorem to get the distance between the ball and the pocket
				
				if(distance < closestDistance)
				{
					closestDistance = distance;
					closestPocketArrayIndex = arrayIndex;
				}
			
				arrayIndex++;
			}		
			
			//Get the coordinates of the seventh pocket following the closest pocket. 
			//It is used to create a path animation that gets the ball into the pocket zone.
			//It does not mean that the seventh pocket is the final pocket, 
			//we only use it's coordinate to make the path.
				int seventhPocketIndex = closestPocketArrayIndex + 7;
				if (seventhPocketIndex > 37)//The ArrayLists indexes go from 0 to 37. After 37, it must go back to 0.
				{
					switch (seventhPocketIndex)
					{
						case 38:
							seventhPocketIndex = 0;
							break;
							
						case 39:
							seventhPocketIndex = 1;
							break;
						
						case 40:
							seventhPocketIndex = 2;
							
						case 41:
							seventhPocketIndex = 3;
							break;
							
						case 42:
							seventhPocketIndex = 4;
							break;
						
						case 43:
							seventhPocketIndex = 5;
							break;
							
						case 44:
							seventhPocketIndex = 6;
					}
				}
				
			Pocket seventhPocket = LogicalRoulette.pocketsList.get(seventhPocketIndex);
			Point2D seventhPocketPathCoord = seventhPocket.getPath().localToScene(Point2D.ZERO);
			Point2D ballCenterCoordinates = ballCenter.localToScene(Point2D.ZERO);
			
			Double seventhPocketXDistance = seventhPocketPathCoord.getX() - ballCenterCoordinates.getX();
			Double seventhPocketYDistance = seventhPocketPathCoord.getY() - ballCenterCoordinates.getY();
		
		//For this animation, the initial position of the ball is at the end of it's rotation.
			MoveTo moveToBallEnd = new MoveTo();
			moveToBallEnd.setX(arcToFinish.getX());
			moveToBallEnd.setY(arcToFinish.getY());	
		
		//Get the ball into the pocket zone by following the path to the seventhPocket's coordinates
			ArcTo arcToPocket = new ArcTo();
			
			arcToPocket.setX(moveToBallEnd.getX() + 0.55*seventhPocketXDistance);//Get the ball at 55% of the path to seventhPockets position. It gets it inside the wheel where we want. 55% is determined by trial and error.
			arcToPocket.setY(moveToBallEnd.getY() + 0.55*seventhPocketYDistance);//Get the ball at 55% of the path to seventhPockets position. It gets it inside the wheel where we want. 55% is determined by trial and error.
			arcToPocket.setRadiusX(OUTERMOSTCIRCLERADIUS-ballStackInnerWheelYTranslation);//ArcTo path elements are elliptical. RadiusX is the distance between the center of the wheel and the ball.
			arcToPocket.setRadiusY(OUTERMOSTCIRCLERADIUS-ballStackInnerWheelYTranslation-100);//YRadius determined by trial and error
			arcToPocket.setXAxisRotation(-(Math.toDegrees(finalSpinAngle) - 45));//- 45 determined by trial and error. It rotates the elliptical path to get a good looking path.
			
			//DEBUG
			//System.out.println("initX:" + moveToBallEnd.getX());
			//System.out.println("initY:" + moveToBallEnd.getY());
			//System.out.println("EndX:" + arcToPocket.getX());
			//System.out.println("EndY:" + arcToPocket.getY());
			
		//Create the ball path from the finished rotation's ball position to the pocket zone
		Path ballToPocketPath = new Path();
		ballToPocketPath.getElements().addAll(moveToBallEnd, arcToPocket);
		
		PathTransition ballToPocketTransition = new PathTransition(Duration.millis(1000), ballToPocketPath, rouletteBallStack);
		ballToPocketTransition.setInterpolator(Interpolator.SPLINE(0.25, 0.4, 0.6, 0.8));//Spline creates a curve going between 0,0 and 1,1 (x=time, y = animation progress, 1 being 100%). Playing with the coordinates parameters allows to adjust the deceleration vs time.
		ballToPocketTransition.play();
		
		ballToPocketTransition.setOnFinished(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						/*Get the ball in it's final resting position in the pocket that it collides
						 * Made by placing the rouletteBallStack in the parent (StackPane) of the collidingPocket's path 
						 */						
						Pocket finalPocket = getCollidingPocket();
						StackPane finalPocketStackPane = (StackPane) finalPocket.getPath().getParent();
						rouletteBallStack.setTranslateY(12);//To replace the initial Y translation of the ballStack and place the ball in the proper place inside the pocket
						rouletteBallStack.setTranslateX(0);//To replace the initial X translation of the ballStack
						finalPocketStackPane.getChildren().add(2, rouletteBallStack);
						
						/*DEBUG
						 * System.out.println("transalteY:" + rouletteBallStack.getTranslateY());
						System.out.println(finalPocket.getPath().getParent());
						System.out.println("transalteX:" + rouletteBallStack.getTranslateX());
						System.out.println(finalPocket.getNumber());
						System.out.println("test");
						System.out.println(rouletteBallStack.getParent());*/
						}	
				});
	}
	
	
	
	//This method is used to calculate the position of the ball after the rotation
	//It calculates a position around the wheel based on a random rotation angle.
	//See WheelBallFinalPositionCalculation.xlsx for more details.
	private double[] calculateBallLastRotationPosition()
	{
		//Final x position of the ball around the wheel
		double x;
		//Final y position of the ball around the wheel
		double y;
		
		double[] coordinateTable = new double[2];//Table containing the x and y coordinates. Used to return them.
		
		//Random rotation angle (from the center of the wheel to the final position of the ball)
		//The angle is in rads for calculation purpose
		finalSpinAngle = Math.random()*2*Math.PI;
		//finalSpinAngle = 2*Math.PI*0.25; //DEBUG (DETERMINED ANGLE)
		
				
		double ballRadius = OUTERMOSTCIRCLERADIUS-ballStackInnerWheelYTranslation;//Value of the length between the center of the wheel and the center of the ball
		double angleChord = 2*ballRadius*Math.sin(finalSpinAngle/2);//Chord of the finalSpinAngle, see https://en.wikipedia.org/wiki/Chord_(geometry)#Chords_in_trigonometry	
				
		if (finalSpinAngle < Math.PI)
			x = -(Math.sin(Math.acos((angleChord/2)/ballRadius))*(angleChord));
		else
			x = Math.sin(Math.acos((angleChord/2)/ballRadius))*(angleChord);
		
		y = Math.cos(Math.acos((angleChord/2)/ballRadius))*(angleChord);
		
		coordinateTable[0] = x;
		coordinateTable[1] = y;	
				
		return coordinateTable;
	}
	
	//Check which pocket is intersecting the ballCenter.
	//Returns that pocket.
	private Pocket getCollidingPocket()
	{
		Shape collisionShape = null;
		Pocket collidingPocket = null;
		for (Pocket pocket: LogicalRoulette.pocketsList)
		{
			collisionShape = Shape.intersect(ballCenter, pocket.getPath());
			if (collisionShape.getBoundsInLocal().getWidth() != -1)//-1 because it is the returned value of .intersect when no intersection is detected
			{
				collidingPocket = pocket;
				//DEBUG
				//System.out.println("pocket:" + pocket.getNumber());
			}
		}
		return collidingPocket;
	}
}
