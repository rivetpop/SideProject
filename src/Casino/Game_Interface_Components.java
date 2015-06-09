//Faire une interface pour les jeux(roulette,blackjack) qui présente un menu (Game) standardisé (ajouter option save et tester) et infos du joueur

package Casino;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Game_Interface_Components
{
	//Menu	
		public HBox upperZone = null;
		private MenuBar mainMenu = null;
		public Menu gameMenu = null;
		public MenuItem menuItemNewPlayer = null;
		public MenuItem menuItemLoadPlayer = null;
		public MenuItem menuItemSave = null;
		public MenuItem menuItemQuit = null;
	
	//Player info zone
		//Global layout containing all the Player_Info items inside other layouts
		public HBox playerInfo = null;
		
		//Layout for the player name and image
		private VBox nameImgZone = null;
		
		//Layout for the cash total and current gain
		private HBox cashGain = null;
		
	public Game_Interface_Components()
	{
		menuItemNewPlayer = new MenuItem("New Player");
		menuItemLoadPlayer = new MenuItem("Change Player");
		menuItemSave = new MenuItem("Save");
		menuItemQuit = new MenuItem("Quit");
		
		gameMenu = new Menu("Menu");
		gameMenu.getItems().addAll(menuItemSave, menuItemLoadPlayer, menuItemNewPlayer, menuItemQuit);
		
		mainMenu = new MenuBar();
		mainMenu.getMenus().addAll(gameMenu);
		
		upperZone = new HBox();
		upperZone.getChildren().addAll(mainMenu);
		upperZone.setTranslateX(5);
		upperZone.setTranslateY(5);
	}
}
