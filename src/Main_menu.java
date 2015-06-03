import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;



public class Main_menu 
{
	protected Scene scene;
	
	//Borderpane to display the different menu items(title, buttons, etc.)
	private BorderPane root = null;
	
	//Hbox to display the game chooser buttons
	private HBox buttonZone = null;
	
	//Main menu title
	private TextArea title = null;
	
	//A button for each game
	Button blackjackButton = null;
	Button rouletteButton = null;
	 
	
	//Create the buttons and add them to the buttonZone HBox
	private void createButtonZone()
	 {
		 //Create one button for each game
		 blackjackButton = new Button("Blackjack");
		 blackjackButton.setPrefSize(125, 50);
		 
		 rouletteButton = new Button("Roulette");
		 rouletteButton.setPrefSize(125, 50);
		 
		 //Add the buttons to the HBox
		 buttonZone = new HBox();
		 buttonZone.getChildren().addAll(blackjackButton, rouletteButton);
	 }
	
	//Add the different menu items to the mainMenu Bordepane. 
	protected void createMainMenu()
	{
		createButtonZone();
		title = new TextArea("Welcome to the Casino! Choose a game...");
		
		root = new BorderPane();
		root.setTop(title);
		root.setCenter(buttonZone);
		
		scene = new Scene(root, 800, 800);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
}
