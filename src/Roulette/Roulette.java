package Roulette;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;

import java.util.ArrayList;
import java.util.Arrays;

import Casino.GameInterface;
import javafx.animation.RotateTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
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
import javafx.util.Duration;
import javafx.scene.effect.Light;

public class Roulette extends GameInterface
{
	public Scene scene = null;
	private Pane root  = null;
	private Group buttonsGroup = null;
	private VBox msgZone = null;
	
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
	private ImageView roulette_imgView = null;
	private Pane rouletteWheel = null;
	static final int INNERCIRCLERADIUS = 120;//Used to calculate the shape of the pockets
	public static final int OUTERCIRCLERADIUS = 180;//Used to calculate the shape of the pockets
	static final int OUTERCIRCLESTROKE = 30;
	
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
		//Pane containing the different table zones
		private Pane tableLayout = null;
		
			//Left Zone containing the zeros
			private VBox tableLeftZone = null;
			
			//Center Zone containing the numbers (1 to 36) and the bottom special bets
				private GridPane tableCenterZone = null;
				
			//Bottom zone containing the 2 to 1's bets
			private GridPane tableRightZone = null;
	
	public Roulette()
	{
		root = new Pane();
		scene = new Scene(root, 800,800);
		
		LogicalRoulette.createPocketObjects();//create the pockets objects, used in setWheel()
		createMenu();
		createPlayerInfo();
		setWheel();
		setBall();
		setTable();
		setButtons();
		setMessageZone("Click on the table to place a bet!");
		checkBallLocation();
		
		/*Line test = new Line (0.0, 0.0, 0.0, 125.0);
		test.setStrokeWidth(5);
		test.setStroke(Color.BLUE);
		test.setTranslateX(250);
		test.setTranslateY(100);*/
		
		root.getChildren().addAll(msgZone, super.playerInfo, super.upperZone, rouletteBallStack, tableLayout, buttonsGroup, rouletteWheel, pocketsPane);
		
		super.playerInfo.setTranslateX(500);
		super.playerInfo.setTranslateY(15);
		
		buttonsGroup.setTranslateX(430);
		buttonsGroup.setTranslateY(370);
		
		msgZone.setTranslateX(475);
		msgZone.setTranslateY(130);
		
		rouletteWheel.setTranslateX(250);
		rouletteWheel.setTranslateY(250);
		//rouletteWheel.setOpacity(0.5);
		
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	private void setWheel()
	{
		//Wheel (image)
			Image roulette_img = new Image("RouletteWheel.png",450D,0, true, true);
			roulette_imgView = new ImageView(roulette_img);
			roulette_imgView.setTranslateX(20);
			roulette_imgView.setTranslateY(30);
			
		//Wheel (made of Inner Circle, numbered and colored pockets(see Pocket class), and an outer circle 
			//Inner circle
				Circle innerCircle = new Circle(INNERCIRCLERADIUS);
				innerCircle.setFill(Color.GOLDENROD);
				Light.Point light = new Light.Point();
				light.setX(150);
				 light.setY(150);
				 light.setZ(150);
				Lighting innerWheelLighting = new Lighting();
				innerWheelLighting.setLight(light);
				
				innerWheelLighting.setSurfaceScale(5.0);
				//innerCircle.setEffect(innerWheelLighting);
				
			//Outer circle
				Circle outerCircle = new Circle(OUTERCIRCLERADIUS);
				outerCircle.setOpacity(1);
				outerCircle.setStrokeWidth(OUTERCIRCLESTROKE);
				outerCircle.setStrokeType(StrokeType.OUTSIDE);
				outerCircle.setStroke(Color.DARKRED);
				outerCircle.setStyle("fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%), linear-gradient(#020b02, #3a3a3a), linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%), linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%), linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%)");
				
			//Graphic Pockets
				 /*pocket00Pane = createPocketPane(00, Color.GREEN);
				 pocket0Pane = createPocketPane(0, Color.GREEN);
				 pocket1Pane = createPocketPane(1, Color.RED);
				 pocket2Pane = createPocketPane(2, Color.BLACK);
				 pocket3Pane = createPocketPane(3, Color.RED);
				 pocket4Pane = createPocketPane(4, Color.BLACK);
				 pocket5Pane = createPocketPane(5, Color.RED);		
				 pocket6Pane = createPocketPane(6, Color.BLACK);		
				 pocket7Pane = createPocketPane(7, Color.RED);
				 pocket8Pane = createPocketPane(8, Color.BLACK);
				 pocket9Pane = createPocketPane(9, Color.RED);
				 pocket10Pane = createPocketPane(10, Color.BLACK);
				 pocket11Pane = createPocketPane(11, Color.BLACK);
				 pocket12Pane = createPocketPane(12, Color.RED);
				 pocket13Pane = createPocketPane(13, Color.BLACK);
				 pocket14Pane = createPocketPane(14, Color.RED);
				 pocket15Pane = createPocketPane(15, Color.BLACK);
				 pocket16Pane = createPocketPane(16, Color.RED);
				 pocket17Pane = createPocketPane(17, Color.BLACK);
				 pocket18Pane = createPocketPane(18, Color.RED);
				 pocket19Pane = createPocketPane(19, Color.RED);
				 pocket20Pane = createPocketPane(20, Color.BLACK);
				 pocket21Pane = createPocketPane(21, Color.RED);
				 pocket22Pane = createPocketPane(22, Color.BLACK);
				 pocket23Pane = createPocketPane(23, Color.RED);
				 pocket24Pane = createPocketPane(24, Color.BLACK);
				 pocket25Pane = createPocketPane(25, Color.RED);
				 pocket26Pane = createPocketPane(26, Color.BLACK);
				 pocket27Pane = createPocketPane(27, Color.RED);
				 pocket28Pane = createPocketPane(28, Color.BLACK);
				 pocket29Pane = createPocketPane(29, Color.BLACK);
				 pocket30Pane = createPocketPane(30, Color.RED);
				 pocket31Pane = createPocketPane(31, Color.BLACK);
				 pocket32Pane = createPocketPane(32, Color.RED);
				 pocket33Pane = createPocketPane(33, Color.BLACK);
				 pocket34Pane = createPocketPane(34, Color.RED);
				 pocket35Pane = createPocketPane(35, Color.BLACK);
				 pocket36Pane = createPocketPane(36, Color.RED);*/
				
				//Paths
				pocket00Path = LogicalRoulette.pocket00.getPath();
				pocket00Path.setFill(Color.GREEN);
				
				pocket27Path = LogicalRoulette.pocket27.getPath();
				pocket27Path.setFill(Color.RED);
				
				//Labels
				Label pocket00Label = new Label();
				pocket00Label.setText("00");
				pocket00Label.setTextFill(Color.WHITE);
				pocket00Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
					
				Label pocket27Label = new Label();
				pocket27Label.setText("27");
				pocket27Label.setTextFill(Color.WHITE);
				pocket27Label.setTranslateY(-0.3*(OUTERCIRCLERADIUS-INNERCIRCLERADIUS));
				
				//Individual pockets Panes
				pocket00Pane = new StackPane();
				pocket00Pane.getChildren().addAll(pocket00Path, pocket00Label);
				pocket00Pane.setTranslateX(235);
				pocket00Pane.setTranslateY(70);
				
				pocket27Pane = new StackPane();
				pocket27Pane.getChildren().addAll(pocket27Path, pocket27Label);
				pocket27Pane.setTranslateX(235);
				pocket27Pane.setTranslateY(70);
				pocket27Pane.setRotate(INNERCIRCLERADIUS/38);
				
				//Pockets main pane
				pocketsPane = new Pane();
				pocketsPane.getChildren().addAll(pocket00Pane, pocket27Pane);
				//pocketsPane.setTranslateX(235);
				//pocketsPane.setTranslateY(70);
				
		
		rouletteWheel = new Pane();
		rouletteWheel.getChildren().addAll(outerCircle, innerCircle);//**********AJOUTER LE PANE DES POCKETS
	}
	
	private void setBall()
	{
		//Ball (Circle)
		rouletteBall = new Circle(10, Color.WHITESMOKE);
	
		//Center point, made of a 1 pixel rectangle
		ballCenter = new Rectangle(1, 1, Color.RED);
				
		//A stackpane to automatically center the ballCenter in the middle of the ball
		rouletteBallStack = new StackPane();
		rouletteBallStack.getChildren().addAll( rouletteBall, ballCenter);
		rouletteBallStack.setTranslateX(250);
		rouletteBallStack.setTranslateY(20);
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
		msg.setMaxWidth(300);
		msg.setWrapText(true);
		msg.setTextAlignment(TextAlignment.CENTER);
		
		msgZone = new VBox();
		
		msgZone.getChildren().add(msg);
		msgZone.setAlignment(Pos.CENTER);
		
		int msgZoneSizeX = 300;
		int msgZoneSizeY = 225;
		msgZone.setMinHeight(msgZoneSizeY);
		msgZone.setMaxHeight(msgZoneSizeY);
		msgZone.setMinWidth(msgZoneSizeX);
		msgZone.setMaxWidth(msgZoneSizeX);
		msgZone.setPrefSize(msgZoneSizeX, msgZoneSizeY);
	}
	
	public void spinTheWheel()
	{
		//Wheel Rotation	
			RotateTransition wheelRotation1 = new RotateTransition(Duration.millis(8000), roulette_imgView);
			
			//Rotations
			int decisiveLastWheelRotationAngle = (int)(Math.random()*360);
			
			wheelRotation1.setByAngle(900+decisiveLastWheelRotationAngle);
			
			//SequentialTransition
			SequentialTransition seqTransition = new SequentialTransition();
			seqTransition.getChildren().addAll(wheelRotation1);
			
			seqTransition.play();
			
			/*
			final KeyValue keyValue = new KeyValue(roulette_imgView.rotateProperty(), 360);
			final KeyFrame keyFrame = new KeyFrame(Duration.millis(3000), keyValue);
			final KeyValue keyValue2 = new KeyValue(roulette_imgView.rotateProperty(), 360);
			final KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1000), keyValue2);
			
			//Timeline
			final Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			
			final Timeline timeLine2 = new Timeline();
			timeLine2.getKeyFrames().add(keyFrame2);
			
			timeLine.play();
			//timeLine2.play();*/
	}
	
	public void checkBallLocation()
	{
		  /*  boolean ballCollision = false;
		    Shape shapeTemp = null;
		    shapeTemp = (Shape.intersect(ballCenter, rouletteBall));
		    
		   if (shapeTemp.getBoundsInLocal().getWidth() != -1) 
		    {
		    	ballCollision = true;
		    }
	     */  
	}
}
