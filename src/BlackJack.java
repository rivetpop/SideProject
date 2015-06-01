import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class BlackJack {

		private MenuBar mainMenu = null;
		private Menu gameMenu = null;
		protected MenuItem newGameItem = null;
		protected MenuItem quitItem = null;
		private VBox upperZone = null;
		protected Scene scene;
		private BorderPane root;
		
		
		public BlackJack(){
			
			createMenu();
			
			root = new BorderPane();
			root.setTop(upperZone);
			
			scene = new Scene(root, 600, 600);
			root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		
		public void createMenu(){
			
			gameMenu = new Menu("Game");
			newGameItem = new MenuItem("New Game");
			quitItem = new MenuItem("Quit");
			gameMenu.getItems().addAll(newGameItem, quitItem);
			
			mainMenu = new MenuBar();
			mainMenu.getMenus().addAll(gameMenu);
			
			upperZone = new VBox();
			upperZone.getChildren().addAll(mainMenu);
			
		}
}
