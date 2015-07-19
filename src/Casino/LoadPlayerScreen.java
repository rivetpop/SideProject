package Casino;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
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
import javafx.scene.text.TextAlignment;

public class LoadPlayerScreen 
{
	Scene scene = null;
	private BorderPane root = null;
	
	private File playerInfoFile = new File(".\\src\\Player_info.dat");//Saved profiles file
	int numberOfProfiles = 0;
	
	private VBox titleZone = null;
	private Text titleText = null;
	Image casino = new Image("CasinoMenu.jpg");
	
	Button loadPlayerButton = null;
	Button cancelButton = null;
	
	public LoadPlayerScreen()
	{
		root = new BorderPane();
		scene = new Scene(root, 800, 800);
		
		//Top zone (Title + Image)
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
			root.setTop(titleZone);
		
		//Center zone (text + ListView)
			Text subtitle = new Text("Enter the name of the player profile\nyou want to load");
			subtitle.setTextAlignment(TextAlignment.CENTER);
			subtitle.getStyleClass().add("title2");
			
			ArrayList playerProfiles_array = readPlayersProfiles();
			ListView<String> profileListView= createPlayersList(playerProfiles_array);
			profileListView.setMaxSize(500, 300);
			
			VBox centerBox = new VBox();
			centerBox.getChildren().addAll(subtitle, profileListView);
			centerBox.setAlignment(Pos.CENTER);
			root.setCenter(centerBox);
			root.setAlignment(centerBox, Pos.TOP_CENTER);
		
		//Bottom zone (buttons)
			loadPlayerButton = new Button("Load");
			loadPlayerButton.setPrefSize(150,50);
			cancelButton = new Button("Cancel");
			cancelButton.setPrefSize(150, 50);
			HBox buttonsZone = new HBox();
			buttonsZone.getChildren().addAll(loadPlayerButton, cancelButton);
			buttonsZone.setAlignment(Pos.TOP_CENTER);
			root.setBottom(buttonsZone);
			BorderPane.setAlignment(buttonsZone, Pos.TOP_CENTER);
		
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
	
	private ListView<String> createPlayersList(ArrayList<String> playerProfiles_array)
	{
		ListView<String> profileList = new ListView<String>();
		ObservableList<String> profilesInfo = FXCollections.observableArrayList();
		
		//Add the profiles info Strings to the ObservableList		
			for (int array_pointer = 0; array_pointer < playerProfiles_array.size(); array_pointer+=2)
			{
				String info_temp = "Profile name: " + playerProfiles_array.get(array_pointer)
									+ "\tCash:" + playerProfiles_array.get(array_pointer+1);
				profilesInfo.add(info_temp);
			}
		
		profileList.setItems(profilesInfo);
		
		return profileList;
	}
}
