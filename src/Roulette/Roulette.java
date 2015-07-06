package Roulette;

import java.util.ArrayList;
import java.util.Arrays;

import Casino.Game_Interface_Components;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

public class Roulette extends Game_Interface_Components 
{
	public Scene scene = null;
	private Pane root  = null;
	
	//Constants for the layouts
		//Constants
			static final int TABLE_MAIN_CELL_WIDTH = 40;
			static final int TABLE_MAIN_CELL_HEIGHT = 55;
			static final int TABLE_MAIN_CELL_GAP = 2;
	
	//Images
	private Image roulette_img = new Image("Roulette.png",500D,0, true, false);
	private ImageView roulette_imgView = null;
	
	//ArrayList grouping the numbers by color
	static final ArrayList blackNumbersList = new ArrayList(Arrays.asList(2,6,8,10,11,13,15,17,20,24,26,28,29,31,33,35));
	static final ArrayList redNumbersList = new ArrayList(Arrays.asList(1,3,5,7,9,12,14,16,21,23,25,27,30,32,34,36));
	
	/*
	//Table image
	private Image table_img = new Image("Roulette_table.png",600D,0, true, false);
	private ImageView table_imgView = null;
	private Game_Interface_Components gameInterface;*/
	
	
	//Table Layout
		//Pane containing the different table zones
		private Pane tableLayout = null;
		
			//Left Zone containing the zeros
			private GridPane tableLeftZone = null;
			
			//VBox containing the Center and BottomZone	
			private VBox tableVBox = null;
				//Center Zone containing the numbers (1 to 36) and the 2:1s
				private GridPane tableCenterZone = null;
				
				//Bottom zone containing the special bets
				private GridPane tableRightZone = null;
	
