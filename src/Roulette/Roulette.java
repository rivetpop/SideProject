///Transformer en BorderPane pour avoir un menu identique à BlackJack? Le menu devrait être dan le top du borderpane...

package Roulette;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Roulette 
{
	public Scene scene = null;
	private Pane root  = null;
	
	//Menu
	private HBox upperZone = null;
	private MenuBar mainMenu = null;
	private Menu gameMenu = null;
	protected MenuItem menuItemNewGame = null;
	protected MenuItem menuItemQuit = null;
	
	//Images
	private Image roulette_img = new Image("Roulette.png",500D,0, true, false);
	private ImageView roulette_imgView = null;
	
	private Image table_img = new Image("Roulette_table.png",600D,0, true, false);
	private ImageView table_imgView = null;
	
	
	public Roulette()
	{
		root = new Pane();
		scene = new Scene(root, 800,800);
		
		createMenu();
		setImages();
		
		root.getChildren().addAll(upperZone, roulette_imgView, table_imgView);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	private void createMenu()
	{
		
		gameMenu = new Menu("Game");
		menuItemNewGame = new MenuItem("New Game");
		menuItemQuit = new MenuItem("Quit");
		gameMenu.getItems().addAll(menuItemNewGame, menuItemQuit);
		
		mainMenu = new MenuBar();
		mainMenu.getMenus().addAll(gameMenu);
		
		upperZone = new HBox();
		upperZone.getChildren().addAll(mainMenu);	
	}
	
	private void setImages()
	{
		roulette_imgView = new ImageView(roulette_img);
		//roulette_imgView.setTranslateX(100);
		//roulette_imgView.setTranslateY(50);
		
		table_imgView = new ImageView(table_img);
		table_imgView.setTranslateX(100);
		table_imgView.setTranslateY(500);
		
	}
	
	
	/*public static void main(String[] args) 
	{
		Number num0 = new Number(0, Number.COLOR_GREEN);
		Number num1 = new Number(1, Number.COLOR_RED);
		Number num2 = new Number(2, Number.COLOR_BLACK);
	}*/
}
