//Faire une interface pour les jeux(roulette,blackjack) qui présente un menu (Game) standardisé (ajouter option save et tester) et infos du joueur

package Casino;

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
		private MenuBar mainMenu = null;
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
		
		upperZone = new HBox();
		upperZone.getChildren().addAll(mainMenu);
		upperZone.setTranslateX(5);
		upperZone.setTranslateY(5);
	}
	
	public void createPlayerInfo(){
		
		playerInfo = new HBox();
		
		playerImg = new ImageView();
		playerImg.setFitWidth(150);
		playerImg.setPreserveRatio(true);
		playerImg.setSmooth(true);
		playerImg.setCache(true);
		
		playerStats = new Text();
		playerStats.getStyleClass().add("text");
	
		
		playerInfo.getChildren().addAll(playerImg, playerStats);
		
	}
}
