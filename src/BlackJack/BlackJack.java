package BlackJack;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

//BlackJack Class
public class BlackJack {

		private MenuBar mainMenu = null;
		private Menu gameMenu = null;
		protected MenuItem menuItemNewGame = null;
		protected MenuItem menuItemQuit = null;
		private HBox upperZone = null;
		private HBox lowerZone = null;
		protected BorderPane centerZone = null;
		public Text txtPlayerName = null;
		public Text txtDealerName = null;
		protected Text txtOptions = null;
		protected HBox dealerZone = null;
		protected HBox playerZone = null;
		
		
		public Button btnHit;
		public Button btnStand;
		public Button btnSplit;
		public Button btnDouble;
		
		public Scene scene;
		private BorderPane root;
		
		
		public BlackJack(){
			
			createMenu();
			createMid();
			createButtons();
			
			root = new BorderPane();
			root.setTop(upperZone);
			root.setBottom(lowerZone);
			root.setCenter(centerZone);
			
			//ImageView spade4 = new ImageView();
			//spade4.setImage(arg0);
			
			scene = new Scene(root, 800, 800);
			root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		}
		
		public void createMenu(){
			
			gameMenu = new Menu("Game");
			menuItemNewGame = new MenuItem("New Game");
			menuItemQuit = new MenuItem("Quit");
			gameMenu.getItems().addAll(menuItemNewGame, menuItemQuit);
			
			mainMenu = new MenuBar();
			mainMenu.getMenus().addAll(gameMenu);
			
			upperZone = new HBox();
			upperZone.getChildren().addAll(mainMenu);
			
		}
		
		public void createMid(){
			
			
			txtPlayerName = new Text();
			txtPlayerName.getStyleClass().add("text");
			
			txtDealerName = new Text();
			txtDealerName.getStyleClass().add("text");
			
			txtOptions = new Text();
			txtOptions.getStyleClass().add("text");
			
			dealerZone = new HBox();
			dealerZone.getChildren().addAll(txtDealerName);
			
			playerZone = new HBox();
			playerZone.getChildren().addAll(txtPlayerName);
			
			
			centerZone = new BorderPane();
			centerZone.setTop(dealerZone);
			centerZone.setBottom(playerZone);
			centerZone.setCenter(txtOptions);
			
			
			
		}
		
		public void createButtons(){
			
			btnHit = new Button("Hit");
			btnHit.setPrefSize(125, 50);
			btnHit.getStyleClass().add("Button");
			
			btnStand = new Button("Stand");
			btnStand.setPrefSize(125, 50);
			btnStand.getStyleClass().add("Button");
			
			btnSplit = new Button("Split");
			btnSplit.setPrefSize(125, 50);
			btnSplit.getStyleClass().add("Button");
			
			btnDouble = new Button("Double");
			btnDouble.setPrefSize(125, 50);
			btnDouble.getStyleClass().add("Button");
			
			lowerZone = new HBox();
			lowerZone.setPadding(new Insets(0, 40, 0, 40));
			lowerZone.setSpacing(10);
			lowerZone.getChildren().addAll(btnHit, btnStand);
			
		}
}
