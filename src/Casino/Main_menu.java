package Casino;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	static Image casino = new Image("CasinoMenu.png");
	
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
		titleText = new Text();
		TextField titleTextField = new TextField();
		titleTextField.setText("Bro Casino");
		titleText.setFont(new Font("Tahoma", 100));
		titleText.setFill(Color.web("#FFFAF0"));
		titleText.textProperty().bind(titleTextField.textProperty());
		
		ImageView casinoView = new ImageView();
		casinoView.setImage(casino);
		casinoView.setFitWidth(400);
		casinoView.setPreserveRatio(true);
		casinoView.setSmooth(true);
		casinoView.setCache(true);
		casinoView.setBlendMode(BlendMode.LIGHTEN);
		
		titleZone.getChildren().addAll(titleText, casinoView);
		
		//Title text effect Source: (Neon Sign application) https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
			Blend blend = new Blend();
			blend.setMode(BlendMode.MULTIPLY);
	
			DropShadow ds = new DropShadow();
			ds.setColor(Color.rgb(254, 235, 66, 0.3));
			ds.setOffsetX(5);
			ds.setOffsetY(5);
			ds.setRadius(5);
			ds.setSpread(0.2);
	
			blend.setBottomInput(ds);
	
			DropShadow ds1 = new DropShadow();
			ds1.setColor(Color.web("#f13a00"));
			ds1.setRadius(20);
			ds1.setSpread(0.2);
	
			Blend blend2 = new Blend();
			blend2.setMode(BlendMode.MULTIPLY);
	
			InnerShadow is = new InnerShadow();
			is.setColor(Color.web("#feeb42"));
			is.setRadius(9);
			is.setChoke(0.8);
			blend2.setBottomInput(is);
	
			InnerShadow is1 = new InnerShadow();
			is1.setColor(Color.web("#f13a00"));
			is1.setRadius(5);
			is1.setChoke(0.4);
			blend2.setTopInput(is1);
	
			Blend blend1 = new Blend();
			blend1.setMode(BlendMode.MULTIPLY);
			blend1.setBottomInput(ds1);
			blend1.setTopInput(blend2);
	
			blend.setTopInput(blend1);
	
			titleText.setEffect(blend);		
		
	}
	
	//Create the buttons and add them to the buttonZone HBox
	private void createButtonZone(){
		
		//Create new/load game button and label
		newPlayerButton = new Button("New Profile");
		newPlayerButton.setPrefSize(175, 50);
		newPlayerButton.setStyle("-fx-font-size:18pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		
		loadPlayerButton = new Button("Load Profile");
		loadPlayerButton.setPrefSize(175, 50);
		loadPlayerButton.setStyle("-fx-font-size:18pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		
		quitButton = new Button("Quit Game");
		quitButton.setPrefSize(175, 50);
		quitButton.setStyle("-fx-font-size:18pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
		
		//Add buttons in the VBox
		newLoadButton = new VBox();
		newLoadButton.setAlignment(Pos.CENTER);
		newLoadButton.setPadding(new Insets(0,0,50,0));
		
		newLoadButton.getChildren().addAll(newPlayerButton, loadPlayerButton, quitButton);
		newLoadButton.setSpacing(15);
		
	}
}
