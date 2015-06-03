import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;



public class Main_menu{
	
	protected Scene scene;
	
	//Borderpane to display the different menu items(title, buttons, etc.)
	private BorderPane root = null;
	
	//MenuBar items
	private MenuBar mainMenu = null;
	private Menu gameMenu = null;
	protected MenuItem menuItemNewGame = null;
	protected MenuItem menuItemQuit = null;
	
	//HBox to contain menu items
	private VBox menuZone = null; 
	
	//Hbox to display the game chooser buttons
	private VBox buttonZone = null;
	
	//Main menu title
	private VBox titleZone = null;
	private Text titleText = null;
	
	//Pick a game text
	private Text pickGameText = null;
	
	//A button for each game
	private HBox buttonGame = null;
	Button blackJackButton = null;
	Button rouletteButton = null;
	 
	//Create Casino image
	Image casino = new Image("CasinoMenu.jpg");
	
	//Add the different menu items to the mainMenu Bordepane. 
	public Main_menu(){
		
		createMenuZone();
		createTitleZone();
		createButtonZone();
		
		root = new BorderPane();
		root.setTop(menuZone);
		root.setCenter(titleZone);
		root.setBottom(buttonZone);
		
		scene = new Scene(root, 800, 800);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	//Create the buttons and add them to the buttonZone HBox
	private void createButtonZone(){
		 //Create one button for each game
		 blackJackButton = new Button("Blackjack");
		 blackJackButton.setPrefSize(125, 50);
		 
		 rouletteButton = new Button("Roulette");
		 rouletteButton.setPrefSize(125, 50);
		 
		 //Add the buttons to the HBox
		 buttonZone = new VBox();
		 buttonZone.setAlignment(Pos.CENTER);
		 buttonZone.setPadding(new Insets(0,0,150,0));
		 
		 buttonGame = new HBox();
		 buttonGame.setAlignment(Pos.CENTER);
		 
		 pickGameText = new Text("Pick A Game!");
		 pickGameText.getStyleClass().add("title");
		 
		 buttonGame.getChildren().addAll(blackJackButton, rouletteButton);
		 buttonZone.getChildren().addAll(pickGameText, buttonGame);
	 }

	//Create de menu zone (New Game)
	public void createMenuZone(){
		
		gameMenu = new Menu("Game");
		menuItemNewGame = new MenuItem("New Game");
		menuItemQuit = new MenuItem("Quit");
		gameMenu.getItems().addAll(menuItemNewGame, menuItemQuit);
		
		mainMenu = new MenuBar();
		mainMenu.getMenus().addAll(gameMenu);
		
		menuZone = new VBox();
		menuZone.getChildren().addAll(mainMenu);
		
	}
	
	//Create the upper zone (title)
	private void createTitleZone(){
		
		titleZone = new VBox();
		titleZone.setAlignment(Pos.TOP_CENTER);
		titleText = new Text("Welcome to Bro Casino!");
		titleText.getStyleClass().add("title");
		titleZone.setPadding(new Insets(30,0,0,0));
		
		ImageView casinoView = new ImageView();
		casinoView.setImage(casino);
		casinoView.setFitWidth(400);
		casinoView.setPreserveRatio(true);
		casinoView.setSmooth(true);
		casinoView.setCache(true);
		
		titleZone.getChildren().addAll(titleText, casinoView);
	}
	
}
