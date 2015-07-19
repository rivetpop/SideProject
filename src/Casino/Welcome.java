package Casino;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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


//Welcome menu class
public class Welcome extends GameInterface{

	protected Scene scene;
	
	//Borderpane to display the different menu items(title, buttons, etc.)
	private BorderPane root = null;
		
	//Main menu title
	private VBox titleZone = null;
	public Text titleWelcome = null;
	public Text titleWelcome2 = null;
	
	//Pick a game text
	private Text pickGameText = null;
	
	
	//A button for each game
	private VBox buttonZone = null;
	private HBox buttonGame = null;
	Button blackJackButton = null;
	Button rouletteButton = null;
	
	public Welcome(){
		
		createWelcomeZone();
		createPickGameZone();
		
		root = new BorderPane();
		root.setTop(titleZone);
		root.setCenter(super.playerInfo);
		root.setBottom(buttonZone);
		
		scene = new Scene(root, 800, 800);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	private void createWelcomeZone(){
		
		titleZone = new VBox();
		titleZone.setAlignment(Pos.TOP_CENTER);
		titleZone.setPadding(new Insets(30,0,0,0));
		titleWelcome = new Text();
		titleWelcome.getStyleClass().add("title");
		titleWelcome2 = new Text();
		titleWelcome2.getStyleClass().add("title");
		
		titleZone.getChildren().addAll(titleWelcome, titleWelcome2);
	}
	
	private void createPickGameZone(){
		
		//Create one button for each game
		blackJackButton = new Button("Blackjack");
		blackJackButton.setPrefSize(125, 50);
		 
		rouletteButton = new Button("Roulette");
		rouletteButton.setPrefSize(125, 50);
		 
		//Add buttons to the HBox
		buttonZone = new VBox();
		buttonZone.setAlignment(Pos.CENTER);
		buttonZone.setPadding(new Insets(0,0,80,0));
		 
		buttonGame = new HBox();
		buttonGame.setAlignment(Pos.CENTER);
		buttonGame.setPadding(new Insets(30,0,0,0));
		 
		pickGameText = new Text("Pick your game!");
		pickGameText.getStyleClass().add("title");
		 
		buttonGame.getChildren().addAll(blackJackButton, rouletteButton);
		buttonZone.getChildren().addAll(pickGameText, buttonGame);
	
	}
}
