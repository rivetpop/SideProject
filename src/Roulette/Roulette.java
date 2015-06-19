package Roulette;

import java.util.ArrayList;
import java.util.Arrays;

import Casino.Game_Interface_Components;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Roulette extends Game_Interface_Components 
{
	public Scene scene = null;
	private Pane root  = null;
	
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
	
	
	//Table GridPane
		//BorderPane containing the different table zones
		private BorderPane tableLayout = null;
		
			//Left Zone containing the zeros
			private GridPane tableLeftZone = null;
			
			//Center Zone containing the numbers (1 to 36) and the 2:1s
			private GridPane tableCenterZone = null;
			
			//Bottom zones containing the special bets
			private VBox tableBottomZones = null;
			private GridPane tableBottomZone1 = null;
			private GridPane tableBottomZone2 = null;
	
	public Roulette()
	{
		Game_Interface_Components gameInterface =  new Game_Interface_Components();
		
		root = new Pane();
		scene = new Scene(root, 800,800);
		
		createNumbers();
		setImages();
		setTable();
		
		root.getChildren().addAll(super.upperZone, roulette_imgView, tableLayout);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	private void createNumbers()
	{
		for (int i=1; i<=36 ; i++)
		{
			if (blackNumbersList.contains(i))
			{
				Number num = new Number(i,"black");
			}
			
		}
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
		
		//Add the texts to the left betting zone
			tableLeftZone = new GridPane();
			
			tableLeftZone.add(new Text("00"), 0, 0);
			tableLeftZone.add(new Text("0"), 0, 1);
		
		/*Add the texts to the center betting zone. 
		The items contained in this zone are made of a Text, inside an Ellipse(red or black), 
		inside a Rectangle. The rectangle's purpose is to fill the grid cell as a colored 
		background.*/
				tableCenterZone = new GridPane();
			
				//Zone 1 to 36
				int i=0;
				int j=2;
				int number = 1;
				
				for (; number<=36 ; number++)
				{
					
					Ellipse ellipse = new Ellipse(12,18);
					
					if (blackNumbersList.contains(number))
					{
						ellipse.setFill(Color.BLACK);
					}
					
					else if (redNumbersList.contains(number))
					{
						ellipse.setFill(Color.RED);
					}
					
					Rectangle rect = new Rectangle(30,50, Color.GREEN);
					Label label = new Label(Integer.toString(number));
					label.setRotate(270);
					label.setTextFill(Color.WHITE);
					
					//A stackpane to place the text in front of the ellipse in front of the rectangle
					StackPane stack = new StackPane();
	
					stack.getChildren().addAll(rect, ellipse, label);
					tableCenterZone.add(stack, i, j);
					
					j--;
					
					if (j==-1)
					{
						i++;
						j=2;
					}
				}
					
				//2 to 1's zone, made of a label in a rectangle
				Rectangle rect1 = new Rectangle(30,50, Color.GREEN);
				Label label1 = new Label("2 to 1");
				label1.setRotate(270);
				label1.setTextFill(Color.WHITE);	
				
				StackPane stack1 = new StackPane();				
				stack1.getChildren().addAll(rect1, label1);
				
				Rectangle rect2 = new Rectangle(30,50, Color.GREEN);
				Label label2 = new Label("2 to 1");
				label2.setRotate(270);
				label2.setTextFill(Color.WHITE);
				
				StackPane stack2 = new StackPane();				
				stack2.getChildren().addAll(rect2, label2);
				
				Rectangle rect3 = new Rectangle(30,50, Color.GREEN);
				Label label3 = new Label("2 to 1");
				label3.setRotate(270);
				label3.setTextFill(Color.WHITE);
				
				StackPane stack3 = new StackPane();				
				stack3.getChildren().addAll(rect3, label3);
				
				tableCenterZone.add(stack1, 13,0);
				tableCenterZone.add(stack2, 13,1);
				tableCenterZone.add(stack3, 13,2);
			
		//Add the text to the bottomZones
				//BottomZone1 (12's)
				tableBottomZone1 = new GridPane();
				
				tableBottomZone1.add(new Text("1st 12"), 0, 0);
				tableBottomZone1.add(new Text("2nd 12"), 1, 0);
				tableBottomZone1.add(new Text("3rd 12"), 2, 0);
				
				//BottomZone2
				tableBottomZone2 = new GridPane();
				
				tableBottomZone2.add(new Text("1 to 18"), 0, 0);
				tableBottomZone2.add(new Text("Even"), 1, 0);
				tableBottomZone2.add(new Text("RED"), 2, 0);
				tableBottomZone2.add(new Text("BLACK"), 3, 0);
				tableBottomZone2.add(new Text("Odd"), 4, 0);
				tableBottomZone2.add(new Text("19 to 36"), 5, 0);
				
				//Add the bottom zones to the VBox tableBottomZones
				tableBottomZones = new VBox();
				tableBottomZones.getChildren().addAll(tableBottomZone1, tableBottomZone2);
		
		
		//Add the zones to the tableLayout BorderPane
			tableLayout = new BorderPane();
			tableLayout.setLeft(tableLeftZone);
			tableLayout.setCenter(tableCenterZone);
			tableLayout.setBottom(tableBottomZones);
		
		//Set the position of the tableLayout BorderPane
			tableLayout.setTranslateX(100);
			tableLayout.setTranslateY(500);
			
		//Set the design of the table
			tableCenterZone.setStyle("-fx-background-color: white; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2");
			
			
	}
	
	
	/*public static void main(String[] args) 
	{
		Number num0 = new Number(0, Number.COLOR_GREEN);
		Number num1 = new Number(1, Number.COLOR_RED);
		Number num2 = new Number(2, Number.COLOR_BLACK);
	}*/
}