	public Roulette()
	{
		Game_Interface_Components gameInterface =  new Game_Interface_Components();
		
		root = new Pane();
		scene = new Scene(root, 800,800);
		
		setImages();
		setTable();
		
		root.getChildren().addAll(super.upperZone, roulette_imgView, tableLayout);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	private void setImages()
	{
		roulette_imgView = new ImageView(roulette_img);
		//roulette_imgView.setTranslateX(100);
		//roulette_imgView.setTranslateY(50);
		
		/*table_imgView = new ImageView(table_img);
		table_imgView.setTranslateX(100);
		table_imgView.setTranslateY(500);*/
	}
	
	private void setTable()
	{
		
		//Create left betting zone
			tableLeftZone = new GridPane();
			
			tableLeftZone.add(new Text("00"), 0, 0);
			tableLeftZone.add(new Text("0"), 0, 1);
		
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
						text.setFont(new Font(15));
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
						Rectangle rect4 = new Rectangle(table_12sZoneWidth, TABLE_MAIN_CELL_HEIGHT*0.75, Color.GREEN);
						Text text_1st12 = new Text("1st 12");
						text_1st12.setFont(new Font(20));
						text_1st12.setFill(Color.WHITE);
						
						StackPane stack_1st12 = new StackPane();
						stack_1st12.getChildren().addAll(rect4, text_1st12);
						
						//12nd 12
						Rectangle rect5 = new Rectangle(table_12sZoneWidth, TABLE_MAIN_CELL_HEIGHT*0.75, Color.GREEN);
						Text text_2nd12 = new Text("2nd 12");
						text_2nd12.setFont(new Font(20));
						text_2nd12.setFill(Color.WHITE);
						
						StackPane stack_2nd12 = new StackPane();
						stack_2nd12.getChildren().addAll(rect5, text_2nd12);
						
						//3rd 12
						Rectangle rect6 = new Rectangle(table_12sZoneWidth, TABLE_MAIN_CELL_HEIGHT*0.75, Color.GREEN);
						Text text_3rd12 = new Text("3rd 12");
						text_3rd12.setFont(new Font(20));
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
						Rectangle rect7 = new Rectangle(table_BottomZoneWidth, TABLE_MAIN_CELL_HEIGHT*0.75, Color.GREEN);
						Text text_1to18 = new Text("1 to 18");
						text_1to18.setFont(new Font(20));
						text_1to18.setFill(Color.WHITE);
						
						StackPane stack_1to18 = new StackPane();
						stack_1to18.getChildren().addAll(rect7, text_1to18);
						
						//Even bet
						Rectangle rect8 = new Rectangle(table_BottomZoneWidth, TABLE_MAIN_CELL_HEIGHT*0.75, Color.GREEN);
						Text text_Even = new Text("Even");
						text_Even.setFont(new Font(20));
						text_Even.setFill(Color.WHITE);
						
						StackPane stack_Even = new StackPane();
						stack_Even.getChildren().addAll(rect8, text_Even);
						
						//Color bets
						double diamondWidth = TABLE_MAIN_CELL_WIDTH*2*0.8;//80% of the cell width
						double diamondHeigth = TABLE_MAIN_CELL_HEIGHT*0.75*0.8;//80% of the cell height
						
							//Red
							Rectangle rect9 = new Rectangle(table_BottomZoneWidth, TABLE_MAIN_CELL_HEIGHT*0.75, Color.GREEN);
							Polygon redBetDiamond = new Polygon();
							redBetDiamond.getPoints().addAll(new Double[]{0.0, 0.0, diamondWidth/2, diamondHeigth/2, diamondWidth, 0.0,  diamondWidth/2, -diamondHeigth/2});
							redBetDiamond.setFill(Color.RED);
							
							StackPane stack_RedBet = new StackPane();
							stack_RedBet.getChildren().addAll(rect9, redBetDiamond);
							
							//Black
							Rectangle rect10 = new Rectangle(table_BottomZoneWidth, TABLE_MAIN_CELL_HEIGHT*0.75, Color.GREEN);
							Polygon blackBetDiamond = new Polygon();
							blackBetDiamond.getPoints().addAll(new Double[]{0.0, 0.0, diamondWidth/2, diamondHeigth/2, diamondWidth, 0.0,  diamondWidth/2, -diamondHeigth/2});
							blackBetDiamond.setFill(Color.BLACK);
							
							StackPane stack_BlackBet = new StackPane();
							stack_BlackBet.getChildren().addAll(rect10, blackBetDiamond);
						
						//Odd bet
						Rectangle rect11 = new Rectangle(table_BottomZoneWidth, TABLE_MAIN_CELL_HEIGHT*0.75, Color.GREEN);
						Text text_Odd = new Text("Odd");
						text_Odd.setFont(new Font(20));
						text_Odd.setFill(Color.WHITE);
						
						StackPane stack_Odd = new StackPane();
						stack_Odd.getChildren().addAll(rect11, text_Odd);
						
						//19 to 36 bet
						Rectangle rect12 = new Rectangle(table_BottomZoneWidth, TABLE_MAIN_CELL_HEIGHT*0.75, Color.GREEN);
						Text text_18to36 = new Text("18 to 36");
						text_18to36.setFont(new Font(20));
						text_18to36.setFill(Color.WHITE);
						
						StackPane stack_18to36 = new StackPane();
						stack_18to36.getChildren().addAll(rect12, text_18to36);
						
						//Add the bets zone to the center zone
						tableCenterZone.add(stack_1to18, 0, 4, 2, 1);
						tableCenterZone.add(stack_Even, 2, 4, 2, 1);
						//tableCenterZone.add(stack_RedBet, 4, 4, 2, 1);
						//tableCenterZone.add(stack_BlackBet, 6, 4, 2, 1);
						tableCenterZone.add(stack_Odd, 8, 4, 2, 1);
						tableCenterZone.add(stack_18to36, 10, 4, 2, 1);
															
						
				/*2 to 1 zones
				*Made of a Text inside a Rectangle. The rectangle's purpose is to fill the grid cell as a colored 
				 background.
				*/		
					Rectangle rect1 = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.GREEN);
					Text text2to1_1 = new Text("2 to 1");
					text2to1_1.setRotate(270);
					text2to1_1.setFont(new Font(15));
					text2to1_1.setFill(Color.WHITE);
					Group text2to1_Group = new Group();
					text2to1_Group.getChildren().add(text2to1_1);//We must place the text in a group, otherwise the text node keeps the non-rotated text's bounds (even after rotation) and it causes display problems 
					
					StackPane stack_2to1_1 = new StackPane();
					stack_2to1_1.getChildren().addAll(rect1, text2to1_Group);
					
							
					Rectangle rect2 = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.GREEN);
					Text text2to1_2 = new Text("2 to 1");
					text2to1_2.setRotate(270);
					text2to1_2.setFont(new Font(15));
					text2to1_2.setFill(Color.WHITE);
					Group text2to1_2Group = new Group();
					text2to1_2Group.getChildren().add(text2to1_2);//We must place the text in a group, otherwise the text node keeps the non-rotated text's bounds (even after rotation) and it causes display problems 
					
