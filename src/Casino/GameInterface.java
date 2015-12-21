package Casino;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameInterface{
	
	//Menu	
		public HBox upperZone = null;
		public MenuBar mainMenu = null;
		public Menu gameMenu = null;
		public MenuItem menuItemCasinoHall = null;
		public MenuItem menuItemQuit = null;
	
	//Player info zone
		public HBox playerInfo = null;
		public ImageView playerImg = null;
		public Text playerStats = null;
		
	public GameInterface(){
		
		createMenu();
		createPlayerInfo();		
	}
	
	public void createMenu(){
		
		menuItemCasinoHall = new MenuItem("Casino Hall");
		menuItemQuit = new MenuItem("Quit");
		
		gameMenu = new Menu("Menu");
		gameMenu.getItems().addAll(menuItemCasinoHall, menuItemQuit);
		
		
		mainMenu = new MenuBar();
		mainMenu.getMenus().addAll(gameMenu);
		mainMenu.setStyle("-fx-font-size:18pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		
		upperZone = new HBox();
		upperZone.getChildren().addAll(mainMenu);
		upperZone.setTranslateX(2);
		upperZone.setTranslateY(2);
	}
	
	public void createPlayerInfo(){
		
		playerInfo = new HBox();
		
		playerInfo.setPadding(new Insets(50, 0, 0, 0));
		playerImg = new ImageView();
		playerImg.setFitWidth(100);
		playerImg.setFitHeight(100);
		playerImg.setSmooth(true);
		playerImg.setCache(true);
		
		playerStats = new Text();
		playerStats.getStyleClass().add("text");
	
		
		playerInfo.getChildren().addAll(playerImg, playerStats);
		
	}
}
