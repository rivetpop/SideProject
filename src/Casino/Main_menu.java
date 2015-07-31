package Casino;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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



public class Main_menu{
	
	protected Scene scene;
	
	//Borderpane to display the different menu items(title, buttons, etc.)
	private BorderPane root = null;
		
	//Main menu title
	protected static VBox titleZone = null;
	protected static Text titleText = null;
	
	//New Game / Load Game buttons
	private VBox newLoadButton = null; 
	Button newPlayerButton = null;
	Button loadPlayerButton = null;
	Button quitButton = null;
	
	
	//Create Casino image
	static Image casino = new Image("CasinoMenu.jpg");
	
	//Add the different menu items to the mainMenu Bordepane. 
	public Main_menu(){
		
		createTitleZone();
		createButtonZone();
		
		root = new BorderPane();
		root.setTop(titleZone);
		root.setCenter(newLoadButton);
		
		scene = new Scene(root, 800, 800);
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	
	//Create the upper zone (title)
	private void createTitleZone(){
		
		titleZone = new VBox();
		titleZone.setAlignment(Pos.TOP_CENTER);
		titleZone.setPadding(new Insets(30,0,0,0));
		titleText = new Text("Bro Casino!");
		titleText.getStyleClass().add("title");
		
		
		ImageView casinoView = new ImageView();
		casinoView.setImage(casino);
		casinoView.setFitWidth(400);
		casinoView.setPreserveRatio(true);
		casinoView.setSmooth(true);
		casinoView.setCache(true);
		
		titleZone.getChildren().addAll(titleText, casinoView);
	}
	
	//Create the buttons and add them to the buttonZone HBox
	private void createButtonZone(){
		
		//Create new/load game button and label
		newPlayerButton = new Button("New Profile");
		newPlayerButton.setPrefSize(175, 50);
		
		loadPlayerButton = new Button("Load Profile");
		loadPlayerButton.setPrefSize(175, 50);
		
		quitButton = new Button("Quit Game");
		quitButton.setPrefSize(175, 50);
		
		//Add buttons in the VBox
		newLoadButton = new VBox();
		newLoadButton.setAlignment(Pos.CENTER);
		newLoadButton.setPadding(new Insets(0,0,50,0));
		
		newLoadButton.getChildren().addAll(newPlayerButton, loadPlayerButton, quitButton);
		
	}
}