					StackPane stack_2to1_2 = new StackPane();
					stack_2to1_2.getChildren().addAll(rect2, text2to1_2Group);
					
					
					Rectangle rect3 = new Rectangle(TABLE_MAIN_CELL_WIDTH, TABLE_MAIN_CELL_HEIGHT, Color.GREEN);
					Text text2to1_3 = new Text("2 to 1");
					text2to1_3.setRotate(270);
					text2to1_3.setFont(new Font(15));
					text2to1_3.setFill(Color.WHITE);
					Group text2to1_3Group = new Group();
					text2to1_3Group.getChildren().add(text2to1_3);//We must place the text in a group, otherwise the text node keeps the non-rotated text's bounds (even after rotation) and it causes display problems 
					
					StackPane stack_2to1_3 = new StackPane();
					stack_2to1_3.getChildren().addAll(rect3, text2to1_3Group);
					
					tableRightZone = new GridPane();
												
					tableRightZone.add(stack_2to1_1, 0,0);
					tableRightZone.add(stack_2to1_2, 0,1);
					tableRightZone.add(stack_2to1_3, 0,2);
					
		
		//Add the zones to the tableLayout Pane
			tableLayout = new Pane();
			tableVBox = new VBox();
			tableLayout.getChildren().addAll(tableLeftZone, tableCenterZone, tableRightZone);
			tableRightZone.setTranslateX(12*TABLE_MAIN_CELL_WIDTH+13*TABLE_MAIN_CELL_GAP);
			
			/*/*tableVBox = new VBox();
			tableVBox.getChildren().addAll(tableCenterZone);
			tableLayout.setCenter(tableVBox);
			
			tableLayout.setRight(tableRightZone);
			Pane.setAlignment(tableRightZone, Pos.TOP_LEFT);*/
		
		//Set the position of the tableLayout Pane
			tableLayout.setTranslateX(100);
			tableLayout.setTranslateY(500);
			
		//Set the design of the table
			tableCenterZone.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			tableCenterZone.setVgap(TABLE_MAIN_CELL_GAP);
			tableCenterZone.setHgap(TABLE_MAIN_CELL_GAP);
			tableCenterZone.setPadding(new Insets(TABLE_MAIN_CELL_GAP));
			
			tableRightZone.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			tableRightZone.setVgap(TABLE_MAIN_CELL_GAP);
			tableRightZone.setHgap(TABLE_MAIN_CELL_GAP);
			tableRightZone.setPadding(new Insets(TABLE_MAIN_CELL_GAP, TABLE_MAIN_CELL_GAP, TABLE_MAIN_CELL_GAP, 0));
	}
	
	
	/*public static void main(String[] args)
	{
		Number num0 = new Number(0, Number.COLOR_GREEN);
		Number num1 = new Number(1, Number.COLOR_RED);
		Number num2 = new Number(2, Number.COLOR_BLACK);
	}*/
}
