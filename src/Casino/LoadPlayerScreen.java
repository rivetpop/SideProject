package Casino;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LoadPlayerScreen 
{
	Scene scene = null;
	private BorderPane root = null;
	
	private File playerInfoFile = new File(".\\src\\Player_info.dat");//Saved profiles file
	int numberOfProfiles = 0;
	
	private VBox titleZone = null;
	private Text titleText = null;
	Image casino = new Image("CasinoMenu.png");
	
	Button loadPlayerButton = null;
	Button cancelButton = null;
	
	TableView<PlayerProfile> profileTableView = null;
	
	public LoadPlayerScreen()
	{
		root = new BorderPane();
		scene = new Scene(root, 800, 800);
		
		//Top zone (Title + Image)
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
			root.setTop(titleZone);
			
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
		
		//Center zone (text + TableView)
			//Text
				Text subtitle = new Text("Select a player profile!");
				subtitle.setTextAlignment(TextAlignment.CENTER);
				subtitle.getStyleClass().add("title2");
			
			//TableView
				ArrayList <String> playerProfiles_array = readPlayersProfiles();
				profileTableView= new TableView<PlayerProfile>();
				
				//First column
					TableColumn<PlayerProfile,String> playerNameCol= new TableColumn<PlayerProfile,String>("Profile name");
					playerNameCol.setMinWidth(324);
					playerNameCol.setCellValueFactory(new PropertyValueFactory<PlayerProfile,String>("playerName"));
					
				//Second column
					TableColumn<PlayerProfile,String> cashCol = new TableColumn<PlayerProfile,String>("Cash");
					cashCol.setMinWidth(174);
					cashCol.setCellValueFactory(new PropertyValueFactory<PlayerProfile,String>("cash"));
					
				profileTableView.getColumns().addAll(playerNameCol, cashCol);
				
				//Populate the table
					profileTableView.setItems(createPlayersList(playerProfiles_array));
			
				//Size
				profileTableView.setMaxSize(500, 220);
			
			//Add the Text and TableView to the VBox
				VBox centerBox = new VBox();
				centerBox.getChildren().addAll(subtitle, profileTableView);
				centerBox.setAlignment(Pos.TOP_CENTER);
				centerBox.setPadding(new Insets(30,0,0,0));
				centerBox.setMargin(profileTableView, new Insets(10,0,0,0));
				
			root.setCenter(centerBox);
		
		//Bottom zone (buttons)
			loadPlayerButton = new Button("Load");
			loadPlayerButton.setPrefSize(150,50);
			loadPlayerButton.setStyle("-fx-font-size:18pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
			cancelButton = new Button("Cancel");
			cancelButton.setPrefSize(150, 50);
			cancelButton.setStyle("-fx-font-size:18pt; -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;");
			HBox buttonsZone = new HBox();
			buttonsZone.getChildren().addAll(loadPlayerButton, cancelButton);
			buttonsZone.setAlignment(Pos.TOP_CENTER);
			buttonsZone.setSpacing(10);
			root.setBottom(buttonsZone);
			BorderPane.setAlignment(buttonsZone, Pos.TOP_CENTER);
			buttonsZone.setPrefHeight(75);
			
			root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			root.setPadding(new Insets(2, 2, 2, 0));
		
		root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}	
	
	//This function gathers the players names and cash and returns them, saved in a vector.
	private ArrayList<String> readPlayersProfiles()
	{
		//Vector to get the players profiles infos (name and cash)
		ArrayList<String> playerProfiles_array = new ArrayList<String>();
		
		BufferedReader bufferRead = null;
		
		//Read the save file (Player_info.dat) to gather the profiles infos (name and cash)
		try
		{	
			playerInfoFile.createNewFile();//This creates the file if it does not exists
			
			bufferRead = new BufferedReader(new FileReader(playerInfoFile));
					
				//Look for the name in the save file
				String line = "";
				numberOfProfiles = 0;//Reset the numberOfProfiles to 0 in case of multiple load player events
				while((line = bufferRead.readLine())!= null)
				{	
					String[] temp_vector = line.split(";");
					playerProfiles_array.add(temp_vector[0]);
					playerProfiles_array.add(temp_vector[1]);
					numberOfProfiles++;
				}
			
		}		
	
		catch (IOException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("IO Error");
			alert.setContentText("File error: Cannot read " + playerInfoFile);
			alert.showAndWait();
		}
	
		finally
		{
			try
			{
				bufferRead.close();
			}
			catch(IOException e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("IO Error");
				alert.setContentText("File error: " + playerInfoFile + " stream cannot be closed)");
				alert.showAndWait();
			}
		}
		
		return playerProfiles_array;
	}
	
	//This function receives an arrayList of player profiles infos and returns them in a collection of PlayerProfile objects
	private ObservableList<PlayerProfile> createPlayersList(ArrayList<String> playerProfiles_array)
	{
		ObservableList<PlayerProfile> profileList = FXCollections.observableArrayList();
		
		//Add the profiles info Strings to the ObservableList		
			for (int array_pointer = 0; array_pointer < playerProfiles_array.size(); array_pointer+=2)
			{
				PlayerProfile player_temp = new PlayerProfile(playerProfiles_array.get(array_pointer), playerProfiles_array.get(array_pointer+1));//profile name
				profileList.add(player_temp);
			}
			
		return profileList;
	}
	
	public static class PlayerProfile //Class used to populate the TableView
	{
		private final SimpleStringProperty playerNameProperty;
		private final SimpleStringProperty  cashProperty;
		
		public PlayerProfile(String playerName, String cash)
		{
			playerNameProperty = new SimpleStringProperty(playerName);
			cashProperty = new SimpleStringProperty(cash);
		}
		
		public String getPlayerName()
		{
			return playerNameProperty.get();
		}
		
		public String getCash()
		{
			return cashProperty.get();
		}
		
		public void setPlayerName(String playerName)
		{
			playerNameProperty.set(playerName);
		}
		
		public void setCash(String cash)
		{
			cashProperty.set(cash);
		}
		
	}
}
