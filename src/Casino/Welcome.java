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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


//Welcome menu class
public class Welcome extends GameInterface{

	protected Scene scene;
	
	//Borderpane to display the different menu items(title, buttons, etc.)
	private BorderPane root = null;

	//Pane for the buttons zone
	Pane buttonPane;
		
	//Main menu title
	private VBox titleZone = null;
	public Text titleWelcome = null;
	public Text titleWelcome2 = null;
	
	//Player info zone
	private HBox playerZone;
	
	//Pick a game text
	private Text pickGameText = null;
	
	
	//A button for each game
	private VBox buttonZone = null;
	private HBox buttonGame = null;
	protected Button blackJackButton = null;
	protected Button rouletteButton = null;
	
	//A change profile button
	protected Button changeProfileButton;
	
	public Welcome(){
		
		buttonPane = new Pane();
		
		createWelcomeZone();
		createPlayerZone();
		createPickGameZone();
		createChangeProfileButton();
		
		root = new BorderPane();
		root.setTop(titleZone);
		root.setCenter(playerZone);
		root.setBottom(buttonPane);
		
		scene = new Scene(root, 800, 800);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	private void createWelcomeZone(){
		
		titleZone = new VBox();
		titleZone.setAlignment(Pos.TOP_CENTER);
		titleZone.setPadding(new Insets(30,0,150,0));
		titleWelcome = new Text();
		titleWelcome.getStyleClass().add("title");
		titleWelcome2 = new Text();
		titleWelcome2.getStyleClass().add("title");
		
		titleZone.getChildren().addAll(titleWelcome, titleWelcome2);
	}
	
	private void createPlayerZone(){
		
		playerZone = new HBox();
		playerZone.setAlignment(Pos.CENTER);
		playerZone.getChildren().addAll(super.playerInfo);
		
	}
	private void createPickGameZone(){
		
		//Create one button for each game
		blackJackButton = new Button("Blackjack");
		blackJackButton.setPrefSize(150, 50);
		blackJackButton.setStyle("-fx-font-size:18pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		 
		rouletteButton = new Button("Roulette");
		rouletteButton.setPrefSize(150, 50);
		rouletteButton.setStyle("-fx-font-size:18pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		 
		//Add buttons to the HBox
		buttonZone = new VBox();
		buttonZone.setAlignment(Pos.CENTER);
		buttonZone.setPadding(new Insets(0,0,80,0));
		
		buttonGame = new HBox();
		buttonGame.setAlignment(Pos.CENTER);
		buttonGame.setPadding(new Insets(30,0,0,0));
		buttonGame.setSpacing(20);
		
		 
		pickGameText = new Text("Pick your game!");
		pickGameText.getStyleClass().add("title");
		pickGameText.setTranslateX(230);
		 
		buttonGame.getChildren().addAll(blackJackButton, rouletteButton);
		buttonZone.getChildren().addAll(pickGameText, buttonGame);
		buttonPane.getChildren().add(buttonZone);
		buttonGame.setTranslateX(230);
		
	}
	
	public void createChangeProfileButton()
	{
		changeProfileButton = new Button("Change profile");
		changeProfileButton.setPrefSize(120, 38);
		changeProfileButton.setStyle("-fx-font-size:10pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		buttonPane.getChildren().add(changeProfileButton);
		changeProfileButton.setTranslateX(660);
		changeProfileButton.setTranslateY(155);
	}
}
